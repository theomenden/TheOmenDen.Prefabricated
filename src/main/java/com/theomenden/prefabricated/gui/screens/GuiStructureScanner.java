package com.theomenden.prefabricated.gui.screens;

import com.theomenden.prefabricated.ClientModRegistry;
import com.theomenden.prefabricated.ModRegistry;
import com.theomenden.prefabricated.Utils;
import com.theomenden.prefabricated.blocks.BlockStructureScanner;
import com.theomenden.prefabricated.config.StructureScannerConfig;
import com.theomenden.prefabricated.gui.GuiBase;
import com.theomenden.prefabricated.gui.controls.ExtendedButton;
import com.theomenden.prefabricated.gui.controls.GuiTextBox;
import it.unimi.dsi.fastutil.ints.IntIntPair;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.AbstractButton;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.world.level.Level;

import java.awt.*;


public class GuiStructureScanner extends GuiBase {
    private final BlockPos blockPos;
    private final Level world;
    private final StructureScannerConfig config;

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
    private GuiTextBox txtZipName;
    private ExtendedButton btnScan;
    private ExtendedButton btnSet;

    public GuiStructureScanner(BlockPos blockPos, Level world, StructureScannerConfig config) {
        super("Structure Scanner");

        // Always assume that this block is 1 above ground level of the structure.
        this.blockPos = blockPos.relative(Direction.DOWN);
        this.world = world;
        this.config = config;
        this.config.blockPos = this.blockPos;
    }

    @Override
    protected void Initialize() {
        super.Initialize();

        this.config.direction = this.world.getBlockState(this.blockPos.relative(Direction.UP)).getValue(BlockStructureScanner.FACING);

        IntIntPair adjustedXYValues = this.getAdjustedXYValue();
        int adjustedX = adjustedXYValues.leftInt();
        int adjustedY = adjustedXYValues.rightInt();

        // Starting position.
        this.btnStartingPositionMoveDown = this.createAndAddButton(adjustedX + 33, adjustedY + 50, 25, 20, "▲", null);

        this.btnStartingPositionMoveLeft = this.createAndAddButton(adjustedX + 20, adjustedY + 75, 25, 20, "◄", null);
        this.btnStartingPositionMoveLeft.fontScale = 2.0f;
        this.btnStartingPositionMoveRight = this.createAndAddButton(adjustedX + 47, adjustedY + 75, 25, 20, "►", null);
        this.btnStartingPositionMoveRight.fontScale = 2.0f;

        this.btnStartingPositionMoveUp = this.createAndAddButton(adjustedX + 33, adjustedY + 100, 25, 20, "▼", null);

        // Length
        this.btnLengthShrink = this.createAndAddButton(adjustedX + 120, adjustedY + 30, 25, 20, "▼", null);
        this.btnLengthGrow = this.createAndAddButton(adjustedX + 147, adjustedY + 30, 25, 20, "▲", null);

        // Width
        this.btnWidthShrink = this.createAndAddButton(adjustedX + 200, adjustedY + 30, 25, 20, "▼", null);
        this.btnWidthGrow = this.createAndAddButton(adjustedX + 227, adjustedY + 30, 25, 20, "▲", null);

        // Height
        this.btnHeightShrink = this.createAndAddButton(adjustedX + 270, adjustedY + 30, 25, 20, "▼", null);
        this.btnHeightGrow = this.createAndAddButton(adjustedX + 297, adjustedY + 30, 25, 20, "▲", null);

        // Zip Text Field
        this.txtZipName = new GuiTextBox(this.minecraft.font, adjustedX + 120, adjustedY + 75, 150, 20, Component.literal(""));

        if (this.config.structureZipName == null || this.config.structureZipName
                .trim()
                .isEmpty()) {
            this.txtZipName.setValue("Structure Name Here");
        } else {
            this.txtZipName.setValue(this.config.structureZipName);
        }

        this.txtZipName.setMaxLength(128);
        this.txtZipName.setBordered(true);
        this.txtZipName.backgroundColor = Color.WHITE.getRGB();
        this.txtZipName.setTextColor(Color.DARK_GRAY.getRGB());
        this.txtZipName.drawsTextShadow = false;
        this.addRenderableWidget(this.txtZipName);

        this.btnSet = this.createAndAddButton(adjustedX + 25, adjustedY + 140, 90, 20, "Set And Close", null);
        this.btnScan = this.createAndAddCustomButton(adjustedX + 200, adjustedY + 140, 90, 20, "Scan");
    }

