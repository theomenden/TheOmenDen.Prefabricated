package com.wuest.prefab;

import com.wuest.prefab.blocks.*;
import com.wuest.prefab.blocks.entities.StructureScannerBlockEntity;
import com.wuest.prefab.items.ItemCompressedChest;
import com.wuest.prefab.items.ItemSickle;
import com.wuest.prefab.items.ItemSwiftBlade;
import com.wuest.prefab.items.ItemWoodenCrate;
import com.wuest.prefab.recipe.ConditionedShapedRecipe;
import com.wuest.prefab.recipe.ConditionedShaplessRecipe;
import com.wuest.prefab.structures.config.BasicStructureConfiguration;
import com.wuest.prefab.structures.config.StructureConfiguration;
import com.wuest.prefab.structures.items.*;
import com.wuest.prefab.structures.messages.StructureTagMessage;
import net.fabricmc.fabric.api.network.ServerSidePacketRegistry;
import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.item.*;
import net.minecraft.recipe.Ingredient;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.tag.ItemTags;
import net.minecraft.util.Identifier;
import net.minecraft.util.Lazy;
import net.minecraft.util.registry.Registry;

import java.util.ArrayList;
import java.util.function.Consumer;
import java.util.function.Supplier;

/**
 * This is the mod registry so there is a way to get to all instances of the blocks/items created by this mod.
 *
 * @author WuestMan
 */
public class ModRegistry {
    public static final ArrayList<Consumer<Object>> guiRegistrations = new ArrayList<>();
    public static final BlockCompressedStone CompressedStone = new BlockCompressedStone(BlockCompressedStone.EnumType.COMPRESSED_STONE);

    /* *********************************** Block Entities Types *********************************** */
    public static BlockEntityType<StructureScannerBlockEntity> StructureScannerEntityType;

    /* *********************************** Block Entities *********************************** */
    public static StructureScannerBlockEntity StructureScannerEntity;

    /* *********************************** Blocks *********************************** */
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
    public static final BlockGrassSlab GrassSlab = new BlockGrassSlab();
    public static final BlockGrassStairs GrassStairs = new BlockGrassStairs();
    public static final BlockCustomWall GrassWall = new BlockCustomWall(Blocks.GRASS_BLOCK, BlockCustomWall.EnumType.GRASS);
    public static final BlockCustomWall DirtWall = new BlockCustomWall(Blocks.DIRT, BlockCustomWall.EnumType.DIRT);
    public static final BlockDirtStairs DirtStairs = new BlockDirtStairs();
    public static final BlockDirtSlab DirtSlab = new BlockDirtSlab();
    public static final BlockStructureScanner StructureScanner = new BlockStructureScanner();

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
    public static final BlockItem GrassSlabItem = new BlockItem(ModRegistry.GrassSlab, new Item.Settings().group(ItemGroup.BUILDING_BLOCKS));
    public static final BlockItem GrassStairsItem = new BlockItem(ModRegistry.GrassStairs, new Item.Settings().group(ItemGroup.BUILDING_BLOCKS));
    public static final BlockItem GrassWallItem = new BlockItem(ModRegistry.GrassWall, new Item.Settings().group(ItemGroup.BUILDING_BLOCKS));
    public static final BlockItem DirtWallItem = new BlockItem(ModRegistry.DirtWall, new Item.Settings().group(ItemGroup.BUILDING_BLOCKS));
    public static final BlockItem DirtStairsItem = new BlockItem(ModRegistry.DirtStairs, new Item.Settings().group(ItemGroup.BUILDING_BLOCKS));
    public static final BlockItem DirtSlabItem = new BlockItem(ModRegistry.DirtSlab, new Item.Settings().group(ItemGroup.BUILDING_BLOCKS));
    public static final BlockItem StructureScannerItem = new BlockItem(ModRegistry.StructureScanner, new Item.Settings().group(ItemGroup.MISC));

    /* *********************************** Messages *********************************** */
    public static final Identifier ConfigSync = new Identifier(Prefab.MODID, "config_sync");
    public static final Identifier PlayerConfigSync = new Identifier(Prefab.MODID, "player_config_sync");
    public static final Identifier StructureBuild = new Identifier(Prefab.MODID, "structure_build");

