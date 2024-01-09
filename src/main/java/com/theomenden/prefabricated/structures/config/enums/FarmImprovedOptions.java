package com.theomenden.prefabricated.structures.config.enums;

@SuppressWarnings({"unchecked", "unused"})
public class FarmImprovedOptions extends BaseOption{
    public static FarmImprovedOptions AutomatedChickenCoop = new FarmImprovedOptions(
            "prefab.gui.farm.improved.chicken",
            "assets/prefabricated/structures/chicken_coop_improved.zip",
            "textures/gui/chicken_coop_improved.png",
            false,
            false);

    public static FarmImprovedOptions AutomatedFarm = new FarmImprovedOptions(
            "prefabricated.gui.farm.improved.automated",
            "assets/prefabricated/structures/automated_farm.zip",
            "textures/gui/automated_farm.png",
            false,
            false);

    public static FarmImprovedOptions FishPond = new FarmImprovedOptions(
            "prefabricated.gui.farm.improved.fish",
            "assets/prefabricated/structures/fish_pond.zip",
            "textures/gui/fish_pond.png",
            false,
            false);

    public static FarmImprovedOptions BeeFarm = new FarmImprovedOptions(
            "prefabricated.gui.farm.improved.bee",
            "assets/prefabricated/structures/bee_farm.zip",
            "textures/gui/bee_farm.png",
            false,
            false);

    public static FarmImprovedOptions SugarCaneFarm = new FarmImprovedOptions(
            "prefabricated.gui.farm.improved.sugar_cane",
            "assets/prefabricated/structures/sugar_cane_farm.zip",
            "textures/gui/sugar_cane_farm.png",
            false,
            true);

    public static FarmImprovedOptions MushroomFarm = new FarmImprovedOptions(
            "prefabricated.gui.farm.mushroom",
            "assets/prefabricated/structures/mushroom_farm.zip",
            "textures/gui/mushroom_farm.png",
            false,
            false);

    public static FarmImprovedOptions Barn = new FarmImprovedOptions(
            "prefabricated.gui.farm.advanced.barn",
            "assets/prefabricated/structures/barn_improved.zip",
            "textures/gui/barn_improved.png",
            false,
            false);

    public static FarmImprovedOptions GreenHouse = new FarmImprovedOptions(
            "prefabricated.gui.farm.advanced.green_house",
            "assets/prefabricated/structures/green_house_improved.zip",
            "textures/gui/green_house.png",
            false,
            false);

    protected FarmImprovedOptions(String translationString,
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
