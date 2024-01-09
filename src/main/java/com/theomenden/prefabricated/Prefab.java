package com.theomenden.prefabricated;

import com.theomenden.prefabricated.config.ModConfiguration;
import com.theomenden.prefabricated.events.ServerEvents;
import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.serializer.GsonConfigSerializer;
import net.fabricmc.api.ModInitializer;
import net.minecraft.client.resources.model.Material;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.material.PushReaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.management.ManagementFactory;

public class Prefab implements ModInitializer {
    /**
     * This is the ModID
     */
    public static final String MODID = "prefabricated";
    /**
     * Simulates an air block that blocks movement and cannot be moved.
     */
    public static final BlockBehaviour.Properties SeeThroughImmovable = BlockBehaviour.Properties.of()
                                                                                                 .air()
                                                                                                 .mapColor(MapColor.NONE)
                                                                                                 .noParticlesOnBreak()
                                                                                                 .noLootTable()
                                                                                                 .forceSolidOn()
                                                                                                 .noOcclusion()
                                                                                                 .pushReaction(PushReaction.IGNORE);

    public static final Logger logger = LoggerFactory.getLogger(Prefab.class);

    /**
     * This is used to determine if the mod is currently being debugged.
     */
    public static boolean isDebug = true;
    /**
     * Determines if structure items will scan their defined space or show the build gui. Default is false.
     * Note: this should only be set to true during debug mode.
     */
    public static boolean useScanningMode = false;
    public static ModConfiguration configuration;
    public static ModConfiguration serverConfiguration;

    static {
        Prefab.isDebug = ManagementFactory.getRuntimeMXBean().getInputArguments().toString().contains("-agentlib:jdwp");
    }

    @Override
    public void onInitialize() {
        Prefab.logger.info("Registering Mod Components");
        ModRegistry.registerModComponents();

        AutoConfig.register(ModConfiguration.class, GsonConfigSerializer::new);

        Prefab.serverConfiguration = new ModConfiguration();
        Prefab.configuration = AutoConfig.getConfigHolder(ModConfiguration.class).getConfig();

        ServerEvents.registerServerEvents();
    }
}
