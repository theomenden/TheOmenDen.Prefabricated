package com.wuest.prefab;

import com.wuest.prefab.config.ModConfiguration;
import com.wuest.prefab.config.RecipeMapGuiProvider;
import me.sargunvohra.mcmods.autoconfig1u.AutoConfig;
import me.sargunvohra.mcmods.autoconfig1u.gui.registry.GuiRegistry;
import net.fabricmc.api.ClientModInitializer;

/**
 * This class represents the client-side initialization.
 */
public class PrefabClient implements ClientModInitializer {

	@Override
	public void onInitializeClient() {
		Prefab.logger.info("Registering client-side components");
		ClientModRegistry.registerModComponents();

		GuiRegistry registry = AutoConfig.getGuiRegistry(ModConfiguration.class);
		RecipeMapGuiProvider providerMap = new RecipeMapGuiProvider();

		registry.registerPredicateProvider(providerMap, (field) -> field.getDeclaringClass() == ModConfiguration.class && field.getName().equals("recipes"));

	}
}