    /* *********************************** Items *********************************** */
    public static final Item PileOfBricks = new Item(new Item.Settings().group(ItemGroup.MATERIALS));
    public static final Item PalletOfBricks = new Item(new Item.Settings().group(ItemGroup.MATERIALS));
    public static final Item BundleOfTimber = new Item(new Item.Settings().group(ItemGroup.MATERIALS));
    public static final Item HeapOfTimber = new Item(new Item.Settings().group(ItemGroup.MATERIALS));
    public static final Item TonOfTimber = new Item(new Item.Settings().group(ItemGroup.MATERIALS));
    public static final Item StringOfLanterns = new Item(new Item.Settings().group(ItemGroup.MATERIALS));
    public static final Item CoilOfLanterns = new Item(new Item.Settings().group(ItemGroup.MATERIALS));
    public static final ItemCompressedChest CompressedChest = new ItemCompressedChest();
    public static final Item WarehouseUpgrade = new Item(new Item.Settings().group(ItemGroup.MATERIALS));
    public static final Item SwiftBladeWood = new ItemSwiftBlade(ToolMaterials.WOOD, 3, 10);
    public static final Item SwiftBladeStone = new ItemSwiftBlade(ToolMaterials.STONE, 3, 10);
    public static final Item SwiftBladeIron = new ItemSwiftBlade(ToolMaterials.IRON, 3, 10);
    public static final Item SwiftBladeDiamond = new ItemSwiftBlade(ToolMaterials.DIAMOND, 3, 10);
    public static final Item SwiftBladeGold = new ItemSwiftBlade(ToolMaterials.GOLD, 3, 10);
    public static final Item SwiftBladeCopper = new ItemSwiftBlade(CustomItemTier.COPPER, 3, 10);
    public static final Item SwiftBladeOsmium = new ItemSwiftBlade(CustomItemTier.OSMIUM, 3, 10);
    public static final Item SwiftBladeBronze = new ItemSwiftBlade(CustomItemTier.BRONZE, 3, 10);
    public static final Item SwiftBladeSteel = new ItemSwiftBlade(CustomItemTier.STEEL, 3, 10);
    public static final Item SwiftBladeObsidian = new ItemSwiftBlade(CustomItemTier.OBSIDIAN, 3, 10);

    public static final ItemSickle SickleWood = new ItemSickle(ToolMaterials.WOOD);
    public static final ItemSickle SickleStone = new ItemSickle(ToolMaterials.STONE);
    public static final ItemSickle SickleGold = new ItemSickle(ToolMaterials.GOLD);
    public static final ItemSickle SickleIron = new ItemSickle(ToolMaterials.IRON);
    public static final ItemSickle SickleDiamond = new ItemSickle(ToolMaterials.DIAMOND);

    // Note: Empty crate must be registered first to avoid null-pointer errors with the rest of the ItemWoodenCrate items.
    public static final ItemWoodenCrate EmptyCrate = new ItemWoodenCrate(ItemWoodenCrate.CrateType.Empty);
    public static final ItemWoodenCrate ClutchOfEggs = new ItemWoodenCrate(ItemWoodenCrate.CrateType.Clutch_Of_Eggs);
    public static final ItemWoodenCrate CartonOfEggs = new ItemWoodenCrate(ItemWoodenCrate.CrateType.Carton_Of_Eggs);
    public static final ItemWoodenCrate BunchOfPotatoes = new ItemWoodenCrate(ItemWoodenCrate.CrateType.Bunch_Of_Potatoes);
    public static final ItemWoodenCrate CrateOfPotatoes = new ItemWoodenCrate(ItemWoodenCrate.CrateType.Crate_Of_Potatoes);
    public static final ItemWoodenCrate BunchOfCarrots = new ItemWoodenCrate(ItemWoodenCrate.CrateType.Bunch_Of_Carrots);
    public static final ItemWoodenCrate CrateOfCarrots = new ItemWoodenCrate(ItemWoodenCrate.CrateType.Crate_Of_Carrots);
    public static final ItemWoodenCrate BunchOfBeets = new ItemWoodenCrate(ItemWoodenCrate.CrateType.Bunch_Of_Beets);
    public static final ItemWoodenCrate CrateOfBeets = new ItemWoodenCrate(ItemWoodenCrate.CrateType.Crate_Of_Beets);

