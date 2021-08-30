package com.wuest.prefab.structures.config.enums;

public class AdvancedFarmOptions extends BaseOption {

    public static AdvancedFarmOptions AutomatedBeeFarm = new AdvancedFarmOptions(
            "prefab.gui.item_automated_be_farm",
            "assets/prefab/structures/advanced_bee_farm.zip",
            "textures/gui/advanced_bee_farm_topdown.png",
            false,
            false);

    protected AdvancedFarmOptions(String translationString,
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
