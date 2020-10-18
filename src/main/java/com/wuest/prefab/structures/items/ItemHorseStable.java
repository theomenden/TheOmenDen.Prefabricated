package com.wuest.prefab.structures.items;


import com.wuest.prefab.structures.predefined.StructureHorseStable;
import net.minecraft.item.ItemUsageContext;

/**
 * @author WuestMan
 */
@SuppressWarnings("ConstantConditions")
public class ItemHorseStable extends StructureItem {
	public ItemHorseStable() {
		super();
	}

	@Override
	public void scanningMode(ItemUsageContext context) {
		StructureHorseStable.ScanStructure(
				context.getWorld(),
				context.getBlockPos(),
				context.getPlayer().getHorizontalFacing());
	}
}
