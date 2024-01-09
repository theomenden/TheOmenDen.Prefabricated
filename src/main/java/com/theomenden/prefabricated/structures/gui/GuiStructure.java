package com.theomenden.prefabricated.structures.gui;

import com.theomenden.prefabricated.ModRegistry;
import com.theomenden.prefabricated.Prefab;
import com.theomenden.prefabricated.Utils;
import com.theomenden.prefabricated.gui.GuiBase;
import com.theomenden.prefabricated.gui.GuiLangKeys;
import com.theomenden.prefabricated.structures.base.Structure;
import com.theomenden.prefabricated.structures.config.StructureConfiguration;
import com.theomenden.prefabricated.structures.messages.StructureTagMessage;
import com.theomenden.prefabricated.structures.render.StructureRenderHandler;
import it.unimi.dsi.fastutil.ints.IntIntPair;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.AbstractButton;
import net.minecraft.client.gui.components.Button;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Player;

/**
 * Generic GUI for all structures.
 *
 * @author WuestMan
 */
public abstract class GuiStructure extends GuiBase {
    public BlockPos pos;
    public StructureTagMessage.EnumStructureConfiguration structureConfiguration;
    protected Player player;
    protected Button btnCancel;
    protected Button btnBuild;
    protected Button btnVisualize;
    protected ResourceLocation structureImageLocation;
    protected StructureConfiguration configuration;
    protected Structure selectedStructure;
    protected Direction structureFacing;

    public GuiStructure(String title) {
        super(title);
    }

    @Override
    public void init() {
        this.player = minecraft.player;
        this.structureFacing = this.player.getDirection().getOpposite();
        this.Initialize();
    }

    /**
     * This method is used to initialize GUI specific items.
     */
    @Override
    protected void Initialize() {
        super.Initialize();
    }

    protected void InitializeStandardButtons() {
        // Get the upper left hand corner of the GUI box.
        IntIntPair adjustedXYValue = this.getAdjustedXYValue();
        int grayBoxX = adjustedXYValue.leftInt();
        int grayBoxY = adjustedXYValue.rightInt();

        // Create the buttons.
        this.btnVisualize = this.createAndAddCustomButton(grayBoxX + 113, grayBoxY + 167, 90, 20, GuiLangKeys.GUI_BUTTON_PREVIEW);
        this.btnBuild = this.createAndAddCustomButton(grayBoxX + 215, grayBoxY + 167, 90, 20, GuiLangKeys.GUI_BUTTON_BUILD);
        this.btnCancel = this.createAndAddButton(grayBoxX + 10, grayBoxY + 167, 90, 20, GuiLangKeys.GUI_BUTTON_CANCEL);
    }

    public void checkVisualizationSetting() {
        if (!Prefab.serverConfiguration.enableStructurePreview) {
            this.btnVisualize.visible = false;
        }
    }

    @Override
    public void render(GuiGraphics guiGraphics, int x, int y, float f) {
        IntIntPair adjustedXYValue = this.getAdjustedXYValue();

        this.preButtonRender(guiGraphics, adjustedXYValue.leftInt(), adjustedXYValue.rightInt(), x, y, f);

        this.renderButtons(guiGraphics, x, y);

        this.postButtonRender(guiGraphics, adjustedXYValue.leftInt(), adjustedXYValue.rightInt(), x, y, f);

        if (this.btnVisualize != null) {
            this.checkVisualizationSetting();
        }
    }

    @Override
    protected void preButtonRender(GuiGraphics guiGraphics, int x, int y, int mouseX, int mouseY, float partialTicks) {
        this.drawStandardControlBoxAndImage(guiGraphics, this.structureImageLocation, x, y, mouseX, mouseY, partialTicks);
    }

    @Override
    protected void postButtonRender(GuiGraphics guiGraphics, int x, int y, int mouseX, int mouseY, float partialTicks) {
    }

    /**
     * Called by the controls from the buttonList when activated. (Mouse pressed for buttons)
     */
    protected void performCancelOrBuildOrHouseFacing(AbstractButton button) {
        if (button == this.btnCancel) {
            this.closeScreen();
        } else if (button == this.btnBuild) {
            FriendlyByteBuf messagePacket = Utils.createStructureMessageBuffer(this.configuration.WriteToCompoundTag(), this.structureConfiguration);
            ClientPlayNetworking.send(ModRegistry.StructureBuild, messagePacket);

            this.closeScreen();
        }
    }

    protected void performPreview() {
        StructureRenderHandler.setStructure(this.selectedStructure, this.configuration);
        this.closeScreen();
    }

    protected void showNoOptionsScreen() {
        minecraft.setScreen(new GuiNoOptions());
    }
}
