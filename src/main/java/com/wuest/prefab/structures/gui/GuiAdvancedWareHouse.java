package com.wuest.prefab.structures.gui;

import com.wuest.prefab.structures.messages.StructureTagMessage;
import net.minecraft.util.Identifier;

/**
 * @author WuestMan
 */
public class GuiAdvancedWareHouse extends GuiWareHouse {
    private static final Identifier structureTopDown = new Identifier("prefab", "textures/gui/advanced_warehouse_top_down.png");

    public GuiAdvancedWareHouse() {
        super();
        this.clientGUIIdentifier = "Advanced Warehouse";
    }

    @Override
    public void Initialize() {
        super.Initialize();
        this.configuration.advanced = true;
        this.structureConfiguration = StructureTagMessage.EnumStructureConfiguration.AdvancedWareHouse;
        this.structureIdentifier = "item.prefab.item_advanced_warehouse";
        this.structureImageLocation = structureTopDown;
    }

}
