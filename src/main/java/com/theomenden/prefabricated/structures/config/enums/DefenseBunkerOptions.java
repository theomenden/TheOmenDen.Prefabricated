package com.theomenden.prefabricated.structures.config.enums;

public class DefenseBunkerOptions extends BaseOption {
    public static DefenseBunkerOptions Default = new DefenseBunkerOptions(
            "item.prefabricated.defense.bunker",
            "assets/prefabricated/structures/defense_bunker.zip",
            "textures/gui/defense_bunker.png",
            false,
            false);

    protected DefenseBunkerOptions(String translationString,
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
