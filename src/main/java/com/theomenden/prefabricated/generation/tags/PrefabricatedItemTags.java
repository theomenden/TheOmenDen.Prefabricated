package com.theomenden.prefabricated.generation.tags;

import com.theomenden.prefabricated.Prefab;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;

public final class PrefabricatedItemTags {

    public static final TagKey<Item> BLUEPRINTS = register("blueprints");

    private PrefabricatedItemTags(){
    }

    private static TagKey<Item> register(String id) {
        return TagKey.create(Registries.ITEM, new ResourceLocation(Prefab.MODID, id));
    }
}
