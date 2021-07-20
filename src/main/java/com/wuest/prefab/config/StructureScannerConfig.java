package com.wuest.prefab.config;

import com.wuest.prefab.base.BaseConfig;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtHelper;
import net.minecraft.util.math.BlockPos;

public class StructureScannerConfig extends BaseConfig {
    public boolean some_value = false;
    public BlockPos blockPos = null;

    @Override
    public void WriteToNBTCompound(NbtCompound compound) {
        compound.putBoolean("some_value", this.some_value);

        if (this.blockPos != null) {
            compound.put("pos", NbtHelper.fromBlockPos(this.blockPos));
        }
    }

    @Override
    public StructureScannerConfig ReadFromCompoundNBT(NbtCompound compound) {
        if(compound.contains("some_value")) {
            this.some_value = compound.getBoolean("some_value");
        }

        if (compound.contains("pos")) {
            this.blockPos = NbtHelper.toBlockPos(compound.getCompound("pos"));
        }

        return this;
    }
}
