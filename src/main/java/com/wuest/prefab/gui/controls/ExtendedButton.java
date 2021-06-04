package com.wuest.prefab.gui.controls;

import com.mojang.blaze3d.systems.RenderSystem;
import com.wuest.prefab.gui.GuiUtils;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawableHelper;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.render.GameRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.text.LiteralText;
import net.minecraft.text.Text;

public class ExtendedButton extends ButtonWidget {
	public ExtendedButton(int xPos, int yPos, int width, int height, Text displayString, PressAction handler) {
		super(xPos, yPos, width, height, displayString, handler);
	}

	/**
	 * Draws this button to the screen.
	 */
	@Override
	public void renderButton(MatrixStack mStack, int mouseX, int mouseY, float partial) {
		if (this.visible) {
			MinecraftClient mc = MinecraftClient.getInstance();
			this.hovered = mouseX >= this.x && mouseY >= this.y && mouseX < this.x + this.width && mouseY < this.y + this.height;

			RenderSystem.setShader(GameRenderer::getPositionTexShader);
			RenderSystem.setShaderTexture(0, WIDGETS_TEXTURE);
			RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, this.alpha);

			int i = this.getYImage(this.isHovered());

			RenderSystem.enableBlend();
			RenderSystem.defaultBlendFunc();
			RenderSystem.enableDepthTest();
			this.drawTexture(mStack, this.x, this.y, 0, 46 + i * 20, this.width / 2, this.height);
			this.drawTexture(mStack, this.x + this.width / 2, this.y, 200 - this.width / 2, 46 + i * 20, this.width / 2, this.height);

			this.renderBackground(mStack, mc, mouseX, mouseY);

			Text buttonText = this.getMessage();
			int strWidth = mc.textRenderer.getWidth(buttonText);
			int ellipsisWidth = mc.textRenderer.getWidth("...");

			if (strWidth > width - 6 && strWidth > ellipsisWidth) {
				buttonText = new LiteralText(mc.textRenderer.trimToWidth(buttonText, width - 6 - ellipsisWidth).getString() + "...");
			}

			DrawableHelper.drawCenteredText(mStack, mc.textRenderer, buttonText, this.x + this.width / 2, this.y + (this.height - 8) / 2, this.getFGColor());
		}
	}

	public int getFGColor() {
		return this.active ? 16777215 : 10526880; // White : Light Grey
	}
}
