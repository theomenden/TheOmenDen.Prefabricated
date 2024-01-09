package com.theomenden.prefabricated.structures.items;

import com.theomenden.prefabricated.ModRegistry;
import com.theomenden.prefabricated.structures.gui.GuiHouseImproved;
import net.minecraft.world.item.context.UseOnContext;

/**
 * @author WuestMan
 */
public class ItemHouseImproved extends StructureItem {
    /**
     * Initializes a new instance of the {@link ItemHouseImproved} class.
     */
    public ItemHouseImproved() {
        super();
    }

    @Override
    public void scanningMode(UseOnContext context) {
    }

    /**
     * Initializes common fields/properties for this structure item.
     */
    @Override
    protected void Initialize() {
        ModRegistry.guiRegistrations.add(x -> this.RegisterGui(GuiHouseImproved.class));
    }
}
