package com.wuest.prefab.structures.items;

import com.wuest.prefab.structures.predefined.StructureTreeFarm;
import net.minecraft.item.ItemUsageContext;

/**
 * @author WuestMan
 */
@SuppressWarnings("ConstantConditions")
public class ItemTreeFarm extends StructureItem {
	public ItemTreeFarm( ) {
		super();
	}


	@Override
	public void scanningMode(ItemUsageContext context) {
		StructureTreeFarm.ScanStructure(
				context.getWorld(),
				context.getBlockPos(),
				context.getPlayer().getHorizontalFacing());
	}
}
