package com.bravelybeep.cobblebeep.pokemon.properties

import com.bravelybeep.cobblebeep.entity.BeepPokemonEntity
import com.cobblemon.mod.common.api.properties.CustomPokemonPropertyType
import com.cobblemon.mod.common.pokemon.properties.IntProperty

object RespawnRadiusHorizontalProperty : CustomPokemonPropertyType<IntProperty> {
    override val keys = setOf("respawn_radius_horizontal")
    override val needsKey = true

    override fun fromString(value: String?) = when (value) {
        null -> respawnRadius(0)
        else -> value.toIntOrNull()?.let { respawnRadius(it) }
    }

    private fun respawnRadius(value: Int) = IntProperty(
        key = keys.first(),
        value = value,
        pokemonApplicator = { _, _ -> },
        entityApplicator = { pokemonEntity, respawnRadius -> (pokemonEntity as BeepPokemonEntity).respawnData.radiusHorizontal = respawnRadius },
        pokemonMatcher = { _, _ -> false },
        entityMatcher = { pokemonEntity, respawnRadius -> (pokemonEntity as BeepPokemonEntity).respawnData.radiusHorizontal == respawnRadius }
    )

    override fun examples() = setOf("0")
}