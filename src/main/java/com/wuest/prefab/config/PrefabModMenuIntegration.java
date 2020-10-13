package com.wuest.prefab.config;

import com.wuest.prefab.Prefab;
import io.github.prospector.modmenu.api.ConfigScreenFactory;
import io.github.prospector.modmenu.api.ModMenuApi;
import me.sargunvohra.mcmods.autoconfig1u.AutoConfig;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;

@Environment(EnvType.CLIENT)
public class PrefabModMenuIntegration implements ModMenuApi {

	@Override
	public String getModId() {
		return Prefab.MODID;
	}

	@Override
	public ConfigScreenFactory<?> getModConfigScreenFactory() {
		return parent -> AutoConfig.getConfigScreen(ModConfiguration.class, parent).get();
	}
}
