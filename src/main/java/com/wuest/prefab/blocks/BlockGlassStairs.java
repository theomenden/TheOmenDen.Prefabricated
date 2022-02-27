package com.wuest.prefab.blocks;

import com.wuest.prefab.ModRegistry;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.core.Direction;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.Tag;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SlabBlock;
import net.minecraft.world.level.block.StairBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.SlabType;

/**
 * This class allows custom stairs blocks to be created.
 *
 * @author Brian
 */
public class BlockGlassStairs extends StairBlock {
    public BlockGlassStairs(BlockState state, Block.Properties properties) {
        super(state, properties);
    }

    @Override
    @Environment(EnvType.CLIENT)
    public boolean skipRendering(BlockState state, BlockState adjacentBlockState, Direction side) {
        // TODO: Figure out how to get this better
        TagKey<Block> tags = TagKey.create(Registry.BLOCK_REGISTRY, new ResourceLocation("c", "glass"));

        Block adjacentBlock = adjacentBlockState.getBlock();
        ResourceLocation location = Registry.BLOCK.getKey(adjacentBlock);
        boolean foundBlock = false;

        for (Holder<Block> blockHolder : Registry.BLOCK.getTagOrEmpty(tags)) {
            if (blockHolder.is(location)) {
                foundBlock = true;
                break;
            }
        }

        return foundBlock || adjacentBlock == this
                || (adjacentBlock == ModRegistry.GlassSlab
                && adjacentBlockState.getValue(SlabBlock.TYPE) == SlabType.DOUBLE);
    }
}
