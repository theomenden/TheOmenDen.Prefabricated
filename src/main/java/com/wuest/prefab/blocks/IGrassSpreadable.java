package com.wuest.prefab.blocks;

import com.wuest.prefab.ModRegistry;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;

import java.util.Random;

public interface IGrassSpreadable {

    /**
     * Determines if grass should spread to this block.
     * @param state The state of the current block.
     * @param worldIn The server world the block resides in.
     * @param pos The position of the block.
     * @param random The random value used for checking.
     */
    default void DetermineGrassSpread(BlockState state, ServerWorld worldIn, BlockPos pos, Random random) {
        if (!worldIn.isClient) {
            if (worldIn.getLightLevel(pos.up()) >= 9) {
                for (int i = 0; i < 4; ++i) {
                    BlockPos blockpos = pos.add(random.nextInt(3) - 1, random.nextInt(5) - 3, random.nextInt(3) - 1);

                    if (blockpos.getY() >= 0 && blockpos.getY() < 256 && !worldIn.isChunkLoaded(blockpos)) {
                        return;
                    }

                    BlockState iblockstate1 = worldIn.getBlockState(blockpos);

                    if ((iblockstate1.getBlock() == Blocks.GRASS_BLOCK
                            || iblockstate1.getBlock() == ModRegistry.GrassStairs
                            || iblockstate1.getBlock() == ModRegistry.GrassWall
                            || iblockstate1.getBlock() == ModRegistry.GrassSlab)
                            && worldIn.getLightLevel(blockpos.up()) >= 4) {

                        BlockState grassState = this.getGrassBlockState(state);

                        worldIn.setBlockState(pos, grassState, 3);
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
