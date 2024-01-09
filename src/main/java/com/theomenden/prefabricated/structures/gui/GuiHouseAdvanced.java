package com.theomenden.prefabricated.structures.gui;

import com.theomenden.prefabricated.ClientModRegistry;
import com.theomenden.prefabricated.Prefab;
import com.theomenden.prefabricated.config.ModConfiguration;
import com.theomenden.prefabricated.gui.GuiLangKeys;
import com.theomenden.prefabricated.gui.GuiUtils;
import com.theomenden.prefabricated.gui.controls.ExtendedButton;
import com.theomenden.prefabricated.gui.controls.GuiCheckBox;
import com.theomenden.prefabricated.structures.config.HouseAdvancedConfiguration;
import com.theomenden.prefabricated.structures.messages.StructureTagMessage;
import com.theomenden.prefabricated.structures.predefined.StructureHouseAdvanced;
import it.unimi.dsi.fastutil.ints.IntIntPair;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.AbstractButton;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.DyeColor;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * @author WuestMan
 */
public class GuiHouseAdvanced extends GuiStructure {
    protected HouseAdvancedConfiguration specificConfiguration;
    protected ModConfiguration serverConfiguration;
    private ExtendedButton btnHouseStyle;
    private GuiCheckBox btnAddMineShaft;
    private ExtendedButton btnBedColor;

    private ArrayList<HouseAdvancedConfiguration.HouseStyle> availableHouseStyles;

    public GuiHouseAdvanced() {
        super("Advanced House");

        this.structureConfiguration = StructureTagMessage.EnumStructureConfiguration.AdvancedHouse;
    }

    @Override
    public @NotNull Component getNarrationMessage() {
        return Component.translatable(GuiLangKeys.translateString(GuiLangKeys.TITLE_ADVANCED_HOUSE));
    }

    @Override
    protected void Initialize() {
        this.modifiedInitialXAxis = 215;
        this.modifiedInitialYAxis = 117;
        this.shownImageHeight = 150;
        this.shownImageWidth = 268;

        this.serverConfiguration = Prefab.serverConfiguration;
        this.configuration = this.specificConfiguration = ClientModRegistry.playerConfig.getClientConfig("Advanced Houses", HouseAdvancedConfiguration.class);
        this.configuration.pos = this.pos;

        this.availableHouseStyles = new ArrayList<>();
        HashMap<String, Boolean> houseConfigurationSettings = this.serverConfiguration.structureOptions.get("item.prefabricated.item_house_advanced");
        boolean selectedStyleInListOfAvailable = false;

        for (HouseAdvancedConfiguration.HouseStyle style : HouseAdvancedConfiguration.HouseStyle.values()) {
            if (houseConfigurationSettings.containsKey(style.getTranslationString())
                    && houseConfigurationSettings.get(style.getTranslationString())) {
                this.availableHouseStyles.add(style);

                if (this.specificConfiguration.houseStyle.getDisplayName().equals(style.getDisplayName())) {
                    selectedStyleInListOfAvailable = true;
                }
            }
        }

        if (this.availableHouseStyles.isEmpty()) {
            // There are no options. Show the no options screen.
            this.showNoOptionsScreen();
            return;
        }

        if (!selectedStyleInListOfAvailable) {
            this.specificConfiguration.houseStyle = this.availableHouseStyles.get(0);
        }

        this.selectedStructure = StructureHouseAdvanced.CreateInstance(this.specificConfiguration.houseStyle.getStructureLocation(), StructureHouseAdvanced.class);

        // Get the upper left hand corner of the GUI box.
        IntIntPair adjustedXYValue = this.getAdjustedXYValue();
        int grayBoxX = adjustedXYValue.leftInt();
        int grayBoxY = adjustedXYValue.rightInt();

        // Create the buttons.
        int yOffset = 25;

        if (this.availableHouseStyles.size() > 1) {
            this.btnHouseStyle = this.createAndAddButton(grayBoxX + 8, grayBoxY + yOffset, 90, 20, this.specificConfiguration.houseStyle.getDisplayName(), false, GuiLangKeys.translateString(GuiLangKeys.HOUSE_STYLE));
            yOffset = yOffset + 35;
        }

        this.btnBedColor = this.createAndAddDyeButton(grayBoxX + 8, grayBoxY + yOffset, 90, 20, this.specificConfiguration.bedColor, GuiLangKeys.translateString(GuiLangKeys.GUI_STRUCTURE_BED_COLOR));
        yOffset = yOffset + 94;

        this.btnAddMineShaft = this.createAndAddCheckBox(grayBoxX + 8, grayBoxY + yOffset, GuiLangKeys.HOUSE_BUILD_MINESHAFT, this.specificConfiguration.addMineshaft, this::buttonClicked);

        // Create the standard buttons.
        this.btnVisualize = this.createAndAddCustomButton(grayBoxX + 24, grayBoxY + 177, 90, 20, GuiLangKeys.GUI_BUTTON_PREVIEW);
        this.btnBuild = this.createAndAddCustomButton(grayBoxX + 310, grayBoxY + 177, 90, 20, GuiLangKeys.GUI_BUTTON_BUILD);
        this.btnCancel = this.createAndAddButton(grayBoxX + 154, grayBoxY + 177, 90, 20, GuiLangKeys.GUI_BUTTON_CANCEL);
    }

