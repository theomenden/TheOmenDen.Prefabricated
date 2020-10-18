package com.wuest.prefab;

import com.wuest.prefab.blocks.*;
import com.wuest.prefab.items.ItemCompressedChest;
import com.wuest.prefab.recipe.ConditionedShapedRecipe;
import com.wuest.prefab.recipe.ConditionedShaplessRecipe;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

/**
 * This is the mod registry so there is a way to get to all instances of the blocks/items created by this mod.
 *
 * @author WuestMan
 */
public class ModRegistry {

	/* *********************************** Blocks *********************************** */

	public static final BlockCompressedStone CompressedStone = new BlockCompressedStone(BlockCompressedStone.EnumType.COMPRESSED_STONE);
	public static final BlockCompressedStone DoubleCompressedStone = new BlockCompressedStone(BlockCompressedStone.EnumType.DOUBLE_COMPRESSED_STONE);
	public static final BlockCompressedStone TripleCompressedStone = new BlockCompressedStone(BlockCompressedStone.EnumType.TRIPLE_COMPRESSED_STONE);
	public static final BlockCompressedStone CompressedDirt = new BlockCompressedStone(BlockCompressedStone.EnumType.COMPRESSED_DIRT);
	public static final BlockCompressedStone DoubleCompressedDirt = new BlockCompressedStone(BlockCompressedStone.EnumType.DOUBLE_COMPRESSED_DIRT);
	public static final BlockCompressedStone CompressedGlowstone = new BlockCompressedStone(BlockCompressedStone.EnumType.COMPRESSED_GLOWSTONE);
	public static final BlockCompressedStone DoubleCompressedGlowstone = new BlockCompressedStone(BlockCompressedStone.EnumType.DOUBLE_COMPRESSED_GLOWSTONE);
	public static final BlockCompressedObsidian CompressedObsidian = new BlockCompressedObsidian(BlockCompressedObsidian.EnumType.COMPRESSED_OBSIDIAN);
	public static final BlockCompressedObsidian DoubleCompressedObsidian = new BlockCompressedObsidian(BlockCompressedObsidian.EnumType.DOUBLE_COMPRESSED_OBSIDIAN);
	public static final BlockGlassSlab GlassSlab = new BlockGlassSlab(AbstractBlock.Settings.copy(Blocks.GLASS));
	public static final BlockGlassStairs GlassStairs = new BlockGlassStairs(Blocks.GLASS.getDefaultState(), AbstractBlock.Settings.copy(Blocks.GLASS));
	public static final BlockPaperLantern PaperLantern = new BlockPaperLantern();
	public static final BlockPhasic Phasic = new BlockPhasic();
	public static final BlockBoundary Boundary = new BlockBoundary();

	/* *********************************** Item Blocks *********************************** */

	public static final BlockItem CompressedStoneItem = new BlockItem(ModRegistry.CompressedStone, new Item.Settings().group(ItemGroup.BUILDING_BLOCKS));
	public static final BlockItem DoubleCompressedStoneItem = new BlockItem(ModRegistry.DoubleCompressedStone, new Item.Settings().group(ItemGroup.BUILDING_BLOCKS));
	public static final BlockItem TripleCompressedStoneItem = new BlockItem(ModRegistry.TripleCompressedStone, new Item.Settings().group(ItemGroup.BUILDING_BLOCKS));
	public static final BlockItem CompressedDirtItem = new BlockItem(ModRegistry.CompressedDirt, new Item.Settings().group(ItemGroup.BUILDING_BLOCKS));
	public static final BlockItem DoubleCompressedDirtItem = new BlockItem(ModRegistry.DoubleCompressedDirt, new Item.Settings().group(ItemGroup.BUILDING_BLOCKS));
	public static final BlockItem CompressedGlowstoneItem = new BlockItem(ModRegistry.CompressedGlowstone, new Item.Settings().group(ItemGroup.BUILDING_BLOCKS));
	public static final BlockItem DoubleCompressedGlowstoneItem = new BlockItem(ModRegistry.DoubleCompressedGlowstone, new Item.Settings().group(ItemGroup.BUILDING_BLOCKS));
	public static final BlockItem CompressedObsidianItem = new BlockItem(ModRegistry.CompressedObsidian, new Item.Settings().group(ItemGroup.BUILDING_BLOCKS));
	public static final BlockItem DoubleCompressedObsidianItem = new BlockItem(ModRegistry.DoubleCompressedObsidian, new Item.Settings().group(ItemGroup.BUILDING_BLOCKS));
	public static final BlockItem GlassSlabItem = new BlockItem(ModRegistry.GlassSlab, new Item.Settings().group(ItemGroup.BUILDING_BLOCKS));
	public static final BlockItem GlassStairsItem = new BlockItem(ModRegistry.GlassStairs, new Item.Settings().group(ItemGroup.BUILDING_BLOCKS));
	public static final BlockItem PaperLanternItem = new BlockItem(ModRegistry.PaperLantern, new Item.Settings().group(ItemGroup.BUILDING_BLOCKS));
	public static final BlockItem PhasicItem = new BlockItem(ModRegistry.Phasic, new Item.Settings().group(ItemGroup.BUILDING_BLOCKS));
	public static final BlockItem BoundaryItem = new BlockItem(ModRegistry.Boundary, new Item.Settings().group(ItemGroup.BUILDING_BLOCKS));


