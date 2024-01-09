package com.theomenden.prefabricated.blocks;


import com.theomenden.prefabricated.ModRegistry;
import lombok.Getter;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.util.StringRepresentable;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.WallBlock;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;

public class BlockCustomWall extends WallBlock implements IGrassSpreadable {
    public EnumType BlockVariant;

    public BlockCustomWall(Block modelBlock, EnumType variant) {
        super(Properties.of()
                .dropsLike(variant.getMaterial())
                .strength(modelBlock.defaultDestroyTime(),
                        modelBlock.getExplosionResistance() * 5.0F / 3.0F)
                .sound(modelBlock.defaultBlockState().getSoundType()));

        this.BlockVariant = variant;
    }

    /**
     * Returns whether this block is of a type that needs random ticking.
     * Called for ref-counting purposes by ExtendedBlockStorage in order to broadly
     * cull a chunk from the random chunk update list for efficiency's sake.
     */
    @Override
    public boolean isRandomlyTicking(BlockState state) {
        return this.BlockVariant == EnumType.DIRT;
    }

    @Override
    public void animateTick(BlockState state, Level level, BlockPos pos, RandomSource random) {
        this.DetermineGrassSpread(state, level, pos, random);
    }

    @Override
    public BlockState getGrassBlockState(BlockState originalState) {
        return ModRegistry.GrassWall.defaultBlockState()
                .setValue(WallBlock.EAST_WALL, originalState.getValue(WallBlock.EAST_WALL))
                .setValue(WallBlock.WEST_WALL, originalState.getValue(WallBlock.WEST_WALL))
                .setValue(WallBlock.NORTH_WALL, originalState.getValue(WallBlock.NORTH_WALL))
                .setValue(WallBlock.SOUTH_WALL, originalState.getValue(WallBlock.SOUTH_WALL))
                .setValue(WallBlock.WATERLOGGED, originalState.getValue(WallBlock.WATERLOGGED))
                .setValue(WallBlock.UP, originalState.getValue(WallBlock.UP));
    }

    public enum EnumType implements StringRepresentable {
        DIRT(0, "block_dirt_wall", "block_dirt_wall", Blocks.DIRT),
        GRASS(1, "block_grass_wall", "block_grass_wall", Blocks.GRASS_BLOCK);

        private static final EnumType[] META_LOOKUP = new EnumType[values().length];

        static {
            for (EnumType customwall$enumtype : values()) {
                META_LOOKUP[customwall$enumtype.getMetadata()] = customwall$enumtype;
            }
        }

        private final int meta;
        private final String name;
        @Getter
        private final String unlocalizedName;
        @Getter
        private final Block material;

        EnumType(int meta, String name, String unlocalizedName, Block blockMaterial) {
            this.meta = meta;
            this.name = name;
            this.unlocalizedName = unlocalizedName;
            this.material = blockMaterial;
        }

        public static EnumType byMetadata(int meta) {
            if (meta < 0 || meta >= META_LOOKUP.length) {
                meta = 0;
            }

            return META_LOOKUP[meta];
        }

        public int getMetadata() {
            return this.meta;
        }

        public String toString() {
            return this.name;
        }

        @Override
        public @NotNull String getSerializedName() {
            return this.name;
        }

    }
}