    @Override
    protected void preButtonRender(GuiGraphics guiGraphics, int x, int y, int mouseX, int mouseY, float partialTicks) {
        this.drawControlBackground(guiGraphics, x, y + 15, 350, 250);
    }

    @Override
    protected void postButtonRender(GuiGraphics guiGraphics, int x, int y, int mouseX, int mouseY, float partialTicks) {

        this.drawString(guiGraphics, "Starting Position", x + 15, y + 20, this.textColor);
        this.drawString(guiGraphics, "Left: " + this.config.blocksToTheLeft + " Down: " + -this.config.blocksDown, x + 15, y + 35, this.textColor);
        this.drawString(guiGraphics, "Length: " + this.config.blocksLong, x + 120, y + 20, this.textColor);
        this.drawString(guiGraphics, "Width: " + this.config.blocksWide, x + 200, y + 20, this.textColor);
        this.drawString(guiGraphics, "Height: " + this.config.blocksTall, x + 270, y + 20, this.textColor);
        this.drawString(guiGraphics, "Name", x + 120, y + 60, this.textColor);
    }

    @Override
    public void buttonClicked(AbstractButton button) {
        this.config.structureZipName = this.txtZipName.getValue();

        if (this.config.structureZipName
                .trim()
                .isEmpty()) {
            this.config.structureZipName = "Structure Name Here";
        }

        this.config.structureZipName = this.config.structureZipName.toLowerCase().trim().replace(' ', '_');

        if (button == this.btnScan) {
            this.sendScanPacket();
            this.closeScreen();
        } else if (button == this.btnSet) {
            // Look through the list of scanners to see if it's already there, if so don't do anything.
            // Otherwise add it to the list of scanners.
            boolean foundExistingConfig = false;

            for (StructureScannerConfig config : ClientModRegistry.structureScanners) {
                if (config.blockPos.getX() == this.config.blockPos.getX()
                        && config.blockPos.getZ() == this.config.blockPos.getZ()
                        && config.blockPos.getY() == this.config.blockPos.getY()) {
                    foundExistingConfig = true;
                    break;
                }
            }

            if (!foundExistingConfig) {
                ClientModRegistry.structureScanners.add(this.config);
            }

            this.closeScreen();
        } else {
            if (button == this.btnStartingPositionMoveLeft) {
                this.config.blocksToTheLeft = this.config.blocksToTheLeft + 1;
            }

            if (button == this.btnStartingPositionMoveRight) {
                this.config.blocksToTheLeft = this.config.blocksToTheLeft - 1;
            }

            if (button == this.btnStartingPositionMoveDown) {
                this.config.blocksDown = this.config.blocksDown - 1;
            }

            if (button == this.btnStartingPositionMoveUp) {
                this.config.blocksDown = this.config.blocksDown + 1;
            }

            if (button == this.btnWidthGrow) {
                this.config.blocksWide += 1;
            }

            if (button == this.btnWidthShrink) {
                this.config.blocksWide -= 1;
            }

            if (button == this.btnLengthGrow) {
                this.config.blocksLong += 1;
            }

            if (button == this.btnLengthShrink) {
                this.config.blocksLong -= 1;
            }

            if (button == this.btnHeightGrow) {
                this.config.blocksTall += 1;
            }

            if (button == this.btnHeightShrink) {
                this.config.blocksTall -= 1;
            }

            this.sendUpdatePacket();
        }
    }

    private void sendUpdatePacket() {
        FriendlyByteBuf messagePacket = Utils.createMessageBuffer(this.config.GetCompoundNBT());
        ClientPlayNetworking.send(ModRegistry.StructureScannerSync, messagePacket);
    }

    private void sendScanPacket() {
        FriendlyByteBuf messagePacket = Utils.createMessageBuffer(this.config.GetCompoundNBT());
        ClientPlayNetworking.send(ModRegistry.StructureScannerAction, messagePacket);
    }
}