	/* *********************************** Messages *********************************** */

	public static final Identifier ConfigSync = new Identifier(Prefab.MODID, "config_sync");

	/* *********************************** Items *********************************** */

	public static final Item PileOfBricks = new Item(new Item.Settings().group(ItemGroup.MATERIALS));
	public static final Item PalletofBricks = new Item(new Item.Settings().group(ItemGroup.MATERIALS));
	public static final Item BundleOfTimber = new Item(new Item.Settings().group(ItemGroup.MATERIALS));
	public static final Item HeapOfTimber = new Item(new Item.Settings().group(ItemGroup.MATERIALS));
	public static final Item TonOfTimber = new Item(new Item.Settings().group(ItemGroup.MATERIALS));
	public static final Item StringOfLanterns = new Item(new Item.Settings().group(ItemGroup.MATERIALS));
	public static final Item CoilOfLanterns = new Item(new Item.Settings().group(ItemGroup.MATERIALS));
	public static final ItemCompressedChest CompressedChest = new ItemCompressedChest();

	public static final Item WarehouseUpgrade = new Item(new Item.Settings().group(ItemGroup.MATERIALS));
	/* *********************************** Blueprint Items *********************************** */

	/*
		public static final RegistryObject<ItemStartHouse> StartHouse = ITEMS.register("item_start_house", ItemStartHouse::new);
		public static final RegistryObject<ItemWareHouse> WareHouse = ITEMS.register("item_warehouse", com.wuest.prefab.Structures.Items.ItemWareHouse::new);
		public static final RegistryObject<ItemChickenCoop> ChickenCoop = ITEMS.register("item_chicken_coop", com.wuest.prefab.Structures.Items.ItemChickenCoop::new);
		public static final RegistryObject<ItemProduceFarm> ProduceFarm = ITEMS.register("item_produce_farm", com.wuest.prefab.Structures.Items.ItemProduceFarm::new);
		public static final RegistryObject<ItemTreeFarm> TreeFarm = ITEMS.register("item_tree_farm", com.wuest.prefab.Structures.Items.ItemTreeFarm::new);
		public static final RegistryObject<ItemFishPond> FishPond = ITEMS.register("item_fish_pond", com.wuest.prefab.Structures.Items.ItemFishPond::new);
		public static final RegistryObject<ItemAdvancedWareHouse> AdvancedWareHouse = ITEMS.register("item_advanced_warehouse", com.wuest.prefab.Structures.Items.ItemAdvancedWareHouse::new);
		public static final RegistryObject<ItemMonsterMasher> MonsterMasher = ITEMS.register("item_monster_masher", com.wuest.prefab.Structures.Items.ItemMonsterMasher::new);

		public static final RegistryObject<ItemHorseStable> HorseStable = ITEMS.register("item_horse_stable", com.wuest.prefab.Structures.Items.ItemHorseStable::new);
		public static final RegistryObject<ItemInstantBridge> InstantBridge = ITEMS.register("item_instant_bridge", com.wuest.prefab.Structures.Items.ItemInstantBridge::new);
		public static final RegistryObject<ItemModerateHouse> ModerateHouse = ITEMS.register("item_moderate_house", com.wuest.prefab.Structures.Items.ItemModerateHouse::new);
		public static final RegistryObject<ItemBulldozer> Bulldozer = ITEMS.register("item_bulldozer", com.wuest.prefab.Structures.Items.ItemBulldozer::new);
		public static final RegistryObject<ItemStructurePart> StructurePart = ITEMS.register("item_structure_part", com.wuest.prefab.Structures.Items.ItemStructurePart::new);
		public static final RegistryObject<ItemVillagerHouses> VillagerHouses = ITEMS.register("item_villager_houses", com.wuest.prefab.Structures.Items.ItemVillagerHouses::new);

		public static final RegistryObject<ItemBasicStructure> Barn = ITEMS.register(EnumBasicStructureName.Barn.getResourceLocation().getPath(), () ->new ItemBasicStructure(EnumBasicStructureName.Barn));
		public static final RegistryObject<ItemBasicStructure> AdvancedCoop = ITEMS.register(EnumBasicStructureName.AdvancedCoop.getResourceLocation().getPath(), () ->new ItemBasicStructure(EnumBasicStructureName.AdvancedCoop));
		public static final RegistryObject<ItemBasicStructure> AdvancedHorseStable = ITEMS.register(EnumBasicStructureName.AdvancedHorseStable.getResourceLocation().getPath(), () ->new ItemBasicStructure(EnumBasicStructureName.AdvancedHorseStable));
		public static final RegistryObject<ItemBasicStructure> MachineryTower = ITEMS.register(EnumBasicStructureName.MachineryTower.getResourceLocation().getPath(), () ->new ItemBasicStructure(EnumBasicStructureName.MachineryTower));
		public static final RegistryObject<ItemBasicStructure> DefenseBunker = ITEMS.register(EnumBasicStructureName.DefenseBunker.getResourceLocation().getPath(), () ->new ItemBasicStructure(EnumBasicStructureName.DefenseBunker));
		public static final RegistryObject<ItemBasicStructure> MineshaftEntrance = ITEMS.register(EnumBasicStructureName.MineshaftEntrance.getResourceLocation().getPath(), () ->new ItemBasicStructure(EnumBasicStructureName.MineshaftEntrance));
		public static final RegistryObject<ItemBasicStructure> EnderGateway = ITEMS.register(EnumBasicStructureName.EnderGateway.getResourceLocation().getPath(), () ->new ItemBasicStructure(EnumBasicStructureName.EnderGateway));
		public static final RegistryObject<ItemBasicStructure> AquaBase = ITEMS.register(EnumBasicStructureName.AquaBase.getResourceLocation().getPath(), () ->new ItemBasicStructure(EnumBasicStructureName.AquaBase));
		public static final RegistryObject<ItemBasicStructure> GrassyPlain = ITEMS.register(EnumBasicStructureName.GrassyPlain.getResourceLocation().getPath(), () ->new ItemBasicStructure(EnumBasicStructureName.GrassyPlain));
		public static final RegistryObject<ItemBasicStructure> MagicTemple = ITEMS.register(EnumBasicStructureName.MagicTemple.getResourceLocation().getPath(), () ->new ItemBasicStructure(EnumBasicStructureName.MagicTemple));
		public static final RegistryObject<ItemBasicStructure> GreenHouse = ITEMS.register(EnumBasicStructureName.GreenHouse.getResourceLocation().getPath(), () ->new ItemBasicStructure(EnumBasicStructureName.GreenHouse));
		public static final RegistryObject<ItemBasicStructure> WatchTower = ITEMS.register(EnumBasicStructureName.WatchTower.getResourceLocation().getPath(), () ->new ItemBasicStructure(EnumBasicStructureName.WatchTower));
		public static final RegistryObject<ItemBasicStructure> WelcomeCenter = ITEMS.register(EnumBasicStructureName.WelcomeCenter.getResourceLocation().getPath(), () ->new ItemBasicStructure(EnumBasicStructureName.WelcomeCenter));
		public static final RegistryObject<ItemBasicStructure> Jail = ITEMS.register(EnumBasicStructureName.Jail.getResourceLocation().getPath(), () ->new ItemBasicStructure(EnumBasicStructureName.Jail));
		public static final RegistryObject<ItemBasicStructure> Saloon = ITEMS.register(EnumBasicStructureName.Saloon.getResourceLocation().getPath(), () ->new ItemBasicStructure(EnumBasicStructureName.Saloon));
		public static final RegistryObject<ItemBasicStructure> SkiLodge = ITEMS.register(EnumBasicStructureName.SkiLodge.getResourceLocation().getPath(), () ->new ItemBasicStructure(EnumBasicStructureName.SkiLodge));
		public static final RegistryObject<ItemBasicStructure> WindMill = ITEMS.register(EnumBasicStructureName.WindMill.getResourceLocation().getPath(), () ->new ItemBasicStructure(EnumBasicStructureName.WindMill));
		public static final RegistryObject<ItemBasicStructure> TownHall = ITEMS.register(EnumBasicStructureName.TownHall.getResourceLocation().getPath(), () ->new ItemBasicStructure(EnumBasicStructureName.TownHall));
		public static final RegistryObject<ItemBasicStructure> NetherGate = ITEMS.register(EnumBasicStructureName.NetherGate.getResourceLocation().getPath(), () ->new ItemBasicStructure(EnumBasicStructureName.NetherGate));
	*/
	
