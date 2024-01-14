package com.theomenden.prefabricated.structures.items;

import com.theomenden.prefabricated.ModRegistry;
import com.theomenden.prefabricated.structures.gui.GuiHouseAdvanced;

/**
 * @author WuestMan
 */
public final class ItemHouseAdvanced extends StructureItem {
    /**
     * Initializes a new instance of the {@link ItemHouseAdvanced} class.
     */
    public ItemHouseAdvanced() {
        super();
    }

    /**
     * Initializes common fields/properties for this structure item.
     */
    @Override
    protected void Initialize() {
        ModRegistry.guiRegistrations.add(x -> this.RegisterGui(GuiHouseAdvanced.class));
    }
}
