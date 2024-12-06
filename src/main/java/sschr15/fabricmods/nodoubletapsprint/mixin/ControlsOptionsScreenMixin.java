package sschr15.fabricmods.nodoubletapsprint.mixin;

import net.minecraft.client.OptionInstance;
import net.minecraft.client.Options;
import net.minecraft.client.gui.screens.options.OptionsSubScreen;
import net.minecraft.client.gui.screens.options.controls.ControlsScreen;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.*;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;
import sschr15.fabricmods.nodoubletapsprint.client.DoubleTapOption;

@Mixin(ControlsScreen.class)
public abstract class ControlsOptionsScreenMixin extends OptionsSubScreen {
    private ControlsOptionsScreenMixin() {
        super(null, null, null);
    }

    @Inject(method = "options", at = @At("RETURN"), cancellable = true)
    private static void onInit(CallbackInfoReturnable<OptionInstance<?>[]> cir) {
        OptionInstance<?>[] newArray = new OptionInstance<?>[cir.getReturnValue().length + 1];
        System.arraycopy(cir.getReturnValue(), 0, newArray, 0, cir.getReturnValue().length);
        newArray[newArray.length - 1] = DoubleTapOption.DOUBLE_TAP_SPRINT;
        cir.setReturnValue(newArray);
    }
}
