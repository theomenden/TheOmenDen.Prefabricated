package com.wuest.prefab.structures.items;

import com.wuest.prefab.structures.config.VillagerHouseConfiguration;
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
}
