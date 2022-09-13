package com.wuest.prefab.blocks.entities;

import com.wuest.prefab.ModRegistry;
import com.wuest.prefab.base.TileEntityBase;
import com.wuest.prefab.config.LightSwitchConfig;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;

import java.util.Objects;

public class LightSwitchBlockEntity extends TileEntityBase<LightSwitchConfig> {
    public LightSwitchBlockEntity(BlockPos pos, BlockState state) {
        super(ModRegistry.LightSwitchEntityType, pos, state);
    }

    @Override
    public void setLevel(Level level) {
        super.setLevel(level);

        if (!level.isClientSide) {
            int hash = Objects.hash(level, this.worldPosition);

            ModRegistry.serverModRegistries.getLightSwitchRegistry().register(hash, this.worldPosition);
        }
    }
}
