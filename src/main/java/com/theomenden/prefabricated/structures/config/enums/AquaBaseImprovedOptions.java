package com.theomenden.prefabricated.structures.config.enums;

public class AquaBaseImprovedOptions extends BaseOption {
    public static AquaBaseImprovedOptions Default = new AquaBaseImprovedOptions(
            "item.prefabricated.aqua_base_improved",
            "assets/prefabricated/structures/aqua_base_improved.zip",
            "textures/gui/aqua_base_improved.png",
            false,
            false);

    protected AquaBaseImprovedOptions(String translationString,
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
