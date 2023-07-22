package sschr15.fabricmods.nodoubletapsprint.mixin;

import net.minecraft.client.KeyMapping;
import net.minecraft.client.Options;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import sschr15.fabricmods.nodoubletapsprint.client.DisableDoubleTapAccessor;

@Mixin(Options.class)
public abstract class OptionsMixin implements DisableDoubleTapAccessor {
    @Shadow @Final public KeyMapping keySprint;
    @Unique private boolean disableDoubleTap = true;

    @Override
    public boolean nodoubletap$isDoubleTapDisabled() {
        return disableDoubleTap;
    }

    @Override
    public void nodoubletap$setDoubleTapDisabled(boolean doubleTapDisabled) {
        this.disableDoubleTap = doubleTapDisabled;
    }

    @Override
    public boolean nodoubletap$isSprintKeyPressed() {
        return keySprint.isDown();
    }

    @Inject(method = "processOptions", at = @At("RETURN"))
    private void onAccept(Options.FieldAccess visitor, CallbackInfo ci) {
        disableDoubleTap = visitor.process("mod_disabledoubletap", this.disableDoubleTap);
    }
}