    /* *********************************** Blueprint Items *********************************** */
    public static final ItemInstantBridge InstantBridge = new ItemInstantBridge();
    public static final ItemModerateHouse ModerateHouse = new ItemModerateHouse();
    public static final ItemStartHouse StartHouse = new ItemStartHouse();
    public static final ItemBulldozer Bulldozer = new ItemBulldozer();
    public static final ItemBulldozer CreativeBulldozer = new ItemBulldozer(true);
    public static final ItemStructurePart StructurePart = new ItemStructurePart();
    public static final ItemBasicStructure Barn = new ItemBasicStructure(BasicStructureConfiguration.EnumBasicStructureName.Barn);
    public static final ItemBasicStructure AdvancedCoop = new ItemBasicStructure(BasicStructureConfiguration.EnumBasicStructureName.AdvancedCoop);
    public static final ItemBasicStructure AdvancedHorseStable = new ItemBasicStructure(BasicStructureConfiguration.EnumBasicStructureName.AdvancedHorseStable);
    public static final ItemBasicStructure MachineryTower = new ItemBasicStructure(BasicStructureConfiguration.EnumBasicStructureName.MachineryTower);
    public static final ItemBasicStructure DefenseBunker = new ItemBasicStructure(BasicStructureConfiguration.EnumBasicStructureName.DefenseBunker);
    public static final ItemBasicStructure MineshaftEntrance = new ItemBasicStructure(BasicStructureConfiguration.EnumBasicStructureName.MineshaftEntrance);
    public static final ItemBasicStructure EnderGateway = new ItemBasicStructure(BasicStructureConfiguration.EnumBasicStructureName.EnderGateway);
    public static final ItemBasicStructure AquaBase = new ItemBasicStructure(BasicStructureConfiguration.EnumBasicStructureName.AquaBase);
    public static final ItemBasicStructure GrassyPlain = new ItemBasicStructure(BasicStructureConfiguration.EnumBasicStructureName.GrassyPlain);
    public static final ItemBasicStructure MagicTemple = new ItemBasicStructure(BasicStructureConfiguration.EnumBasicStructureName.MagicTemple);
    public static final ItemBasicStructure GreenHouse = new ItemBasicStructure(BasicStructureConfiguration.EnumBasicStructureName.GreenHouse);
    public static final ItemBasicStructure WatchTower = new ItemBasicStructure(BasicStructureConfiguration.EnumBasicStructureName.WatchTower);
    public static final ItemBasicStructure WelcomeCenter = new ItemBasicStructure(BasicStructureConfiguration.EnumBasicStructureName.WelcomeCenter);
    public static final ItemBasicStructure Jail = new ItemBasicStructure(BasicStructureConfiguration.EnumBasicStructureName.Jail);
    public static final ItemBasicStructure Saloon = new ItemBasicStructure(BasicStructureConfiguration.EnumBasicStructureName.Saloon);
    public static final ItemBasicStructure SkiLodge = new ItemBasicStructure(BasicStructureConfiguration.EnumBasicStructureName.SkiLodge);
    public static final ItemBasicStructure WindMill = new ItemBasicStructure(BasicStructureConfiguration.EnumBasicStructureName.WindMill);
    public static final ItemBasicStructure TownHall = new ItemBasicStructure(BasicStructureConfiguration.EnumBasicStructureName.TownHall);
    public static final ItemBasicStructure NetherGate = new ItemBasicStructure(BasicStructureConfiguration.EnumBasicStructureName.NetherGate);
    public static final ItemBasicStructure SugarCaneFarm = new ItemBasicStructure(BasicStructureConfiguration.EnumBasicStructureName.SugarCaneFarm);
    public static final ItemBasicStructure AdvancedAquaBase = new ItemBasicStructure(BasicStructureConfiguration.EnumBasicStructureName.AdvancedAquaBase);
    public static final ItemBasicStructure WorkShop = new ItemBasicStructure(BasicStructureConfiguration.EnumBasicStructureName.WorkShop);
    public static final ItemBasicStructure Warehouse = new ItemBasicStructure(BasicStructureConfiguration.EnumBasicStructureName.Warehouse);
    public static final ItemBasicStructure ChickenCoop = new ItemBasicStructure(BasicStructureConfiguration.EnumBasicStructureName.ChickenCoop);
    public static final ItemBasicStructure ProduceFarm = new ItemBasicStructure(BasicStructureConfiguration.EnumBasicStructureName.ProduceFarm);
    public static final ItemBasicStructure TreeFarm = new ItemBasicStructure(BasicStructureConfiguration.EnumBasicStructureName.TreeFarm);
    public static final ItemBasicStructure FishPond = new ItemBasicStructure(BasicStructureConfiguration.EnumBasicStructureName.FishPond);
    public static final ItemBasicStructure AdvancedWareHouse = new ItemBasicStructure(BasicStructureConfiguration.EnumBasicStructureName.AdvancedWarehouse);
    public static final ItemBasicStructure MonsterMasher = new ItemBasicStructure(BasicStructureConfiguration.EnumBasicStructureName.MonsterMasher);
    public static final ItemBasicStructure HorseStable = new ItemBasicStructure(BasicStructureConfiguration.EnumBasicStructureName.HorseStable);
    public static final ItemBasicStructure VillagerHouses = new ItemBasicStructure(BasicStructureConfiguration.EnumBasicStructureName.VillagerHouses, 10);
    public static final ItemBasicStructure ModernBuildings = new ItemBasicStructure(BasicStructureConfiguration.EnumBasicStructureName.ModernBuildings, 5);

