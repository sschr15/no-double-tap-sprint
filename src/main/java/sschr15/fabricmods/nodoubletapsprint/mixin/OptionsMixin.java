package sschr15.fabricmods.nodoubletapsprint.mixin;

import net.minecraft.client.option.GameOptions;
import net.minecraft.client.option.KeyBinding;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import sschr15.fabricmods.nodoubletapsprint.client.DisableDoubleTapAccessor;

@Mixin(GameOptions.class)
public abstract class OptionsMixin implements DisableDoubleTapAccessor {
    @Shadow @Final public KeyBinding keySprint;
    @Unique private boolean disableDoubleTap = true;

    @Override
    public boolean isDoubleTapDisabled() {
        return disableDoubleTap;
    }

    @Override
    public void setDoubleTapDisabled(boolean doubleTapDisabled) {
        this.disableDoubleTap = doubleTapDisabled;
    }

    @Override
    public boolean isSprintKeyPressed() {
        return keySprint.isPressed();
    }

    @Inject(method = "accept", at = @At("RETURN"))
    private void onAccept(GameOptions.Visitor visitor, CallbackInfo ci) {
        disableDoubleTap = visitor.visitBoolean("mod_disabledoubletap", this.disableDoubleTap);
    }
}
