package com.wuest.prefab.blocks.entities;

import com.wuest.prefab.ModRegistry;
import com.wuest.prefab.base.TileEntityBase;
import com.wuest.prefab.config.StructureScannerConfig;
import com.wuest.prefab.structures.base.BuildClear;
import com.wuest.prefab.structures.base.BuildShape;
import com.wuest.prefab.structures.base.PositionOffset;
import com.wuest.prefab.structures.base.Structure;
import net.minecraft.block.BlockState;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;

public class StructureScannerBlockEntity extends TileEntityBase<StructureScannerConfig> {
    public StructureScannerBlockEntity(BlockPos pos, BlockState state) {
        super(ModRegistry.StructureScannerEntityType, pos, state);

        this.config = new StructureScannerConfig();
        this.config.blockPos = pos;
    }

    public static void ScanShape(StructureScannerConfig config, ServerPlayerEntity playerEntity, ServerWorld serverWorld) {
        BuildClear clearedSpace = new BuildClear();
        clearedSpace.getShape().setDirection(config.direction);
        clearedSpace.getShape().setHeight(config.blocksTall);
        clearedSpace.getShape().setWidth(config.blocksWide);
        clearedSpace.getShape().setLength(config.blocksLong);

        BuildShape buildShape = clearedSpace.getShape().Clone();

        Direction playerFacing = config.direction;

        // Scanning the structure doesn't contain the starting corner block but the clear does.
        buildShape.setWidth(buildShape.getWidth() - 1);
        buildShape.setLength(buildShape.getLength() - 1);

        clearedSpace.getShape().setWidth(clearedSpace.getShape().getWidth());
        clearedSpace.getShape().setLength(clearedSpace.getShape().getLength());

        int downOffset = config.blocksDown < 0 ? Math.abs(config.blocksDown) : 0;

        clearedSpace.getStartingPosition().setHeightOffset(downOffset);
        clearedSpace.getStartingPosition().setHorizontalOffset(playerFacing, config.blocksParallel);
        clearedSpace.getStartingPosition().setHorizontalOffset(playerFacing.rotateYCounterclockwise(), config.blocksToTheLeft);

        BlockPos cornerPos = config.blockPos
                .offset(playerFacing.rotateYCounterclockwise(), config.blocksToTheLeft)
                .offset(playerFacing, config.blocksParallel)
                .down(downOffset);

        BlockPos otherCorner = cornerPos
                .offset(playerFacing, buildShape.getLength())
                .offset(playerFacing.rotateYClockwise(), buildShape.getWidth())
                .up(buildShape.getHeight());

        Structure.ScanStructure(
                serverWorld,
                config.blockPos,
                cornerPos,
                otherCorner,
                "..\\src\\main\\resources\\assets\\prefab\\structures\\" + config.structureZipName + ".zip",
                clearedSpace,
                playerFacing,
                false,
                false);
    }
}
