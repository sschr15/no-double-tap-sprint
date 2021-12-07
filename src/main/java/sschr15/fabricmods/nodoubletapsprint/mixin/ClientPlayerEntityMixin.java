package sschr15.fabricmods.nodoubletapsprint.mixin;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayerEntity;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;
import sschr15.fabricmods.nodoubletapsprint.client.DisableDoubleTapAccessor;

@Mixin(ClientPlayerEntity.class)
public abstract class ClientPlayerEntityMixin {
    @Shadow protected @Final MinecraftClient client;
    @Shadow public abstract void setSprinting(boolean sprinting);

    /**
     * @reason Removes double tap sprinting (as a config option)
     */
    @Redirect(method = "tickMovement", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/network/ClientPlayerEntity;setSprinting(Z)V"))
    private void setSprinting(ClientPlayerEntity player, boolean sprinting) {
        DisableDoubleTapAccessor options = (DisableDoubleTapAccessor) client.options;
        setSprinting(options.isDoubleTapDisabled() ? options.isSprintKeyPressed() : sprinting);
    }
}
