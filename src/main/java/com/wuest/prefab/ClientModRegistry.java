package com.wuest.prefab;

import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.minecraft.client.render.RenderLayer;

public class ClientModRegistry {

	public static void registerModComponents() {
		ClientModRegistry.registerBlockLayers();
	}

	private static void registerServerToClientMessageHandlers() {

	}

	private static void registerBlockLayers() {
		BlockRenderLayerMap.INSTANCE.putBlock(ModRegistry.GlassStairs, RenderLayer.getCutout());
		BlockRenderLayerMap.INSTANCE.putBlock(ModRegistry.GlassSlab, RenderLayer.getCutout());
		BlockRenderLayerMap.INSTANCE.putBlock(ModRegistry.PaperLantern, RenderLayer.getCutout());
	}
}
