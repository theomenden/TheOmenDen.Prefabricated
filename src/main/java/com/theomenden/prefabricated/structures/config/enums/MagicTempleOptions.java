package com.theomenden.prefabricated.structures.config.enums;

public class MagicTempleOptions extends BaseOption {
    public static MagicTempleOptions Default = new MagicTempleOptions(
            "item.prefabricated.magic_temple",
            "assets/prefabricated/structures/magic_temple.zip",
            "textures/gui/magic_temple.png",
            false,
            false);

    protected MagicTempleOptions(String translationString,
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
