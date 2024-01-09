package com.theomenden.prefabricated.registries;

import com.theomenden.prefabricated.ModRegistry;
import com.theomenden.prefabricated.Prefab;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.ChatFormatting;
import net.minecraft.Util;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;

import java.util.Comparator;

public class ModRegistryGroups {

    public static final ResourceKey<CreativeModeTab> PREFABRICATED_ITEM_GROUP = ResourceKey.create(Registries.CREATIVE_MODE_TAB, new ResourceLocation(Prefab.MODID, "logo"));
    public static final ResourceKey<CreativeModeTab> PREFABRICATED_BUILDING_BLOCKS = ResourceKey.create(Registries.CREATIVE_MODE_TAB, new ResourceLocation(Prefab.MODID, "prefabricated_building_blocks"));
    public static final ResourceKey<CreativeModeTab> PREFABRICATED_UTILITY_BLOCKS = ResourceKey.create(Registries.CREATIVE_MODE_TAB, new ResourceLocation(Prefab.MODID, "prefabricated_utility_blocks"));

    public static final ResourceKey<CreativeModeTab> PREFABRICATED_WEAPONS = ResourceKey.create(Registries.CREATIVE_MODE_TAB, new ResourceLocation("prefabricated.weapons"));
    public static final ResourceKey<CreativeModeTab> PREFABRICATED_TOOLS = ResourceKey.create(Registries.CREATIVE_MODE_TAB, new ResourceLocation("prefabricated.tools"));

    public static Component PREFABRICATED_TAB_TEXT = Component
            .literal(" (Prefabricated)")
            .withStyle(ChatFormatting.DARK_AQUA);

    public static final CreativeModeTab MAIN_ITEM_GROUP = FabricItemGroup
            .builder()
            .title(Component.translatable(
                    Util.makeDescriptionId("itemGroup", new ResourceLocation(Prefab.MODID, "logo"))
            ))
            .icon(() -> new ItemStack(ModRegistry.LogoItem))
            .displayItems((context, entries) ->
                    BuiltInRegistries.ITEM
                            .holders()
                            .filter(itemReference -> itemReference
                                    .key()
                                    .location()
                                    .getNamespace()
                                    .equals(Prefab.MODID))
                            .sorted(Comparator.comparing(itemReference -> itemReference
                                    .key()
                                    .location()
                                    .getPath()))
                            .map(Holder.Reference::value)
                            .forEachOrdered(entries::accept))
            .build();


    public static final CreativeModeTab BUILDING_BLOCK_GROUP = FabricItemGroup
            .builder()
            .title(Component
                    .translatable(
                            "itemGroup.prefabricated.building_blocks")
                    .append(PREFABRICATED_TAB_TEXT))
            .icon(() -> new ItemStack(ModRegistry.LogoItem))
            .displayItems((context, entries) -> {
                entries.accept(ModRegistry.DirtSlab);
                entries.accept(ModRegistry.DirtStairs);
                entries.accept(ModRegistry.DirtWall);
                entries.accept(ModRegistry.GlassSlab);
                entries.accept(ModRegistry.GlassStairs);
                entries.accept(ModRegistry.GrassSlab);
                entries.accept(ModRegistry.GrassStairs);
                entries.accept(ModRegistry.GrassWall);
                entries.accept(ModRegistry.QuartzCrete);
                entries.accept(ModRegistry.QuartzCreteSlab);
                entries.accept(ModRegistry.ChiseledQuartzCrete);
                entries.accept(ModRegistry.QuartzCreteWall);
                entries.accept(ModRegistry.QuartzCreteBricks);
                entries.accept(ModRegistry.QuartzCreteStairs);
                entries.accept(ModRegistry.QuartzCretePillar);
                entries.accept(ModRegistry.SmoothQuartzCrete);
                entries.accept(ModRegistry.SmoothQuartzCreteWall);
                entries.accept(ModRegistry.SmoothQuartzCreteStairs);
                entries.accept(ModRegistry.SmoothQuartzCreteSlab);
                entries.accept(ModRegistry.Phasic);
                entries.accept(ModRegistry.Boundary);
                entries.accept(ModRegistry.DarkLamp);
                entries.accept(ModRegistry.PaperLantern);
            })
            .build();


