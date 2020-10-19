package com.wuest.prefab;

import com.wuest.prefab.blocks.BlockBoundary;
import com.wuest.prefab.config.EntityPlayerConfiguration;
import com.wuest.prefab.mixins.SavePlayerDataMixin;
import com.wuest.prefab.network.message.ConfigSyncMessage;
import com.wuest.prefab.network.message.PlayerEntityTagMessage;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.network.ClientSidePacketRegistry;
import net.fabricmc.fabric.mixin.screenhandler.ServerPlayerEntityMixin;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.RenderLayer;

import java.util.UUID;

public class ClientModRegistry {

	public static EntityPlayerConfiguration playerConfig = new EntityPlayerConfiguration();

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

		ClientSidePacketRegistry.INSTANCE.register(ModRegistry.PlayerConfigSync, (packetContext, attachedData) -> {
			// Can only access the "attachedData" on the "network thread" which is here.
			PlayerEntityTagMessage syncMessage = PlayerEntityTagMessage.decode(attachedData);

			packetContext.getTaskQueue().execute(() -> {
				// This is now on the "main" client thread and things can be done in the world!
				UUID playerUUID = MinecraftClient.getInstance().player.getUuid();

				EntityPlayerConfiguration.loadFromTag(playerUUID, syncMessage.getMessageTag());
			});
		});
	}

	private static void registerBlockLayers() {
		BlockRenderLayerMap.INSTANCE.putBlock(ModRegistry.GlassStairs, RenderLayer.getCutout());
		BlockRenderLayerMap.INSTANCE.putBlock(ModRegistry.GlassSlab, RenderLayer.getCutout());
		BlockRenderLayerMap.INSTANCE.putBlock(ModRegistry.PaperLantern, RenderLayer.getCutout());
		BlockRenderLayerMap.INSTANCE.putBlock(ModRegistry.Boundary, RenderLayer.getTranslucent());
		BlockRenderLayerMap.INSTANCE.putBlock(ModRegistry.Phasic, RenderLayer.getTranslucent());
	}
}
