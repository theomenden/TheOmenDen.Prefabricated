package com.wuest.prefab.structures.items;


import com.wuest.prefab.structures.predefined.StructureMonsterMasher;
import net.minecraft.item.ItemUsageContext;

/**
 * @author WuestMan
 */
@SuppressWarnings("ConstantConditions")
public class ItemMonsterMasher extends StructureItem {
	public ItemMonsterMasher( ) {
		super();
	}

	@Override
	public void scanningMode(ItemUsageContext context) {
		StructureMonsterMasher.ScanStructure(
				context.getWorld(),
				context.getBlockPos(),
				context.getPlayer().getHorizontalFacing());
	}
}
