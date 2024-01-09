package com.theomenden.prefabricated.gui;

import com.theomenden.prefabricated.Prefab;
import com.theomenden.prefabricated.Utils;
import com.theomenden.prefabricated.blocks.FullDyeColor;
import com.theomenden.prefabricated.gui.controls.CustomButton;
import com.theomenden.prefabricated.gui.controls.ExtendedButton;
import com.theomenden.prefabricated.gui.controls.GuiCheckBox;
import com.theomenden.prefabricated.gui.controls.GuiSlider;
import it.unimi.dsi.fastutil.ints.IntIntPair;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.AbstractButton;
import net.minecraft.client.gui.components.AbstractWidget;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.components.events.GuiEventListener;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.FormattedText;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.DyeColor;
import org.jetbrains.annotations.Nullable;

import java.awt.*;

public abstract class GuiBase extends Screen {

    private final ResourceLocation backgroundTextures = ResourceLocation.tryBuild(Prefab.MODID, "textures/gui/default_background.png");
    private final ResourceLocation narrowPanelTexture = ResourceLocation.tryBuild(Prefab.MODID, "textures/gui/custom_background.png");
    private final ResourceLocation leftPanelTexture = ResourceLocation.tryBuild(Prefab.MODID, "textures/gui/custom_left_panel.png");
    private final ResourceLocation middlePanelTexture = ResourceLocation.tryBuild(Prefab.MODID, "textures/gui/custom_middle_panel.png");
    private final ResourceLocation rightPanelTexture = ResourceLocation.tryBuild(Prefab.MODID, "textures/gui/custom_right_panel.png");
    protected int modifiedInitialXAxis = 0;
    protected int modifiedInitialYAxis = 0;
    protected int imagePanelWidth = 0;
    protected int imagePanelHeight = 0;
    protected int shownImageHeight = 0;
    protected int shownImageWidth = 0;
    protected int textColor = Color.DARK_GRAY.getRGB();
    private final boolean pauseGame;

    public GuiBase(String title) {
        super(Utils.createTextComponent(title));
        this.pauseGame = true;
    }

    @Override
    public void init() {
        this.Initialize();
    }

    /**
     * This method is used to initialize GUI specific items.
     */
    protected void Initialize() {
        this.modifiedInitialXAxis = 160;
        this.modifiedInitialYAxis = 120;
        this.imagePanelWidth = 325;
        this.imagePanelHeight = 300;
        this.shownImageHeight = 150;
        this.shownImageWidth = 268;
    }

    /**
     * Gets the X-Coordinates of the center of the screen.
     *
     * @return The coordinate value.
     */
    protected int getCenteredXAxis() {
        return this.width / 2;
    }

    /**
     * Gets the Y-Coordinates of the center off the screen.
     *
     * @return The coordinate value.
     */
    protected int getCenteredYAxis() {
        return this.height / 2;
    }