	public static void registerModComponents() {
		ModRegistry.registerBlocks();

		ModRegistry.registerItems();

		ModRegistry.registerBluePrints();

		ModRegistry.registerItemBlocks();

		ModRegistry.RegisterClientToServerMessageHandlers();

		ModRegistry.RegisterRecipeSerializers();
	}

	private static void registerBlocks() {
		ModRegistry.registerBlock(BlockCompressedStone.EnumType.COMPRESSED_STONE.getUnlocalizedName(), ModRegistry.CompressedStone);
		ModRegistry.registerBlock(BlockCompressedStone.EnumType.DOUBLE_COMPRESSED_STONE.getUnlocalizedName(), ModRegistry.DoubleCompressedStone);
		ModRegistry.registerBlock(BlockCompressedStone.EnumType.TRIPLE_COMPRESSED_STONE.getUnlocalizedName(), ModRegistry.TripleCompressedStone);
		ModRegistry.registerBlock(BlockCompressedStone.EnumType.COMPRESSED_DIRT.getUnlocalizedName(), ModRegistry.CompressedDirt);
		ModRegistry.registerBlock(BlockCompressedStone.EnumType.DOUBLE_COMPRESSED_DIRT.getUnlocalizedName(), ModRegistry.DoubleCompressedDirt);
		ModRegistry.registerBlock(BlockCompressedStone.EnumType.COMPRESSED_GLOWSTONE.getUnlocalizedName(), ModRegistry.CompressedGlowstone);
		ModRegistry.registerBlock(BlockCompressedStone.EnumType.DOUBLE_COMPRESSED_GLOWSTONE.getUnlocalizedName(), ModRegistry.DoubleCompressedGlowstone);
		ModRegistry.registerBlock(BlockCompressedObsidian.EnumType.COMPRESSED_OBSIDIAN.asString(), ModRegistry.CompressedObsidian);
		ModRegistry.registerBlock(BlockCompressedObsidian.EnumType.DOUBLE_COMPRESSED_OBSIDIAN.asString(), ModRegistry.DoubleCompressedObsidian);
		ModRegistry.registerBlock("block_glass_slab", ModRegistry.GlassSlab);
		ModRegistry.registerBlock("block_glass_stairs", ModRegistry.GlassStairs);
		ModRegistry.registerBlock("block_paper_lantern", ModRegistry.PaperLantern);
		ModRegistry.registerBlock("block_phasic", ModRegistry.Phasic);
		ModRegistry.registerBlock("block_boundary", ModRegistry.Boundary);
	}

