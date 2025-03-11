package com.bravelybeep.cobblebeep.mixin;

import com.bravelybeep.cobblebeep.PokeSpawnerData;
import com.bravelybeep.cobblebeep.mixinaccess.PokemonEntityMixinAccess;
import com.cobblemon.mod.common.entity.pokemon.PokemonEntity;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.NbtOps;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

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

    @Inject(method = "saveWithoutId", at = @At("HEAD"))
    void injectSaveWithoutId(CompoundTag nbt, CallbackInfoReturnable<CompoundTag> cir) {
        PokeSpawnerData.CODEC.encodeStart(NbtOps.INSTANCE, respawnData).ifSuccess(tag -> nbt.put("Respawn", tag));
    }

    @Inject(method = "load", at = @At("HEAD"))
    void injectLoad(CompoundTag nbt, CallbackInfo ci) {
        if (nbt.contains("Spawner")) {
            var spawnerTag = nbt.get("Spawner");
            PokeSpawnerData.CODEC.parse(NbtOps.INSTANCE, spawnerTag).ifSuccess(tag -> respawnData = tag);
        }
    }
}
