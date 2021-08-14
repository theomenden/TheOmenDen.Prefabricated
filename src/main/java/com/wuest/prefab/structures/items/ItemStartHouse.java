package com.wuest.prefab.structures.items;

import com.wuest.prefab.ClientModRegistry;
import com.wuest.prefab.ModRegistry;
import com.wuest.prefab.Prefab;
import com.wuest.prefab.structures.gui.GuiStartHouseChooser;
import com.wuest.prefab.structures.predefined.StructureAlternateStart;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.util.ActionResult;
import net.minecraft.util.math.Direction;

/**
 * @author WuestMan
 */
@SuppressWarnings("ALL")
public class ItemStartHouse extends StructureItem {
    public ItemStartHouse() {
        super();
    }

    /**
     * Does something when the item is right-clicked.
     */
    @Override
    public ActionResult useOnBlock(ItemUsageContext context) {
        if (context.getWorld().isClient) {
            if (context.getSide() == Direction.UP) {
                if (!Prefab.useScanningMode) {
                    // Open the client side gui to determine the house options.
                    ClientModRegistry.openGuiForItem(context);
                } else {
                    this.scanningMode(context);
                }
                return ActionResult.PASS;
            }
        }

        return ActionResult.FAIL;
    }

    @Override
    public void scanningMode(ItemUsageContext context) {
		/*StructureAlternateStart.ScanBasicHouseStructure(
				context.getWorld(),
				context.getBlockPos(),
				context.getPlayer().getHorizontalFacing());*/

        /*StructureAlternateStart.ScanRanchStructure(
                context.getWorld(),
                context.getBlockPos(),
                context.getPlayer().getHorizontalFacing());*/

		/*StructureAlternateStart.ScanLoftStructure(
				context.getWorld(),
				context.getBlockPos(),
				context.getPlayer().getHorizontalFacing());*/

        /*StructureAlternateStart.ScanHobbitStructure(
                context.getWorld(),
                context.getBlockPos(),
                context.getPlayer().getHorizontalFacing());*/

        /*StructureAlternateStart.ScanStructure(
                context.getWorld(),
                context.getBlockPos(),
                context.getPlayer().getHorizontalFacing(),
                "desert_house",
                false,
                false);*/

		/*StructureAlternateStart.ScanStructure(
				context.getWorld(),
				context.getBlockPos(),
				context.getPlayer().getHorizontalFacing(),
				"snowy_house",
				false,
				false);*/

        StructureAlternateStart.ScanDesert2Structure(
                context.getWorld(),
                context.getBlockPos(),
                context.getPlayer().getHorizontalFacing());

        /*StructureAlternateStart.ScanSubAquaticStructure(
                context.getWorld(),
                context.getBlockPos(),
                context.getPlayer().getHorizontalFacing());*/
    }

    /**
     * Initializes common fields/properties for this structure item.
     */
    @Override
    protected void Initialize() {
        ModRegistry.guiRegistrations.add(x -> this.RegisterGui(GuiStartHouseChooser.class));
    }
}