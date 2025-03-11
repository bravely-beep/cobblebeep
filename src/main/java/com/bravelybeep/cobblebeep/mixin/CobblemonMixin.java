package com.bravelybeep.cobblebeep.mixin;

import com.bravelybeep.cobblebeep.CobbleBeep;
import com.cobblemon.mod.common.Cobblemon;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Cobblemon.class)
public class CobblemonMixin {
    @Inject(
            method = "initialize",
            at = @At(
                    value = "INVOKE",
                    target = "com/cobblemon/mod/common/api/reactive/SimpleObservable.emit([Ljava/lang/Object;)V"),
            remap = false
    )
    void injectCustomPropertyInit(CallbackInfo ci) {
        CobbleBeep.INSTANCE.registerCustomProperties();
    }
}
