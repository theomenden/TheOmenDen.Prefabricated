package com.theomenden.prefabricated.config;

import com.theomenden.prefabricated.base.BaseConfig;
import net.minecraft.nbt.CompoundTag;

public final class LightSwitchConfig extends BaseConfig {
    @Override
    public void WriteToNBTCompound(CompoundTag compound) {

    }

    @Override
    public <T extends BaseConfig> T ReadFromCompoundNBT(CompoundTag compound) {
        return (T) this;
    }
}