    /* *********************************** Recipe Serializers *********************************** */
    public static final RecipeSerializer<ConditionedShapedRecipe> ConditionedShapedRecipeSeriaizer = new ConditionedShapedRecipe.Serializer();
    public static final RecipeSerializer<ConditionedShaplessRecipe> ConditionedShapelessRecipeSeriaizer = new ConditionedShaplessRecipe.Serializer();

    public static void registerModComponents() {
        ModRegistry.registerBlockEntities();

        ModRegistry.registerBlocks();

        ModRegistry.registerItems();

        ModRegistry.registerBluePrints();

        ModRegistry.registerItemBlocks();

        ModRegistry.RegisterClientToServerMessageHandlers();

        ModRegistry.RegisterRecipeSerializers();
    }

    private static void registerBlockEntities() {
        StructureScannerEntityType = Registry.register(
                Registry.BLOCK_ENTITY_TYPE,
                "prefab:structure_scanner_entity",
                FabricBlockEntityTypeBuilder
                        .create(StructureScannerBlockEntity::new, ModRegistry.StructureScanner)
                        .build(null));
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
        ModRegistry.registerBlock("block_grass_slab", ModRegistry.GrassSlab);
        ModRegistry.registerBlock("block_grass_stairs", ModRegistry.GrassStairs);
        ModRegistry.registerBlock(BlockCustomWall.EnumType.GRASS.getUnlocalizedName(), ModRegistry.GrassWall);
        ModRegistry.registerBlock(BlockCustomWall.EnumType.DIRT.getUnlocalizedName(), ModRegistry.DirtWall);
        ModRegistry.registerBlock("block_dirt_stairs", ModRegistry.DirtStairs);
        ModRegistry.registerBlock("block_dirt_slab", ModRegistry.DirtSlab);

        ModRegistry.registerBlock("block_structure_scanner", ModRegistry.StructureScanner);
    }

