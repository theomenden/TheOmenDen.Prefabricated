package com.wuest.prefab.config;

import com.wuest.prefab.base.BaseConfig;
import net.minecraft.nbt.NbtCompound;

public class StructureScannerConfig extends BaseConfig {
    public boolean some_value = false;

    @Override
    public void WriteToNBTCompound(NbtCompound compound) {
        compound.putBoolean("some_value", this.some_value);
    }

    @Override
    public StructureScannerConfig ReadFromCompoundNBT(NbtCompound compound) {
        if(compound.contains("some_value")) {
            this.some_value = compound.getBoolean("some_value");
        }

        return this;
    }
}
