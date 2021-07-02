package com.wuest.prefab.structures.gui;

import com.wuest.prefab.ClientModRegistry;
import com.wuest.prefab.structures.config.HorseStableConfiguration;
import com.wuest.prefab.structures.messages.StructureTagMessage;
import com.wuest.prefab.structures.predefined.StructureHorseStable;
import net.minecraft.client.gui.widget.PressableWidget;
import net.minecraft.util.Identifier;

/**
 * @author WuestMan
 */
public class GuiHorseStable extends GuiStructure {
    private static final Identifier structureTopDown = new Identifier("prefab", "textures/gui/horse_stable_top_down.png");
    protected HorseStableConfiguration configuration;

    public GuiHorseStable() {
        super("Horse Stable");
        this.structureConfiguration = StructureTagMessage.EnumStructureConfiguration.HorseStable;
    }

    @Override
    protected void Initialize() {
        super.Initialize();
        this.structureImageLocation = structureTopDown;
        this.configuration = ClientModRegistry.playerConfig.getClientConfig("Horse Stable", HorseStableConfiguration.class);
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
            StructureHorseStable structure = StructureHorseStable.CreateInstance(StructureHorseStable.ASSETLOCATION, StructureHorseStable.class);
            this.performPreview(structure, this.configuration);
        }
    }
}