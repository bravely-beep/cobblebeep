package com.bravelybeep.cobblebeep.mixin;

import com.bravelybeep.cobblebeep.entity.PokeSpawnerData;
import com.bravelybeep.cobblebeep.mixinaccess.PokemonEntityMixinAccess;
import com.cobblemon.mod.common.entity.pokemon.PokemonEntity;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;

@Mixin(PokemonEntity.class)
public class PokemonEntityMixin implements PokemonEntityMixinAccess {
    @Unique
    private PokeSpawnerData respawnData = new PokeSpawnerData();

    @Override
    public @NotNull PokeSpawnerData cobbleBeep$getRespawnData() {
        return respawnData;
    }

    @Override
    public void cobbleBeep$setRespawnData(@NotNull PokeSpawnerData value) {
        respawnData = value;
    }
}
