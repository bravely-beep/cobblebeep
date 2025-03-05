package com.bravelybeep.cobblebeep.pokemon.properties

import com.cobblemon.mod.common.api.properties.CustomPokemonPropertyType
import com.cobblemon.mod.common.entity.pokemon.PokemonEntity
import com.cobblemon.mod.common.pokemon.properties.BooleanProperty

object InvulnerableProperty : CustomPokemonPropertyType<BooleanProperty> {
    override val keys = setOf("invulnerable")
    override val needsKey = true

    override fun fromString(value: String?) = when {
        value == null || value.lowercase() in listOf("true", "yes") -> invulnerable(true)
        value.lowercase() in listOf("false", "no") -> invulnerable(false)
        else -> null
    }

    private fun invulnerable(value: Boolean) = BooleanProperty(
        key = keys.first(),
        value = value,
        pokemonApplicator = { _, _ -> },
        entityApplicator = { pokemonEntity, invulnerable -> pokemonEntity.isInvulnerable = invulnerable },
        pokemonMatcher = { _, _ -> false },
        entityMatcher = { pokemonEntity, invulnerable -> pokemonEntity.isInvulnerable == invulnerable }
    )

    override fun examples() = setOf("yes", "no")
}