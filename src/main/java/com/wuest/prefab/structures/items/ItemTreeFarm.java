package com.wuest.prefab.structures.items;

import com.wuest.prefab.ModRegistry;
import com.wuest.prefab.structures.gui.GuiTreeFarm;
import com.wuest.prefab.structures.predefined.StructureTreeFarm;
import net.minecraft.item.ItemUsageContext;

/**
 * @author WuestMan
 */
@SuppressWarnings("ConstantConditions")
public class ItemTreeFarm extends StructureItem {
    public ItemTreeFarm() {
        super();
    }


    @Override
    public void scanningMode(ItemUsageContext context) {
        StructureTreeFarm.ScanStructure(
                context.getWorld(),
                context.getBlockPos(),
                context.getPlayer().getHorizontalFacing());
    }

    /**
     * Initializes common fields/properties for this structure item.
     */
    @Override
    protected void Initialize() {
        ModRegistry.guiRegistrations.add(x -> this.RegisterGui(GuiTreeFarm.class));
    }
}
