package com.wuest.prefab.structures.items;

import com.wuest.prefab.structures.config.BasicStructureConfiguration;
import com.wuest.prefab.structures.config.enums.*;
import com.wuest.prefab.structures.predefined.StructureBasic;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsageContext;

/**
 * This class is used for basic structures to show the basic GUI.
 *
 * @author WuestMan
 */
@SuppressWarnings({"AccessStaticViaInstance", "ConstantConditions"})
public class ItemBasicStructure extends StructureItem {
	public final BasicStructureConfiguration.EnumBasicStructureName structureType;

	public ItemBasicStructure(BasicStructureConfiguration.EnumBasicStructureName structureType) {
		super();

		this.structureType = structureType;
	}

	public static ItemStack getBasicStructureItemInHand(PlayerEntity player) {
		ItemStack stack = player.getOffHandStack();

		// Get off hand first since that is the right-click hand if there is
		// something in there.
		if (!(stack.getItem() instanceof ItemBasicStructure)) {
			if (player.getMainHandStack().getItem() instanceof ItemBasicStructure) {
				stack = player.getMainHandStack();
			} else {
				stack = null;
			}
		}

		return stack;
	}

	/**
	 * Does something when the item is right-clicked.
	 */
	@Override
	public void scanningMode(ItemUsageContext context) {
		StructureBasic basicStructure = new StructureBasic();
		ItemStack stack = context.getPlayer().getStackInHand(context.getHand());
		BasicStructureConfiguration structureConfiguration = new BasicStructureConfiguration();
		structureConfiguration.basicStructureName = ((ItemBasicStructure) stack.getItem()).structureType;
		structureConfiguration.chosenOption = TownHallOptions.Default;

		boolean isWaterStructure = structureConfiguration.basicStructureName == BasicStructureConfiguration.EnumBasicStructureName.AquaBase;

		basicStructure.ScanStructure(
				context.getWorld(),
				context.getBlockPos(),
				context.getPlayer().getHorizontalFacing(),
				structureConfiguration, isWaterStructure, isWaterStructure);
	}
}