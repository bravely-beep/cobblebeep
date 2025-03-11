package com.bravelybeep.cobblebeep

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
                PokemonProperties.CODEC.optionalFieldOf("Properties", null).forGetter(PokeSpawnerData::properties),
                Codec.INT.optionalFieldOf("Timer", -1).forGetter(PokeSpawnerData::timer),
                Codec.INT.optionalFieldOf("RadiusHorizontal", 0).forGetter(PokeSpawnerData::radiusHorizontal),
                Codec.INT.optionalFieldOf("RadiusVertical", 0).forGetter(PokeSpawnerData::radiusVertical),
                Codec.BOOL.optionalFieldOf("DenyCaughtSpecies", false).forGetter(PokeSpawnerData::denyCaughtSpecies),
                Codec.BOOL.optionalFieldOf("DenyCaughtForm", false).forGetter(PokeSpawnerData::denyCaughtForm),
            ).apply(it, ::PokeSpawnerData)
        }
    }

    fun copy() = PokeSpawnerData(properties?.copy(), timer, radiusHorizontal, radiusVertical, denyCaughtSpecies, denyCaughtForm)
}