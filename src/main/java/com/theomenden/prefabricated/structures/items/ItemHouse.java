package com.theomenden.prefabricated.structures.items;

import com.theomenden.prefabricated.ModRegistry;
import com.theomenden.prefabricated.structures.gui.GuiHouse;
import net.minecraft.world.item.context.UseOnContext;

/**
 * @author WuestMan
 */
@SuppressWarnings("ALL")
public class ItemHouse extends StructureItem {
    public ItemHouse() {
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
        ModRegistry.guiRegistrations.add(x -> this.RegisterGui(GuiHouse.class));
    }
}