package com.theomenden.prefabricated.structures.config.enums;

public class ModernBuildingsOptions extends BaseOption{
    public static ModernBuildingsOptions HipsterFruitStand = new ModernBuildingsOptions(
            "prefabricated.gui.modern.hipster_fruit_stand",
            "assets/prefabricated/structures/modern_hipster_fruit_stand.zip",
            "textures/gui/modern_hipster_fruit_stand.png",
            false,
            true);

    public static ModernBuildingsOptions Cinema = new ModernBuildingsOptions(
            "prefabricated.gui.modern.cinema",
            "assets/prefabricated/structures/modern_cinema.zip",
            "textures/gui/modern_cinema.png",
            false,
            false);

    public static ModernBuildingsOptions ApartmentBuilding = new ModernBuildingsOptions(
            "prefabricated.gui.modern.apartment",
            "assets/prefabricated/structures/modern_apartment.zip",
            "textures/gui/modern_apartment.png",
            false,
            true);

    public static ModernBuildingsOptions MiniHotel = new ModernBuildingsOptions(
            "prefabricated.gui.modern.mini_hotel",
            "assets/prefabricated/structures/modern_mini_hotel.zip",
            "textures/gui/modern_mini_hotel.png",
            false,
            true);

    public static ModernBuildingsOptions Cottage = new ModernBuildingsOptions(
            "prefabricated.gui.modern.cottage",
            "assets/prefabricated/structures/modern_cottage.zip",
            "textures/gui/modern_cottage.png",
            false,
            true);

    public static ModernBuildingsOptions Restaurant = new ModernBuildingsOptions(
            "prefabricated.gui.modern.restaurant",
            "assets/prefabricated/structures/modern_restaurant.zip",
            "textures/gui/modern_restaurant.png",
            false,
            true);

    public static ModernBuildingsOptions JuiceShop = new ModernBuildingsOptions(
            "prefabricated.gui.modern.juice_shop",
            "assets/prefabricated/structures/modern_juice_shop.zip",
            "textures/gui/modern_juice_shop.png",
            false,
            true);

    protected ModernBuildingsOptions(String translationString,
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
