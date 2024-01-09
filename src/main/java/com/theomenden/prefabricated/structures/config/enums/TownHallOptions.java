package com.theomenden.prefabricated.structures.config.enums;

public class TownHallOptions extends BaseOption {
    public static TownHallOptions Default = new TownHallOptions(
            "item.prefabricated.town_hall",
            "assets/prefabricated/structures/town_hall.zip",
            "textures/gui/town_hall.png",
            false,
            false);

    protected TownHallOptions(String translationString,
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
