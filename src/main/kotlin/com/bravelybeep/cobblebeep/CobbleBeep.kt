package com.bravelybeep.cobblebeep

import com.bravelybeep.cobblebeep.entity.BeepPokemonEntity
import com.bravelybeep.cobblebeep.entity.PokeSpawner
import com.bravelybeep.cobblebeep.pokemon.properties.*
import com.bravelybeep.cobblebeep.registry.ModEntityTypes
import com.cobblemon.mod.common.api.events.CobblemonEvents
import com.cobblemon.mod.common.api.properties.CustomPokemonProperty
import net.fabricmc.api.ModInitializer

object CobbleBeep : ModInitializer {
    @JvmStatic
    val MOD_ID = "cobblebeep"

    override fun onInitialize() {
        ModEntityTypes.register()

        CobblemonEvents.POKEMON_PROPERTY_INITIALISED.subscribe {
            CustomPokemonProperty.register(HideLabelProperty)
            CustomPokemonProperty.register(InvulnerableProperty)
            CustomPokemonProperty.register(PersistenceRequiredProperty)
            CustomPokemonProperty.register(RespawnDenyCaughtFormProperty)
            CustomPokemonProperty.register(RespawnDenyCaughtSpeciesProperty)
            CustomPokemonProperty.register(RespawnRadiusHorizontalProperty)
            CustomPokemonProperty.register(RespawnRadiusVerticalProperty)
            CustomPokemonProperty.register(RespawnTimeProperty)
            CustomPokemonProperty.register(UnbattleableProperty)
        }

        CobblemonEvents.BATTLE_FAINTED.subscribe {
            it.killed.originalPokemon.entity.let { pokemonEntity ->
                (pokemonEntity as BeepPokemonEntity);
                val level = pokemonEntity.level();
                if (pokemonEntity.respawnData.timer >= 0) {
                    val respawner = PokeSpawner(level)
                    respawner.data = pokemonEntity.respawnData.copy()
                    respawner.moveTo(pokemonEntity.position(), pokemonEntity.yRot, pokemonEntity.xRot)
                    level.addFreshEntity(respawner)
                }
            }
        }
    }
}