    private static void registerItems() {
        ModRegistry.registerItem("item_pile_of_bricks", ModRegistry.PileOfBricks);
        ModRegistry.registerItem("item_pallet_of_bricks", ModRegistry.PalletOfBricks);
        ModRegistry.registerItem("item_bundle_of_timber", ModRegistry.BundleOfTimber);
        ModRegistry.registerItem("item_heap_of_timber", ModRegistry.HeapOfTimber);
        ModRegistry.registerItem("item_ton_of_timber", ModRegistry.TonOfTimber);
        ModRegistry.registerItem("item_string_of_lanterns", ModRegistry.StringOfLanterns);
        ModRegistry.registerItem("item_coil_of_lanterns", ModRegistry.CoilOfLanterns);
        ModRegistry.registerItem("item_compressed_chest", ModRegistry.CompressedChest);
        ModRegistry.registerItem("item_warehouse_upgrade", ModRegistry.WarehouseUpgrade);

        ModRegistry.registerItem("item_swift_blade_wood", ModRegistry.SwiftBladeWood);
        ModRegistry.registerItem("item_swift_blade_stone", ModRegistry.SwiftBladeStone);
        ModRegistry.registerItem("item_swift_blade_iron", ModRegistry.SwiftBladeIron);
        ModRegistry.registerItem("item_swift_blade_diamond", ModRegistry.SwiftBladeDiamond);
        ModRegistry.registerItem("item_swift_blade_gold", ModRegistry.SwiftBladeGold);
        ModRegistry.registerItem("item_swift_blade_copper", ModRegistry.SwiftBladeCopper);
        ModRegistry.registerItem("item_swift_blade_osmium", ModRegistry.SwiftBladeOsmium);
        ModRegistry.registerItem("item_swift_blade_bronze", ModRegistry.SwiftBladeBronze);
        ModRegistry.registerItem("item_swift_blade_steel", ModRegistry.SwiftBladeSteel);
        ModRegistry.registerItem("item_swift_blade_obsidian", ModRegistry.SwiftBladeObsidian);

        ModRegistry.registerItem("item_sickle_wood", ModRegistry.SickleWood);
        ModRegistry.registerItem("item_sickle_stone", ModRegistry.SickleStone);
        ModRegistry.registerItem("item_sickle_gold", ModRegistry.SickleGold);
        ModRegistry.registerItem("item_sickle_iron", ModRegistry.SickleIron);
        ModRegistry.registerItem("item_sickle_diamond", ModRegistry.SickleDiamond);

        ModRegistry.registerItem("item_wooden_crate", ModRegistry.EmptyCrate);
        ModRegistry.registerItem("item_clutch_of_eggs", ModRegistry.ClutchOfEggs);
        ModRegistry.registerItem("item_carton_of_eggs", ModRegistry.CartonOfEggs);
        ModRegistry.registerItem("item_bunch_of_potatoes", ModRegistry.BunchOfPotatoes);
        ModRegistry.registerItem("item_crate_of_potatoes", ModRegistry.CrateOfPotatoes);
        ModRegistry.registerItem("item_bunch_of_carrots", ModRegistry.BunchOfCarrots);
        ModRegistry.registerItem("item_crate_of_carrots", ModRegistry.CrateOfCarrots);
        ModRegistry.registerItem("item_bunch_of_beets", ModRegistry.BunchOfBeets);
        ModRegistry.registerItem("item_crate_of_beets", ModRegistry.CrateOfBeets);
    }

