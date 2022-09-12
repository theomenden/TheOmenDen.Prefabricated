package com.wuest.prefab.config;

import com.wuest.prefab.base.BaseConfig;
import net.minecraft.nbt.CompoundTag;

public class LightSwitchConfig extends BaseConfig {
    @Override
    public void WriteToNBTCompound(CompoundTag compound) {

    }

    @Override
    public <T extends BaseConfig> T ReadFromCompoundNBT(CompoundTag compound) {
        return (T) this;
    }
}