    public static final CreativeModeTab UTILITY_BLOCK_GROUP = FabricItemGroup
            .builder()
            .title(Component
                    .translatable("itemGroup.prefabricated.utility_blocks")
                    .append(PREFABRICATED_TAB_TEXT))
            .icon(() -> new ItemStack(ModRegistry.LogoItem))
            .displayItems((context, entries) -> {
                entries.accept(ModRegistry.InstantBridge);
                entries.accept(ModRegistry.House);
                entries.accept(ModRegistry.HouseImproved);
                entries.accept(ModRegistry.HouseAdvanced);
                entries.accept(ModRegistry.Bulldozer);
                entries.accept(ModRegistry.CreativeBulldozer);
                entries.accept(ModRegistry.MachineryTower);
                entries.accept(ModRegistry.DefenseBunker);
                entries.accept(ModRegistry.MineshaftEntrance);
                entries.accept(ModRegistry.EnderGateway);
                entries.accept(ModRegistry.AquaBase);
                entries.accept(ModRegistry.GrassyPlain);
                entries.accept(ModRegistry.MagicTemple);
                entries.accept(ModRegistry.WatchTower);
                entries.accept(ModRegistry.WelcomeCenter);
                entries.accept(ModRegistry.Jail);
                entries.accept(ModRegistry.Saloon);
                entries.accept(ModRegistry.SkiLodge);
                entries.accept(ModRegistry.WindMill);
                entries.accept(ModRegistry.TownHall);
                entries.accept(ModRegistry.NetherGate);
                entries.accept(ModRegistry.AquaBaseImproved);
                entries.accept(ModRegistry.Warehouse);
                entries.accept(ModRegistry.WareHouseImproved);
                entries.accept(ModRegistry.VillagerHouses);
                entries.accept(ModRegistry.ModernBuildings);
                entries.accept(ModRegistry.ModernBuildingsImproved);
                entries.accept(ModRegistry.ModernBuildingsAdvanced);
                entries.accept(ModRegistry.Farm);
                entries.accept(ModRegistry.FarmImproved);
                entries.accept(ModRegistry.FarmAdvanced);
            })
            .build();

    public static final CreativeModeTab PREFABRICATED_WEAPONS_GROUP = FabricItemGroup
            .builder()
            .title(Component
                    .translatable("itemGroup.prefabricated.weapons")
                    .append(PREFABRICATED_TAB_TEXT))
            .icon(() -> new ItemStack(ModRegistry.LogoItem))
            .displayItems((context, entries) -> {
                entries.accept(ModRegistry.SwiftBladeWood);
                entries.accept(ModRegistry.SwiftBladeStone);
                entries.accept(ModRegistry.SwiftBladeCopper);
                entries.accept(ModRegistry.SwiftBladeBronze);
                entries.accept(ModRegistry.SwiftBladeSteel);
                entries.accept(ModRegistry.SwiftBladeIron);
                entries.accept(ModRegistry.SwiftBladeGold);
                entries.accept(ModRegistry.SwiftBladeDiamond);
                entries.accept(ModRegistry.SwiftBladeObsidian);
                entries.accept(ModRegistry.SwiftBladeOsmium);
                entries.accept(ModRegistry.SwiftBladeNetherite);
            })
            .build();

    public static final CreativeModeTab PREFABRICATED_TOOLS_GROUP = FabricItemGroup
            .builder()
            .title(Component
                    .translatable("itemGroup.prefabricated.tools")
                    .append(PREFABRICATED_TAB_TEXT))
            .icon(() -> new ItemStack(ModRegistry.LogoItem))
            .displayItems((context, entries) -> {
                entries.accept(ModRegistry.SickleWood);
                entries.accept(ModRegistry.SickleStone);
                entries.accept(ModRegistry.SickleIron);
                entries.accept(ModRegistry.SickleGold);
                entries.accept(ModRegistry.SickleDiamond);
                entries.accept(ModRegistry.SickleNetherite);
            })
            .build();
    public static void registerItemGroups(){
        Registry.register(BuiltInRegistries.CREATIVE_MODE_TAB, PREFABRICATED_ITEM_GROUP,MAIN_ITEM_GROUP);
        Registry.register(BuiltInRegistries.CREATIVE_MODE_TAB, PREFABRICATED_BUILDING_BLOCKS, BUILDING_BLOCK_GROUP);
        Registry.register(BuiltInRegistries.CREATIVE_MODE_TAB, PREFABRICATED_UTILITY_BLOCKS, UTILITY_BLOCK_GROUP );
        Registry.register(BuiltInRegistries.CREATIVE_MODE_TAB, PREFABRICATED_WEAPONS, PREFABRICATED_WEAPONS_GROUP );
        Registry.register(BuiltInRegistries.CREATIVE_MODE_TAB, PREFABRICATED_TOOLS, PREFABRICATED_TOOLS_GROUP );

       registerUtilityItems();
       registerBuildingBlocks();
       registerToolItems();
       registerCombatItems();
    }

