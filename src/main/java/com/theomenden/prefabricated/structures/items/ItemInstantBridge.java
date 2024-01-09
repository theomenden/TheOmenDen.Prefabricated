package com.theomenden.prefabricated.structures.items;

import com.theomenden.prefabricated.ModRegistry;
import com.theomenden.prefabricated.structures.gui.GuiInstantBridge;
import net.minecraft.world.item.Item;

/**
 * This is the instant bridge item.
 *
 * @author WuestMan
 */
public class ItemInstantBridge extends StructureItem {
    public ItemInstantBridge() {
        super(new Item.Properties()
                .durability(10));
    }

    /**
     * Initializes common fields/properties for this structure item.
     */
    @Override
    protected void Initialize() {
        ModRegistry.guiRegistrations.add(x -> this.RegisterGui(GuiInstantBridge.class));
    }
}
