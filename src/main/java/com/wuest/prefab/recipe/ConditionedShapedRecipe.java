package com.wuest.prefab.recipe;

import com.google.common.annotations.VisibleForTesting;
import com.google.common.base.Strings;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.google.gson.*;
import com.wuest.prefab.ModRegistry;
import com.wuest.prefab.Prefab;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.inventory.CraftingInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.recipe.Ingredient;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.recipe.ShapedRecipe;
import net.minecraft.util.Identifier;
import net.minecraft.util.JsonHelper;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.World;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class ConditionedShapedRecipe extends ShapedRecipe {

    private final int width;
    private final int height;
    private final DefaultedList<Ingredient> inputs;
    private final ItemStack output;
    private final Identifier id;
    private final String group;
    private final String configName;

    public ConditionedShapedRecipe(Identifier id, String group, int width, int height, DefaultedList<Ingredient> ingredients, ItemStack output, String configName) {
        super(id, group, width, height, ingredients, output);

        this.id = id;
        this.group = group;
        this.width = width;
        this.height = height;
        this.inputs = ingredients;
        this.output = output;
        this.configName = configName;
    }

    private static DefaultedList<Ingredient> getIngredients(String[] pattern, Map<String, Ingredient> key, int width, int height) {
        DefaultedList<Ingredient> defaultedList = DefaultedList.ofSize(width * height, Ingredient.EMPTY);
        Set<String> set = Sets.newHashSet(key.keySet());
        set.remove(" ");

        for (int i = 0; i < pattern.length; ++i) {
            for (int j = 0; j < pattern[i].length(); ++j) {
                String string = pattern[i].substring(j, j + 1);
                Ingredient ingredient = key.get(string);
                if (ingredient == null) {
                    throw new JsonSyntaxException("Pattern references symbol '" + string + "' but it's not defined in the key");
                }

                set.remove(string);
                defaultedList.set(j + width * i, ingredient);
            }
        }

        if (!set.isEmpty()) {
            throw new JsonSyntaxException("Key defines symbols that aren't used in pattern: " + set);
        } else {
            return defaultedList;
        }
    }

    @VisibleForTesting
    static String[] combinePattern(String... lines) {
        int i = Integer.MAX_VALUE;
        int j = 0;
        int k = 0;
        int l = 0;

        for (int m = 0; m < lines.length; ++m) {
            String string = lines[m];
            i = Math.min(i, findNextIngredient(string));
            int n = findNextIngredientReverse(string);
            j = Math.max(j, n);
            if (n < 0) {
                if (k == m) {
                    ++k;
                }

                ++l;
            } else {
                l = 0;
            }
        }

        if (lines.length == l) {
            return new String[0];
        } else {
            String[] strings = new String[lines.length - l - k];

            for (int o = 0; o < strings.length; ++o) {
                strings[o] = lines[o + k].substring(i, j + 1);
            }

            return strings;
        }
    }

    private static int findNextIngredient(String pattern) {
        int i;
        for (i = 0; i < pattern.length() && pattern.charAt(i) == ' '; ++i) {
        }

        return i;
    }

    private static int findNextIngredientReverse(String pattern) {
        int i;
        for (i = pattern.length() - 1; i >= 0 && pattern.charAt(i) == ' '; --i) {
        }

        return i;
    }

    private static String[] getPattern(JsonArray json) {
        String[] strings = new String[json.size()];
        if (strings.length > 3) {
            throw new JsonSyntaxException("Invalid pattern: too many rows, 3 is maximum");
        } else if (strings.length == 0) {
            throw new JsonSyntaxException("Invalid pattern: empty pattern not allowed");
        } else {
            for (int i = 0; i < strings.length; ++i) {
                String string = JsonHelper.asString(json.get(i), "pattern[" + i + "]");
                if (string.length() > 3) {
                    throw new JsonSyntaxException("Invalid pattern: too many columns, 3 is maximum");
                }

                if (i > 0 && strings[0].length() != string.length()) {
                    throw new JsonSyntaxException("Invalid pattern: each row must be the same width");
                }

                strings[i] = string;
            }

            return strings;
        }
    }

    private static Map<String, Ingredient> getComponents(JsonObject json) {
        Map<String, Ingredient> map = Maps.newHashMap();
        Iterator var2 = json.entrySet().iterator();

        while (var2.hasNext()) {
            Map.Entry<String, JsonElement> entry = (Map.Entry) var2.next();
            if (((String) entry.getKey()).length() != 1) {
                throw new JsonSyntaxException("Invalid key entry: '" + (String) entry.getKey() + "' is an invalid symbol (must be 1 character only).");
            }

            if (" ".equals(entry.getKey())) {
                throw new JsonSyntaxException("Invalid key entry: ' ' is a reserved symbol.");
            }

            JsonElement ingredientJSON = entry.getValue();
            Ingredient ingredient = Ingredient.fromJson(ingredientJSON);

            if (ingredient.isEmpty() || ingredient.getMatchingStacks().length == 0) {
                // Unable to find a corresponding item for this key. Clear out all entries and return.
                map.clear();
                break;
            }

            map.put(entry.getKey(), ingredient);
        }

        map.put(" ", Ingredient.EMPTY);
        return map;
    }

    public static ItemStack getItemStack(JsonObject json) {
        String string = JsonHelper.getString(json, "item");

        Item item = (Item) Registry.ITEM.getOrEmpty(new Identifier(string)).orElseThrow(() -> {
            return new JsonSyntaxException("Unknown item '" + string + "'");
        });

        int stackCount = 1;

        if (JsonHelper.hasNumber(json, "count")) {
            stackCount = JsonHelper.getInt(json, "count");
        }

        if (json.has("data")) {
            throw new JsonParseException("Disallowed data tag found");
        } else {
            return new ItemStack(item, stackCount);
        }
    }

    public Identifier getId() {
        return this.id;
    }

    public RecipeSerializer<?> getSerializer() {
        return ModRegistry.ConditionedShapedRecipeSeriaizer;
    }

    @Environment(EnvType.CLIENT)
    public String getGroup() {
        return this.group;
    }

    public ItemStack getOutput() {
        return this.output;
    }

    public DefaultedList<Ingredient> getPreviewInputs() {
        return this.inputs;
    }

    @Environment(EnvType.CLIENT)
    public boolean fits(int width, int height) {
        return width >= this.width && height >= this.height;
    }

    public boolean matches(CraftingInventory craftingInventory, World world) {
        for (int i = 0; i <= craftingInventory.getWidth() - this.width; ++i) {
            for (int j = 0; j <= craftingInventory.getHeight() - this.height; ++j) {
                if (this.matchesSmall(craftingInventory, i, j, true)) {
                    return true;
                }

                if (this.matchesSmall(craftingInventory, i, j, false)) {
                    return true;
                }
            }
        }

        return false;
    }

    private boolean matchesSmall(CraftingInventory inv, int offsetX, int offsetY, boolean bl) {
        for (int i = 0; i < inv.getWidth(); ++i) {
            for (int j = 0; j < inv.getHeight(); ++j) {
                int k = i - offsetX;
                int l = j - offsetY;
                Ingredient ingredient = Ingredient.EMPTY;
                if (k >= 0 && l >= 0 && k < this.width && l < this.height) {
                    if (bl) {
                        ingredient = this.inputs.get(this.width - k - 1 + l * this.width);
                    } else {
                        ingredient = this.inputs.get(k + l * this.width);
                    }
                }

                if (!ingredient.test(inv.getStack(i + j * inv.getWidth()))) {
                    return false;
                }
            }
        }

        return true;
    }

    public ItemStack craft(CraftingInventory craftingInventory) {
        return this.getOutput().copy();
    }

    public int getWidth() {
        return this.width;
    }

    public int getHeight() {
        return this.height;
    }

    public static class Serializer implements RecipeSerializer<ConditionedShapedRecipe> {
        public ConditionedShapedRecipe read(Identifier identifier, JsonObject jsonObject) {
            String groupName = JsonHelper.getString(jsonObject, "group", "");
            String configName = JsonHelper.getString(jsonObject, "configName", "");
            Map<String, Ingredient> map = ConditionedShapedRecipe.getComponents(JsonHelper.getObject(jsonObject, "key"));

            if (map.size() == 1 && map.containsKey(" ")) {
                // This is just an empty recipe. return empty recipe.
                return new ConditionedShapedRecipe(identifier, groupName, 3, 3, DefaultedList.ofSize(3 * 3, Ingredient.EMPTY), ItemStack.EMPTY, configName);
            }

            String[] strings = ConditionedShapedRecipe.combinePattern(ConditionedShapedRecipe.getPattern(JsonHelper.getArray(jsonObject, "pattern")));
            int width = strings[0].length();
            int height = strings.length;
            DefaultedList<Ingredient> defaultedList = ConditionedShapedRecipe.getIngredients(strings, map, width, height);
            ItemStack itemStack = this.validateRecipeOutput(ConditionedShapedRecipe.getItemStack(JsonHelper.getObject(jsonObject, "result")), configName);
            return new ConditionedShapedRecipe(identifier, groupName, width, height, defaultedList, itemStack, configName);
        }

        public ConditionedShapedRecipe read(Identifier identifier, PacketByteBuf packetByteBuf) {
            int width = packetByteBuf.readVarInt();
            int height = packetByteBuf.readVarInt();
            String groupName = packetByteBuf.readString(32767);
            String configName = packetByteBuf.readString(32767);
            DefaultedList<Ingredient> defaultedList = DefaultedList.ofSize(width * height, Ingredient.EMPTY);

            for (int k = 0; k < defaultedList.size(); ++k) {
                defaultedList.set(k, Ingredient.fromPacket(packetByteBuf));
            }

            ItemStack itemStack = this.validateRecipeOutput(packetByteBuf.readItemStack(), configName);
            return new ConditionedShapedRecipe(identifier, groupName, width, height, defaultedList, itemStack, configName);
        }

        public void write(PacketByteBuf packetByteBuf, ConditionedShapedRecipe shapedRecipe) {
            packetByteBuf.writeVarInt(shapedRecipe.width);
            packetByteBuf.writeVarInt(shapedRecipe.height);
            packetByteBuf.writeString(shapedRecipe.group);
            packetByteBuf.writeString(shapedRecipe.configName);
            Iterator var3 = shapedRecipe.inputs.iterator();

            while (var3.hasNext()) {
                Ingredient ingredient = (Ingredient) var3.next();
                ingredient.write(packetByteBuf);
            }

            packetByteBuf.writeItemStack(shapedRecipe.output);
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
