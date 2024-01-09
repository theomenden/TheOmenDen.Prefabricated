package com.theomenden.prefabricated.structures.config.enums;

public class WatchTowerOptions extends BaseOption {
    public static WatchTowerOptions Default = new WatchTowerOptions(
            "item.prefabricated.item_watch_tower",
            "assets/prefabricated/structures/watch_tower.zip",
            "textures/gui/watch_tower.png",
            true,
            false);

    public static WatchTowerOptions Copper = new WatchTowerOptions(
            "item.prefabricated.item_watch_tower_2",
            "assets/prefabricated/structures/watch_tower_2.zip",
            "textures/gui/watch_tower_2.png",
            true,
            true);

    public static WatchTowerOptions Dark = new WatchTowerOptions(
            "item.prefabricated.item_watch_tower_dark",
            "assets/prefabricated/structures/watch_tower_dark.zip",
            "textures/gui/watch_tower_dark.png",
            true,
            true);

    protected WatchTowerOptions(String translationString,
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
