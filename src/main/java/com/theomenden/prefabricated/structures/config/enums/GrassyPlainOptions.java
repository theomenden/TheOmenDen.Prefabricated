package com.theomenden.prefabricated.structures.config.enums;

public class GrassyPlainOptions extends BaseOption {
    public static GrassyPlainOptions Default = new GrassyPlainOptions(
            "item.prefabricated.grassy_plain",
            "assets/prefabricated/structures/grassy_plain.zip",
            "textures/gui/grassy_plain.png",
            false,
            false);

    protected GrassyPlainOptions(String translationString,
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
