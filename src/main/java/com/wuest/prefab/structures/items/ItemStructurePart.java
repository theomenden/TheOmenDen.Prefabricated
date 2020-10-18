package com.wuest.prefab.structures.items;

import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;

/**
 * @author WuestMan
 */
public class ItemStructurePart extends StructureItem {
	public ItemStructurePart() {
		super(new Item.Settings()
				.group(ItemGroup.MISC)
				.maxDamage(10));
	}
}
