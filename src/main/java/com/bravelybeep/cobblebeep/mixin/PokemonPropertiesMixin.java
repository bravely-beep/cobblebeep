package com.bravelybeep.cobblebeep.mixin;

import com.bravelybeep.cobblebeep.entity.BeepPokemonEntity;
import com.bravelybeep.cobblebeep.entity.PokeSpawnerData;
import com.cobblemon.mod.common.api.pokemon.PokemonProperties;
import com.cobblemon.mod.common.entity.pokemon.PokemonEntity;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(PokemonProperties.class)
public abstract class PokemonPropertiesMixin {
    @Shadow
    public abstract PokemonProperties copy();

    @Inject(
        method = "createEntity(Lnet/minecraft/world/level/Level;Lnet/minecraft/server/level/ServerPlayer;)Lcom/cobblemon/mod/common/entity/pokemon/PokemonEntity;",
        at = @At("RETURN"),
        remap = false
    )
    public void injectCreateEntity(Level world, @Nullable ServerPlayer player, CallbackInfoReturnable<PokemonEntity> cir) {
        var pokemonEntity = (BeepPokemonEntity)cir.getReturnValue();
        if (pokemonEntity.getRespawnData().getTimer() >= 0)
            pokemonEntity.getRespawnData().setProperties(this.copy());
    }
}
