package com.wuest.prefab.structures.config.enums;

public class StarterFarmOptions extends BaseOption {
    public static StarterFarmOptions ElevatedFarm = new StarterFarmOptions(
            "prefab.gui.item_elevated_farm",
            "assets/prefab/structures/elevated_farm.zip",
            "textures/gui/elevated_farm_topdown.png",
            false,
            false);

    protected StarterFarmOptions(String translationString,
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
