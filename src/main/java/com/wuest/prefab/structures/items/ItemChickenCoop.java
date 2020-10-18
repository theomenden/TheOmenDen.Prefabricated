package com.wuest.prefab.structures.items;

import com.wuest.prefab.structures.predefined.StructureChickenCoop;
import net.minecraft.item.ItemUsageContext;

/**
 * @author WuestMan
 */
@SuppressWarnings("ConstantConditions")
public class ItemChickenCoop extends StructureItem {
	public ItemChickenCoop( ) {
		super();
	}

	@Override
	public void scanningMode(ItemUsageContext context) {
		StructureChickenCoop.ScanStructure(
				context.getWorld(),
				context.getBlockPos(),
				context.getPlayer().getHorizontalFacing());
	}
}
