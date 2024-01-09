package com.theomenden.prefabricated.generation.tags;

import com.theomenden.prefabricated.Prefab;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;

public final class PrefabricatedBlockTags {
    public static final TagKey<Block> PREFAB_DIRT_BLOCKS = register("dirt_blocks");
    public static final TagKey<Block> PREFAB_QUARTZ_BLOCKS = register("quartz_blocks");

    private PrefabricatedBlockTags(){
    }

    private static TagKey<Block> register(String id) {
        return TagKey.create(Registries.BLOCK, new ResourceLocation(Prefab.MODID, id));
    }
}
