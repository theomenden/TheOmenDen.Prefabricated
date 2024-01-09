package com.theomenden.prefabricated.gui.controls;

import com.mojang.blaze3d.systems.RenderSystem;
import com.theomenden.prefabricated.Prefab;
import com.theomenden.prefabricated.Utils;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;

public class CustomButton extends ExtendedButton {
    private final ResourceLocation buttonTexture = new ResourceLocation(Prefab.MODID, "textures/gui/prefab_button.png");
    private final ResourceLocation buttonTexturePressed = new ResourceLocation(Prefab.MODID, "textures/gui/prefab_button_pressed.png");
    private final ResourceLocation buttonTextureHover = new ResourceLocation(Prefab.MODID, "textures/gui/prefab_button_highlight.png");

    public CustomButton(int xPos, int yPos, int width, int height, Component displayString, OnPress handler) {
        super(xPos, yPos, width, height, displayString, handler, null);
    }

    /**
     * Draws this button to the screen.
     */
    @Override
    public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTick) {
        if(!this.visible) {
            return;
        }
        Minecraft mc = Minecraft.getInstance();
        this.isHovered = isMouseHoveringInBounds(mouseX, mouseY);
        ResourceLocation buttonTexture = this.isHovered ? this.buttonTextureHover : this.buttonTexture;
        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderTexture(0, buttonTexture);
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, this.alpha);
        // matrixStack, x, y, width, height, 0, 0, regionWidth, regionHeight, textureWidth, textureHeight
        guiGraphics.blit(buttonTexture,getX(), getY(),0f,0f,90, 20, 90, 20);
        final int color = 14737632;

        Component buttonText = this.getMessage();
        int strWidth = mc.font.width(buttonText);
        int ellipsisWidth = mc.font.width("...");

        if (strWidth > width - 6 && strWidth > ellipsisWidth) {
            buttonText = Utils.createTextComponent(mc.font.substrByWidth(buttonText, width - 6 - ellipsisWidth).getString() + "...");
        }

        guiGraphics.drawCenteredString(mc.font, buttonText,this.getX() + this.width / 2, this.getY() + (this.height - 8) / 2, color);
    }

    private boolean isMouseHoveringInBounds(int mouseX, int mouseY) {
        return isMouseOutOfX(mouseX)
                && isMouseOutOfY(mouseY)
                && isMouseBeyondWidthBoundary(mouseX)
                && isMouseBeyondHeightBoundary(mouseY);
    }

    private boolean isMouseOutOfX(int mouseX) {
        return mouseX >= this.getX();
    }
    private boolean isMouseOutOfY(int mouseY) {
        return mouseY >= this.getY();
    }

    private boolean isMouseBeyondWidthBoundary(int mouseX) {
        return mouseX < this.getX() + this.width;
    }

    private boolean isMouseBeyondHeightBoundary(int mouseY) {
        return mouseY < this.getY() + this.height;
    }
}
