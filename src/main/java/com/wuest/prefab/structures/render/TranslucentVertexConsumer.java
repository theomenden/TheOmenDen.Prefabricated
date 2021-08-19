package com.wuest.prefab.structures.render;

import net.minecraft.client.render.VertexConsumer;
import net.minecraft.util.math.MathHelper;

public class TranslucentVertexConsumer implements VertexConsumer {
    private final VertexConsumer inner;
    private final int tintAlpha;

    public TranslucentVertexConsumer(VertexConsumer inner, int alpha) {
        this.inner = inner;

        // Alpha value should be between 0 and 255
        this.tintAlpha = MathHelper.clamp(alpha, 0, 0xFF);;
    }

    @Override
    public VertexConsumer vertex(double x, double y, double z) {
        return inner.vertex(x, y, z);
    }

    @Override
    public VertexConsumer color(int red, int green, int blue, int alpha) {
        return inner.color(red, green, blue, (alpha * tintAlpha) / 0xFF);
    }

    @Override
    public VertexConsumer texture(float u, float v) {
        return inner.texture(u, v);
    }

    @Override
    public VertexConsumer overlay(int u, int v) {
        return inner.overlay(u, v);
    }

    @Override
    public VertexConsumer light(int u, int v) {
        return inner.light(u, v);
    }

    @Override
    public VertexConsumer normal(float x, float y, float z) {
        return inner.normal(x, y, z);
    }

    @Override
    public void next() {
        inner.next();
    }

    @Override
    public void fixedColor(int red, int green, int blue, int alpha) {
        inner.fixedColor(red, green, blue, (alpha * tintAlpha) / 0xFF);
    }

    @Override
    public void unfixColor() {
        inner.unfixColor();
    }

}
