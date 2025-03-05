package com.bravelybeep.cobblebeep.pokemon.properties

import com.bravelybeep.cobblebeep.entity.BeepPokemonEntity
import com.cobblemon.mod.common.api.properties.CustomPokemonPropertyType
import com.cobblemon.mod.common.pokemon.properties.BooleanProperty

object RespawnDenyCaughtFormProperty : CustomPokemonPropertyType<BooleanProperty> {
    override val keys = setOf("respawn_deny_caught_form")
    override val needsKey = true

    override fun fromString(value: String?) = when {
        value == null || value.lowercase() in listOf("true", "yes") -> denyCaughtForm(true)
        value.lowercase() in listOf("false", "no") -> denyCaughtForm(false)
        else -> null
    }

    private fun denyCaughtForm(value: Boolean) = BooleanProperty(
        key = keys.first(),
        value = value,
        pokemonApplicator = { _, _ -> },
        entityApplicator = { pokemonEntity, denyCaughtForm -> (pokemonEntity as BeepPokemonEntity).respawnData.denyCaughtForm = denyCaughtForm },
        pokemonMatcher = { _, _ -> false },
        entityMatcher = { pokemonEntity, denyCaughtForm -> (pokemonEntity as BeepPokemonEntity).respawnData.denyCaughtForm == denyCaughtForm }
    )

    override fun examples() = setOf("yes", "no")
}