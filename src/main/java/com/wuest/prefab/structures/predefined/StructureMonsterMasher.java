package com.wuest.prefab.structures.predefined;

import com.wuest.prefab.Prefab;
import com.wuest.prefab.structures.base.BuildBlock;
import com.wuest.prefab.structures.base.BuildClear;
import com.wuest.prefab.structures.base.Structure;
import com.wuest.prefab.structures.config.MonsterMasherConfiguration;
import com.wuest.prefab.structures.config.StructureConfiguration;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.WallSignBlock;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.MobSpawnerBlockEntity;
import net.minecraft.block.entity.SignBlockEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.text.LiteralText;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.World;

import java.util.ArrayList;

/**
 * @author WuestMan
 */
@SuppressWarnings({"ConstantConditions", "SpellCheckingInspection"})
public class StructureMonsterMasher extends Structure {
	public static final String ASSETLOCATION = "assets/prefab/structures/monster_masher.zip";

	private ArrayList<BlockPos> mobSpawnerPos = new ArrayList<BlockPos>();
	private BlockPos signPosition = null;

	public static void ScanStructure(World world, BlockPos originalPos, Direction playerFacing) {
		BuildClear clearedSpace = new BuildClear();
		clearedSpace.getShape().setDirection(Direction.SOUTH);
		clearedSpace.getShape().setHeight(18);
		clearedSpace.getShape().setLength(15);
		clearedSpace.getShape().setWidth(13);
		clearedSpace.getStartingPosition().setSouthOffset(1);
		clearedSpace.getStartingPosition().setEastOffset(6);

		Structure.ScanStructure(
				world,
				originalPos,
				originalPos.east(6).south(),
				originalPos.south(15).west(6).up(18),
				"..\\src\\main\\resources\\assets\\prefab\\structures\\monster_masher.zip",
				clearedSpace,
				playerFacing, false, false);
	}

	@Override
	protected Boolean CustomBlockProcessingHandled(StructureConfiguration configuration, BuildBlock block, World world, BlockPos originalPos, Direction assumedNorth,
												   Block foundBlock, BlockState blockState, PlayerEntity player) {
		Identifier foundBlockIdentifier = Registry.BLOCK.getId(foundBlock);
		if (foundBlockIdentifier.getNamespace().equals(Registry.BLOCK.getId(Blocks.WHITE_STAINED_GLASS).getNamespace())
				&& foundBlockIdentifier.getPath().endsWith("stained_glass")) {
			MonsterMasherConfiguration wareHouseConfiguration = (MonsterMasherConfiguration) configuration;

			blockState = this.getStainedGlassBlock(wareHouseConfiguration.dyeColor);
			block.setBlockState(blockState);
			this.priorityOneBlocks.add(block);

			return true;
		} else if (foundBlockIdentifier.getNamespace().equals(Registry.BLOCK.getId(Blocks.SPAWNER).getNamespace())
				&& foundBlockIdentifier.getPath().equals(Registry.BLOCK.getId(Blocks.SPAWNER).getPath())) {
			if (Prefab.serverConfiguration.includeSpawnersInMasher) {
				this.mobSpawnerPos.add(block.getStartingPosition().getRelativePosition(
						originalPos,
						this.getClearSpace().getShape().getDirection(),
						configuration.houseFacing));
			} else {
				return true;
			}
		} else if (foundBlock instanceof WallSignBlock) {
			this.signPosition = block.getStartingPosition().getRelativePosition(
					originalPos,
					this.getClearSpace().getShape().getDirection(),
					configuration.houseFacing);
		}

		return false;
	}

	/**
	 * This method is used after the main building is build for any additional structures or modifications.
	 *
	 * @param configuration The structure configuration.
	 * @param world         The current world.
	 * @param originalPos   The original position clicked on.
	 * @param assumedNorth  The assumed northern direction.
	 * @param player        The player which initiated the construction.
	 */
	@Override
	public void AfterBuilding(StructureConfiguration configuration, ServerWorld world, BlockPos originalPos, Direction assumedNorth, PlayerEntity player) {
		int monstersPlaced = 0;

		// Set the spawner.
		for (BlockPos pos : this.mobSpawnerPos) {
			BlockEntity tileEntity = world.getBlockEntity(pos);

			if (tileEntity instanceof MobSpawnerBlockEntity) {
				MobSpawnerBlockEntity spawner = (MobSpawnerBlockEntity) tileEntity;

				switch (monstersPlaced) {
					case 0: {
						// Zombie.
						spawner.getLogic().setEntityId(EntityType.ZOMBIE);
						break;
					}

					case 1: {
						// Skeleton.
						spawner.getLogic().setEntityId(EntityType.SKELETON);
						break;
					}

					case 2: {
						// Spider.
						spawner.getLogic().setEntityId(EntityType.SPIDER);
						break;
					}

					default: {
						// Creeper.
						spawner.getLogic().setEntityId(EntityType.CREEPER);
						break;
					}
				}

				monstersPlaced++;
			}
		}

		if (this.signPosition != null) {
			BlockEntity tileEntity = world.getBlockEntity(this.signPosition);

			if (tileEntity instanceof SignBlockEntity) {
				SignBlockEntity signTile = (SignBlockEntity) tileEntity;
				signTile.setTextOnRow(0, new LiteralText("Lamp On=Mobs"));

				signTile.setTextOnRow(2, new LiteralText("Lamp Off=No Mobs"));
			}
		}
	}
}
