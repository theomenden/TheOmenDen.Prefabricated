package com.theomenden.prefabricated.structures.render;

import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.util.Mth;
import org.jetbrains.annotations.NotNull;

public class TranslucentVertexConsumer implements VertexConsumer {
    private final VertexConsumer inner;
    private final int tintAlpha;

    public TranslucentVertexConsumer(VertexConsumer inner, int alpha) {
        this.inner = inner;

        // Alpha value should be between 0 and 255
        this.tintAlpha = Mth.clamp(alpha, 0, 0xFF);
        ;
    }

    @Override
    public @NotNull VertexConsumer vertex(double x, double y, double z) {
        return inner.vertex(x, y, z);
    }

    @Override
    public @NotNull VertexConsumer color(int red, int green, int blue, int alpha) {
        return inner.color(red, green, blue, (alpha * tintAlpha) / 0xFF);
    }

    @Override
    public @NotNull VertexConsumer uv(float u, float v) {
        return inner.uv(u, v);
    }

    @Override
    public @NotNull VertexConsumer overlayCoords(int u, int v) {
        return inner.overlayCoords(u, v);
    }

    @Override
    public @NotNull VertexConsumer uv2(int u, int v) {
        return inner.uv2(u, v);
    }

    @Override
    public @NotNull VertexConsumer normal(float x, float y, float z) {
        return inner.normal(x, y, z);
    }

    @Override
    public void endVertex() {
        inner.endVertex();
    }

    @Override
    public void defaultColor(int red, int green, int blue, int alpha) {
        inner.defaultColor(red, green, blue, (alpha * tintAlpha) / 0xFF);
    }

    @Override
    public void unsetDefaultColor() {
        inner.unsetDefaultColor();
    }
}