	private static void registerItems() {
		ModRegistry.registerItem("item_pile_of_bricks", ModRegistry.PileOfBricks);
		ModRegistry.registerItem("item_pallet_of_bricks", ModRegistry.PalletofBricks);
		ModRegistry.registerItem("item_bundle_of_timber", ModRegistry.BundleOfTimber);
		ModRegistry.registerItem("item_heap_of_timber", ModRegistry.HeapOfTimber);
		ModRegistry.registerItem("item_ton_of_timber", ModRegistry.TonOfTimber);
		ModRegistry.registerItem("item_string_of_lanterns", ModRegistry.StringOfLanterns);
		ModRegistry.registerItem("item_coil_of_lanterns", ModRegistry.CoilOfLanterns);
		ModRegistry.registerItem("item_compressed_chest", ModRegistry.CompressedChest);
		ModRegistry.registerItem("item_warehouse_upgrade", ModRegistry.WarehouseUpgrade);
	}

	private static void registerBluePrints() {

	}

	private static void registerItemBlocks() {
		ModRegistry.registerItem(BlockCompressedStone.EnumType.COMPRESSED_STONE.getUnlocalizedName(), ModRegistry.CompressedStoneItem);
		ModRegistry.registerItem(BlockCompressedStone.EnumType.DOUBLE_COMPRESSED_STONE.getUnlocalizedName(), ModRegistry.DoubleCompressedStoneItem);
		ModRegistry.registerItem(BlockCompressedStone.EnumType.TRIPLE_COMPRESSED_STONE.getUnlocalizedName(), ModRegistry.TripleCompressedStoneItem);
		ModRegistry.registerItem(BlockCompressedStone.EnumType.COMPRESSED_DIRT.getUnlocalizedName(), ModRegistry.CompressedDirtItem);
		ModRegistry.registerItem(BlockCompressedStone.EnumType.DOUBLE_COMPRESSED_DIRT.getUnlocalizedName(), ModRegistry.DoubleCompressedDirtItem);
		ModRegistry.registerItem(BlockCompressedStone.EnumType.COMPRESSED_GLOWSTONE.getUnlocalizedName(), ModRegistry.CompressedGlowstoneItem);
		ModRegistry.registerItem(BlockCompressedStone.EnumType.DOUBLE_COMPRESSED_GLOWSTONE.getUnlocalizedName(), ModRegistry.DoubleCompressedGlowstoneItem);
		ModRegistry.registerItem(BlockCompressedObsidian.EnumType.COMPRESSED_OBSIDIAN.asString(), ModRegistry.CompressedObsidianItem);
		ModRegistry.registerItem(BlockCompressedObsidian.EnumType.DOUBLE_COMPRESSED_OBSIDIAN.asString(), ModRegistry.DoubleCompressedObsidianItem);
		ModRegistry.registerItem("block_glass_slab", ModRegistry.GlassSlabItem);
		ModRegistry.registerItem("block_glass_stairs", ModRegistry.GlassStairsItem);
		ModRegistry.registerItem("block_paper_lantern", ModRegistry.PaperLanternItem);
		ModRegistry.registerItem("block_phasic", ModRegistry.PhasicItem);
		ModRegistry.registerItem("block_boundary", ModRegistry.BoundaryItem);
	}

