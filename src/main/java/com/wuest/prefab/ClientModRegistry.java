package com.wuest.prefab;

import com.wuest.prefab.network.message.ConfigSyncMessage;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.network.ClientSidePacketRegistry;
import net.minecraft.client.render.RenderLayer;

public class ClientModRegistry {

	public static void registerModComponents() {
		ClientModRegistry.registerBlockLayers();

		ClientModRegistry.registerServerToClientMessageHandlers();
	}

	private static void registerServerToClientMessageHandlers() {
		ClientSidePacketRegistry.INSTANCE.register(ModRegistry.ConfigSync,
				(packetContext, attachedData) -> {
					// Can only access the "attachedData" on the "network thread" which is here.
					ConfigSyncMessage syncMessage = ConfigSyncMessage.decode(attachedData);

					packetContext.getTaskQueue().execute(() -> {
						// This is now on the "main" client thread and things can be done in the world!
						Prefab.serverConfiguration.readFromTag(syncMessage.getMessageTag());
					});
				}
		);
	}

	private static void registerBlockLayers() {
		BlockRenderLayerMap.INSTANCE.putBlock(ModRegistry.GlassStairs, RenderLayer.getCutout());
		BlockRenderLayerMap.INSTANCE.putBlock(ModRegistry.GlassSlab, RenderLayer.getCutout());
		BlockRenderLayerMap.INSTANCE.putBlock(ModRegistry.PaperLantern, RenderLayer.getCutout());
	}
}
