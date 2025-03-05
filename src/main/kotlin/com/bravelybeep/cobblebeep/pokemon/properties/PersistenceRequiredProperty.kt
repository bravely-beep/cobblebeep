package com.bravelybeep.cobblebeep.pokemon.properties

import com.bravelybeep.cobblebeep.entity.BeepMob
import com.cobblemon.mod.common.api.properties.CustomPokemonPropertyType
import com.cobblemon.mod.common.pokemon.properties.BooleanProperty

object PersistenceRequiredProperty : CustomPokemonPropertyType<BooleanProperty> {
    override val keys = setOf("persistence_required")
    override val needsKey = true

    override fun fromString(value: String?) = when {
        value == null || value.lowercase() in listOf("true", "yes") -> persistenceRequired(true)
        value.lowercase() in listOf("false", "no") -> persistenceRequired(false)
        else -> null
    }

    private fun persistenceRequired(value: Boolean) = BooleanProperty(
        key = keys.first(),
        value = value,
        pokemonApplicator = { _, _ -> },
        entityApplicator = { pokemonEntity, persistenceRequired -> (pokemonEntity as BeepMob).persistenceRequired = persistenceRequired },
        pokemonMatcher = { _, _ -> false },
        entityMatcher = { pokemonEntity, persistenceRequired -> (pokemonEntity as BeepMob).persistenceRequired == persistenceRequired }
    )

    override fun examples() = setOf("yes", "no")
}