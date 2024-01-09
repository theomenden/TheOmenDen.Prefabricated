package com.theomenden.prefabricated.structures.config.enums;

public class SaloonOptions extends BaseOption {
    public static SaloonOptions Default = new SaloonOptions(
            "item.prefabricated.saloon",
            "assets/prefabricated/structures/saloon.zip",
            "textures/gui/saloon.png",
            false,
            false);

    protected SaloonOptions(String translationString,
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
