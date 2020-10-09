package com.wuest.prefab.blocks;

import com.wuest.prefab.ModRegistry;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.block.*;
import net.minecraft.block.enums.SlabType;
import net.minecraft.tag.BlockTags;
import net.minecraft.tag.Tag;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.Direction;

/**
 * This class allows custom stairs blocks to be created.
 *
 * @author Brian
 */
public class BlockGlassStairs extends StairsBlock {
	public BlockGlassStairs(BlockState state, AbstractBlock.Settings properties) {
		super(state, properties);
	}

	@Override
	@Environment(EnvType.CLIENT)
	public boolean isSideInvisible(BlockState state, BlockState adjacentBlockState, Direction side) {
		Tag<Block> tags = BlockTags.getTagGroup().getTag(new Identifier("c", "glass"));
		Block adjacentBlock = adjacentBlockState.getBlock();

		return tags.contains(adjacentBlock) || adjacentBlock == this
				|| (adjacentBlock == ModRegistry.GlassSlab
				&& adjacentBlockState.get(SlabBlock.TYPE) == SlabType.DOUBLE);
	}
}
