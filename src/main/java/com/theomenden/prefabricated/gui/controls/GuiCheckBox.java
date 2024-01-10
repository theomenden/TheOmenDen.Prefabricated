package com.theomenden.prefabricated.gui.controls;


import com.mojang.blaze3d.systems.RenderSystem;
import com.theomenden.prefabricated.Prefab;
import com.theomenden.prefabricated.Utils;
import com.theomenden.prefabricated.gui.GuiUtils;
import lombok.Getter;
import lombok.Setter;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.AbstractButton;
import net.minecraft.client.gui.narration.NarratedElementType;
import net.minecraft.client.gui.narration.NarrationElementOutput;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.FormattedText;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;

import java.awt.*;

/**
 * @author WuestMan
 */
@SuppressWarnings({"UnusedReturnValue", "unused"})
public class GuiCheckBox extends AbstractButton {
    private static final ResourceLocation buttonTexture = new ResourceLocation(Prefab.MODID, "textures/gui/prefab_checkbox.png");
    private static final ResourceLocation buttonTexturePressed = new ResourceLocation(Prefab.MODID, "textures/gui/prefab_checkbox_selected.png");
    private static final ResourceLocation buttonTextureHover = new ResourceLocation(Prefab.MODID, "textures/gui/prefab_checkbox_hover.png");
    private static final ResourceLocation buttonTextureHoverSelected = new ResourceLocation(Prefab.MODID, "textures/gui/prefab_checkbox_hover_selected.png");

    protected int boxWidth;
    protected int boxHeight;
    /**
     * -- GETTER --
     *  Gets the string color to write.
     *
     * @return The color used when writing the string value of this checkbox.
     */
    @Getter
    protected int stringColor;
    @Getter
    protected boolean withShadow;
    protected Minecraft mineCraft;
    protected String displayString;
    protected IPressable handler;
    protected int labelWidth;
    @Setter
    protected boolean isChecked;

    public GuiCheckBox(int xPos, int yPos, String displayString, boolean isChecked, IPressable handler) {
        super(xPos, yPos, 11, 12, Utils.createTextComponent(displayString));

        this.boxWidth = 11;
        this.mineCraft = Minecraft.getInstance();
        this.displayString = displayString;
        this.stringColor = Color.DARK_GRAY.getRGB();
        this.handler = handler;
        this.withShadow = false;
        this.labelWidth = 98;
        this.isChecked = isChecked;
    }

    /**
     * Sets the color used when writing the text for this checkbox.
     *
     * @param color The color used for the text.
     * @return An updated instance of this class.
     */
    public GuiCheckBox setStringColor(int color) {
        this.stringColor = color;
        return this;
    }

    @Override
    public void onPress() {
        this.isChecked = !this.isChecked;

        if (this.handler != null) {
            this.handler.onPress(this);
        }
    }

    /**
     * Sets the value of whether shadows are included when writing the text of this checkbox.
     *
     * @param value The new value of the property.
     * @return An updated instance of this class
     */
    public GuiCheckBox setWithShadow(boolean value) {
        this.withShadow = value;
        return this;
    }

    public GuiCheckBox setLabelWidth(int value) {
        this.labelWidth = value;
        return this;
    }

    public boolean isChecked() {
        return this.isChecked;
    }

    @Override
    public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTick) {
        if(this.visible) {
            ResourceLocation resourceLocation = GuiCheckBox.buttonTexture;
            RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
            this.isHovered = mouseX >= this.getX() && mouseY >= this.getY() && mouseX < this.getX() + this.boxWidth && mouseY < this.getY() + this.height;

            if (this.isChecked()) {
                resourceLocation = GuiCheckBox.buttonTexturePressed;

                if (this.isHovered) {
                    resourceLocation = GuiCheckBox.buttonTextureHoverSelected;
                }
            } else if (this.isHovered) {
                resourceLocation = GuiCheckBox.buttonTextureHover;
            }

            GuiUtils.bindAndDrawScaledTextureZ(resourceLocation, guiGraphics, this.getX(), this.getY(), 11, 11,11,11,11,11);


            if (this.withShadow) {
                guiGraphics.drawString(
                        this.mineCraft.font,
                        displayString,
                        this.getX() + this.boxWidth + 2,
                        this.getY() + 4,
                        this.stringColor,
                        true);
            } else {
                guiGraphics.drawWordWrap(
                        this.mineCraft.font,
                        FormattedText.of(displayString),
                        this.getX() + this.boxWidth + 2,
                        this.getY() + 2,
                        this.labelWidth,
                        this.stringColor);
            }
        }
    }

    @Override
    public @NotNull MutableComponent createNarrationMessage() {
        Component state = isChecked ? Component.translatable("options.on") : Component.translatable("options.off");
        String msg = displayString + ": ";
        return Component.translatable("narration.checkbox", Component.literal(msg).append(state));
    }

    @Override
    public void updateWidgetNarration(NarrationElementOutput builder) {
        builder.add(NarratedElementType.TITLE, this.createNarrationMessage());
        if (this.active) {
            if (this.isFocused()) {
                builder.add(NarratedElementType.USAGE, Component.translatable("narration.checkbox.usage.focused"));
            } else {
                builder.add(NarratedElementType.USAGE, Component.translatable("narration.checkbox.usage.hovered"));
            }
        }
    }

    @Environment(EnvType.CLIENT)
    public interface IPressable {
        void onPress(GuiCheckBox p_onPress_1_);
    }
}
