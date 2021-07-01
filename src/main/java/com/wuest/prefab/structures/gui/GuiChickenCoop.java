package com.wuest.prefab.structures.gui;

import com.wuest.prefab.ClientModRegistry;
import com.wuest.prefab.structures.config.ChickenCoopConfiguration;
import com.wuest.prefab.structures.messages.StructureTagMessage;
import com.wuest.prefab.structures.predefined.StructureChickenCoop;
import net.minecraft.client.gui.widget.PressableWidget;
import net.minecraft.util.Identifier;

/**
 * @author WuestMan
 */
public class GuiChickenCoop extends GuiStructure {
    private static final Identifier structureTopDown = new Identifier("prefab", "textures/gui/chicken_coop_topdown.png");
    protected ChickenCoopConfiguration configuration;

    public GuiChickenCoop() {
        super("Chicken Coop");
        this.structureConfiguration = StructureTagMessage.EnumStructureConfiguration.ChickenCoop;
    }

    @Override
    protected void Initialize() {
        super.Initialize();
        this.structureImageLocation = structureTopDown;
        this.configuration = ClientModRegistry.playerConfig.getClientConfig("Chicken Coop", ChickenCoopConfiguration.class);
        this.configuration.pos = this.pos;

        this.InitializeStandardButtons();
    }

    /**
     * Called by the controls from the buttonList when activated. (Mouse pressed for buttons)
     */
    @Override
    public void buttonClicked(PressableWidget button) {
        this.performCancelOrBuildOrHouseFacing(this.configuration, button);

        if (button == this.btnVisualize) {
            StructureChickenCoop structure = StructureChickenCoop.CreateInstance(StructureChickenCoop.ASSETLOCATION, StructureChickenCoop.class);
            this.performPreview(structure, this.configuration);
        }
    }
}
