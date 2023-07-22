package sschr15.fabricmods.nodoubletapsprint.mixin;

import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;
import sschr15.fabricmods.nodoubletapsprint.client.DisableDoubleTapAccessor;

@Mixin(LocalPlayer.class)
public abstract class ClientPlayerEntityMixin {
    @Shadow protected @Final Minecraft minecraft;
    @Shadow public abstract void setSprinting(boolean sprinting);

    /**
     * @reason Removes double tap sprinting (as a config option)
     */
    @Redirect(method = "aiStep", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/player/LocalPlayer;setSprinting(Z)V"))
    private void setSprinting(LocalPlayer player, boolean sprinting) {
        DisableDoubleTapAccessor options = (DisableDoubleTapAccessor) minecraft.options;
        setSprinting(options.nodoubletap$isDoubleTapDisabled() ? options.nodoubletap$isSprintKeyPressed() : sprinting);
    }
}
