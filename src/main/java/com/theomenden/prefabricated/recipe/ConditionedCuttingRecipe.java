package com.theomenden.prefabricated.recipe;

import com.google.common.base.Strings;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.theomenden.prefabricated.ModRegistry;
import com.theomenden.prefabricated.Prefab;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.GsonHelper;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.StonecutterRecipe;
import net.minecraft.world.level.block.Blocks;
import org.jetbrains.annotations.NotNull;

public final class ConditionedCuttingRecipe extends StonecutterRecipe {
    private final String configName;
    public ConditionedCuttingRecipe(ResourceLocation id, String group, Ingredient ingredient, ItemStack result, String configName) {
        super(id, group, ingredient, result);

        this.configName = configName;
    }

    public @NotNull ItemStack getToastSymbol() {
        return new ItemStack(Blocks.FURNACE);
    }

    public @NotNull RecipeSerializer<?> getSerializer() {
        return ModRegistry.CONDITIONED_SMELTING_RECIPE_RECIPE_SERIALIZER;
    }

    public static class Serializer implements RecipeSerializer<ConditionedCuttingRecipe> {
        public @NotNull ConditionedCuttingRecipe fromJson(ResourceLocation identifier, JsonObject jsonObject) {
            String string = GsonHelper.getAsString(jsonObject, "group", "");
            String configName = GsonHelper.getAsString(jsonObject, "configName", "");
            JsonElement jsonElement = GsonHelper.isArrayNode(jsonObject, "ingredient") ?  GsonHelper.getAsJsonArray(jsonObject, "ingredient") : GsonHelper.getAsJsonObject(jsonObject, "ingredient");
            Ingredient ingredient = Ingredient.fromJson((JsonElement)jsonElement);
            String string2 = GsonHelper.getAsString(jsonObject, "result");
            ResourceLocation identifier2 = new ResourceLocation(string2);
            ItemStack itemStack = new ItemStack(BuiltInRegistries.ITEM.getOptional(identifier2).orElseThrow(() -> {
                return new IllegalStateException("Item: " + string2 + " does not exist");
            }));

            itemStack = this.validateRecipeOutput(itemStack, configName);

            float experience = GsonHelper.getAsFloat(jsonObject, "experience", 0.0F);
            int cookingtime = GsonHelper.getAsInt(jsonObject, "cookingtime", 200);
            return new ConditionedCuttingRecipe(identifier, string, ingredient, itemStack, configName);
        }

        public @NotNull ConditionedCuttingRecipe fromNetwork(ResourceLocation identifier, FriendlyByteBuf packetByteBuf) {
            String group = packetByteBuf.readUtf();
            String configName = packetByteBuf.readUtf() ;
            Ingredient ingredient = Ingredient.fromNetwork(packetByteBuf);
            ItemStack itemStack = this.validateRecipeOutput(packetByteBuf.readItem(), configName);
            float experience = packetByteBuf.readFloat();
            int cookTime = packetByteBuf.readVarInt();
            return new ConditionedCuttingRecipe(identifier, group, ingredient, itemStack, configName);
        }

        public void toNetwork(FriendlyByteBuf packetByteBuf, ConditionedCuttingRecipe abstractCuttingRecipe) {
            packetByteBuf.writeUtf(abstractCuttingRecipe.group);
            packetByteBuf.writeUtf(abstractCuttingRecipe.configName);
            abstractCuttingRecipe.ingredient.toNetwork(packetByteBuf);
            packetByteBuf.writeItem(abstractCuttingRecipe.result);
        }

        public ItemStack validateRecipeOutput(ItemStack originalOutput, String configName) {
            if (originalOutput == ItemStack.EMPTY) {
                return ItemStack.EMPTY;
            }

            if (!Strings.isNullOrEmpty(configName)
                    && Prefab.serverConfiguration.recipes.containsKey(configName)
                    && !Prefab.serverConfiguration.recipes.get(configName)) {
                // The configuration option for this recipe was turned off.
                // Specify that the recipe has no output which basically makes it disabled.
                return ItemStack.EMPTY;
            }

            return originalOutput;
        }
    }
}
