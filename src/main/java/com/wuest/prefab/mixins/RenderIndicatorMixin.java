package com.wuest.prefab.mixins;

import com.wuest.prefab.ClientModRegistry;
import com.wuest.prefab.blocks.BlockStructureScanner;
import com.wuest.prefab.config.StructureScannerConfig;
import com.wuest.prefab.structures.render.StructureRenderHandler;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.debug.DebugRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(DebugRenderer.class)
public class RenderIndicatorMixin {
    @Inject(method = "render", at = @At(value = "TAIL"))
    public void renderWorldLast(MatrixStack matrices, VertexConsumerProvider.Immediate vertexConsumers, double cameraX, double cameraY, double cameraZ, CallbackInfo ci) {
        MinecraftClient mc = MinecraftClient.getInstance();

        if (mc.player != null && (!mc.player.isSneaking())) {
            StructureRenderHandler.RenderTest(mc.world, matrices, cameraX, cameraY, cameraZ);
        }

        // It there are structure scanners; run the rendering for them now.
        if (ClientModRegistry.structureScanners != null && ClientModRegistry.structureScanners.size() != 0) {
            StructureRenderHandler.renderScanningBoxes(matrices, cameraX, cameraY, cameraZ);
        }
    }
}