    @Override
    protected void preButtonRender(GuiGraphics guiGraphics, int x, int y, int mouseX, int mouseY, float partialTicks) {
        int imagePanelUpperLeft = x + 136;
        int imagePanelWidth = 285;
        int imagePanelMiddle = imagePanelWidth / 2;

        this.renderBackground(guiGraphics);

        this.drawControlLeftPanel(guiGraphics, x + 2, y + 10, 135, 190);
        this.drawControlRightPanel(guiGraphics, imagePanelUpperLeft, y + 10, imagePanelWidth, 190);

        int middleOfImage = this.shownImageWidth / 2;
        int imageLocation = imagePanelUpperLeft + (imagePanelMiddle - middleOfImage);

        GuiUtils.bindAndDrawScaledTexture(
                this.specificConfiguration.houseStyle.getHousePicture(),
                guiGraphics,
                imageLocation,
                y + 15,
                this.shownImageWidth,
                this.shownImageHeight,
                this.shownImageWidth,
                this.shownImageHeight,
                this.shownImageWidth,
                this.shownImageHeight);

        this.btnAddMineShaft.visible = this.serverConfiguration.starterHouseOptions.addMineshaft;
    }

    @Override
    protected void postButtonRender(GuiGraphics guiGraphics, int x, int y, int mouseX, int mouseY, float partialTicks) {
        // Draw the text here.
        int yOffSet = 15;

        if (this.availableHouseStyles.size() > 1) {
            this.drawString(guiGraphics, GuiLangKeys.translateString(GuiLangKeys.HOUSE_STYLE), x + 8, y + yOffSet, this.textColor);
            yOffSet = yOffSet + 35;
        }

        this.drawString(guiGraphics, GuiLangKeys.translateString(GuiLangKeys.GUI_STRUCTURE_BED_COLOR), x + 8, y + yOffSet, this.textColor);
    }

    /**
     * Called by the controls from the buttonList when activated. (Mouse pressed for buttons)
     */
    @Override
    public void buttonClicked(AbstractButton button) {
        this.specificConfiguration.addMineshaft = this.btnAddMineShaft.visible && this.btnAddMineShaft.isChecked();
        this.configuration.houseFacing = minecraft.player.getDirection().getOpposite();

        this.performCancelOrBuildOrHouseFacing(button);

        if (button == this.btnHouseStyle) {
            for (int i = 0; i < this.availableHouseStyles.size(); i++) {
                HouseAdvancedConfiguration.HouseStyle option = this.availableHouseStyles.get(i);
                HouseAdvancedConfiguration.HouseStyle chosenOption = null;

                if (this.specificConfiguration.houseStyle.getDisplayName().equals(option.getDisplayName())) {
                    if (i == this.availableHouseStyles.size() - 1) {
                        // This is the last option, set the text to the first option.
                        chosenOption = this.availableHouseStyles.get(0);
                    } else {
                        chosenOption = this.availableHouseStyles.get(i + 1);
                    }
                }

                if (chosenOption != null) {
                    this.specificConfiguration.houseStyle = chosenOption;
                    this.selectedStructure = StructureHouseAdvanced.CreateInstance(this.specificConfiguration.houseStyle.getStructureLocation(), StructureHouseAdvanced.class);
                    GuiUtils.setButtonText(btnHouseStyle, this.specificConfiguration.houseStyle.getDisplayName());
                    break;
                }
            }
        } else if (button == this.btnVisualize) {
            this.performPreview();
        } else if (button == this.btnBedColor) {
            this.specificConfiguration.bedColor = DyeColor.byId(this.specificConfiguration.bedColor.getId() + 1);
            GuiUtils.setButtonText(btnBedColor, GuiLangKeys.translateDye(this.specificConfiguration.bedColor));
        }
    }
}
