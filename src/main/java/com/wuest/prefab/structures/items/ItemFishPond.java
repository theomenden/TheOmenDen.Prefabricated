package com.wuest.prefab.structures.items;


import com.wuest.prefab.structures.predefined.StructureFishPond;
import net.minecraft.item.ItemUsageContext;

/**
 * @author WuestMan
 */
@SuppressWarnings("ConstantConditions")
public class ItemFishPond extends StructureItem {
	public ItemFishPond( ) {
		super();
	}

	@Override
	public void scanningMode(ItemUsageContext context) {
		StructureFishPond.ScanStructure(
				context.getWorld(),
				context.getBlockPos(),
				context.getPlayer().getHorizontalFacing());
	}
}