    private static void registerBluePrints() {
        ModRegistry.registerItem("item_start_house", ModRegistry.StartHouse);
        ModRegistry.registerItem("item_instant_bridge", ModRegistry.InstantBridge);
        ModRegistry.registerItem("item_moderate_house", ModRegistry.ModerateHouse);
        ModRegistry.registerItem("item_bulldozer", ModRegistry.Bulldozer);
        ModRegistry.registerItem("item_creative_bulldozer", ModRegistry.CreativeBulldozer);
        ModRegistry.registerItem("item_structure_part", ModRegistry.StructurePart);


        ModRegistry.registerItem(BasicStructureConfiguration.EnumBasicStructureName.Barn.getItemTextureLocation().getPath(), ModRegistry.Barn);
        ModRegistry.registerItem(BasicStructureConfiguration.EnumBasicStructureName.AdvancedCoop.getItemTextureLocation().getPath(), ModRegistry.AdvancedCoop);
        ModRegistry.registerItem(BasicStructureConfiguration.EnumBasicStructureName.AdvancedHorseStable.getItemTextureLocation().getPath(), ModRegistry.AdvancedHorseStable);
        ModRegistry.registerItem(BasicStructureConfiguration.EnumBasicStructureName.MachineryTower.getItemTextureLocation().getPath(), ModRegistry.MachineryTower);
        ModRegistry.registerItem(BasicStructureConfiguration.EnumBasicStructureName.DefenseBunker.getItemTextureLocation().getPath(), ModRegistry.DefenseBunker);
        ModRegistry.registerItem(BasicStructureConfiguration.EnumBasicStructureName.MineshaftEntrance.getItemTextureLocation().getPath(), ModRegistry.MineshaftEntrance);
        ModRegistry.registerItem(BasicStructureConfiguration.EnumBasicStructureName.EnderGateway.getItemTextureLocation().getPath(), ModRegistry.EnderGateway);
        ModRegistry.registerItem(BasicStructureConfiguration.EnumBasicStructureName.AquaBase.getItemTextureLocation().getPath(), ModRegistry.AquaBase);
        ModRegistry.registerItem(BasicStructureConfiguration.EnumBasicStructureName.GrassyPlain.getItemTextureLocation().getPath(), ModRegistry.GrassyPlain);
        ModRegistry.registerItem(BasicStructureConfiguration.EnumBasicStructureName.MagicTemple.getItemTextureLocation().getPath(), ModRegistry.MagicTemple);
        ModRegistry.registerItem(BasicStructureConfiguration.EnumBasicStructureName.GreenHouse.getItemTextureLocation().getPath(), ModRegistry.GreenHouse);
        ModRegistry.registerItem(BasicStructureConfiguration.EnumBasicStructureName.WatchTower.getItemTextureLocation().getPath(), ModRegistry.WatchTower);
        ModRegistry.registerItem(BasicStructureConfiguration.EnumBasicStructureName.WelcomeCenter.getItemTextureLocation().getPath(), ModRegistry.WelcomeCenter);
        ModRegistry.registerItem(BasicStructureConfiguration.EnumBasicStructureName.Jail.getItemTextureLocation().getPath(), ModRegistry.Jail);
        ModRegistry.registerItem(BasicStructureConfiguration.EnumBasicStructureName.Saloon.getItemTextureLocation().getPath(), ModRegistry.Saloon);
        ModRegistry.registerItem(BasicStructureConfiguration.EnumBasicStructureName.SkiLodge.getItemTextureLocation().getPath(), ModRegistry.SkiLodge);
        ModRegistry.registerItem(BasicStructureConfiguration.EnumBasicStructureName.WindMill.getItemTextureLocation().getPath(), ModRegistry.WindMill);
        ModRegistry.registerItem(BasicStructureConfiguration.EnumBasicStructureName.TownHall.getItemTextureLocation().getPath(), ModRegistry.TownHall);
        ModRegistry.registerItem(BasicStructureConfiguration.EnumBasicStructureName.NetherGate.getItemTextureLocation().getPath(), ModRegistry.NetherGate);
        ModRegistry.registerItem(BasicStructureConfiguration.EnumBasicStructureName.SugarCaneFarm.getItemTextureLocation().getPath(), ModRegistry.SugarCaneFarm);
        ModRegistry.registerItem(BasicStructureConfiguration.EnumBasicStructureName.AdvancedAquaBase.getItemTextureLocation().getPath(), ModRegistry.AdvancedAquaBase);
        ModRegistry.registerItem(BasicStructureConfiguration.EnumBasicStructureName.WorkShop.getItemTextureLocation().getPath(), ModRegistry.WorkShop);
        ModRegistry.registerItem(BasicStructureConfiguration.EnumBasicStructureName.Warehouse.getItemTextureLocation().getPath(), ModRegistry.Warehouse);
        ModRegistry.registerItem(BasicStructureConfiguration.EnumBasicStructureName.ChickenCoop.getItemTextureLocation().getPath(), ModRegistry.ChickenCoop);
        ModRegistry.registerItem(BasicStructureConfiguration.EnumBasicStructureName.ProduceFarm.getItemTextureLocation().getPath(), ModRegistry.ProduceFarm);
        ModRegistry.registerItem(BasicStructureConfiguration.EnumBasicStructureName.TreeFarm.getItemTextureLocation().getPath(), ModRegistry.TreeFarm);
        ModRegistry.registerItem(BasicStructureConfiguration.EnumBasicStructureName.FishPond.getItemTextureLocation().getPath(), ModRegistry.FishPond);
        ModRegistry.registerItem(BasicStructureConfiguration.EnumBasicStructureName.AdvancedWarehouse.getItemTextureLocation().getPath(), ModRegistry.AdvancedWareHouse);
        ModRegistry.registerItem(BasicStructureConfiguration.EnumBasicStructureName.MonsterMasher.getItemTextureLocation().getPath(), ModRegistry.MonsterMasher);
        ModRegistry.registerItem(BasicStructureConfiguration.EnumBasicStructureName.HorseStable.getItemTextureLocation().getPath(), ModRegistry.HorseStable);
        ModRegistry.registerItem(BasicStructureConfiguration.EnumBasicStructureName.VillagerHouses.getItemTextureLocation().getPath(), ModRegistry.VillagerHouses);
        ModRegistry.registerItem(BasicStructureConfiguration.EnumBasicStructureName.ModernBuildings.getItemTextureLocation().getPath(), ModRegistry.ModernBuildings);
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
        ModRegistry.registerItem("block_grass_slab", ModRegistry.GrassSlabItem);
        ModRegistry.registerItem("block_grass_stairs", ModRegistry.GrassStairsItem);
        ModRegistry.registerItem(BlockCustomWall.EnumType.GRASS.getUnlocalizedName(), ModRegistry.GrassWallItem);
        ModRegistry.registerItem(BlockCustomWall.EnumType.DIRT.getUnlocalizedName(), ModRegistry.DirtWallItem);
        ModRegistry.registerItem("block_dirt_stairs", ModRegistry.DirtStairsItem);
        ModRegistry.registerItem("block_dirt_slab", ModRegistry.DirtSlabItem);

        ModRegistry.registerItem("block_structure_scanner", ModRegistry.StructureScannerItem);
    }

