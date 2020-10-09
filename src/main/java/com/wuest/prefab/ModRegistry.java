package com.wuest.prefab;

import com.wuest.prefab.blocks.BlockCompressedObsidian;
import com.wuest.prefab.blocks.BlockCompressedStone;
import com.wuest.prefab.blocks.BlockGlassSlab;
import com.wuest.prefab.blocks.BlockGlassStairs;
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

	/*
	 * Deferred registry for items.
	 */
	/*	public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, Prefab.MODID);*/

	/**
	 * Deferred registry for blocks.
	 */
	/*	public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, Prefab.MODID);*/

	/* *********************************** Blocks *********************************** */

/*

	public static final RegistryObject<BlockPhasing> BlockPhasing = BLOCKS.register("block_phasing", com.wuest.prefab.Blocks.BlockPhasing::new);
	public static final RegistryObject<BlockBoundary> BlockBoundary = BLOCKS.register("block_boundary", com.wuest.prefab.Blocks.BlockBoundary::new);
	public static final RegistryObject<BlockPaperLantern> PaperLantern = BLOCKS.register("block_paper_lantern", BlockPaperLantern::new);
	public static final RegistryObject<BlockGlassStairs> GlassStairs = BLOCKS.register("block_glass_stairs", () -> new BlockGlassStairs(Blocks.GLASS.getDefaultState(), Block.Properties.from(Blocks.GLASS)));
	public static final RegistryObject<BlockGlassSlab> GlassSlab = BLOCKS.register("block_glass_slab", () -> new BlockGlassSlab(Block.Properties.from(Blocks.GLASS)));
*/

	/* *********************************** Item Blocks *********************************** */

/*
	public static final RegistryObject<BlockItem> CompressedStoneItem = ITEMS.register(BlockCompressedStone.EnumType.COMPRESSED_STONE.getUnlocalizedName(), () -> new BlockItem(CompressedStone.get(), new Item.Properties().group(ItemGroup.BUILDING_BLOCKS)));
	public static final RegistryObject<BlockItem> DoubleCompressedStoneItem = ITEMS.register(BlockCompressedStone.EnumType.DOUBLE_COMPRESSED_STONE.getUnlocalizedName(), () -> new BlockItem(DoubleCompressedStone.get(), new Item.Properties().group(ItemGroup.BUILDING_BLOCKS)));
	public static final RegistryObject<BlockItem> TripleCompressedStoneItem = ITEMS.register(BlockCompressedStone.EnumType.TRIPLE_COMPRESSED_STONE.getUnlocalizedName(), () -> new BlockItem(TripleCompressedStone.get(), new Item.Properties().group(ItemGroup.BUILDING_BLOCKS)));
	public static final RegistryObject<BlockItem> CompressedGlowStoneItem = ITEMS.register(BlockCompressedStone.EnumType.COMPRESSED_GLOWSTONE.getUnlocalizedName(), () -> new BlockItem(CompressedGlowStone.get(), new Item.Properties().group(ItemGroup.BUILDING_BLOCKS)));
	public static final RegistryObject<BlockItem> DoubleCompressedGlowStoneItem = ITEMS.register(BlockCompressedStone.EnumType.DOUBLE_COMPRESSED_GLOWSTONE.getUnlocalizedName(), () -> new BlockItem(DoubleCompressedGlowStone.get(), new Item.Properties().group(ItemGroup.BUILDING_BLOCKS)));
	public static final RegistryObject<BlockItem> CompressedDirtItem = ITEMS.register(BlockCompressedStone.EnumType.COMPRESSED_DIRT.getUnlocalizedName(), () -> new BlockItem(CompressedDirt.get(), new Item.Properties().group(ItemGroup.BUILDING_BLOCKS)));
	public static final RegistryObject<BlockItem> DoubleCompressedDirtItem = ITEMS.register(BlockCompressedStone.EnumType.DOUBLE_COMPRESSED_DIRT.getUnlocalizedName(), () -> new BlockItem(DoubleCompressedDirt.get(), new Item.Properties().group(ItemGroup.BUILDING_BLOCKS)));
	public static final RegistryObject<BlockItem> CompressedObsidianItem = ITEMS.register(BlockCompressedObsidian.EnumType.COMPRESSED_OBSIDIAN.getString(), () -> new BlockItem(CompressedObsidian.get(), new Item.Properties().group(ItemGroup.BUILDING_BLOCKS)));
	public static final RegistryObject<BlockItem> DoubleCompressedObsidianItem = ITEMS.register(BlockCompressedObsidian.EnumType.DOUBLE_COMPRESSED_OBSIDIAN.getString(), () -> new BlockItem(DoubleCompressedObsidian.get(), new Item.Properties().group(ItemGroup.BUILDING_BLOCKS)));
	public static final RegistryObject<BlockItem> BlockPhasingItem = ITEMS.register("block_phasing", () -> new BlockItem(BlockPhasing.get(), new Item.Properties().group(ItemGroup.BUILDING_BLOCKS)));
	public static final RegistryObject<BlockItem> BlockBoundaryItem = ITEMS.register("block_boundary", () -> new BlockItem(BlockBoundary.get(), new Item.Properties().group(ItemGroup.BUILDING_BLOCKS)));
	public static final RegistryObject<BlockItem> PaperLanternItem = ITEMS.register("block_paper_lantern", () -> new BlockItem(PaperLantern.get(), new Item.Properties().group(ItemGroup.BUILDING_BLOCKS)));
	public static final RegistryObject<BlockItem> GlassStairsItem = ITEMS.register("block_glass_stairs", () -> new BlockItem(GlassStairs.get(), new Item.Properties().group(ItemGroup.BUILDING_BLOCKS)));
	public static final RegistryObject<BlockItem> GlassSlabItem = ITEMS.register("block_glass_slab", () -> new BlockItem(GlassSlab.get(), new Item.Properties().group(ItemGroup.BUILDING_BLOCKS)));
*/

	/* *********************************** Items *********************************** */