	/**
	 * This is where the mod messages are registered.
	 */
	private static void RegisterClientToServerMessageHandlers() {
		/*AtomicInteger index = new AtomicInteger();
		Prefab.network.messageBuilder(ConfigSyncMessage.class, index.getAndIncrement())
				.encoder(ConfigSyncMessage::encode)
				.decoder(ConfigSyncMessage::decode)
				.consumer(ConfigSyncHandler::handle)
				.add();

		Prefab.network.messageBuilder(PlayerEntityTagMessage.class, index.getAndIncrement())
				.encoder(PlayerEntityTagMessage::encode)
				.decoder(PlayerEntityTagMessage::decode)
				.consumer(PlayerEntityHandler::handle)
				.add();

		Prefab.network.messageBuilder(StructureTagMessage.class, index.getAndIncrement())
				.encoder(StructureTagMessage::encode)
				.decoder(StructureTagMessage::decode)
				.consumer(StructureHandler::handle)
				.add();*/
	}

	private static void RegisterRecipeSerializers() {
		Registry.register(Registry.RECIPE_SERIALIZER, new Identifier(Prefab.MODID, "condition_crafting_shaped"), new ConditionedShapedRecipe.Serializer());
		Registry.register(Registry.RECIPE_SERIALIZER, new Identifier(Prefab.MODID, "condition_crafting_shapeless"), new ConditionedShaplessRecipe.Serializer());
	}

	private static void registerBlock(String registryName, Block block) {
		Registry.register(Registry.BLOCK, new Identifier(Prefab.MODID, registryName), block);
	}

	private static void registerItem(String registryName, Item item) {
		Registry.register(Registry.ITEM, new Identifier(Prefab.MODID, registryName), item);
	}
}