    /**
     * This is where the mod messages are registered.
     */
    private static void RegisterClientToServerMessageHandlers() {

        ModRegistry.registerStructureBuilderMessageHandler();
    }

    private static void RegisterRecipeSerializers() {
        Registry.register(Registry.RECIPE_SERIALIZER, new Identifier(Prefab.MODID, "condition_crafting_shaped"), ModRegistry.ConditionedShapedRecipeSeriaizer);
        Registry.register(Registry.RECIPE_SERIALIZER, new Identifier(Prefab.MODID, "condition_crafting_shapeless"), ModRegistry.ConditionedShapelessRecipeSeriaizer);
    }

    private static void registerBlock(String registryName, Block block) {
        Registry.register(Registry.BLOCK, new Identifier(Prefab.MODID, registryName), block);
    }

    private static void registerItem(String registryName, Item item) {
        Registry.register(Registry.ITEM, new Identifier(Prefab.MODID, registryName), item);
    }

    private static void registerStructureBuilderMessageHandler() {
        ServerSidePacketRegistry.INSTANCE.register(ModRegistry.StructureBuild,
                (packetContext, attachedData) -> {
                    // Can only access the "attachedData" on the "network thread" which is here.
                    StructureTagMessage message = StructureTagMessage.decode(attachedData);
                    StructureTagMessage.EnumStructureConfiguration structureConfig = message.getStructureConfig();

                    packetContext.getTaskQueue().execute(() -> {
                        ServerPlayerEntity serverPlayerEntity = (ServerPlayerEntity) packetContext.getPlayer();
                        // This is now on the "main" server thread and things can be done in the world!
                        StructureConfiguration configuration = structureConfig.structureConfig.ReadFromCompoundNBT(message.getMessageTag());

                        configuration.BuildStructure(serverPlayerEntity, serverPlayerEntity.getServerWorld());
                    });
                }
        );
    }

