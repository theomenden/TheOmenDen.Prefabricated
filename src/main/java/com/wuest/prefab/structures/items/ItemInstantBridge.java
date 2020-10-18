package com.wuest.prefab.structures.items;

import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;

/**
 * This is the instant bridge item.
 *
 * @author WuestMan
 */
public class ItemInstantBridge extends StructureItem {
	public ItemInstantBridge( ) {
		super(new Item.Settings()
				.group(ItemGroup.MISC)
				.maxDamage(10));
	}
}
