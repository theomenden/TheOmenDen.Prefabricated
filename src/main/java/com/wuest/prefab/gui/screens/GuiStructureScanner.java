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

    private GuiCheckBox chkSomeValue;
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

        this.chkSomeValue = this.createAndAddCheckBox(adjustedXYValues.first + 15, adjustedXYValues.second + 25, "Some Value", this.config.some_value, this::buttonClicked);

        this.btnSave = this.createAndAddButton(adjustedXYValues.first + 15, adjustedXYValues.second + 50, 90, 20, "Save");
    }

    @Override
    protected void preButtonRender(MatrixStack matrixStack, int x, int y, int mouseX, int mouseY, float partialTicks) {
        this.drawControlBackground(matrixStack, x, y + 15, 350, 250);
    }

    @Override
    protected void postButtonRender(MatrixStack matrixStack, int x, int y, int mouseX, int mouseY, float partialTicks) {

    }

    @Override
    public void buttonClicked(PressableWidget button) {
        if (button == this.chkSomeValue) {
            this.config.some_value = this.chkSomeValue.isChecked();
        } else if (button == this.btnSave) {
            PacketByteBuf messagePacket = Utils.createMessageBuffer(this.config.GetCompoundNBT());
            ClientPlayNetworking.send(ModRegistry.StructureScannerSync, messagePacket);
            this.closeScreen();
        }
    }
}