    @Override
    public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTick) {
        IntIntPair adjustedXYValue = this.getAdjustedXYValue();
        this.preButtonRender(guiGraphics, adjustedXYValue.leftInt(), adjustedXYValue.rightInt(), mouseX, mouseY, partialTick);
        this.renderButtons(guiGraphics, mouseX, mouseY);
        this.postButtonRender(guiGraphics, adjustedXYValue.leftInt(), adjustedXYValue.rightInt(), mouseX, mouseY, partialTick);
    }

    /**
     * Creates a button using the button clicked event as the handler. Then adds it to the buttons list and returns the created object.
     *
     * @param x      The x-axis position.
     * @param y      The y-axis position.
     * @param width  The width of the button.
     * @param height The height of the button.
     * @param text   The text of the button.
     * @return A new button.
     */
    public ExtendedButton createAndAddButton(int x, int y, int width, int height, String text) {
        return this.createAndAddButton(x, y, width, height, text, true);
    }

    /**
     * Creates a button using the button clicked event as the handler. Then adds it to the buttons list and returns the created object.
     *
     * @param x      The x-axis position.
     * @param y      The y-axis position.
     * @param width  The width of the button.
     * @param height The height of the button.
     * @param text   The text of the button.
     * @param label  The label of the button.
     * @return A new button.
     */
    public ExtendedButton createAndAddButton(int x, int y, int width, int height, String text, String label) {
        ExtendedButton returnValue = new ExtendedButton(x, y, width, height, GuiLangKeys.translateToComponent(text), this::buttonClicked, label);

        return this.addRenderableWidget(returnValue);
    }

    /**
     * Creates a button using the button clicked event as the handler. Then adds it to the buttons list and returns the created object.
     *
     * @param x      The x-axis position.
     * @param y      The y-axis position.
     * @param width  The width of the button.
     * @param height The height of the button.
     * @param text   The text of the button.
     * @param label  The label of the button.
     * @return A new button.
     */
    public ExtendedButton createAndAddButton(int x, int y, int width, int height, String text, boolean translate, String label) {
        ExtendedButton returnValue = new ExtendedButton(x, y, width, height, translate ? GuiLangKeys.translateToComponent(text) : Utils.createTextComponent(text), this::buttonClicked, label);

        return this.addRenderableWidget(returnValue);
    }

    /**
     * Creates a button using the button clicked event as the handler. Then adds it to the buttons list and returns the created object.
     *
     * @param x      The x-axis position.
     * @param y      The y-axis position.
     * @param width  The width of the button.
     * @param height The height of the button.
     * @param text   The text of the button.
     * @return A new button.
     */
    public ExtendedButton createAndAddButton(int x, int y, int width, int height, String text, boolean translate) {
        ExtendedButton returnValue = new ExtendedButton(x, y, width, height, translate ? GuiLangKeys.translateToComponent(text) : Utils.createTextComponent(text), this::buttonClicked, null);

        return this.addRenderableWidget(returnValue);
    }

    public CustomButton createAndAddCustomButton(int x, int y, int width, int height, String text) {
        return this.createAndAddCustomButton(x, y, width, height, text, true);
    }

    public CustomButton createAndAddCustomButton(int x, int y, int width, int height, String text, boolean translate) {
        CustomButton returnValue = new CustomButton(x, y, width, height, translate ? GuiLangKeys.translateToComponent(text) : Utils.createTextComponent(text), this::buttonClicked);

        return this.addRenderableWidget(returnValue);
    }

    /**
     * Creates a button using the button clicked event as the handler. Then adds it to the buttons list and returns the created object.
     *
     * @param x      The x-axis position.
     * @param y      The y-axis position.
     * @param width  The width of the button.
     * @param height The height of the button.
     * @param color  The color to describe on the button.
     * @param label  The label of the button.
     * @return A new button.
     */
    public ExtendedButton createAndAddDyeButton(int x, int y, int width, int height, DyeColor color, @Nullable String label) {
        ExtendedButton returnValue = new ExtendedButton(x, y, width, height, Utils.createTextComponent(GuiLangKeys.translateDye(color)), this::buttonClicked, label);

        return this.addRenderableWidget(returnValue);
    }

    /**
     * Creates a button using the button clicked event as the handler. Then adds it to the buttons list and returns the created object.
     *
     * @param x      The x-axis position.
     * @param y      The y-axis position.
     * @param width  The width of the button.
     * @param height The height of the button.
     * @param color  The color to describe on the button.
     * @param label  The label of the button.
     * @return A new button.
     */
    public ExtendedButton createAndAddFullDyeButton(int x, int y, int width, int height, FullDyeColor color, @Nullable String label) {
        ExtendedButton returnValue = new ExtendedButton(x, y, width, height, Utils.createTextComponent(GuiLangKeys.translateFullDye(color)), this::buttonClicked, label);

        return this.addRenderableWidget(returnValue);
    }

    public GuiCheckBox createAndAddCheckBox(int xPos, int yPos, String displayString, boolean isChecked,
                                            GuiCheckBox.IPressable handler) {
        GuiCheckBox checkBox = new GuiCheckBox(xPos, yPos, GuiLangKeys.translateString(displayString), isChecked, handler);

        return this.addRenderableWidget(checkBox);
    }

    public GuiSlider createAndAddSlider(int xPos, int yPos, int width, int height, String prefix, String suf,
                                        double minVal, double maxVal, double currentVal, boolean showDec, boolean drawStr,
                                        Button.OnPress handler) {
        GuiSlider slider = new GuiSlider(xPos, yPos, width, height, Utils.createTextComponent(prefix), Utils.createTextComponent(suf), minVal, maxVal, currentVal, showDec,
                drawStr, handler);

        return this.addRenderableWidget(slider);
    }

    protected void drawControlBackground(GuiGraphics guiGraphics, int grayBoxX, int grayBoxY, int width, int height) {
        GuiUtils.bindAndDrawScaledTexture(
                this.backgroundTextures,
                guiGraphics,
                grayBoxX,
                grayBoxY,
                width,
                height,
                width,
                height,
                width,
                height);
    }

    protected void drawControlLeftPanel(GuiGraphics guiGraphics, int grayBoxX, int grayBoxY, int width, int height) {
        GuiUtils.drawContinuousTexturedBox(
                this.leftPanelTexture,
                grayBoxX,
                grayBoxY,
                0,
                0,
                width,
                height,
                89,
                233,
                2,
                2,
                4,
                4,
                0);
    }

    protected void drawControlRightPanel(GuiGraphics guiGraphics, int grayBoxX, int grayBoxY, int width, int height) {
        GuiUtils.drawContinuousTexturedBox(
                this.rightPanelTexture,
                grayBoxX,
                grayBoxY,
                0,
                0,
                width,
                height,
                89,
                233,
                2,
                2,
                4,
                4,
                0);
    }

    protected void drawStandardControlBoxAndImage(GuiGraphics guiGraphics, ResourceLocation imageLocation, int x, int y, int mouseX, int mouseY, float partialTicks) {
        this.renderBackground(guiGraphics);
        this.drawControlBackground(guiGraphics, x, y, this.imagePanelWidth, this.imagePanelHeight);

        if (imageLocation != null) {
            int imagePanelMiddle = this.imagePanelWidth / 2;

            int middleOfImage = this.shownImageWidth / 2;
            int imagePos = x + (imagePanelMiddle - middleOfImage - 5);

            GuiUtils.renderProvidedTexture(
                    guiGraphics,
                    imageLocation,
                    imagePos,
                    y + 10,
                    1,
                    this.shownImageWidth,
                    this.shownImageHeight,
                    this.shownImageWidth,
                    this.shownImageHeight);
        }
    }

    protected void renderButtons(GuiGraphics guiGraphics, int mouseX, int mouseY) {
        for (GuiEventListener button : this.children()) {
            if (button instanceof AbstractWidget currentButton) {
                if (currentButton.visible) {
                    currentButton.render(guiGraphics, mouseX, mouseY, minecraft.getFrameTime());
                }
            }
        }
    }

    /**
     * Gets the adjusted x/y coordinates for the topleft most part of the screen.
     *
     * @return A new tuple containing the x/y coordinates.
     */
    protected IntIntPair getAdjustedXYValue() {
        return IntIntPair.of(this.getCenteredXAxis() - this.modifiedInitialXAxis, this.getCenteredYAxis() - this.modifiedInitialYAxis);
    }

    /**
     * Draws a string on the screen.
     *
     * @param text  The text to draw.
     * @param x     The X-Coordinates of the string to start.
     * @param y     The Y-Coordinates of the string to start.
     * @param color The color of the text.
     */
    public void drawString(GuiGraphics guiGraphics, String text, float x, float y, int color) {
        guiGraphics.drawString(this.font, text, Float.floatToIntBits(x), Float.floatToIntBits(y), color);
    }

    /**
     * Draws a string on the screen with word wrapping.
     *
     * @param str       The text to draw.
     * @param x         The X-Coordinates of the string to start.
     * @param y         The Y-Coordinates of the string to start.
     * @param wrapWidth The maximum width before wrapping begins.
     * @param textColor The color of the text.
     */
    public void drawSplitString(GuiGraphics guiGraphics, String str, int x, int y, int wrapWidth, int textColor) {
        guiGraphics.drawWordWrap(this.font, FormattedText.of(str),x,y, wrapWidth, textColor  );
    }
    /**
     * Closes the current screen.
     */
    public void closeScreen() {minecraft.setScreen(null);}
    /**
     * This event is called when a particular button is clicked.
     *
     * @param button The button which was clicked.
     */
    public abstract void buttonClicked(AbstractButton button);

    protected abstract void preButtonRender(GuiGraphics guiGraphics, int x, int y, int mouseX, int mouseY, float partialTicks);

    protected abstract void postButtonRender(GuiGraphics guiGraphics, int x, int y, int mouseX, int mouseY, float partialTicks);
}
