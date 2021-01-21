package com.wuest.prefab.structures.items;

import com.wuest.prefab.structures.config.ModerateHouseConfiguration;
import com.wuest.prefab.structures.predefined.StructureModerateHouse;
import net.minecraft.item.ItemUsageContext;

/**
 * @author WuestMan
 */
@SuppressWarnings("ConstantConditions")
public class ItemModerateHouse extends StructureItem {
	/**
	 * Initializes a new instance of the {@link ItemModerateHouse} class.
	 */
	public ItemModerateHouse() {
		super();
	}

	@Override
	public void scanningMode(ItemUsageContext context) {
		StructureModerateHouse.ScanStructure(
				context.getWorld(),
				context.getBlockPos(),
				context.getPlayer().getHorizontalFacing(),
				ModerateHouseConfiguration.HouseStyle.WORKSHOP_HOME);
	}
}
