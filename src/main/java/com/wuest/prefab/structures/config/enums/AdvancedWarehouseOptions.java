package com.wuest.prefab.structures.config.enums;

public class AdvancedWarehouseOptions extends BaseOption{
    public static AdvancedWarehouseOptions Default = new AdvancedWarehouseOptions(
            "item.prefab.item_warehouse_improved",
            "assets/prefab/structures/warehouse_improved.zip",
            "textures/gui/warehouse_improved.png",
            false,
            true);

    protected AdvancedWarehouseOptions(String translationString,
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
