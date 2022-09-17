package com.wuest.prefab.config;

import com.wuest.prefab.Utils;
import com.wuest.prefab.gui.GuiLangKeys;
import me.shedaniel.autoconfig.gui.registry.api.GuiProvider;
import me.shedaniel.autoconfig.gui.registry.api.GuiRegistryAccess;
import me.shedaniel.clothconfig2.api.AbstractConfigListEntry;
import me.shedaniel.clothconfig2.gui.entries.BooleanListEntry;
import me.shedaniel.clothconfig2.gui.entries.MultiElementListEntry;
import net.minecraft.network.chat.Component;

import java.lang.reflect.Field;
import java.util.*;

public class StructureOptionGuiProvider implements GuiProvider {
    @Override
    public List<AbstractConfigListEntry> get(String s, Field field, Object savedObject, Object defaultObject, GuiRegistryAccess guiRegistryAccess) {
        try {
            HashMap<String, HashMap<String, Boolean>> savedHashMap = (HashMap<String, HashMap<String, Boolean>>) field.get(savedObject);
            HashMap<String, HashMap<String, Boolean>> defaultHashMap = (HashMap<String, HashMap<String, Boolean>>) field.get(defaultObject);

            for (Map.Entry<String, HashMap<String, Boolean>> defaultEntry : defaultHashMap.entrySet()) {
                // Make sure that the saved hashmap has this entry; if not add it by default.
                if (!savedHashMap.containsKey(defaultEntry.getKey())) {
                    savedHashMap.put(defaultEntry.getKey(), defaultEntry.getValue());
                }

                // check this saved hashmap value against the default one to make sure that all sub-values are saved.
                HashMap<String, Boolean> savedSubValues = savedHashMap.get(defaultEntry.getKey());
                HashMap<String, Boolean> defaultSubValues = defaultEntry.getValue();

                for (Map.Entry<String, Boolean> defaultSubvalue : defaultSubValues.entrySet()) {
                    if (!savedSubValues.containsKey(defaultSubvalue.getKey())) {
                        savedSubValues.put(defaultSubvalue.getKey(), defaultSubvalue.getValue());
                    }
                }
            }


            ArrayList<AbstractConfigListEntry> entries = new ArrayList<>();

            SortedMap<String, HashMap<String, Boolean>> sortedMap = new TreeMap<>(savedHashMap);

            for (Map.Entry<String, HashMap<String, Boolean>> map : sortedMap.entrySet()) {
                ArrayList<AbstractConfigListEntry<?>> childEntries = new ArrayList<>();
                SortedMap<String, Boolean> sortedChildEntries = new TreeMap<>(map.getValue());

                for (Map.Entry<String, Boolean> childMap : sortedChildEntries.entrySet()) {
                    BooleanListEntry entry = new BooleanListEntry(
                            // Field Name
                            GuiLangKeys.translateToComponent(childMap.getKey()),
                            // Value
                            childMap.getValue(),
                            // Resent Button Text
                            Utils.createTextComponent("Reset"),
                            // Default Value
                            () -> true,
                            // Save consumer
                            (value) -> {
                                // Update the main map because the current map is the sorted one.
                                HashMap<String, Boolean> mainMapValue = map.getValue();
                                mainMapValue.put(childMap.getKey(), value);

                                // make sure to update the sorted map, so they stay in-sync.
                                childMap.setValue(value);
                            },
                            // tooltip supplier
                            () -> {
                                String itemName = GuiLangKeys.translateToComponent(map.getKey()).getString();

                                return java.util.Optional.of(new Component[]{
                                        Utils.createTextComponent("Enables or disables the option for " + itemName)
                                });
                            }
                    );

                    childEntries.add(entry);
                }

                MultiElementListEntry<Map.Entry<String, HashMap<String, Boolean>>> item = new MultiElementListEntry<>(
                        GuiLangKeys.translateToComponent(map.getKey()),
                        map,
                        childEntries,
                        false);

                entries.add(item);
            }

            return entries;
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        return null;
    }
}
