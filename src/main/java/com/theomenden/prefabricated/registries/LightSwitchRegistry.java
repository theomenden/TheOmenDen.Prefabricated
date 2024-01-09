package com.theomenden.prefabricated.registries;

import com.theomenden.prefabricated.ModRegistry;
import com.theomenden.prefabricated.blocks.BlockDarkLamp;
import com.theomenden.prefabricated.blocks.BlockLightSwitch;
import it.unimi.dsi.fastutil.objects.ReferenceReferencePair;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;

import java.util.Vector;

public class LightSwitchRegistry extends ILevelBasedRegistry<BlockPos> {
    private static final int SearchBlockRadius = 24;

    public LightSwitchRegistry() {
        super();
    }

    @Override
    protected void onElementRemoved(Level level, BlockPos element) {
        this.setNearbyLights(element, level, false);
    }

    @Override
    protected void onElementRegistered(Level level, BlockPos element) {
        // Do nothing here as it's not necessary.
    }

    public void flipSwitch(Level level, BlockPos incomingBlockPos, boolean turnOn) {
        // Don't do anything client-side.
        if (!level.isClientSide && this.internalRegistry.containsKey(level)) {
            Vector<BlockPos> blockPositions = this.internalRegistry.get(level);

            // Make sure to check for null in-case the key was removed between the contains check and the get.
            if (blockPositions != null) {
                blockPositions
                        .stream()
                        .filter(blockPos -> blockPos.hashCode() == incomingBlockPos.hashCode())
                        .findFirst()
                        .ifPresent(blockPos -> this.setNearbyLights(blockPos, level, turnOn));
            }
        }
    }

    public boolean checkForNearbyOnSwitch(Level level, BlockPos blockPos) {
        ReferenceReferencePair<BlockPos, BlockPos> searchPositions = this.getSearchStartAndEnd(blockPos);

        for (BlockPos worldPos: BlockPos.betweenClosed(searchPositions.left(), searchPositions.right())) {
            BlockState blockState = level.getBlockState(worldPos);

            if (blockState.getBlock() == ModRegistry.LightSwitch) {
                // Always use the first one found.
                return blockState.getValue(BlockLightSwitch.POWERED);
            }
        }

        return false;
    }

    private void setNearbyLights(BlockPos blockPos, Level level, boolean turnOn) {
        ReferenceReferencePair<BlockPos, BlockPos> searchPositions = this.getSearchStartAndEnd(blockPos);

        for (BlockPos worldPos: BlockPos.betweenClosed(searchPositions.left(), searchPositions.right())) {
            BlockState blockState = level.getBlockState(worldPos);

            if (blockState.getBlock() == ModRegistry.DarkLamp) {
                blockState = blockState.setValue(BlockDarkLamp.LIT, turnOn);
                level.setBlock(worldPos, blockState, 3);
            }
        }
    }

    private ReferenceReferencePair<BlockPos, BlockPos> getSearchStartAndEnd(BlockPos startingPos) {
        BlockPos startPos = startingPos
                .below(LightSwitchRegistry.SearchBlockRadius)
                .south(LightSwitchRegistry.SearchBlockRadius)
                .west(LightSwitchRegistry.SearchBlockRadius);

        BlockPos endPos = startingPos
                .above(LightSwitchRegistry.SearchBlockRadius)
                .north(LightSwitchRegistry.SearchBlockRadius)
                .east(LightSwitchRegistry.SearchBlockRadius);

        return ReferenceReferencePair.of(startPos, endPos);
    }
}
