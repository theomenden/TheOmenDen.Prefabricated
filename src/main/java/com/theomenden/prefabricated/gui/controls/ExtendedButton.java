package com.theomenden.prefabricated.gui.controls;

import com.google.common.collect.Lists;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import com.theomenden.prefabricated.Utils;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.components.Tooltip;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.network.chat.Component;
import org.jetbrains.annotations.Nullable;

public class ExtendedButton extends Button{
    private final @Nullable String label;
    public float fontScale = 1;

    public ExtendedButton(int xPos, int yPos, int width, int height, Component displayString, OnPress handler, @Nullable String label) {
        super(xPos, yPos, width, height, displayString, handler, Button.DEFAULT_NARRATION);

        this.label = label;

        if(label != null && !label.isEmpty()) {
            Tooltip buttonExplanationLabel = Tooltip.create(Component.literal(this.label));
            this.setTooltip(buttonExplanationLabel);
        }
    }

    /**
     * Draws this button to the screen
     * @param guiGraphics the GuiGraphics object used for rendering.
     * @param mouseX the x-coordinate of the mouse cursor.
     * @param mouseY the y-coordinate of the mouse cursor.
     * @param partialTick the partial tick time.
     */
    @Override
    public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTick) {
        if (this.visible) {
            Minecraft mc = Minecraft.getInstance();

            this.isHovered = isMouseHoveringInBounds(mouseX, mouseY);

            RenderSystem.setShader(GameRenderer::getPositionTexShader);
            RenderSystem.setShaderTexture(0, WIDGETS_LOCATION);
            RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, this.alpha);

            int i;

            if(this.active && this.isHoveredOrFocused()) {
                i = 2;
            } else if (this.active) {
                i = 1;
            } else {
                i = 0;
            }

            if(this.isHovered && this.labelIsNotNullOrEmpty()){
                var tooltipComponent = Component.literal(this.label);
               guiGraphics.renderComponentTooltip(
                       mc.font,
                       Lists.newArrayList(tooltipComponent),
                       mouseX,
                       mouseY
               );
            }

            RenderSystem.enableBlend();
            RenderSystem.defaultBlendFunc();
            RenderSystem.enableDepthTest();
            guiGraphics.blit(WIDGETS_LOCATION,this.getX(), this.getY(), 0, 46 + i * 20, this.width / 2, this.height);
            guiGraphics.blit(WIDGETS_LOCATION,this.getX() + this.width / 2, this.getY(), 200 - this.width / 2, 46 + i * 20, this.width / 2, this.height);

            Component buttonText = this.getMessage();
            int strWidth = mc.font.width(buttonText);
            int ellipsisWidth = mc.font.width("...");

            if (strWidth > width - 6 && strWidth > ellipsisWidth) {
                buttonText = Utils.createTextComponent(mc.font
                        .substrByWidth(buttonText, width - 6 - ellipsisWidth)
                        .getString() + "...");
            }

            PoseStack originalStack = new PoseStack();

            originalStack.pushPose();
            originalStack.scale(this.fontScale, this.fontScale, this.fontScale);

            int xPosition = (int) ((this.getX() + this.width / 2) / this.fontScale);
            int yPosition = (int) ((this.getY() + (this.height - (8 * this.fontScale)) / 2) / this.fontScale);

            this.renderString(guiGraphics, mc.font, this.getFGColor());
            guiGraphics.drawCenteredString(mc.font, buttonText, xPosition, yPosition, this.getFGColor());
            originalStack.popPose();
        }
    }

    @Nullable
    @Override
    public Tooltip getTooltip() {
        return Tooltip.create(Component.translatable(this.label));
    }

    public int getFGColor() {
        return this.active ? 16777215 : 10526880; // White : Light Grey
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

    private boolean labelIsNotNullOrEmpty() {
        return this.label != null
                && !this.label.isEmpty();
    }
}
