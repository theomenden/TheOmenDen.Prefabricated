package com.theomenden.prefabricated.base;

import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;

/**
 * The base block for any block associated with a tile entity.
 *
 * @author WuestMan
 */
public abstract class TileBlockBase<T extends TileEntityBase> extends BaseEntityBlock {

    /**
     * Initializes a new instance of the TileBlockBase class.
     */
    public TileBlockBase(BlockBehaviour.Properties properties) {
        super(properties);
    }

    @Override
    public @NotNull RenderShape getRenderShape(BlockState state) {
        return RenderShape.MODEL;
    }
}