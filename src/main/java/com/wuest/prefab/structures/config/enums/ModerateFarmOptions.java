package com.wuest.prefab.structures.config.enums;

public class ModerateFarmOptions extends BaseOption{
    public static ModerateFarmOptions AutomatedFarm = new ModerateFarmOptions(
            "prefab.gui.item_automated_farm",
            "assets/prefab/structures/automated_farm.zip",
            "textures/gui/automated_farm_topdown.png",
            false,
            false);

    protected ModerateFarmOptions(String translationString,
                                  String assetLocation,
                                  String pictureLocation,
                                  boolean hasBedColor,
                                  boolean hasGlassColor) {
        super(
                translationString,
                assetLocation,
                pictureLocation,
                hasBedColor,
                hasGlassColor);
    }
}
