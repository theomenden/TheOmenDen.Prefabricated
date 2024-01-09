package com.theomenden.prefabricated.structures.config.enums;

public class WarehouseImprovedOptions extends BaseOption{
    public static WarehouseImprovedOptions Default = new WarehouseImprovedOptions(
            "item.prefabricated.item_warehouse_improved",
            "assets/prefabricated/structures/warehouse_improved.zip",
            "textures/gui/warehouse_improved.png",
            false,
            true);

    protected WarehouseImprovedOptions(String translationString,
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
