package com.bravelybeep.cobblebeep.registry

import com.bravelybeep.cobblebeep.CobbleBeep.MOD_ID
import com.bravelybeep.cobblebeep.entity.PokeSpawner
import net.minecraft.core.Registry
import net.minecraft.core.registries.BuiltInRegistries
import net.minecraft.resources.ResourceLocation
import net.minecraft.world.entity.EntityType
import net.minecraft.world.entity.MobCategory

object ModEntityTypes {
    @JvmField
    val POKE_SPAWNER_KEY = ResourceLocation.fromNamespaceAndPath(MOD_ID, "poke_spawner")!!
    @JvmField
    val POKE_SPAWNER: EntityType<PokeSpawner> = EntityType.Builder
        .of({ _, level -> PokeSpawner(level) }, MobCategory.MISC)
        .sized(0.0F, 0.0F)
        .clientTrackingRange(0)
        .build(POKE_SPAWNER_KEY.toString())

    fun register() {
        Registry.register(BuiltInRegistries.ENTITY_TYPE, POKE_SPAWNER_KEY, POKE_SPAWNER)
    }
}