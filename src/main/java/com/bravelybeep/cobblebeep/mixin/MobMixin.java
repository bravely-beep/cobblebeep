package com.bravelybeep.cobblebeep.mixin;

import com.bravelybeep.cobblebeep.mixinaccess.MobMixinAccess;
import net.minecraft.world.entity.Mob;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(Mob.class)
public class MobMixin implements MobMixinAccess {
    @Shadow
    private boolean persistenceRequired;

    @Override
    public boolean cobbleBeep$getPersistenceRequired() {
        return persistenceRequired;
    }

    @Override
    public void cobbleBeep$setPersistenceRequired(boolean value) {
        persistenceRequired = value;
    }
}
