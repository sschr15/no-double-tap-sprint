package sschr15.fabricmods.nodoubletapsprint.client;

import net.minecraft.client.option.CyclingOption;

public class DoubleTapOption {
    public static final CyclingOption<Boolean> DOUBLE_TAP_SPRINT = CyclingOption.create(
            "controls.double_tap_sprint",
            gameOptions -> !((DisableDoubleTapAccessor) gameOptions).isDoubleTapDisabled(),
            (gameOptions, option, value) -> ((DisableDoubleTapAccessor) gameOptions).setDoubleTapDisabled(!value)
    );
}
