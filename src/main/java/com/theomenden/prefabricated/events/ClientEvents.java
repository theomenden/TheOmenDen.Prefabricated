package com.theomenden.prefabricated.events;

import com.theomenden.prefabricated.ClientModRegistry;
import com.theomenden.prefabricated.ModRegistry;
import com.theomenden.prefabricated.Utils;
import com.theomenden.prefabricated.structures.config.BasicStructureConfiguration;
import com.theomenden.prefabricated.structures.events.StructureClientEventHandler;
import com.theomenden.prefabricated.structures.gui.GuiStructure;
import com.theomenden.prefabricated.structures.items.ItemBasicStructure;
import com.theomenden.prefabricated.structures.items.StructureItem;
import com.theomenden.prefabricated.structures.messages.StructureTagMessage;
import com.theomenden.prefabricated.structures.render.StructureRenderHandler;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.minecraft.client.Minecraft;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.item.ItemStack;

public final class ClientEvents {
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
                    var mainHandStack = Minecraft.getInstance().player.getMainHandItem();
                    var offHandStack = Minecraft.getInstance().player.getOffhandItem();
                    boolean foundCorrectStructureItem = false;

                    if (mainHandStack != ItemStack.EMPTY || offHandStack != ItemStack.EMPTY) {
                        StructureTagMessage.EnumStructureConfiguration structureConfigurationEnum = StructureTagMessage.EnumStructureConfiguration.getByConfigurationInstance(StructureRenderHandler.currentConfiguration);

                        if (mainHandStack != ItemStack.EMPTY && mainHandStack.getItem() instanceof StructureItem) {
                            // Check main hand.
                            foundCorrectStructureItem = ClientEvents.checkIfStackIsCorrectGui(structureConfigurationEnum, mainHandStack);
                        }

                        if (!foundCorrectStructureItem && offHandStack != ItemStack.EMPTY && offHandStack.getItem() instanceof StructureItem) {
                            // Main hand is not correct item; check off-hand
                            foundCorrectStructureItem = ClientEvents.checkIfStackIsCorrectGui(structureConfigurationEnum, offHandStack);
                        }
                    }

                    if (foundCorrectStructureItem) {
                        FriendlyByteBuf messagePacket = Utils.createStructureMessageBuffer(
                                StructureRenderHandler.currentConfiguration.WriteToCompoundTag(),
                                StructureTagMessage.EnumStructureConfiguration.getByConfigurationInstance(StructureRenderHandler.currentConfiguration));

                        ClientPlayNetworking.send(ModRegistry.StructureBuild, messagePacket);
                    }

                    StructureRenderHandler.currentStructure = null;
                }
            }
        });
    }

    public static boolean checkIfStackIsCorrectGui(StructureTagMessage.EnumStructureConfiguration currentConfiguration, ItemStack stack) {
        var currentItem = stack.getItem();
        GuiStructure mainHandGui = ClientModRegistry.ModGuis.get(currentItem);

        if (currentConfiguration == mainHandGui.structureConfiguration) {
            if (currentConfiguration == StructureTagMessage.EnumStructureConfiguration.Basic) {
                ItemBasicStructure item = (ItemBasicStructure) stack.getItem();
                BasicStructureConfiguration.EnumBasicStructureName basicStructureName = ((BasicStructureConfiguration)StructureRenderHandler.currentConfiguration).basicStructureName;

                return item.structureType == basicStructureName;
            } else {
                return true;
            }
        }

        return false;
    }
}
