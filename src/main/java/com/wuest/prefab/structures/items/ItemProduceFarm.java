package com.wuest.prefab.structures.items;


import com.wuest.prefab.structures.predefined.StructureProduceFarm;
import net.minecraft.item.ItemUsageContext;

/**
 * @author WuestMan
 */
@SuppressWarnings("ConstantConditions")
public class ItemProduceFarm extends StructureItem {
	public ItemProduceFarm() {
		super();
	}

	@Override
	public void scanningMode(ItemUsageContext context) {
		StructureProduceFarm.ScanStructure(
				context.getWorld(),
				context.getBlockPos(),
				context.getPlayer().getHorizontalFacing());
	}
}