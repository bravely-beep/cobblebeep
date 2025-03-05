package com.bravelybeep.cobblebeep.pokemon.properties

import com.bravelybeep.cobblebeep.entity.BeepPokemonEntity
import com.cobblemon.mod.common.api.properties.CustomPokemonPropertyType
import com.cobblemon.mod.common.pokemon.properties.BooleanProperty

object RespawnDenyCaughtSpeciesProperty : CustomPokemonPropertyType<BooleanProperty> {
    override val keys = setOf("respawn_deny_caught_species")
    override val needsKey = true

    override fun fromString(value: String?) = when {
        value == null || value.lowercase() in listOf("true", "yes") -> denyCaughtSpecies(true)
        value.lowercase() in listOf("false", "no") -> denyCaughtSpecies(false)
        else -> null
    }

    private fun denyCaughtSpecies(value: Boolean) = BooleanProperty(
        key = keys.first(),
        value = value,
        pokemonApplicator = { _, _ -> },
        entityApplicator = { pokemonEntity, denyCaughtSpecies -> (pokemonEntity as BeepPokemonEntity).respawnData.denyCaughtSpecies = denyCaughtSpecies },
        pokemonMatcher = { _, _ -> false },
        entityMatcher = { pokemonEntity, denyCaughtSpecies -> (pokemonEntity as BeepPokemonEntity).respawnData.denyCaughtSpecies == denyCaughtSpecies }
    )

    override fun examples() = setOf("yes", "no")
}