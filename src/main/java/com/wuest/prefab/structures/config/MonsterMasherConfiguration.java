package com.wuest.prefab.structures.config;

import com.wuest.prefab.ModRegistry;
import com.wuest.prefab.blocks.FullDyeColor;
import com.wuest.prefab.structures.predefined.StructureMonsterMasher;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.DyeColor;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;

/**
 * @author WuestMan
 */
public class MonsterMasherConfiguration extends StructureConfiguration {
	private static String dyeColorTag = "dyeColor";
	public FullDyeColor dyeColor;

	@Override
	public void Initialize() {
		super.Initialize();
		this.houseFacing = Direction.NORTH;
		this.dyeColor = FullDyeColor.CYAN;
	}

	@Override
	protected void CustomReadFromNBTTag(NbtCompound messageTag, StructureConfiguration config) {
		if (messageTag.contains(MonsterMasherConfiguration.dyeColorTag)) {
			((MonsterMasherConfiguration) config).dyeColor = FullDyeColor.byId(messageTag.getInt(MonsterMasherConfiguration.dyeColorTag));
		}
	}

	@Override
	protected NbtCompound CustomWriteToCompoundNBT(NbtCompound tag) {
		tag.putInt(MonsterMasherConfiguration.dyeColorTag, this.dyeColor.getId());

		return tag;
	}

	/**
	 * Custom method to read the CompoundNBT message.
	 *
	 * @param messageTag The message to create the configuration from.
	 * @return An new configuration object with the values derived from the CompoundNBT.
	 */
	@Override
	public MonsterMasherConfiguration ReadFromCompoundNBT(NbtCompound messageTag) {
		MonsterMasherConfiguration config = new MonsterMasherConfiguration();

		return (MonsterMasherConfiguration) super.ReadFromCompoundNBT(messageTag, config);
	}

	/**
	 * This is used to actually build the structure as it creates the structure instance and calls build structure.
	 *
	 * @param player      The player which requested the build.
	 * @param world       The world instance where the build will occur.
	 * @param hitBlockPos This hit block position.
	 */
	@Override
	protected void ConfigurationSpecificBuildStructure(PlayerEntity player, ServerWorld world, BlockPos hitBlockPos) {
		StructureMonsterMasher structure = StructureMonsterMasher.CreateInstance(StructureMonsterMasher.ASSETLOCATION, StructureMonsterMasher.class);

		if (structure.BuildStructure(this, world, hitBlockPos, Direction.NORTH, player)) {
			this.RemoveStructureItemFromPlayer(player, ModRegistry.MonsterMasher);
		}
	}
}
