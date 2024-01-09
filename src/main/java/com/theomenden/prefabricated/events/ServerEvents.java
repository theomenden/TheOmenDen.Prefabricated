package com.theomenden.prefabricated.events;

import com.theomenden.prefabricated.ModRegistry;
import com.theomenden.prefabricated.Prefab;
import com.theomenden.prefabricated.Utils;
import com.theomenden.prefabricated.config.ModConfiguration;
import com.theomenden.prefabricated.items.ItemSickle;
import com.theomenden.prefabricated.registries.ModRegistries;
import com.theomenden.prefabricated.structures.events.StructureEventHandler;
import me.shedaniel.autoconfig.AutoConfig;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerEntityEvents;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerLifecycleEvents;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;

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
        ServerEvents.serverStarting();

        ServerEvents.serverStarted();

        ServerEvents.playerJoinedServer();

        StructureEventHandler.registerStructureServerSideEvents();
    }

    private static void serverStarting() {
        ServerLifecycleEvents.SERVER_STARTING.register((server -> {
            // Only do this for server-side.
            ModRegistry.serverModRegistries = new ModRegistries();
        }));
    }

    private static void serverStarted() {
        ServerLifecycleEvents.SERVER_STARTED.register((server) -> {
            // Get the server configuration.
            // This will be pushed to the player when they join the world.
            Prefab.serverConfiguration = AutoConfig.getConfigHolder(ModConfiguration.class).getConfig();

            // Do this when the server starts so that all appropriate tags are used.
            ItemSickle.setEffectiveBlocks();
        });
    }

    private static void playerJoinedServer() {
        ServerEntityEvents.ENTITY_LOAD.register((entity, serverWorld) -> {
            if (entity instanceof ServerPlayer) {
                // Send the message to the client.
                FriendlyByteBuf messagePacket = Utils.createMessageBuffer(Prefab.serverConfiguration.writeCompoundTag());
                ServerPlayNetworking.send((ServerPlayer) entity, ModRegistry.ConfigSync, messagePacket);
            }
        });
    }
}
