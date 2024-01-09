package com.theomenden.prefabricated.structures.config.enums;

public class MachineryTowerOptions extends BaseOption {
    public static MachineryTowerOptions Default = new MachineryTowerOptions(
            "item.prefabricated.machinery.tower",
            "assets/prefabricated/structures/machinery_tower.zip",
            "textures/gui/machinery_tower.png",
            false,
            true);

    protected MachineryTowerOptions(String translationString,
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
