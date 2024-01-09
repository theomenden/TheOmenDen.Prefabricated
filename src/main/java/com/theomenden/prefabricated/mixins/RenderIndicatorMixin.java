package com.theomenden.prefabricated.mixins;

import com.mojang.blaze3d.vertex.PoseStack;
import com.theomenden.prefabricated.ClientModRegistry;
import com.theomenden.prefabricated.config.StructureScannerConfig;
import com.theomenden.prefabricated.structures.render.StructureRenderHandler;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.debug.DebugRenderer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.List;

@Mixin(DebugRenderer.class)
public class RenderIndicatorMixin {
    @Inject(method = "render", at = @At(value = "TAIL"))
    public void renderWorldLast(PoseStack matrices, MultiBufferSource.BufferSource vertexConsumers, double cameraX, double cameraY, double cameraZ, CallbackInfo ci) {
        Minecraft mc = Minecraft.getInstance();

        if (mc.player != null && (!mc.player.isCrouching())) {
            StructureRenderHandler.RenderTest(mc.level, matrices, cameraX, cameraY, cameraZ);
        }

        /* Run the rendering for existing structure scanners now. */
        if (scannersArePresent(ClientModRegistry.structureScanners)) {
            StructureRenderHandler.renderScanningBoxes(matrices, cameraX, cameraY, cameraZ);
        }
    }

    @Unique
    private static boolean scannersArePresent(List<StructureScannerConfig> scanners) {
        return scanners != null && !scanners.isEmpty();
    }
}
