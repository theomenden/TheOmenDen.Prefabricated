package com.theomenden.prefabricated.structures.base;

import com.theomenden.prefabricated.gui.GuiLangKeys;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;

/**
 * This enum is used to list the names of the structure materials.
 *
 * @author WuestMan
 */
@SuppressWarnings("SpellCheckingInspection")
public enum EnumStairsMaterial {
	Brick("prefabricated.gui.material.brick", Blocks.BRICK_STAIRS.defaultBlockState()),
	Cobblestone("prefabricated.gui.material.cobble_stone", Blocks.COBBLESTONE_STAIRS.defaultBlockState()),
	StoneBrick("prefabricated.gui.material.stone_brick", Blocks.STONE_BRICK_STAIRS.defaultBlockState()),
	Granite("prefabricated.gui.material.granite", Blocks.POLISHED_GRANITE_STAIRS.defaultBlockState()),
	Andesite("prefabricated.gui.material.andesite", Blocks.POLISHED_ANDESITE_STAIRS.defaultBlockState()),
	Diorite("prefabricated.gui.material.diorite", Blocks.POLISHED_DIORITE_STAIRS.defaultBlockState()),
	Oak("prefabricated.wall.block.type.oak", Blocks.OAK_STAIRS.defaultBlockState()),
	Spruce("prefabricated.wall.block.type.spruce", Blocks.SPRUCE_STAIRS.defaultBlockState()),
	Birch("prefabricated.wall.block.type.birch", Blocks.BIRCH_STAIRS.defaultBlockState()),
	Jungle("prefabricated.wall.block.type.jungle", Blocks.JUNGLE_STAIRS.defaultBlockState()),
	Acacia("prefabricated.wall.block.type.acacia", Blocks.ACACIA_STAIRS.defaultBlockState()),
	DarkOak("prefabricated.wall.block.type.darkoak", Blocks.DARK_OAK_STAIRS.defaultBlockState()),
	SandStone("prefabricated.ceiling.block.type.sand", Blocks.SANDSTONE_STAIRS.defaultBlockState()),
	RedSandStone("prefabricated.gui.material.red_sandstone", Blocks.RED_SANDSTONE_STAIRS.defaultBlockState());

	public final BlockState stairsState;
	private final String name;

	EnumStairsMaterial(String name, BlockState stairsState) {
		this.name = name;
		this.stairsState = stairsState;
	}

	public static EnumStairsMaterial getByOrdinal(int ordinal) {
		for (EnumStairsMaterial value : EnumStairsMaterial.values()) {
			if (value.ordinal() == ordinal) {
				return value;
			}
		}

		return EnumStairsMaterial.Cobblestone;
	}

	public String getTranslatedName() {
		return GuiLangKeys.translateString(this.name);
	}

	public BlockState getFullBlock() {
        return switch (this) {
            case Acacia -> Blocks.ACACIA_SLAB.defaultBlockState();
            case Andesite -> Blocks.POLISHED_ANDESITE_SLAB.defaultBlockState();
            case Birch -> Blocks.BIRCH_SLAB.defaultBlockState();
            case Cobblestone -> Blocks.COBBLESTONE_SLAB.defaultBlockState();
            case DarkOak -> Blocks.DARK_OAK_SLAB.defaultBlockState();
            case Diorite -> Blocks.POLISHED_DIORITE_SLAB.defaultBlockState();
            case Granite -> Blocks.POLISHED_GRANITE_SLAB.defaultBlockState();
            case Jungle -> Blocks.JUNGLE_SLAB.defaultBlockState();
            case Oak -> Blocks.OAK_SLAB.defaultBlockState();
            case Spruce -> Blocks.SPRUCE_SLAB.defaultBlockState();
            case StoneBrick -> Blocks.STONE_BRICK_SLAB.defaultBlockState();
            case Brick -> Blocks.BRICK_SLAB.defaultBlockState();
            case SandStone -> Blocks.SANDSTONE_SLAB.defaultBlockState();
            case RedSandStone -> Blocks.RED_SANDSTONE_SLAB.defaultBlockState();
            default -> Blocks.STONE_SLAB.defaultBlockState();
        };
	}
}
