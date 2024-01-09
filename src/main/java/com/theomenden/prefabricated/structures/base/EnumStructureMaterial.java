package com.theomenden.prefabricated.structures.base;

import com.theomenden.prefabricated.ModRegistry;
import com.theomenden.prefabricated.gui.GuiLangKeys;
import lombok.Getter;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;

/**
 * This enum is used to list the names of the structure materials.
 *
 * @author WuestMan
 */
@SuppressWarnings({"unused", "SpellCheckingInspection"})
public enum EnumStructureMaterial {
	Cobblestone("prefabricated.gui.material.cobble_stone", Blocks.COBBLESTONE.defaultBlockState(), Blocks.COBBLESTONE_STAIRS.defaultBlockState(), Blocks.COBBLESTONE_WALL.defaultBlockState(), 0),
	Stone("prefabricated.gui.material.stone", Blocks.STONE.defaultBlockState(), Blocks.STONE_STAIRS.defaultBlockState(), Blocks.COBBLESTONE_WALL.defaultBlockState(), 1),
	StoneBrick("prefabricated.gui.material.stone_brick", Blocks.STONE_BRICKS.defaultBlockState(), Blocks.STONE_BRICK_STAIRS.defaultBlockState(), Blocks.STONE_BRICK_WALL.defaultBlockState(), 2),
	Brick("prefabricated.gui.material.brick", Blocks.BRICKS.defaultBlockState(), Blocks.BRICK_STAIRS.defaultBlockState(), Blocks.BRICK_WALL.defaultBlockState(), 3),
	ChiseledStone("prefabricated.gui.material.chiseled_stone", Blocks.CHISELED_STONE_BRICKS.defaultBlockState(), Blocks.STONE_BRICK_STAIRS.defaultBlockState(), Blocks.STONE_BRICK_WALL.defaultBlockState(), 4),
	Granite("prefabricated.gui.material.granite", Blocks.GRANITE.defaultBlockState(), Blocks.GRANITE_STAIRS.defaultBlockState(), Blocks.GRANITE_WALL.defaultBlockState(), 5),
	SmoothGranite("prefabricated.gui.material.smooth_granite", Blocks.POLISHED_GRANITE.defaultBlockState(), Blocks.POLISHED_GRANITE_STAIRS.defaultBlockState(), Blocks.GRANITE_WALL.defaultBlockState(), 6),
	Andesite("prefabricated.gui.material.andesite", Blocks.ANDESITE.defaultBlockState(), Blocks.ANDESITE_STAIRS.defaultBlockState(), Blocks.ANDESITE_WALL.defaultBlockState(), 7),
	SmoothAndesite("prefabricated.gui.material.smooth_andesite", Blocks.POLISHED_ANDESITE.defaultBlockState(), Blocks.POLISHED_ANDESITE_STAIRS.defaultBlockState(), Blocks.ANDESITE_WALL.defaultBlockState(), 8),
	Diorite("prefabricated.gui.material.diorite", Blocks.DIORITE.defaultBlockState(), Blocks.DIORITE_STAIRS.defaultBlockState(), Blocks.DIORITE_WALL.defaultBlockState(), 9),
	SmoothDiorite("prefabricated.gui.material.smooth_diorite", Blocks.POLISHED_DIORITE.defaultBlockState(), Blocks.POLISHED_DIORITE_STAIRS.defaultBlockState(), Blocks.DIORITE_WALL.defaultBlockState(), 10),
	Oak("prefabricated.wall.block.type.oak", Blocks.OAK_PLANKS.defaultBlockState(), Blocks.OAK_STAIRS.defaultBlockState(), Blocks.OAK_FENCE.defaultBlockState(), 11),
	Spruce("prefabricated.wall.block.type.spruce", Blocks.SPRUCE_PLANKS.defaultBlockState(), Blocks.SPRUCE_STAIRS.defaultBlockState(), Blocks.SPRUCE_FENCE.defaultBlockState(), 12),
	Birch("prefabricated.wall.block.type.birch", Blocks.BIRCH_PLANKS.defaultBlockState(), Blocks.BIRCH_STAIRS.defaultBlockState(), Blocks.BIRCH_FENCE.defaultBlockState(), 13),
	Jungle("prefabricated.wall.block.type.jungle", Blocks.JUNGLE_PLANKS.defaultBlockState(), Blocks.JUNGLE_STAIRS.defaultBlockState(), Blocks.JUNGLE_FENCE.defaultBlockState(), 14),
	Acacia("prefabricated.wall.block.type.acacia", Blocks.ACACIA_PLANKS.defaultBlockState(), Blocks.ACACIA_STAIRS.defaultBlockState(), Blocks.ACACIA_FENCE.defaultBlockState(), 15),
	DarkOak("prefabricated.wall.block.type.darkoak", Blocks.DARK_OAK_PLANKS.defaultBlockState(), Blocks.DARK_OAK_STAIRS.defaultBlockState(), Blocks.DARK_OAK_FENCE.defaultBlockState(), 16),
	SandStone("prefabricated.ceiling.block.type.sand", Blocks.SANDSTONE.defaultBlockState(), Blocks.SANDSTONE_STAIRS.defaultBlockState(), Blocks.SANDSTONE_WALL.defaultBlockState(), 17),
	RedSandStone("prefabricated.gui.material.red_sandstone", Blocks.RED_SANDSTONE.defaultBlockState(), Blocks.RED_SANDSTONE_STAIRS.defaultBlockState(), Blocks.RED_SANDSTONE_WALL.defaultBlockState(), 18),
	Glass("block.minecraft.glass", Blocks.GLASS.defaultBlockState(), ModRegistry.GlassStairs.defaultBlockState(), Blocks.GLASS_PANE.defaultBlockState(), 19);

	@Getter
	private final String name;
	@Getter
	private final BlockState blockType;
	@Getter
	private final int number;
	private final BlockState stairsState;

	private final BlockState wallState;

	EnumStructureMaterial(String name, BlockState blockType, BlockState stairsState, BlockState wallState, int number) {
		this.name = name;
		this.blockType = blockType;
		this.number = number;
		this.stairsState = stairsState;
		this.wallState = wallState;
	}

	public static EnumStructureMaterial getMaterialByNumber(int number) {
		for (EnumStructureMaterial material : EnumStructureMaterial.values()) {
			if (material.getNumber() == number) {
				return material;
			}
		}

		return EnumStructureMaterial.Cobblestone;
	}

	public String getTranslatedName() {
		return GuiLangKeys.translateString(this.name);
	}

	public BlockState getStairsBlock() {
		return this.stairsState;
	}

	public BlockState getWallBlock() {
		return this.wallState;
	}
}
