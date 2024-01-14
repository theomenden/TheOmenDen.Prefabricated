package com.theomenden.prefabricated.blocks.entities;

import com.theomenden.prefabricated.ModRegistry;
import com.theomenden.prefabricated.base.TileEntityBase;
import com.theomenden.prefabricated.config.LightSwitchConfig;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;

public final class LightSwitchBlockEntity extends TileEntityBase<LightSwitchConfig> {
    public LightSwitchBlockEntity(BlockPos pos, BlockState state) {
        super(ModRegistry.LightSwitchEntityType, pos, state);
    }

    @Override
    public void setLevel(Level level) {
        super.setLevel(level);

        if (!level.isClientSide) {
            ModRegistry.serverModRegistries.getLightSwitchRegistry().register(level, this.worldPosition);
        }
    }
}
