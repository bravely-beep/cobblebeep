package com.bravelybeep.cobblebeep.entity

import com.cobblemon.mod.common.api.pokemon.PokemonProperties
import com.mojang.serialization.Codec
import com.mojang.serialization.codecs.RecordCodecBuilder

class PokeSpawnerData(
    var properties: PokemonProperties? = null,
    var timer: Int = -1,
    var radiusHorizontal: Int = 0,
    var radiusVertical: Int = 0,
    var denyCaughtSpecies: Boolean = false,
    var denyCaughtForm: Boolean = false,
) {
    companion object {
        @JvmField
        val CODEC: Codec<PokeSpawnerData> = RecordCodecBuilder.create {
            it.group(
                PokemonProperties.CODEC.fieldOf("Properties").forGetter(PokeSpawnerData::properties),
                Codec.INT.fieldOf("Timer").forGetter(PokeSpawnerData::timer),
                Codec.INT.fieldOf("RadiusHorizontal").forGetter(PokeSpawnerData::radiusHorizontal),
                Codec.INT.fieldOf("RadiusVertical").forGetter(PokeSpawnerData::radiusVertical),
                Codec.BOOL.fieldOf("DenyCaughtSpecies").forGetter(PokeSpawnerData::denyCaughtSpecies),
                Codec.BOOL.fieldOf("DenyCaughtForm").forGetter(PokeSpawnerData::denyCaughtForm),
            ).apply(it, ::PokeSpawnerData)
        }
    }

    fun copy() = PokeSpawnerData(properties?.copy(), timer, radiusHorizontal, radiusVertical, denyCaughtSpecies, denyCaughtForm)
}