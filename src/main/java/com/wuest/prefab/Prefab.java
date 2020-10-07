package com.wuest.prefab;

import net.fabricmc.api.ModInitializer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Prefab implements ModInitializer {
	/**
	 * This is the ModID
	 */
	public static final String MODID = "prefab";

	public static Logger logger;

	/**
	 * This is used to determine if the mod is currently being debugged.
	 */
	public static boolean isDebug = false;

	/**
	 * Determines if structure items will scan their defined space or show the build gui. Default is false.
	 * Note: this should only be set to true during debug mode.
	 */
	public static boolean useScanningMode = false;

	static {
		Prefab.logger = LogManager.getLogger("Prefab");
		Prefab.isDebug = java.lang.management.ManagementFactory.getRuntimeMXBean().getInputArguments().toString().contains("-agentlib:jdwp");
	}

	@Override
	public void onInitialize() {
		Prefab.logger.info("Registering Mod Components");
		ModRegistry.registerModComponents();
	}
}