    private static void registerUtilityItems() {
        ItemGroupEvents.modifyEntriesEvent(PREFABRICATED_UTILITY_BLOCKS).register(entries -> {
            entries.accept(ModRegistry.InstantBridge);
            entries.accept(ModRegistry.House);
            entries.accept(ModRegistry.HouseImproved);
            entries.accept(ModRegistry.HouseAdvanced);
            entries.accept(ModRegistry.Bulldozer);
            entries.accept(ModRegistry.CreativeBulldozer);
            entries.accept(ModRegistry.MachineryTower);
            entries.accept(ModRegistry.DefenseBunker);
            entries.accept(ModRegistry.MineshaftEntrance);
            entries.accept(ModRegistry.EnderGateway);
            entries.accept(ModRegistry.AquaBase);
            entries.accept(ModRegistry.GrassyPlain);
            entries.accept(ModRegistry.MagicTemple);
            entries.accept(ModRegistry.WatchTower);
            entries.accept(ModRegistry.WelcomeCenter);
            entries.accept(ModRegistry.Jail);
            entries.accept(ModRegistry.Saloon);
            entries.accept(ModRegistry.SkiLodge);
            entries.accept(ModRegistry.WindMill);
            entries.accept(ModRegistry.TownHall);
            entries.accept(ModRegistry.NetherGate);
            entries.accept(ModRegistry.AquaBaseImproved);
            entries.accept(ModRegistry.Warehouse);
            entries.accept(ModRegistry.WareHouseImproved);
            entries.accept(ModRegistry.VillagerHouses);
            entries.accept(ModRegistry.ModernBuildings);
            entries.accept(ModRegistry.ModernBuildingsImproved);
            entries.accept(ModRegistry.ModernBuildingsAdvanced);
            entries.accept(ModRegistry.Farm);
            entries.accept(ModRegistry.FarmImproved);
            entries.accept(ModRegistry.FarmAdvanced);
        });
    }

    private static void registerToolItems() {
        ItemGroupEvents.modifyEntriesEvent(PREFABRICATED_TOOLS).register(entries -> {
            entries.accept(ModRegistry.SickleWood);
            entries.accept(ModRegistry.SickleStone);
            entries.accept(ModRegistry.SickleIron);
            entries.accept(ModRegistry.SickleGold);
            entries.accept(ModRegistry.SickleDiamond);
            entries.accept(ModRegistry.SickleNetherite);
        });
    }

    private static void registerCombatItems() {
        ItemGroupEvents.modifyEntriesEvent(PREFABRICATED_WEAPONS).register(entries -> {
            entries.accept(ModRegistry.SwiftBladeWood);
            entries.accept(ModRegistry.SwiftBladeStone);
            entries.accept(ModRegistry.SwiftBladeCopper);
            entries.accept(ModRegistry.SwiftBladeBronze);
            entries.accept(ModRegistry.SwiftBladeSteel);
            entries.accept(ModRegistry.SwiftBladeIron);
            entries.accept(ModRegistry.SwiftBladeGold);
            entries.accept(ModRegistry.SwiftBladeDiamond);
            entries.accept(ModRegistry.SwiftBladeObsidian);
            entries.accept(ModRegistry.SwiftBladeOsmium);
            entries.accept(ModRegistry.SwiftBladeNetherite);
        });
    }

    private static void registerBuildingBlocks() {
        ItemGroupEvents.modifyEntriesEvent(PREFABRICATED_BUILDING_BLOCKS).register(entries -> {
            entries.accept(ModRegistry.DirtSlab);
            entries.accept(ModRegistry.DirtStairs);
            entries.accept(ModRegistry.DirtWall);
            entries.accept(ModRegistry.GlassSlab);
            entries.accept(ModRegistry.GlassStairs);
            entries.accept(ModRegistry.GrassSlab);
            entries.accept(ModRegistry.GrassStairs);
            entries.accept(ModRegistry.GrassWall);
            entries.accept(ModRegistry.PaperLantern);
            entries.accept(ModRegistry.QuartzCrete);
            entries.accept(ModRegistry.QuartzCreteSlab);
            entries.accept(ModRegistry.ChiseledQuartzCrete);
            entries.accept(ModRegistry.QuartzCreteWall);
            entries.accept(ModRegistry.QuartzCreteBricks);
            entries.accept(ModRegistry.QuartzCreteStairs);
            entries.accept(ModRegistry.QuartzCretePillar);
            entries.accept(ModRegistry.SmoothQuartzCrete);
            entries.accept(ModRegistry.SmoothQuartzCreteWall);
            entries.accept(ModRegistry.SmoothQuartzCreteStairs);
            entries.accept(ModRegistry.SmoothQuartzCreteSlab);
            entries.accept(ModRegistry.Phasic);
            entries.accept(ModRegistry.Boundary);
            entries.accept(ModRegistry.DarkLamp);
            entries.accept(ModRegistry.PaperLantern);
        });
    }
}
