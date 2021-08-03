package com.wuest.prefab.blocks;

import com.wuest.prefab.ModRegistry;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;

import java.util.Random;

public interface IGrassSpreadable {

    /**
     * Determines if grass should spread to this block.
     * @param state The state of the current block.
     * @param worldIn The server world the block resides in.
     * @param pos The position of the block.
     * @param random The random value used for checking.
     */
    default void DetermineGrassSpread(BlockState state, ServerLevel worldIn, BlockPos pos, Random random) {
        if (!worldIn.isClientSide) {
            if (worldIn.getBrightness(pos.above()) >= 9) {
                for (int i = 0; i < 4; ++i) {
                    BlockPos blockpos = pos.offset(random.nextInt(3) - 1, random.nextInt(5) - 3, random.nextInt(3) - 1);

                    if (blockpos.getY() >= 0 && blockpos.getY() < 256 && !worldIn.isLoaded(blockpos)) {
                        return;
                    }

                    BlockState iblockstate1 = worldIn.getBlockState(blockpos);

                    if ((iblockstate1.getBlock() == Blocks.GRASS_BLOCK
                            || iblockstate1.getBlock() == ModRegistry.GrassStairs
                            || iblockstate1.getBlock() == ModRegistry.GrassWall
                            || iblockstate1.getBlock() == ModRegistry.GrassSlab)
                            && worldIn.getBrightness(blockpos.above()) >= 4) {

                        BlockState grassState = this.getGrassBlockState(state);

                        worldIn.setBlock(pos, grassState, 3);
                    }
                }
            }
        }
    }

    /**
     * Gets the block state of the associated grass block.
     * @param originalState The original non-grass block
     * @return A block state for the new grass.
     */
    BlockState getGrassBlockState(BlockState originalState);
}
