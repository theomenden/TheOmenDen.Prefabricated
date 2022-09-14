package com.wuest.prefab.registries;

import com.wuest.prefab.ModRegistry;
import com.wuest.prefab.Prefab;
import com.wuest.prefab.Tuple;
import com.wuest.prefab.blocks.BlockDarkLamp;
import com.wuest.prefab.blocks.BlockLightSwitch;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;

import java.util.ArrayList;
import java.util.HashMap;

public class LightSwitchRegistry {
    private final HashMap<Level, ArrayList<BlockPos>> lightSwitchLocations;
    private static int SearchBlockRadius = 24;

    public LightSwitchRegistry() {
        this.lightSwitchLocations = new HashMap<>();
    }

    public void register(Level level, BlockPos blockPos) {
        ArrayList<BlockPos> blockPositions;

        if (!this.lightSwitchLocations.containsKey(level)) {
            blockPositions = new ArrayList<>();
            this.lightSwitchLocations.put(level, blockPositions);
        } else {
            blockPositions = this.lightSwitchLocations.get(level);
        }

        // Make sure to check for null in-case the key was removed between the contains check and the get.
        if (blockPositions != null) {
            blockPositions.add(blockPos);
        }
    }

    public void remove(Level level, BlockPos blockPos) {
        if (this.lightSwitchLocations.containsKey(level)) {
            ArrayList<BlockPos> positions = this.lightSwitchLocations.get(level);

            // Make sure to check for null in-case the key was removed between the contains check and the get.
            if (positions != null) {
                // Try to find this block position in the collection.
                for (int i = 0; i < positions.size(); i++) {
                    BlockPos currentPos = positions.get(i);

                    if (currentPos.hashCode() == blockPos.hashCode()) {
                        // This light switch was removed, turn off any nearby lamps.
                        // This needs to happen even if the lamp would be turned on by another switch.
                        this.setNearbyLights(currentPos, level, false);
                        positions.remove(i);
                        break;
                    }
                }

                if (positions.size() == 0) {
                    // No more block positions, remove the level from the array.
                    this.lightSwitchLocations.remove(level);
                }
            }
        }
    }

    public void flipSwitch(Level level, BlockPos incomingBlockPos, boolean turnOn) {
        // Don't do anything client-side.
        if (!level.isClientSide && this.lightSwitchLocations.containsKey(level)) {
            ArrayList<BlockPos> blockPositions = this.lightSwitchLocations.get(level);

            // Make sure to check for null in-case the key was removed between the contains check and the get.
            if (blockPositions != null) {
                for (BlockPos blockPos : blockPositions) {
                    if (blockPos.hashCode() == incomingBlockPos.hashCode()) {
                        this.setNearbyLights(blockPos, level, turnOn);
                        break;
                    }
                }
            }
        }
    }

    public boolean checkForNearbyOnSwitch(Level level, BlockPos blockPos) {
        Tuple<BlockPos, BlockPos> searchPositions = this.getSearchStartAndEnd(blockPos);
        BlockState lampState = level.getBlockState(blockPos);

        for (BlockPos worldPos: BlockPos.betweenClosed(searchPositions.getFirst(), searchPositions.getSecond())) {
            BlockState blockState = level.getBlockState(worldPos);

            if (blockState.getBlock() == ModRegistry.LightSwitch) {
                // Always use the first one found.
                lampState = lampState.setValue(BlockDarkLamp.LIT, blockState.getValue(BlockLightSwitch.POWERED));
                level.setBlock(worldPos, lampState, 3);
                break;
            }
        }

        return false;
    }

    private void setNearbyLights(BlockPos blockPos, Level level, boolean turnOn) {
        Tuple<BlockPos, BlockPos> searchPositions = this.getSearchStartAndEnd(blockPos);

        for (BlockPos worldPos: BlockPos.betweenClosed(searchPositions.getFirst(), searchPositions.getSecond())) {
            BlockState blockState = level.getBlockState(worldPos);

            if (blockState.getBlock() == ModRegistry.DarkLamp) {
                blockState = blockState.setValue(BlockDarkLamp.LIT, turnOn);
                level.setBlock(worldPos, blockState, 3);
            }
        }
    }

    private Tuple<BlockPos, BlockPos> getSearchStartAndEnd(BlockPos startingPos) {
        BlockPos startPos = startingPos
                .below(LightSwitchRegistry.SearchBlockRadius)
                .south(LightSwitchRegistry.SearchBlockRadius)
                .west(LightSwitchRegistry.SearchBlockRadius);

        BlockPos endPos = startingPos
                .above(LightSwitchRegistry.SearchBlockRadius)
                .north(LightSwitchRegistry.SearchBlockRadius)
                .east(LightSwitchRegistry.SearchBlockRadius);

        return new Tuple<>(startPos, endPos);
    }
}
