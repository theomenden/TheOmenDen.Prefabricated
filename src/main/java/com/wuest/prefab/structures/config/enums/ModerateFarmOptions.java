package com.wuest.prefab.structures.config.enums;

import net.minecraft.util.math.Direction;

public class ModerateFarmOptions extends BaseOption{
    public static ModerateFarmOptions AutomatedFarm = new ModerateFarmOptions(
            "prefab.gui.item_automated_farm",
            "assets/prefab/structures/automated_farm.zip",
            "textures/gui/automated_farm_topdown.png",
            Direction.SOUTH,
            10,
            30,
            25,
            1,
            15,
            0,
            false,
            false);

    protected ModerateFarmOptions(String translationString,
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
