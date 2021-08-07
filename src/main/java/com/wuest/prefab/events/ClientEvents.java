package com.wuest.prefab.events;

import com.wuest.prefab.ClientModRegistry;
import com.wuest.prefab.ModRegistry;
import com.wuest.prefab.Utils;
import com.wuest.prefab.structures.events.StructureClientEventHandler;
import com.wuest.prefab.structures.messages.StructureTagMessage;
import com.wuest.prefab.structures.render.StructureRenderHandler;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.minecraft.network.FriendlyByteBuf;

public class ClientEvents {
    /**
     * Determines how long a shader has been running.
     */
    public static int ticksInGame;

    public static void registerClientEvents() {
        StructureClientEventHandler.registerStructureClientSideEvents();

        ClientEvents.registerClientEndTick();
    }

    public static void registerClientEndTick() {
        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            if (ClientModRegistry.keyBinding.isDown()) {
                if (StructureRenderHandler.currentStructure != null) {
                    FriendlyByteBuf messagePacket = Utils.createStructureMessageBuffer(
                            StructureRenderHandler.currentConfiguration.WriteToCompoundNBT(),
                            StructureTagMessage.EnumStructureConfiguration.getByConfigurationInstance(StructureRenderHandler.currentConfiguration));

                    ClientPlayNetworking.send(ModRegistry.StructureBuild, messagePacket);

                    StructureRenderHandler.currentStructure = null;
                }
            }
        });
    }
}
