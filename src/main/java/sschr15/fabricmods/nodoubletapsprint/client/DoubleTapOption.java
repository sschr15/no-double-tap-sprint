package sschr15.fabricmods.nodoubletapsprint.client;

import net.minecraft.client.Minecraft;
import net.minecraft.client.OptionInstance;

public class DoubleTapOption {
    public static final OptionInstance<Boolean> DOUBLE_TAP_SPRINT = OptionInstance.createBoolean(
            "controls.double_tap_sprint",
            ((DisableDoubleTapAccessor) Minecraft.getInstance().options).nodoubletap$isDoubleTapDisabled(),
            bl -> ((DisableDoubleTapAccessor) Minecraft.getInstance().options).nodoubletap$setDoubleTapDisabled(bl)
    );
}
