package com.theomenden.prefabricated.structures.config.enums;

public class WindMillOptions extends BaseOption {
    public static WindMillOptions Default = new WindMillOptions(
            "item.prefabricated.wind_mill",
            "assets/prefabricated/structures/wind_mill.zip",
            "textures/gui/wind_mill.png",
            false,
            false);

    protected WindMillOptions(String translationString,
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
