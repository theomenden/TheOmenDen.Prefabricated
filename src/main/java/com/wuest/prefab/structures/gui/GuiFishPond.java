package com.wuest.prefab.structures.gui;

import com.wuest.prefab.ClientModRegistry;
import com.wuest.prefab.structures.config.FishPondConfiguration;
import com.wuest.prefab.structures.messages.StructureTagMessage;
import com.wuest.prefab.structures.predefined.StructureFishPond;
import net.minecraft.client.gui.widget.PressableWidget;
import net.minecraft.util.Identifier;

/**
 * @author WuestMan
 */
public class GuiFishPond extends GuiStructure {
    private static final Identifier structureTopDown = new Identifier("prefab", "textures/gui/fish_pond_top_down.png");
    protected FishPondConfiguration configuration;

    public GuiFishPond() {
        super("Fish Pond");
        this.structureConfiguration = StructureTagMessage.EnumStructureConfiguration.FishPond;
    }

    @Override
    protected void Initialize() {
        super.Initialize();
        this.structureImageLocation = structureTopDown;
        this.configuration = ClientModRegistry.playerConfig.getClientConfig("Fish Pond", FishPondConfiguration.class);
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
            StructureFishPond structure = StructureFishPond.CreateInstance(StructureFishPond.ASSETLOCATION, StructureFishPond.class);
            this.performPreview(structure, this.configuration);
        }
    }
}
