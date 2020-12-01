package com.wuest.prefab.structures.predefined;

import com.wuest.prefab.Prefab;
import com.wuest.prefab.structures.base.BuildClear;
import com.wuest.prefab.structures.base.BuildingMethods;
import com.wuest.prefab.structures.base.Structure;
import com.wuest.prefab.structures.config.BulldozerConfiguration;
import net.fabricmc.fabric.impl.tool.attribute.ToolManagerImpl;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.FluidBlock;
import net.minecraft.entity.decoration.AbstractDecorationEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;

import java.util.ArrayList;

/**
 * @author WuestMan
 */
public class StructureBulldozer extends Structure {

	/**
	 * Initializes a new instance of the {@link StructureBulldozer} class.
	 */
	public StructureBulldozer() {
		BuildClear clearedSpace = new BuildClear();
		clearedSpace.getShape().setDirection(Direction.SOUTH);
		clearedSpace.getShape().setHeight(15);
		clearedSpace.getShape().setLength(16);
		clearedSpace.getShape().setWidth(16);
		clearedSpace.getStartingPosition().setSouthOffset(1);
		clearedSpace.getStartingPosition().setEastOffset(8);
		clearedSpace.getStartingPosition().setHeightOffset(1);

		this.setClearSpace(clearedSpace);
		this.setBlocks(new ArrayList<>());
	}

	/**
	 * This method is to process before a clear space block is set to air.
	 *
	 * @param pos The block position being processed.
	 */
	@Override
	public void BeforeClearSpaceBlockReplaced(BlockPos pos) {
		BlockState state = this.world.getBlockState(pos);
		BulldozerConfiguration configuration = (BulldozerConfiguration) this.configuration;

		// Only harvest up to diamond level and non-indestructable blocks.
		//Tag<Item> blah = new Tag.Identified<Item>();
		//ToolManagerImpl.entry(state.getBlock()).getMiningLevel()
		boolean pickAxeEffective = ToolManagerImpl.handleIsEffectiveOnIgnoresVanilla(state, new ItemStack(Items.DIAMOND_PICKAXE), null, false);
		boolean axeEffective = ToolManagerImpl.handleIsEffectiveOnIgnoresVanilla(state, new ItemStack(Items.DIAMOND_AXE), null, false);
		boolean shovelEffective = ToolManagerImpl.handleIsEffectiveOnIgnoresVanilla(state, new ItemStack(Items.DIAMOND_SHOVEL), null, false);

		if (!configuration.creativeMode && Prefab.serverConfiguration.allowBulldozerToCreateDrops && (pickAxeEffective || axeEffective || shovelEffective) && state.getHardness(world, pos) >= 0.0f) {
			Block.dropStacks(state, this.world, pos);
		}

		if (configuration.creativeMode && state.getBlock() instanceof FluidBlock) {
			// This is a fluid block, replace it with stone so it can be cleared.
			BuildingMethods.ReplaceBlock(this.world, pos, Blocks.STONE);
		}
	}

	@Override
	public void BeforeHangingEntityRemoved(AbstractDecorationEntity hangingEntity) {
		// Only generate drops for this hanging entity if the bulldozer allows it.
		// By default the base class doesn't allow hanging entities to generate drops.
		if (Prefab.serverConfiguration.allowBulldozerToCreateDrops) {
			hangingEntity.onBreak(null);
		}
	}
}