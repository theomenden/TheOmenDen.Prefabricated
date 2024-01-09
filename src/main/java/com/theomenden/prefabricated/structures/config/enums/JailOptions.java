package com.theomenden.prefabricated.structures.config.enums;

public class JailOptions extends BaseOption {
    public static JailOptions Default = new JailOptions(
            "item.prefabricated.jail",
            "assets/prefabricated/structures/jail.zip",
            "textures/gui/jail.png",
            false,
            false);

    protected JailOptions(String translationString,
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
