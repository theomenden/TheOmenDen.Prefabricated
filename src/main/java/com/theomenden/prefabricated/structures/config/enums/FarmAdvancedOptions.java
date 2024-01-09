package com.theomenden.prefabricated.structures.config.enums;

public class FarmAdvancedOptions extends BaseOption {

    public static FarmAdvancedOptions AutomatedBambooFarm = new FarmAdvancedOptions(
            "prefabricated.gui.farm.advanced.bamboo",
            "assets/prefabricated/structures/automated_bamboo_farm.zip",
            "textures/gui/automated_bamboo_farm.png",
            false,
            true);

    public static FarmAdvancedOptions AutomatedMelonFarm = new FarmAdvancedOptions(
            "prefabricated.gui.farm.advanced.melon",
            "assets/prefabricated/structures/automated_melon_farm.zip",
            "textures/gui/automated_melon_farm.png",
            false,
            true);

    public static FarmAdvancedOptions Barn = new FarmAdvancedOptions(
            "prefabricated.gui.farm.advanced.barn",
            "assets/prefabricated/structures/barn_advanced.zip",
            "textures/gui/barn_advanced.png",
            false,
            false);

    public static FarmAdvancedOptions GreenHouse = new FarmAdvancedOptions(
            "prefabricated.gui.farm.advanced.green_house",
            "assets/prefabricated/structures/green_house_advanced.zip",
            "textures/gui/green_house_advanced.png",
            false,
            false);

    public static FarmAdvancedOptions LargeHorseStable = new FarmAdvancedOptions(
            "prefabricated.gui.farm.advanced.horse",
            "assets/prefabricated/structures/horse_stable_advanced.zip",
            "textures/gui/horse_stable_advanced.png",
            false,
            false);

    public static FarmAdvancedOptions MonsterMasher = new FarmAdvancedOptions(
            "prefabricated.gui.farm.advanced.monster",
            "assets/prefabricated/structures/monster_masher.zip",
            "textures/gui/monster_masher.png",
            false,
            false);

    public static FarmAdvancedOptions ProduceFarm = new FarmAdvancedOptions(
            "prefabricated.gui.farm.advanced.produce",
            "assets/prefabricated/structures/produce_farm.zip",
            "textures/gui/produce_farm.png",
            false,
            true);

    public static FarmAdvancedOptions TreeFarm = new FarmAdvancedOptions(
            "prefabricated.gui.farm.advanced.tree",
            "assets/prefabricated/structures/tree_farm.zip",
            "textures/gui/tree_farm.png",
            false,
            false);

    protected FarmAdvancedOptions(String translationString,
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
