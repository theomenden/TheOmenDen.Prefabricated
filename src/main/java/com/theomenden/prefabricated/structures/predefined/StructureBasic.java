package com.theomenden.prefabricated.structures.predefined;

import com.theomenden.prefabricated.Prefab;
import com.theomenden.prefabricated.blocks.FullDyeColor;
import com.theomenden.prefabricated.structures.base.BuildBlock;
import com.theomenden.prefabricated.structures.base.BuildingMethods;
import com.theomenden.prefabricated.structures.base.Structure;
import com.theomenden.prefabricated.structures.config.BasicStructureConfiguration;
import com.theomenden.prefabricated.structures.config.BasicStructureConfiguration.EnumBasicStructureName;
import com.theomenden.prefabricated.structures.config.StructureConfiguration;
import com.theomenden.prefabricated.structures.config.enums.FarmAdvancedOptions;
import com.theomenden.prefabricated.structures.config.enums.BaseOption;
import com.theomenden.prefabricated.structures.config.enums.FarmImprovedOptions;
import it.unimi.dsi.fastutil.objects.ReferenceReferencePair;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.animal.Chicken;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.SpawnerBlockEntity;
import net.minecraft.world.level.block.state.BlockState;

import java.util.ArrayList;
import java.util.stream.IntStream;

/**
 * This is the basic structure to be used for structures which don't need a lot of configuration or a custom player
 * created structures.
 *
 * @author WuestMan
 */
public final class StructureBasic extends Structure {
    private final ArrayList<BlockPos> mobSpawnerPos = new ArrayList<>();
    private BlockPos customBlockPos = null;

    @Override
    protected Boolean CustomBlockProcessingHandled(StructureConfiguration configuration, BuildBlock block, Level world, BlockPos originalPos,
                                                   Block foundBlock, BlockState blockState, Player player) {
        BasicStructureConfiguration config = (BasicStructureConfiguration) configuration;

        String structureName = config.basicStructureName.getName();
        BaseOption chosenOption = config.chosenOption;

        if (foundBlock instanceof HopperBlock && structureName.equals(EnumBasicStructureName.FarmImproved.getName()) && chosenOption == FarmImprovedOptions.AutomatedChickenCoop) {
            this.customBlockPos = block.getStartingPosition().getRelativePosition(
                    originalPos,
                    this.getClearSpace().getShape().getDirection(),
                    configuration.houseFacing);
        } else if (foundBlock instanceof TrapDoorBlock && structureName.equals(EnumBasicStructureName.MineshaftEntrance.getName())) {
            this.customBlockPos = block.getStartingPosition().getRelativePosition(
                    originalPos,
                    this.getClearSpace().getShape().getDirection(),
                    configuration.houseFacing);
        } else if (foundBlock instanceof BedBlock && config.chosenOption.getHasBedColor()) {
            // Even if a structure has a bed; we may want to keep a specific color to match what the design of the structure is.
            BlockPos bedHeadPosition = block.getStartingPosition().getRelativePosition(originalPos, this.getClearSpace().getShape().getDirection(), configuration.houseFacing);
            BlockPos bedFootPosition = block.getSubBlock().getStartingPosition().getRelativePosition(
                    originalPos,
                    this.getClearSpace().getShape().getDirection(),
                    configuration.houseFacing);

            ReferenceReferencePair<BlockState, BlockState> blockStateTuple = BuildingMethods.getBedState(bedHeadPosition, bedFootPosition, config.bedColor);
            block.setBlockState(blockStateTuple.left());
            block.getSubBlock().setBlockState(blockStateTuple.right());

            this.priorityOneBlocks.add(block);
            return true;
        } else if (foundBlock instanceof SpawnerBlock && structureName.equals(EnumBasicStructureName.FarmAdvanced.getName()) && chosenOption == FarmAdvancedOptions.MonsterMasher
                && Prefab.serverConfiguration.includeSpawnersInMasher) {
            this.mobSpawnerPos.add(block.getStartingPosition().getRelativePosition(
                    originalPos,
                    this.getClearSpace().getShape().getDirection(),
                    configuration.houseFacing));
        }

        return false;
    }

    @Override
    protected Boolean BlockShouldBeClearedDuringConstruction(StructureConfiguration configuration, Level world, BlockPos originalPos, BlockPos blockPos) {
        BasicStructureConfiguration config = (BasicStructureConfiguration) configuration;

        if (config.basicStructureName.getName().equals(EnumBasicStructureName.AquaBase.getName())
                || config.basicStructureName.getName().equals(EnumBasicStructureName.AquaBaseImproved.getName())) {
            BlockState blockState = world.getBlockState(blockPos);
            // Don't clear water blocks for this building.
            return blockState.getBlock() != Blocks.WATER;
        }

        return true;
    }

