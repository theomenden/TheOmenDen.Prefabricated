package com.theomenden.prefabricated.structures.config.enums;

import com.theomenden.prefabricated.structures.config.enums.BaseOption;

public class FarmOptions extends BaseOption {
    public static FarmOptions BeefFarm = new FarmOptions(
            "prefabricated.gui.farm.beef",
            "assets/prefabricated/structures/beef_farm.zip",
            "textures/gui/beef_farm.png",
            false,
            false);

    public static FarmOptions BerryFarm = new FarmOptions(
            "prefabricated.gui.farm.berry",
            "assets/prefabricated/structures/berry_farm.zip",
            "textures/gui/berry_farm.png",
            false,
            false);

    public static FarmOptions CactusFarm = new FarmOptions(
            "prefabricated.gui.farm.cactus",
            "assets/prefabricated/structures/cactus_farm.zip",
            "textures/gui/cactus_farm.png",
            false,
            true);

    public static FarmOptions ChickenCoop = new FarmOptions(
            "prefabricated.gui.farm.chicken",
            "assets/prefabricated/structures/chicken_coop.zip",
            "textures/gui/chicken_coop.png",
            false,
            false);

    public static FarmOptions HorseStable = new FarmOptions(
            "prefabricated.gui.farm.horse",
            "assets/prefabricated/structures/horse_stable.zip",
            "textures/gui/horse_stable.png",
            false,
            false);

    public static FarmOptions ElevatedFarm = new FarmOptions(
            "prefabricated.gui.farm.elevated",
            "assets/prefabricated/structures/elevated_farm.zip",
            "textures/gui/elevated_farm.png",
            false,
            false);

    public static FarmOptions MultiLevelFarm = new FarmOptions(
            "prefabricated.gui.farm.multi_level",
            "assets/prefabricated/structures/multi_level_farm.zip",
            "textures/gui/multi_level_farm.png",
            false,
            false);

    public static FarmOptions RabbitHutch = new FarmOptions(
            "prefabricated.gui.farm.rabbit",
            "assets/prefabricated/structures/rabbit_hutch.zip",
            "textures/gui/rabbit_hutch.png",
            false,
            false);

    protected FarmOptions(String translationString,
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