/*
	public static final RegistryObject<ItemPileOfBricks> ItemPileOfBricks = ITEMS.register("item_pile_of_bricks", com.wuest.prefab.Items.ItemPileOfBricks::new);
	public static final RegistryObject<ItemPalletOfBricks> ItemPalletOfBricks = ITEMS.register("item_pallet_of_bricks", com.wuest.prefab.Items.ItemPalletOfBricks::new);
	public static final RegistryObject<ItemBundleOfTimber> ItemBundleOfTimber = ITEMS.register("item_bundle_of_timber", com.wuest.prefab.Items.ItemBundleOfTimber::new);
	public static final RegistryObject<ItemBundleOfTimber> ItemHeapOfTimber = ITEMS.register("item_heap_of_timber", com.wuest.prefab.Items.ItemBundleOfTimber::new);
	public static final RegistryObject<ItemBundleOfTimber> ItemTonOfTimber = ITEMS.register("item_ton_of_timber", com.wuest.prefab.Items.ItemBundleOfTimber::new);
	public static final RegistryObject<ItemCompressedChest> ItemCompressedChest = ITEMS.register("item_compressed_chest", com.wuest.prefab.Items.ItemCompressedChest::new);
	public static final RegistryObject<ItemStringOfLanterns> ItemStringOfLanterns = ITEMS.register("item_string_of_lanterns", com.wuest.prefab.Items.ItemStringOfLanterns::new);
	public static final RegistryObject<ItemCoilOfLanterns> ItemCoilOfLanterns = ITEMS.register("item_coil_of_lanterns", com.wuest.prefab.Items.ItemCoilOfLanterns::new);
*/

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
	public static final RegistryObject<ItemWarehouseUpgrade> WarehouseUpgrade = ITEMS.register("item_warehouse_upgrade", com.wuest.prefab.Items.ItemWarehouseUpgrade::new);
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
	}

	private static void registerItems() {

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

	private static void RegisterServerToClientMessages() {

	}

	/**
	 * This is where mod capabilities are registered.
	 */
	public static void RegisterCapabilities() {
	}

	private static void registerBlock(String registryName, Block block) {
		Registry.register(Registry.BLOCK, new Identifier(Prefab.MODID, registryName), block);
	}

	private static void registerItem(String registryName, Item item) {
		Registry.register(Registry.ITEM, new Identifier(Prefab.MODID, registryName), item);
	}
}