package com.wuest.prefab.config;

import me.sargunvohra.mcmods.autoconfig1u.ConfigData;
import me.sargunvohra.mcmods.autoconfig1u.annotation.Config;
import me.sargunvohra.mcmods.autoconfig1u.annotation.ConfigEntry;
import me.sargunvohra.mcmods.autoconfig1u.shadowed.blue.endless.jankson.Comment;

import java.util.HashMap;

@Config(name = "Prefab")
public class ModConfiguration implements ConfigData {

	@ConfigEntry.BoundedDiscrete(min = 5, max = 16)
	@Comment("Max starter house size")
	public int maximumStartingHouseSize = 16;

	@Comment("Enables the loft starter house.")
	public boolean enableLoftHouse = false;

	@Comment("Add spawners to monster masher.")
	public boolean includeSpawnersInMasher = true;

	@Comment("Enables preview buttons in screens.")
	public boolean enableStructurePreview = true;

	@Comment("Bulldozer makes drops.")
	public boolean allowBulldozerToCreateDrops = true;

	@Comment("Generated mineshafts have chests with materials.")
	public boolean includeMineshaftChest = true;

	@ConfigEntry.Category("recipes")
	public HashMap<String, Boolean> recipes = new HashMap<>();

	public ModConfiguration() {
		for (String key : RecipeKeys.Keys) {
			this.recipes.put(key, true);
		}
	}

	public static class RecipeKeys {
		public static String compressedStoneKey = "Compressed Stone";
		public static String compressedGlowStoneKey = "Compressed Glowstone";
		public static String compressedDirteKey = "Compressed Dirt";
		public static String compressedChestKey = "Compressed Chest";
		public static String pileOfBricksKey = "Pile of Bricks";
		public static String warehouseKey = "Warehouse";
		public static String produceFarmKey = "Produce Farm";
		public static String treeFarmKey = "Tree Farm";
		public static String chickenCoopKey = "Chicken Coop";
		public static String fishFarmKey = "Fish Farm";
		public static String warehouseUpgradeKey = "Warehouse Upgrade";
		public static String advancedWarehouseKey = "Advanced Warehouse";
		public static String monsterMasherKey = "Monster Masher";
		public static String bundleofTimberKey = "Bundle of Timber";
		public static String horseStableKey = "Horse Stable";
		public static String netherGateKey = "Nether Gate";
		public static String advancedChickenCoopKey = "Advanced Chicken Coop";
		public static String advancedHorseStableKey = "Advanced Horse Stable";
		public static String barnKey = "Barn";
		public static String machineryTowerKey = "Machinery Tower";
		public static String defenseBunkerKey = "Defense Bunker";
		public static String mineshaftEntranceKey = "Mineshaft Entrance";
		public static String enderGatewayKey = "Ender Gateway";
		public static String aquaBaseKey = "Aqua Base";
		public static String grassyPlainsKey = "Grassy Plains";
		public static String magicTempleKey = "Magic Temple";
		public static String instantBridgeKey = "Instant Bridge";
		public static String paperLanternKey = "Paper Lantern";
		public static String compressedObsidianKey = "Compressed Obsidian";
		public static String villagerHousesKey = "Villager Houses";
		public static String phasicBlockKey = "Phasic Block";
		public static String smartGlassKey = "Smart Glass";
		public static String greenHouseKey = "Green House";
		public static String startingHouseKey = "Starting House";
		public static String glassStairsKey = "Glass Stairs";
		public static String glassSlabsKey = "Glass Slabs";
		public static String moderateHouseKey = "Moderate House";
		public static String watchTowerKey = "Watch Tower";
		public static String bulldozerKey = "Bulldozer";
		public static String structurePartKey = "StructurePart";
		public static String jailKey = "Jail";
		public static String saloonKey = "Saloon";
		public static String skiLodgeKey = "Ski Lodge";
		public static String windMillKey = "Windmill";
		public static String townHallKey = "Town Hall";
		public static String heapOfTimberKey = "Heap of Timber";
		public static String tonOfTimberKey = "Ton of Timber";

		public static String[] Keys = new String[]
				{RecipeKeys.compressedStoneKey, RecipeKeys.compressedGlowStoneKey, RecipeKeys.compressedDirteKey, RecipeKeys.compressedChestKey, RecipeKeys.pileOfBricksKey,
						RecipeKeys.warehouseKey, RecipeKeys.produceFarmKey, RecipeKeys.treeFarmKey, RecipeKeys.chickenCoopKey, RecipeKeys.fishFarmKey,
						RecipeKeys.warehouseUpgradeKey, RecipeKeys.advancedWarehouseKey, RecipeKeys.monsterMasherKey, RecipeKeys.bundleofTimberKey, RecipeKeys.horseStableKey,
						RecipeKeys.netherGateKey, RecipeKeys.advancedChickenCoopKey, RecipeKeys.advancedHorseStableKey, RecipeKeys.barnKey,
						RecipeKeys.machineryTowerKey, RecipeKeys.defenseBunkerKey, RecipeKeys.mineshaftEntranceKey, RecipeKeys.enderGatewayKey, RecipeKeys.magicTempleKey,
						RecipeKeys.instantBridgeKey, RecipeKeys.paperLanternKey, RecipeKeys.compressedObsidianKey, RecipeKeys.villagerHousesKey,
						RecipeKeys.phasicBlockKey, RecipeKeys.smartGlassKey, RecipeKeys.greenHouseKey, RecipeKeys.startingHouseKey, RecipeKeys.glassStairsKey,
						RecipeKeys.glassSlabsKey, RecipeKeys.moderateHouseKey, RecipeKeys.grassyPlainsKey, RecipeKeys.aquaBaseKey, RecipeKeys.watchTowerKey,
						RecipeKeys.bulldozerKey, RecipeKeys.structurePartKey, RecipeKeys.jailKey, RecipeKeys.saloonKey, RecipeKeys.skiLodgeKey, RecipeKeys.windMillKey,
						RecipeKeys.townHallKey, RecipeKeys.heapOfTimberKey, RecipeKeys.tonOfTimberKey};
	}
}
