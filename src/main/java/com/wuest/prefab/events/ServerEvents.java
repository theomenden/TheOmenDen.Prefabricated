package com.wuest.prefab.events;

import com.wuest.prefab.ModRegistry;
import com.wuest.prefab.Prefab;
import com.wuest.prefab.Utils;
import com.wuest.prefab.network.message.ConfigSyncMessage;
import com.wuest.prefab.structures.events.StructureEventHandler;
import io.netty.buffer.Unpooled;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerEntityEvents;
import net.fabricmc.fabric.api.network.ServerSidePacketRegistry;
import net.minecraft.entity.Entity;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;

import java.util.ArrayList;

public class ServerEvents {
	/**
	 * Determines the affected blocks by redstone power.
	 */
	public static ArrayList<BlockPos> RedstoneAffectedBlockPositions = new ArrayList<>();

	static {
		ServerEvents.RedstoneAffectedBlockPositions = new ArrayList<>();
	}

	public static void registerServerEvents() {
		ServerEvents.playerJoinedServer();

		StructureEventHandler.registerStructureServerSideEvents();
	}

	private static void playerJoinedServer() {
		ServerEntityEvents.ENTITY_LOAD.register((entity, serverWorld) -> {
			if (entity instanceof ServerPlayerEntity) {
				// Send the message to the client.
				PacketByteBuf messagePacket = Utils.createMessageBuffer(Prefab.configuration.writeCompoundTag());

				ServerSidePacketRegistry.INSTANCE.sendToPlayer((ServerPlayerEntity) entity, ModRegistry.ConfigSync, messagePacket);
			}
		});
	}
}
