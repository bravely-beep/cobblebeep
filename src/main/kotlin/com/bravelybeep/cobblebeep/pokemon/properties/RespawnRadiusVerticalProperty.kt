package com.bravelybeep.cobblebeep.pokemon.properties

import com.bravelybeep.cobblebeep.entity.BeepPokemonEntity
import com.cobblemon.mod.common.api.properties.CustomPokemonPropertyType
import com.cobblemon.mod.common.pokemon.properties.IntProperty

object RespawnRadiusVerticalProperty : CustomPokemonPropertyType<IntProperty> {
    override val keys = setOf("respawn_radius_vertical", "respawn_radius_v")
    override val needsKey = true

    override fun fromString(value: String?) = when (value) {
        null -> respawnRadius(0)
        else -> value.toIntOrNull()?.let { respawnRadius(it) }
    }

    private fun respawnRadius(value: Int) = IntProperty(
        key = keys.first(),
        value = value,
        pokemonApplicator = { _, _ -> },
        entityApplicator = { pokemonEntity, respawnRadius -> (pokemonEntity as BeepPokemonEntity).respawnData.radiusVertical = respawnRadius },
        pokemonMatcher = { _, _ -> false },
        entityMatcher = { pokemonEntity, respawnRadius -> (pokemonEntity as BeepPokemonEntity).respawnData.radiusVertical == respawnRadius }
    )

    override fun examples() = setOf("0")
}