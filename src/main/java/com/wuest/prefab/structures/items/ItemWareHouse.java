package com.wuest.prefab.structures.items;

import com.wuest.prefab.structures.predefined.StructureWarehouse;
import net.minecraft.item.ItemUsageContext;

/**
 * This class is used to build the warehouse structure.
 *
 * @author WuestMan
 */
@SuppressWarnings("ConstantConditions")
public class ItemWareHouse extends StructureItem {
	public ItemWareHouse() {
		super();
	}

	@Override
	public void scanningMode(ItemUsageContext context) {
		StructureWarehouse.ScanStructure(
				context.getWorld(),
				context.getBlockPos(),
				context.getPlayer().getHorizontalFacing(),
				false);
	}
}