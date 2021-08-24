package com.wuest.prefab.structures.items;

import com.wuest.prefab.ModRegistry;
import com.wuest.prefab.structures.gui.GuiInstantBridge;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;

/**
 * This is the instant bridge item.
 *
 * @author WuestMan
 */
public class ItemInstantBridge extends StructureItem {
    public ItemInstantBridge() {
        super(new Item.Settings()
                .group(ModRegistry.PREFAB_GROUP)
                .maxDamage(10));
    }

    /**
     * Initializes common fields/properties for this structure item.
     */
    @Override
    protected void Initialize() {
        ModRegistry.guiRegistrations.add(x -> this.RegisterGui(GuiInstantBridge.class));
    }
}
