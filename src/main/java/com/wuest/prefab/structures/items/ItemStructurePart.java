package com.wuest.prefab.structures.items;

import com.wuest.prefab.ModRegistry;
import com.wuest.prefab.structures.gui.GuiStructurePart;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;

/**
 * @author WuestMan
 */
public class ItemStructurePart extends StructureItem {
    public ItemStructurePart() {
        super(new Item.Settings()
                .group(ItemGroup.MISC)
                .maxDamage(10));
    }

    /**
     * Initializes common fields/properties for this structure item.
     */
    @Override
    protected void Initialize() {
        ModRegistry.guiRegistrations.add(x -> this.RegisterGui(GuiStructurePart.class));
    }
}
