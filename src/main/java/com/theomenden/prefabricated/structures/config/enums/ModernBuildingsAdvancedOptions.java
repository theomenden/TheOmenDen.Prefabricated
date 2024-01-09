package com.theomenden.prefabricated.structures.config.enums;

public class ModernBuildingsAdvancedOptions extends BaseOption{

    public static ModernBuildingsAdvancedOptions TreeHouse = new ModernBuildingsAdvancedOptions(
            "prefab.gui.modern.tree_house",
            "assets/prefabricated/structures/modern_tree_house.zip",
            "textures/gui/modern_tree_house.png",
            true,
            true);

    protected ModernBuildingsAdvancedOptions(String translationString,
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
