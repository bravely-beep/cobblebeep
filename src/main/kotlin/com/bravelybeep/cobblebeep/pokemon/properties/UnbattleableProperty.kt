package com.bravelybeep.cobblebeep.pokemon.properties

import com.bravelybeep.cobblebeep.entity.BeepPokemonEntity
import com.cobblemon.mod.common.api.properties.CustomPokemonPropertyType
import com.cobblemon.mod.common.entity.pokemon.PokemonEntity
import com.cobblemon.mod.common.pokemon.properties.BooleanProperty

object UnbattleableProperty : CustomPokemonPropertyType<BooleanProperty> {
    override val keys = setOf("unbattleable")
    override val needsKey = true

    override fun fromString(value: String?) = when {
        value == null || value.lowercase() in listOf("true", "yes") -> unbattleable(true)
        value.lowercase() in listOf("false", "no") -> unbattleable(false)
        else -> null
    }

    private fun unbattleable(value: Boolean) = BooleanProperty(
        key = keys.first(),
        value = value,
        pokemonApplicator = { _, _ -> },
        entityApplicator = { pokemonEntity, unbattleable -> pokemonEntity.entityData.set(PokemonEntity.UNBATTLEABLE, unbattleable) },
        pokemonMatcher = { _, _ -> false },
        entityMatcher = { pokemonEntity, unbattleable -> pokemonEntity.entityData.get(PokemonEntity.UNBATTLEABLE) == unbattleable }
    )

    override fun examples() = setOf("yes", "no")
}