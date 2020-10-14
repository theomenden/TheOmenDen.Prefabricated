package com.wuest.prefab.events;

import com.wuest.prefab.ModRegistry;
import com.wuest.prefab.Prefab;
import com.wuest.prefab.network.message.ConfigSyncMessage;
import io.netty.buffer.Unpooled;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerEntityEvents;
import net.fabricmc.fabric.api.network.ServerSidePacketRegistry;
import net.minecraft.entity.Entity;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;

public class ServerEvents {

	public static void RegisterServerEvents() {
		ServerEvents.PlayerJoinedServer();
	}

	private static void PlayerJoinedServer() {
		ServerEntityEvents.ENTITY_LOAD.register(new ServerEntityEvents.Load() {
			@Override
			public void onLoad(Entity entity, ServerWorld serverWorld) {
				if (entity instanceof ServerPlayerEntity) {
					// Send the message to the client.
					ConfigSyncMessage message = new ConfigSyncMessage();
					message.setMessageTag(Prefab.configuration.writeCompoundTag());
					PacketByteBuf byteBuf = new PacketByteBuf(Unpooled.buffer());

					ConfigSyncMessage.encode(message, byteBuf);

					ServerSidePacketRegistry.INSTANCE.sendToPlayer((ServerPlayerEntity) entity, ModRegistry.ConfigSync, byteBuf);
				}
			}
		});
	}
}
