package com.wuest.prefab.structures.config.enums;

import net.minecraft.util.math.Direction;

public class AdvancedFarmOptions extends BaseOption {

    public static AdvancedFarmOptions AutomatedBeeFarm = new AdvancedFarmOptions(
            "prefab.gui.item_automated_be_farm",
            "assets/prefab/structures/advanced_bee_farm.zip",
            "textures/gui/advanced_bee_farm_topdown.png",
            Direction.SOUTH,
            10,
            30,
            25,
            1,
            15,
            0,
            false,
            false);

    protected AdvancedFarmOptions(String translationString,
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
