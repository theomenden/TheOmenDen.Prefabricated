package com.wuest.prefab.structures.items;

import com.wuest.prefab.ModRegistry;
import com.wuest.prefab.structures.config.VillagerHouseConfiguration;
import com.wuest.prefab.structures.gui.GuiVillagerHouses;
import com.wuest.prefab.structures.predefined.StructureVillagerHouses;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemUsageContext;


/**
 * @author WuestMan
 */
@SuppressWarnings("ConstantConditions")
public class ItemVillagerHouses extends StructureItem {
    public ItemVillagerHouses() {
        super(new Item.Settings()
                .group(ItemGroup.MISC)
                .maxDamage(10));
    }

    @Override
    public void scanningMode(ItemUsageContext context) {
        StructureVillagerHouses.ScanStructure(
                context.getWorld(),
                context.getBlockPos(),
                context.getPlayer().getHorizontalFacing(),
                VillagerHouseConfiguration.HouseStyle.LONG_HOUSE);
    }

    /**
     * Initializes common fields/properties for this structure item.
     */
    @Override
    protected void Initialize() {
        ModRegistry.guiRegistrations.add(x -> this.RegisterGui(GuiVillagerHouses.class));
    }
}