    public enum CustomItemTier implements ToolMaterial {
        COPPER("Copper", ToolMaterials.STONE.getMiningLevel(), ToolMaterials.STONE.getDurability(), ToolMaterials.STONE.getMiningSpeedMultiplier(),
                ToolMaterials.STONE.getAttackDamage(), ToolMaterials.STONE.getEnchantability(), () -> {
            return Ingredient
                    .fromTag(ItemTags.getTagGroup().getTag(new Identifier("c", "copper_ingots")));
        }),
        OSMIUM("Osmium", ToolMaterials.IRON.getMiningLevel(), 500, ToolMaterials.IRON.getMiningSpeedMultiplier(),
                ToolMaterials.IRON.getAttackDamage() + .5f, ToolMaterials.IRON.getEnchantability(), () -> {
            return Ingredient
                    .fromTag(ItemTags.getTagGroup().getTag(new Identifier("c", "osmium_ingots")));
        }),
        BRONZE("Bronze", ToolMaterials.IRON.getMiningLevel(), ToolMaterials.IRON.getDurability(), ToolMaterials.IRON.getMiningSpeedMultiplier(),
                ToolMaterials.IRON.getAttackDamage(), ToolMaterials.IRON.getEnchantability(), () -> {
            return Ingredient
                    .fromTag(ItemTags.getTagGroup().getTag(new Identifier("c", "bronze_ingots")));
        }),
        STEEL("Steel", ToolMaterials.DIAMOND.getMiningLevel(), (int) (ToolMaterials.IRON.getDurability() * 1.5),
                ToolMaterials.DIAMOND.getMiningSpeedMultiplier(), ToolMaterials.DIAMOND.getAttackDamage(),
                ToolMaterials.DIAMOND.getEnchantability(), () -> {
            return Ingredient
                    .fromTag(ItemTags.getTagGroup().getTag(new Identifier("c", "steel_ingots")));
        }),
        OBSIDIAN("Obsidian", ToolMaterials.DIAMOND.getMiningLevel() + 1, (int) (ToolMaterials.DIAMOND.getDurability() * 1.5),
                ToolMaterials.DIAMOND.getMiningSpeedMultiplier(), ToolMaterials.DIAMOND.getAttackDamage() + 2,
                ToolMaterials.DIAMOND.getEnchantability(), () -> {
            return Ingredient.ofItems(Item.fromBlock(Blocks.OBSIDIAN));
        });

        private final String name;
        private final int harvestLevel;
        private final int maxUses;
        private final float efficiency;
        private final float attackDamage;
        private final int enchantability;
        private final Lazy<Ingredient> repairMaterial;

        CustomItemTier(String name, int harvestLevelIn, int maxUsesIn, float efficiencyIn, float attackDamageIn,
                       int enchantability, Supplier<Ingredient> repairMaterialIn) {
            this.name = name;
            this.harvestLevel = harvestLevelIn;
            this.maxUses = maxUsesIn;
            this.efficiency = efficiencyIn;
            this.attackDamage = attackDamageIn;
            this.enchantability = enchantability;
            this.repairMaterial = new Lazy<>(repairMaterialIn);
        }

        public static CustomItemTier getByName(String name) {
            for (CustomItemTier item : CustomItemTier.values()) {
                if (item.getName().equals(name)) {
                    return item;
                }
            }

            return null;
        }

        public String getName() {
            return this.name;
        }

        public int getDurability() {
            return this.maxUses;
        }

        public float getMiningSpeedMultiplier() {
            return this.efficiency;
        }

        public float getAttackDamage() {
            return this.attackDamage;
        }

        public int getMiningLevel() {
            return this.harvestLevel;
        }

        public int getEnchantability() {
            return this.enchantability;
        }

        public Ingredient getRepairIngredient() {
            return this.repairMaterial.get();
        }
    }
}