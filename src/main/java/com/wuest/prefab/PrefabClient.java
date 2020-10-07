package com.wuest.prefab;

import net.fabricmc.api.ClientModInitializer;

/**
 * This class represents the client-side initialization.
 */
public class PrefabClient implements ClientModInitializer {

	@Override
	public void onInitializeClient() {
		Prefab.logger.info("Registering client-side components");
		ClientModRegistry.registerModComponents();
	}

}
