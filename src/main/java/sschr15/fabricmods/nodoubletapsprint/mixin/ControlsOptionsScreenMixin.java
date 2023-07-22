package sschr15.fabricmods.nodoubletapsprint.mixin;

import net.minecraft.client.gui.screens.OptionsSubScreen;
import net.minecraft.client.gui.screens.controls.ControlsScreen;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.*;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;
import sschr15.fabricmods.nodoubletapsprint.client.DoubleTapOption;

@Mixin(ControlsScreen.class)
public abstract class ControlsOptionsScreenMixin extends OptionsSubScreen {
    private ControlsOptionsScreenMixin() {
        super(null, null, null);
    }

    @Inject(method = "init",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/client/Options;autoJump()Lnet/minecraft/client/OptionInstance;"),
            locals = LocalCapture.CAPTURE_FAILHARD
    )
    private void onInit(CallbackInfo ci, int i, int j, int k) {
        addRenderableWidget(DoubleTapOption.DOUBLE_TAP_SPRINT.createButton(options, i, k + 24, 150));
    }

    @ModifyVariable(method = "init", index = 3, at = @At(value = "FIELD", target = "Lnet/minecraft/network/chat/CommonComponents;GUI_DONE:Lnet/minecraft/network/chat/Component;"))
    private int modifyHeight(int yDrawPosition) {
        return yDrawPosition + 24;
    }
}
