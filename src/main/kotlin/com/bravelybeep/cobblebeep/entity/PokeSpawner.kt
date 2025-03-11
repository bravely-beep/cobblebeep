package com.bravelybeep.cobblebeep.entity

import com.bravelybeep.cobblebeep.PokeSpawnerData
import com.bravelybeep.cobblebeep.registry.ModEntityTypes
import com.cobblemon.mod.common.api.pokedex.PokedexEntryProgress.CAUGHT
import com.cobblemon.mod.common.entity.pokemon.PokemonEntity
import com.cobblemon.mod.common.util.pokedex
import net.minecraft.nbt.CompoundTag
import net.minecraft.nbt.NbtOps
import net.minecraft.network.protocol.Packet
import net.minecraft.network.protocol.game.ClientGamePacketListener
import net.minecraft.network.syncher.SynchedEntityData
import net.minecraft.server.level.ServerEntity
import net.minecraft.server.level.ServerPlayer
import net.minecraft.world.entity.EntitySelector
import net.minecraft.world.entity.Marker
import net.minecraft.world.level.Level
import net.minecraft.world.phys.Vec3

class PokeSpawner(level: Level) : Marker(ModEntityTypes.POKE_SPAWNER, level) {
    var data = PokeSpawnerData()

    override fun tick() {
        if (data.timer > 0) data.timer--
        else if (data.timer == 0) {
            if (spawn()) data.timer = RETRY_TICKS
            else discard()
        }
    }

    // Returns whether the spawn should be retried
    private fun spawn(): Boolean {
        // Setup required values
        val properties = (data.properties) ?: return false
        if (properties.species.isNullOrBlank()) return false
        val player = level().getNearestPlayer(this.x, this.y, this.z, PLAYER_DISTANCE, EntitySelector.NO_SPECTATORS) ?: return true
        player as ServerPlayer

        // Create Pokemon entity
        val pokemonEntity = properties.createEntity(level(), player)
        pokemonEntity as BeepPokemonEntity
        pokemonEntity.setPersistenceRequired()

        // If configured, deny spawn if player already obtained species / form
        val pokedex = player.pokedex()
        val species = pokemonEntity.pokemon.species.resourceIdentifier;
        val form = pokemonEntity.pokemon.form.name;
        val isCaughtSpecies = pokedex.getHighestKnowledgeForSpecies(species) == CAUGHT;
        val isCaughtForm = pokedex.getSpeciesRecord(species)?.getFormRecord(form)?.knowledge == CAUGHT;
        if (data.denyCaughtSpecies && isCaughtSpecies) return true
        if (data.denyCaughtForm && isCaughtForm) return true

        // Apply random offset
        val offsetH = (pokemonEntity.random.nextDouble() * 2 - 1) * data.radiusHorizontal;
        val offsetV = (pokemonEntity.random.nextDouble() * 2 - 1) * data.radiusVertical;
        val offset = Vec3(offsetH, offsetV, offsetH);
        pokemonEntity.moveTo(position().add(offset), yRot, xRot)
        pokemonEntity.entityData.set(PokemonEntity.SPAWN_DIRECTION, yRot)

        // Spawn if there's space
        if (!level().noCollision(pokemonEntity)) return true
        if (!level().addFreshEntity(pokemonEntity)) return true
        return false
    }

    override fun defineSynchedData(builder: SynchedEntityData.Builder) {}

    override fun readAdditionalSaveData(compoundTag: CompoundTag) {
        if (compoundTag.contains("Spawner")) {
            val spawnerTag = compoundTag.get("Spawner")
            PokeSpawnerData.CODEC.parse(NbtOps.INSTANCE, spawnerTag).ifSuccess { data = it }
        }
        super.readAdditionalSaveData(compoundTag)
    }

    override fun addAdditionalSaveData(compoundTag: CompoundTag) {
        PokeSpawnerData.CODEC.encodeStart(NbtOps.INSTANCE, data).ifSuccess { compoundTag.put("Spawner", it) }
        super.addAdditionalSaveData(compoundTag)
    }

    override fun getAddEntityPacket(serverEntity: ServerEntity): Packet<ClientGamePacketListener>? {
        throw IllegalStateException("PokeSpawners should never be sent")
    }

    companion object {
        @JvmStatic
        val PLAYER_DISTANCE = 20.0
        @JvmStatic
        val RETRY_TICKS = 30
    }
}