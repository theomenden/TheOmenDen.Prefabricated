package com.wuest.prefab.structures.items;


import com.wuest.prefab.structures.predefined.StructureWarehouse;
import net.minecraft.item.ItemUsageContext;

/**
 * @author WuestMan
 */
@SuppressWarnings("ConstantConditions")
public class ItemAdvancedWareHouse extends ItemWareHouse {
	public ItemAdvancedWareHouse() {
		super();
	}

	@Override
	public void scanningMode(ItemUsageContext context) {
		StructureWarehouse.ScanStructure(
				context.getWorld(),
				context.getBlockPos(),
				context.getPlayer().getHorizontalFacing(),
				true);
	}
}
