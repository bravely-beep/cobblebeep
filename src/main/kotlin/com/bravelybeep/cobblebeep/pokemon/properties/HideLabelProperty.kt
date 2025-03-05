package com.bravelybeep.cobblebeep.pokemon.properties

import com.cobblemon.mod.common.api.properties.CustomPokemonPropertyType
import com.cobblemon.mod.common.entity.pokemon.PokemonEntity
import com.cobblemon.mod.common.pokemon.properties.BooleanProperty

object HideLabelProperty : CustomPokemonPropertyType<BooleanProperty> {
    override val keys = setOf("hide_label")
    override val needsKey = true

    override fun fromString(value: String?) = when {
        value == null || value.lowercase() in listOf("true", "yes") -> hideLabel(true)
        value.lowercase() in listOf("false", "no") -> hideLabel(false)
        else -> null
    }

    private fun hideLabel(value: Boolean) = BooleanProperty(
        key = keys.first(),
        value = value,
        pokemonApplicator = { _, _ -> },
        entityApplicator = { pokemonEntity, hideLabel -> pokemonEntity.entityData.set(PokemonEntity.HIDE_LABEL, hideLabel) },
        pokemonMatcher = { _, _ -> false },
        entityMatcher = { pokemonEntity, hideLabel -> pokemonEntity.entityData.get(PokemonEntity.HIDE_LABEL) == hideLabel }
    )

    override fun examples() = setOf("yes", "no")
}