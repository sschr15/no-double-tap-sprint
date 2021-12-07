package sschr15.fabricmods.nodoubletapsprint.mixin;

import net.minecraft.client.gui.screen.option.ControlsOptionsScreen;
import net.minecraft.client.gui.screen.option.GameOptionsScreen;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;
import sschr15.fabricmods.nodoubletapsprint.client.DoubleTapOption;

@Mixin(ControlsOptionsScreen.class)
public abstract class ControlsOptionsScreenMixin extends GameOptionsScreen {
    private ControlsOptionsScreenMixin() {
        super(null, null, null);
    }

    @Inject(method = "init",
            at = @At(
                    value = "FIELD",
                    target = "Lnet/minecraft/client/option/Option;AUTO_JUMP:Lnet/minecraft/client/option/CyclingOption;"
            ),
            locals = LocalCapture.CAPTURE_FAILHARD
    )
    private void onInit(CallbackInfo ci, int i, int j, int k) {
        addDrawableChild(DoubleTapOption.DOUBLE_TAP_SPRINT.createButton(gameOptions, j, k, 150));
    }
}
