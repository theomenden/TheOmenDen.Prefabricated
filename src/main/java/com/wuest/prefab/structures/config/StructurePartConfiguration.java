package com.wuest.prefab.structures.config;

import com.wuest.prefab.ModRegistry;
import com.wuest.prefab.Prefab;
import com.wuest.prefab.structures.base.EnumStairsMaterial;
import com.wuest.prefab.structures.base.EnumStructureMaterial;
import com.wuest.prefab.structures.predefined.StructurePart;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.player.Player;

/**
 * @author WuestMan
 */
public class StructurePartConfiguration extends StructureConfiguration {
	/**
	 * Determines the type of material to build the part with.
	 */
	public EnumStructureMaterial partMaterial;

	/**
	 * Determines the material used for stairs and roofs.
	 */
	public EnumStairsMaterial stairsMaterial;

	/**
	 * The height for this non-stairs style.
	 */
	public int generalWidth;

	/**
	 * The width for this non-stairs style.
	 */
	public int generalHeight;

	/**
	 * The width of stairs when the style is set to Stairs.
	 */
	public int stairWidth;

	/**
	 * The height of the stairs when the style is set to Stairs.
	 */
	public int stairHeight;

	/**
	 * The style of this part.
	 */
	public EnumStyle style;

	/**
	 * Initializes any properties for this class.
	 */
	@Override
	public void Initialize() {
		super.Initialize();

		this.partMaterial = EnumStructureMaterial.Cobblestone;
		this.stairsMaterial = EnumStairsMaterial.Cobblestone;
		this.style = EnumStyle.DoorWay;
		this.stairHeight = 3;
		this.stairWidth = 2;
		this.generalHeight = 3;
		this.generalWidth = 3;
	}

	/**
	 * Custom method to read the CompoundNBT message.
	 *
	 * @param messageTag The message to create the configuration from.
	 * @return An new configuration object with the values derived from the CompoundNBT.
	 */
	@Override
	public StructurePartConfiguration ReadFromCompoundNBT(CompoundTag messageTag) {
		StructurePartConfiguration config = new StructurePartConfiguration();

		return (StructurePartConfiguration) super.ReadFromCompoundNBT(messageTag, config);
	}

	@Override
	protected void ConfigurationSpecificBuildStructure(Player player, ServerLevel world, BlockPos hitBlockPos) {
		StructurePart structure = StructurePart.CreateInstance();

		if (structure.BuildStructure(this, world, hitBlockPos, Direction.NORTH, player)) {
			this.DamageHeldItem(player, ModRegistry.StructurePart);
		}
	}

	/**
	 * Custom method which can be overridden to write custom properties to the tag.
	 *
	 * @param tag The CompoundNBT to write the custom properties too.
	 * @return The updated tag.
	 */
	@Override
	protected CompoundTag CustomWriteToCompoundNBT(CompoundTag tag) {
		tag.putString("material", this.partMaterial.name());
		tag.putString("style", this.style.name());
		tag.putInt("stair_height", this.stairHeight);
		tag.putInt("stair_width", this.stairWidth);
		tag.putInt("general_height", this.generalHeight);
		tag.putInt("general_width", this.generalWidth);
		tag.putString("stairs_material", this.stairsMaterial.name());
		return tag;
	}

	/**
	 * Custom method to read the CompoundNBT message.
	 *
	 * @param messageTag The message to create the configuration from.
	 * @param config     The configuration to read the settings into.
	 */
	@Override
	protected void CustomReadFromNBTTag(CompoundTag messageTag, StructureConfiguration config) {
		if (messageTag.contains("material")) {
			((StructurePartConfiguration) config).partMaterial = EnumStructureMaterial.valueOf(messageTag.getString("material"));
		}

		if (messageTag.contains("stairs_material")) {
			((StructurePartConfiguration) config).stairsMaterial = EnumStairsMaterial.valueOf(messageTag.getString("stairs_material"));
		}

		if (messageTag.contains("style")) {
			((StructurePartConfiguration) config).style = EnumStyle.valueOf(messageTag.getString("style"));
		}

		if (messageTag.contains("stair_height")) {
			((StructurePartConfiguration) config).stairHeight = messageTag.getInt("stair_height");
		}

		if (messageTag.contains("stair_width")) {
			((StructurePartConfiguration) config).stairWidth = messageTag.getInt("stair_width");
		}

		if (messageTag.contains("general_height")) {
			((StructurePartConfiguration) config).generalHeight = messageTag.getInt("general_height");
		}

		if (messageTag.contains("general_width")) {
			((StructurePartConfiguration) config).generalWidth = messageTag.getInt("general_width");
		}
	}

	/**
	 * @author WuestMan
	 */
	public enum EnumStyle {
		DoorWay("prefab.gui.part_style.door_way", "textures/gui/doorway_topdown.png" ),
		Floor("prefab.gui.part_style.floor", "textures/gui/floor_topdown.png" ),
		Frame("prefab.gui.part_style.frame", "textures/gui/frame_topdown.png"),
		Gate("prefab.gui.part_style.gate", "textures/gui/gate_topdown.png"),
		Roof("prefab.gui.part_style.roof", "textures/gui/roof_topdown.png"),
		Stairs("prefab.gui.part_style.stairs", "textures/gui/stairs_topdown.png"),
		Wall("prefab.gui.part_style.wall", "textures/gui/wall_topdown.png");

		public final String translateKey;
		public final String resourceLocation;

		EnumStyle(String translateKey, String resourceLocation) {
			this.translateKey = translateKey;
			this.resourceLocation = resourceLocation;
		}

		public static EnumStyle getByOrdinal(int ordinal) {
			for (EnumStyle dimension : EnumStyle.values()) {
				if (dimension.ordinal() == ordinal) {
					return dimension;
				}
			}

			return EnumStyle.DoorWay;
		}

		public ResourceLocation getPictureLocation() {
			if (this.resourceLocation != null) {
				return new ResourceLocation(Prefab.MODID, this.resourceLocation);
			}

			return null;
		}
	}
}