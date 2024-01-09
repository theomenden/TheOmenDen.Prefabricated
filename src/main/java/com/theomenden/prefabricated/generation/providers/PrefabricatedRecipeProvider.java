package com.theomenden.prefabricated.generation.providers;

import com.theomenden.prefabricated.ModRegistry;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeProvider;

import java.util.function.Consumer;

public final class PrefabricatedRecipeProvider extends FabricRecipeProvider {
    public PrefabricatedRecipeProvider(FabricDataOutput dataOutput) {
        super(dataOutput);
    }

    @Override
    public void buildRecipes(Consumer<FinishedRecipe> exporter) {
        RecipeProvider.slab(exporter, RecipeCategory.BUILDING_BLOCKS, ModRegistry.QuartzCreteSlab, ModRegistry.QuartzCrete);
    }
}
