package com.theomenden.prefabricated.generation.providers;

import com.theomenden.prefabricated.ModRegistry;
import com.theomenden.prefabricated.generation.tags.PrefabricatedBlockTags;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;

import java.util.concurrent.CompletableFuture;

public final class PrefabricatedBlockTagProvider extends FabricTagProvider.BlockTagProvider {

    public PrefabricatedBlockTagProvider(FabricDataOutput fabricDataOutput, CompletableFuture<HolderLookup.Provider> registryLookupCompleteFuture){
        super(fabricDataOutput, registryLookupCompleteFuture);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {
        this.getOrCreateTagBuilder(PrefabricatedBlockTags.PREFAB_DIRT_BLOCKS)
                .add(ModRegistry.DirtSlab)
                .add(ModRegistry.DirtStairs)
                .add(ModRegistry.DirtWall)
                .add(ModRegistry.CompressedDirt)
                .add(ModRegistry.DoubleCompressedDirt);

        this.getOrCreateTagBuilder(PrefabricatedBlockTags.PREFAB_QUARTZ_BLOCKS)
                .add(ModRegistry.QuartzCrete)
                .add(ModRegistry.QuartzCreteBricks)
                .add(ModRegistry.QuartzCretePillar)
                .add(ModRegistry.QuartzCreteWall)
                .add(ModRegistry.QuartzCreteSlab)
                .add(ModRegistry.QuartzCreteStairs)
                .add(ModRegistry.SmoothQuartzCrete)
                .add(ModRegistry.SmoothQuartzCreteSlab)
                .add(ModRegistry.SmoothQuartzCreteStairs)
                .add(ModRegistry.SmoothQuartzCreteWall);
    }

    private FabricTagBuilder getOrCreateTagBuilder(ResourceLocation id){
        TagKey<Block> tag = TagKey.create(Registries.BLOCK, id);
        return this.getOrCreateTagBuilder(tag);
    }
}
