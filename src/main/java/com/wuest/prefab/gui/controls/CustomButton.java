package com.wuest.prefab.gui.controls;

import com.mojang.blaze3d.systems.RenderSystem;
import com.wuest.prefab.Utils;
import com.wuest.prefab.gui.GuiUtils;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawableHelper;
import net.minecraft.client.render.GameRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class CustomButton extends ExtendedButton {
    private final Identifier buttonTexture = new Identifier("prefab", "textures/gui/prefab_button.png");
    private final Identifier buttonTexturePressed = new Identifier("prefab", "textures/gui/prefab_button_pressed.png");
    private final Identifier buttonTextureHover = new Identifier("prefab", "textures/gui/prefab_button_highlight.png");

    public CustomButton(int xPos, int yPos, Text displayString, PressAction handler) {
        super(xPos, yPos, 200, 90, displayString, handler, null);
    }

    public CustomButton(int xPos, int yPos, int width, int height, Text displayString, PressAction handler) {
        super(xPos, yPos, width, height, displayString, handler, null);
    }

    /**
     * Draws this button to the screen.
     */
    @Override
    public void renderButton(MatrixStack mStack, int mouseX, int mouseY, float partial) {
        if (this.visible) {
            MinecraftClient mc = MinecraftClient.getInstance();
            this.hovered = mouseX >= this.x && mouseY >= this.y && mouseX < this.x + this.width && mouseY < this.y + this.height;
            Identifier buttonTexture = this.hovered ? this.buttonTextureHover : this.buttonTexture;
            RenderSystem.setShader(GameRenderer::getPositionTexShader);
            RenderSystem.setShaderTexture(0, buttonTexture);
            RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, this.alpha);

            GuiUtils.bindAndDrawScaledTexture(mStack, this.x, this.y, this.width, this.height, 90, 20, 90, 20);
            int color = 14737632;

            Text buttonText = this.getMessage();
            int strWidth = mc.textRenderer.getWidth(buttonText);
            int ellipsisWidth = mc.textRenderer.getWidth("...");

            if (strWidth > width - 6 && strWidth > ellipsisWidth)
                buttonText = Utils.createTextComponent(mc.textRenderer.trimToWidth(buttonText, width - 6 - ellipsisWidth).getString() + "...");

            DrawableHelper.drawCenteredText(mStack, mc.textRenderer, buttonText, this.x + this.width / 2, this.y + (this.height - 8) / 2, color);
        }
    }
}
