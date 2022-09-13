package com.wuest.prefab.registries;

import com.wuest.prefab.Prefab;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;

import java.util.HashMap;

public class LightSwitchRegistry {
    private final HashMap<Integer, BlockPos> lightSwitchLocations;

    public LightSwitchRegistry() {
        this.lightSwitchLocations = new HashMap<>();
    }

    public void register(int hash, BlockPos blockPos) {
        if (!this.lightSwitchLocations.containsKey(hash)) {
            this.lightSwitchLocations.put(hash, blockPos);
        }
    }

    public void remove(int hash) {
        if (this.lightSwitchLocations.containsKey(hash)) {
            this.lightSwitchLocations.remove(hash);
        }
    }

    public void flipSwitch(int hash, Level level, boolean turnOn) {
        // Don't do anything client-side.
        if (!level.isClientSide && this.lightSwitchLocations.containsKey(hash)) {
            BlockPos blockPos = this.lightSwitchLocations.get(hash);

            this.setNearbyLights(blockPos,level, turnOn);
        }
    }

    protected void setNearbyLights(BlockPos blockPos, Level level, boolean turnOn) {

    }
}
