package com.wuest.prefab.gui.screens;

import com.wuest.prefab.ModRegistry;
import com.wuest.prefab.Tuple;
import com.wuest.prefab.Utils;
import com.wuest.prefab.config.StructureScannerConfig;
import com.wuest.prefab.gui.GuiBase;
import com.wuest.prefab.gui.controls.ExtendedButton;
import com.wuest.prefab.gui.controls.GuiCheckBox;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.minecraft.client.gui.widget.PressableWidget;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class GuiStructureScanner extends GuiBase {
    private final BlockPos blockPos;
    private final World world;
    private StructureScannerConfig config;

    private ExtendedButton btnStartingPositionMoveLeft;
    private ExtendedButton btnStartingPositionMoveRight;
    private ExtendedButton btnStartingPositionMoveDown;
    private ExtendedButton btnStartingPositionMoveUp;
    private ExtendedButton btnWidthGrow;
    private ExtendedButton btnWidthShrink;
    private ExtendedButton btnLengthGrow;
    private ExtendedButton btnLengthShrink;
    private ExtendedButton btnHeightGrow;
    private ExtendedButton btnHeightShrink;
    private ExtendedButton btnSave;

    public GuiStructureScanner(BlockPos blockPos, World world, StructureScannerConfig config) {
        super("Structure Scanner");

        this.blockPos = blockPos;
        this.world = world;
        this.config = config;
        this.config.blockPos = this.blockPos;
    }

    @Override
    protected void Initialize() {
        super.Initialize();

        Tuple<Integer, Integer> adjustedXYValues = this.getAdjustedXYValue();
        int adjustedX = adjustedXYValues.first;
        int adjustedY = adjustedXYValues.second;

        // Starting position.
        this.btnStartingPositionMoveLeft = this.createAndAddButton(adjustedX + 20, adjustedY + 30, 25, 20, "◄");
        this.btnStartingPositionMoveLeft.fontScale = 2.0f;
        this.btnStartingPositionMoveRight = this.createAndAddButton(adjustedX + 47, adjustedY + 30, 25, 20, "►");
        this.btnStartingPositionMoveRight.fontScale = 2.0f;
        this.btnStartingPositionMoveDown = this.createAndAddButton(adjustedX + 20, adjustedY + 55, 25, 20, "▲");
        this.btnStartingPositionMoveUp = this.createAndAddButton(adjustedX + 47, adjustedY + 55, 25, 20, "▼");

        // Length
        this.btnLengthGrow = this.createAndAddButton(adjustedX + 120, adjustedY + 30, 25, 20, "▲");
        this.btnLengthShrink = this.createAndAddButton(adjustedX + 147, adjustedY + 30, 25, 20, "▼");

        // Width
        this.btnWidthGrow = this.createAndAddButton(adjustedX + 200, adjustedY + 30, 25, 20, "▲");
        this.btnWidthShrink = this.createAndAddButton(adjustedX + 227, adjustedY + 30, 25, 20, "▼");

        // Height
        this.btnHeightGrow = this.createAndAddButton(adjustedX + 270, adjustedY + 30, 25, 20, "▲");
        this.btnHeightShrink = this.createAndAddButton(adjustedX + 297, adjustedY + 30, 25, 20, "▼");

        this.btnSave = this.createAndAddButton(adjustedX + 15, adjustedY + 140, 90, 20, "Save");
    }

    @Override
    protected void preButtonRender(MatrixStack matrixStack, int x, int y, int mouseX, int mouseY, float partialTicks) {
        this.drawControlBackground(matrixStack, x, y + 15, 350, 250);
    }

    @Override
    protected void postButtonRender(MatrixStack matrixStack, int x, int y, int mouseX, int mouseY, float partialTicks) {
        this.drawString(matrixStack, "Starting Position", x + 15, y + 20, this.textColor);
        this.drawString(matrixStack, "Length", x + 120, y + 20, this.textColor);
        this.drawString(matrixStack, "Width", x + 200, y + 20, this.textColor);
        this.drawString(matrixStack, "Height", x + 270, y + 20, this.textColor);

        this.drawString(matrixStack, "Name", x + 120, y + 60, this.textColor);
    }

    @Override
    public void buttonClicked(PressableWidget button) {
        if (button == this.btnSave) {
            this.closeScreen();
        } else {
            if (button == this.btnStartingPositionMoveLeft) {
                this.config.blocksToTheLeft = this.config.blocksToTheLeft - 1;
            }

            if (button == this.btnStartingPositionMoveRight) {
                this.config.blocksToTheLeft = this.config.blocksToTheLeft + 1;
            }

            if (button == this.btnStartingPositionMoveDown) {
                this.config.blocksDown = this.config.blocksDown - 1;
            }

            if (button == this.btnStartingPositionMoveUp) {
                this.config.blocksDown = this.config.blocksDown + 1;
            }

            this.sendUpdatePacket();
        }
    }

    private void sendUpdatePacket() {
        PacketByteBuf messagePacket = Utils.createMessageBuffer(this.config.GetCompoundNBT());
        ClientPlayNetworking.send(ModRegistry.StructureScannerSync, messagePacket);
    }
}
