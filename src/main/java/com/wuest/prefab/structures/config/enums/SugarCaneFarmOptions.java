package com.wuest.prefab.structures.config.enums;

public class SugarCaneFarmOptions extends BaseOption {
    public static SugarCaneFarmOptions Default = new SugarCaneFarmOptions(
            "item.prefab.sugar_cane_farm",
            "assets/prefab/structures/sugar_cane_farm.zip",
            "textures/gui/sugar_cane_farm_topdown.png",
            false,
            false);

    protected SugarCaneFarmOptions(String translationString,
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
