package com.theomenden.prefabricated.generation.providers;

import com.theomenden.prefabricated.ModRegistry;
import com.theomenden.prefabricated.generation.tags.PrefabricatedItemTags;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

import java.util.concurrent.CompletableFuture;

public final class PrefabricatedItemTagProvider extends FabricTagProvider.ItemTagProvider {
    public PrefabricatedItemTagProvider(FabricDataOutput fabricDataOutput, CompletableFuture<HolderLookup.Provider> providerCompletableFuture, BlockTagProvider blockTagProvider) {
        super(fabricDataOutput, providerCompletableFuture, blockTagProvider);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {
        this.getOrCreateTagBuilder(ItemTags.DIRT)
                .add(ModRegistry.DirtStairsItem)
                .add(ModRegistry.DirtWallItem)
                .add(ModRegistry.DirtSlabItem);

        this.getOrCreateTagBuilder(ItemTags.SWORDS)
                .add(ModRegistry.SwiftBladeCopper)
                .add(ModRegistry.SwiftBladeIron)
                .add(ModRegistry.SwiftBladeGold)
                .add(ModRegistry.SwiftBladeDiamond)
                .add(ModRegistry.SwiftBladeNetherite)
                .add(ModRegistry.SwiftBladeSteel)
                .add(ModRegistry.SwiftBladeStone)
                .add(ModRegistry.SwiftBladeObsidian)
                .add(ModRegistry.SwiftBladeWood)
                .add(ModRegistry.SwiftBladeOsmium);

        this.copy(BlockTags.STAIRS, ItemTags.STAIRS);
        this.copy(BlockTags.WALLS, ItemTags.WALLS);
        this.copy(BlockTags.SLABS, ItemTags.SLABS);
    }

    private FabricTagBuilder getOrCreateTagBuilder(ResourceLocation id) {
        TagKey<Item> tag = TagKey.create(Registries.ITEM, id);
        return this.getOrCreateTagBuilder(tag);
    }

    private void copyFromResource(ResourceLocation id) {
        TagKey<Block> blockTagKey = TagKey.create(Registries.BLOCK, id);
        TagKey<Item> itemTagKey = TagKey.create(Registries.ITEM, id);

        this.copy(blockTagKey, itemTagKey);
    }
}