    /**
     * This method is used after the main building is build for any additional structures or modifications.
     *
     * @param configuration The structure configuration.
     * @param world         The current world.
     * @param originalPos   The original position clicked on.
     * @param player        The player which initiated the construction.
     */
    @Override
    public void AfterBuilding(StructureConfiguration configuration, ServerLevel world, BlockPos originalPos, Player player) {
        BasicStructureConfiguration config = (BasicStructureConfiguration) configuration;
        String structureName = config.basicStructureName.getName();
        BaseOption chosenOption = config.chosenOption;

        if (this.customBlockPos != null) {
            if (structureName.equals(EnumBasicStructureName.FarmImproved.getName()) && chosenOption == FarmImprovedOptions.AutomatedChickenCoop) {
                // For the advanced chicken coop, spawn 4 chickens above the hopper.
                IntStream
                        .range(0, 4)
                        .mapToObj(i -> new Chicken(EntityType.CHICKEN, world))
                        .forEach(entity -> {
                            entity.setPos(this.customBlockPos.getX(), this.customBlockPos
                                    .above()
                                    .getY(), this.customBlockPos.getZ());
                            world.addFreshEntity(entity);
                        });
            } else if (structureName.equals(EnumBasicStructureName.MineshaftEntrance.getName())) {
                // Build the mineshaft where the trap door exists.
                BuildingMethods.PlaceMineShaft(world, this.customBlockPos.below(), configuration.houseFacing, true);
            }

            this.customBlockPos = null;
        }

        if (structureName.equals(EnumBasicStructureName.FarmAdvanced.getName()) && chosenOption == FarmAdvancedOptions.MonsterMasher) {
            int monstersPlaced = 0;

            // Set the spawner.
            for (BlockPos spawnerPos : this.mobSpawnerPos) {
                BlockEntity tileEntity = world.getBlockEntity(spawnerPos);

                if (tileEntity instanceof SpawnerBlockEntity spawner) {

                    switch (monstersPlaced) {
                        case 0 -> // Zombie.
                                spawner
                                        .getSpawner()
                                        .setEntityId(EntityType.ZOMBIE, world, world.random, spawnerPos);
                        case 1 -> // Skeleton.
                                spawner
                                        .getSpawner()
                                        .setEntityId(EntityType.SKELETON, world, world.random, spawnerPos);
                        case 2 -> // Witch.
                                spawner
                                        .getSpawner()
                                        .setEntityId(EntityType.WITCH, world, world.random, spawnerPos);
                        default -> // Creeper.
                                spawner
                                        .getSpawner()
                                        .setEntityId(EntityType.CREEPER, world, world.random, spawnerPos);
                    }

                    monstersPlaced++;
                }
            }
        }

        if (structureName.equals(EnumBasicStructureName.AquaBase.getName())
                || structureName.equals(EnumBasicStructureName.AquaBaseImproved.getName())) {
            // Replace the entrance area with air blocks.
            BlockPos airPos = originalPos.above(4).relative(configuration.houseFacing.getOpposite(), 1);

            // This is the first wall.
            world.removeBlock(airPos.relative(configuration.houseFacing.getClockWise()), false);
            world.removeBlock(airPos, false);
            world.removeBlock(airPos.relative(configuration.houseFacing.getCounterClockWise()), false);

            airPos = airPos.below();
            world.removeBlock(airPos.relative(configuration.houseFacing.getClockWise()), false);
            world.removeBlock(airPos, false);
            world.removeBlock(airPos.relative(configuration.houseFacing.getCounterClockWise()), false);

            airPos = airPos.below();
            world.removeBlock(airPos.relative(configuration.houseFacing.getClockWise()), false);
            world.removeBlock(airPos, false);
            world.removeBlock(airPos.relative(configuration.houseFacing.getCounterClockWise()), false);

            airPos = airPos.below();
            world.removeBlock(airPos.relative(configuration.houseFacing.getClockWise()), false);
            world.removeBlock(airPos, false);
            world.removeBlock(airPos.relative(configuration.houseFacing.getCounterClockWise()), false);

            // Second part of the wall.
            airPos = airPos.relative(configuration.houseFacing.getOpposite()).above();
            world.removeBlock(airPos.relative(configuration.houseFacing.getClockWise()), false);
            world.removeBlock(airPos, false);
            world.removeBlock(airPos.relative(configuration.houseFacing.getCounterClockWise()), false);

            airPos = airPos.above();
            world.removeBlock(airPos.relative(configuration.houseFacing.getClockWise()), false);
            world.removeBlock(airPos, false);
            world.removeBlock(airPos.relative(configuration.houseFacing.getCounterClockWise()), false);

            airPos = airPos.above();
            world.removeBlock(airPos, false);
        }
    }

    @Override
    protected boolean hasGlassColor(StructureConfiguration configuration) {
        BasicStructureConfiguration config = (BasicStructureConfiguration) configuration;
        BaseOption chosenOption = config.chosenOption;
        return chosenOption.getHasGlassColor();
    }

    @Override
    protected FullDyeColor getGlassColor(StructureConfiguration configuration) {
        BasicStructureConfiguration config = (BasicStructureConfiguration) configuration;
        return config.glassColor;
    }
}
