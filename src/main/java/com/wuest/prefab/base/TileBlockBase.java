package com.wuest.prefab.base;

import com.wuest.prefab.Prefab;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.BlockRenderType;
import net.minecraft.block.BlockState;
import net.minecraft.block.BlockWithEntity;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityTicker;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.apache.logging.log4j.Level;
import org.jetbrains.annotations.Nullable;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * The base block for any block associated with a tile entity.
 *
 * @author WuestMan
 */
public abstract class TileBlockBase<T extends TileEntityBase> extends BlockWithEntity {

    /**
     * Initializes a new instance of the TileBlockBase class.
     */
    public TileBlockBase(AbstractBlock.Settings properties) {
        super(properties);
    }

    @Override
	public BlockRenderType getRenderType(BlockState state) {
		return BlockRenderType.MODEL;
	}
}