package com.wuest.prefab.blocks;

import com.wuest.prefab.base.TileBlockBase;
import com.wuest.prefab.blocks.entities.LightSwitchBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.AttachFace;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.Nullable;

public class BlockLightSwitch extends TileBlockBase<LightSwitchBlockEntity> {
    protected static final VoxelShape NORTH_AABB;
    protected static final VoxelShape SOUTH_AABB;
    protected static final VoxelShape WEST_AABB;
    protected static final VoxelShape EAST_AABB;
    protected static final VoxelShape UP_AABB_Z;
    protected static final VoxelShape UP_AABB_X;
    protected static final VoxelShape DOWN_AABB_Z;
    protected static final VoxelShape DOWN_AABB_X;

    public static final DirectionProperty FACING;

    public static final EnumProperty<AttachFace> FACE;

    public static final BooleanProperty POWERED;

    static {
        FACING = HorizontalDirectionalBlock.FACING;
        FACE = EnumProperty.create("face", AttachFace.class);
        POWERED = BooleanProperty.create("powered");
        NORTH_AABB = Block.box(5.0, 4.0, 10.0, 11.0, 12.0, 16.0);
        SOUTH_AABB = Block.box(5.0, 4.0, 0.0, 11.0, 12.0, 6.0);
        WEST_AABB = Block.box(10.0, 4.0, 5.0, 16.0, 12.0, 11.0);
        EAST_AABB = Block.box(0.0, 4.0, 5.0, 6.0, 12.0, 11.0);
        UP_AABB_Z = Block.box(5.0, 0.0, 4.0, 11.0, 6.0, 12.0);
        UP_AABB_X = Block.box(4.0, 0.0, 5.0, 12.0, 6.0, 11.0);
        DOWN_AABB_Z = Block.box(5.0, 10.0, 4.0, 11.0, 16.0, 12.0);
        DOWN_AABB_X = Block.box(4.0, 10.0, 5.0, 12.0, 16.0, 11.0);
    }

    public static boolean canAttach(LevelReader levelReader, BlockPos blockPos, Direction direction) {
        BlockPos blockPos2 = blockPos.relative(direction);
        return levelReader.getBlockState(blockPos2).isFaceSturdy(levelReader, blockPos2, direction.getOpposite());
    }

    protected static Direction getConnectedDirection(BlockState blockState) {
        switch (blockState.getValue(FACE)) {
            case CEILING:
                return Direction.DOWN;
            case FLOOR:
                return Direction.UP;
            default:
                return blockState.getValue(FACING);
        }
    }

    /**
     * Initializes a new instance of the TileBlockBase class.
     */
    public BlockLightSwitch() {
        super(Properties.of(Material.DECORATION).noCollission().strength(0.5F).sound(SoundType.WOOD));

        this.registerDefaultState(this.defaultBlockState()
                .setValue(BlockLightSwitch.FACING, Direction.NORTH)
                .setValue(BlockLightSwitch.FACE, AttachFace.FLOOR)
                .setValue(BlockLightSwitch.POWERED, false));
    }

    @Override
    public VoxelShape getShape(BlockState blockState, BlockGetter blockGetter, BlockPos blockPos, CollisionContext collisionContext) {
        switch (blockState.getValue(FACE)) {
            case FLOOR:
                switch ((blockState.getValue(FACING)).getAxis()) {
                    case X:
                        return UP_AABB_X;
                    case Z:
                    default:
                        return UP_AABB_Z;
                }
            case WALL:
                switch (blockState.getValue(FACING)) {
                    case EAST:
                        return EAST_AABB;
                    case WEST:
                        return WEST_AABB;
                    case SOUTH:
                        return SOUTH_AABB;
                    case NORTH:
                    default:
                        return NORTH_AABB;
                }
            case CEILING:
            default:
                switch ((blockState.getValue(FACING)).getAxis()) {
                    case X:
                        return DOWN_AABB_X;
                    case Z:
                    default:
                        return DOWN_AABB_Z;
                }
        }
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext ctx) {
        Direction[] directions = ctx.getNearestLookingDirections();

        for (Direction direction : directions) {
            BlockState blockState;

            if (direction.getAxis() == Direction.Axis.Y) {
                blockState = (this.defaultBlockState().setValue(FACE, direction == Direction.UP
                        ? AttachFace.CEILING
                        : AttachFace.FLOOR)).setValue(FACING, ctx.getHorizontalDirection());
            } else {
                blockState = (this.defaultBlockState().setValue(FACE, AttachFace.WALL)).setValue(FACING, direction.getOpposite());
            }

            if (blockState.canSurvive(ctx.getLevel(), ctx.getClickedPos())) {
                return blockState;
            }
        }

        return null;
    }

    @Override
    public boolean canSurvive(BlockState blockState, LevelReader levelReader, BlockPos blockPos) {
        return BlockLightSwitch.canAttach(levelReader, blockPos, BlockLightSwitch.getConnectedDirection(blockState).getOpposite());
    }

    @Override
    public BlockState updateShape(BlockState blockState, Direction direction, BlockState blockState2, LevelAccessor levelAccessor, BlockPos blockPos, BlockPos blockPos2) {
        return BlockLightSwitch.getConnectedDirection(blockState).getOpposite() == direction && !blockState.canSurvive(levelAccessor, blockPos)
                ? Blocks.AIR.defaultBlockState()
                : super.updateShape(blockState, direction, blockState2, levelAccessor, blockPos, blockPos2);
    }

    @Override
    public BlockState rotate(BlockState state, Rotation rotation) {
        return state.setValue(BlockLightSwitch.FACING, rotation.rotate(state.getValue(BlockLightSwitch.FACING)));
    }

    @Override
    public BlockState mirror(BlockState state, Mirror mirror) {
        return state.rotate(mirror.getRotation(state.getValue(BlockLightSwitch.FACING)));
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(BlockLightSwitch.FACING)
                .add(BlockLightSwitch.FACE)
                .add(BlockLightSwitch.POWERED);
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos blockPos, BlockState blockState) {
        return new LightSwitchBlockEntity(blockPos, blockState);
    }
}
