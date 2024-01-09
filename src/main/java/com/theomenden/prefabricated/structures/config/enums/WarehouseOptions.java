package com.theomenden.prefabricated.structures.config.enums;

public class WarehouseOptions extends BaseOption {
    public static WarehouseOptions Default = new WarehouseOptions(
            "item.prefabricated.item_warehouse",
            "assets/prefabricated/structures/warehouse.zip",
            "textures/gui/warehouse.png",
            false,
            true);

    protected WarehouseOptions(String translationString,
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
