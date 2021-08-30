package com.wuest.prefab.structures.config.enums;

import net.minecraft.util.math.Direction;

public class StarterFarmOptions extends BaseOption {
    public static StarterFarmOptions ElevatedFarm = new StarterFarmOptions(
            "prefab.gui.item_elevated_farm",
            "assets/prefab/structures/elevated_farm.zip",
            "textures/gui/elevated_farm_topdown.png",
            Direction.SOUTH,
            10,
            30,
            25,
            1,
            15,
            0,
            false,
            false);

    protected StarterFarmOptions(String translationString,
                                  String assetLocation,
                                  String pictureLocation,
                                  Direction direction,
                                  int height,
                                  int width,
                                  int length,
                                  int offsetParallelToPlayer,
                                  int offsetToLeftOfPlayer,
                                  int heightOffset,
                                  boolean hasBedColor,
                                  boolean hasGlassColor) {
        super(
                translationString,
                assetLocation,
                pictureLocation,
                direction,
                height,
                width,
                length,
                offsetParallelToPlayer,
                offsetToLeftOfPlayer,
                heightOffset,
                hasBedColor,
                hasGlassColor);
    }
}
