package com.theomenden.prefabricated.structures.config.enums;

public class MineshaftEntranceOptions extends BaseOption {
    public static MineshaftEntranceOptions Default = new MineshaftEntranceOptions(
            "item.prefab.mineshaft.entrance",
            "assets/prefabricated/structures/mineshaft_entrance.zip",
            "textures/gui/mineshaft_entrance.png",
            true,
            false);

    protected MineshaftEntranceOptions(String translationString,
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
