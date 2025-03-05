package com.bravelybeep.cobblebeep.pokemon.properties

import com.bravelybeep.cobblebeep.entity.BeepPokemonEntity
import com.cobblemon.mod.common.api.properties.CustomPokemonPropertyType
import com.cobblemon.mod.common.pokemon.properties.IntProperty

object RespawnTimeProperty : CustomPokemonPropertyType<IntProperty> {
    override val keys = setOf("respawn_time", "respawn")
    override val needsKey = true

    override fun fromString(value: String?) = when (value) {
        null -> respawnTime(72000)
        else -> value.toIntOrNull()?.let { respawnTime(it) }
    }

    private fun respawnTime(value: Int) = IntProperty(
        key = keys.first(),
        value = value,
        pokemonApplicator = { _, _ -> },
        entityApplicator = { pokemonEntity, respawnTime -> (pokemonEntity as BeepPokemonEntity).respawnData.timer = respawnTime },
        pokemonMatcher = { _, _ -> false },
        entityMatcher = { pokemonEntity, respawnTime -> (pokemonEntity as BeepPokemonEntity).respawnData.timer == respawnTime }
    )

    override fun examples() = setOf("72000")
}