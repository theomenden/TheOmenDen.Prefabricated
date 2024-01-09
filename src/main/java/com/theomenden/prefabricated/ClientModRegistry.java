package com.theomenden.prefabricated;

import com.mojang.blaze3d.platform.InputConstants;
import com.theomenden.prefabricated.base.BaseConfig;
import com.theomenden.prefabricated.blocks.BlockCustomWall;
import com.theomenden.prefabricated.blocks.BlockGrassSlab;
import com.theomenden.prefabricated.blocks.BlockGrassStairs;
import com.theomenden.prefabricated.config.EntityPlayerConfiguration;
import com.theomenden.prefabricated.config.StructureScannerConfig;
import com.theomenden.prefabricated.gui.GuiBase;
import com.theomenden.prefabricated.gui.screens.GuiStructureScanner;
import com.theomenden.prefabricated.network.message.ConfigSyncMessage;
import com.theomenden.prefabricated.network.message.PlayerEntityTagMessage;
import com.theomenden.prefabricated.structures.gui.GuiStructure;
import com.theomenden.prefabricated.structures.items.StructureItem;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;

import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.minecraft.client.KeyMapping;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.renderer.BiomeColors;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.core.BlockPos;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.GrassColor;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import org.lwjgl.glfw.GLFW;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.function.Consumer;

public class ClientModRegistry {

    public static EntityPlayerConfiguration playerConfig = new EntityPlayerConfiguration();
    public static KeyMapping keyBinding;
    public static ArrayList<StructureScannerConfig> structureScanners = new ArrayList<>();
    /**
     * The hashmap of mod guis.
     */
    public static HashMap<StructureItem, GuiStructure> ModGuis = new HashMap<>();

    public static void registerModComponents() {
        ClientModRegistry.registerKeyBindings();

        ClientModRegistry.registerBlockLayers();

        ClientModRegistry.registerServerToClientMessageHandlers();

        ClientModRegistry.RegisterGuis();
    }

    public static void openGuiForItem(UseOnContext itemUseContext) {
        ClientModRegistry.ModGuis
                .entrySet()
                .stream()
                .filter(entry -> entry.getKey() == itemUseContext
                        .getItemInHand()
                        .getItem())
                .map(Map.Entry::getValue)
                .forEach(screen -> {
                    screen.pos = itemUseContext.getClickedPos();
                    Minecraft
                            .getInstance()
                            .setScreen(screen);
                });
    }

    public static void openGuiForBlock(BlockPos blockPos, Level world, BaseConfig config) {
        GuiBase screen = null;

        if (config instanceof StructureScannerConfig) {
            screen = new GuiStructureScanner(blockPos, world, (StructureScannerConfig) config);
        }

        if (screen != null) {
            Minecraft.getInstance().setScreen(screen);
        }
    }

    private static void registerServerToClientMessageHandlers() {

        ClientPlayNetworking.registerGlobalReceiver(ModRegistry.ConfigSync,
                (client, handler, buf, responseSender) -> {
                    // Can only access the "attachedData" on the "network thread" which is here.
                    ConfigSyncMessage syncMessage = ConfigSyncMessage.decode(buf);

                    client.execute(() -> {
                        // This is now on the "main" client thread and things can be done in the world!
                        Prefab.serverConfiguration.readFromTag(syncMessage.getMessageTag());
                    });
                }
        );

        ClientPlayNetworking.registerGlobalReceiver(ModRegistry.PlayerConfigSync, (client, handler, buf, responseSender) -> {
            // Can only access the "attachedData" on the "network thread" which is here.
            PlayerEntityTagMessage syncMessage = PlayerEntityTagMessage.decode(buf);

            client.execute(() -> {
                // This is now on the "main" client thread and things can be done in the world!
                UUID playerUUID = Minecraft.getInstance().player.getUUID();

                EntityPlayerConfiguration playerConfiguration = EntityPlayerConfiguration.loadFromTag(playerUUID, syncMessage.getMessageTag());
                ClientModRegistry.playerConfig.builtStarterHouse = playerConfiguration.builtStarterHouse;
                ClientModRegistry.playerConfig.givenHouseBuilder = playerConfiguration.givenHouseBuilder;
            });
        });
    }

    private static void registerBlockLayers() {
        BlockRenderLayerMap.INSTANCE.putBlock(ModRegistry.GlassStairs, RenderType.cutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModRegistry.GlassSlab, RenderType.cutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModRegistry.PaperLantern, RenderType.cutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModRegistry.Boundary, RenderType.translucent());
        BlockRenderLayerMap.INSTANCE.putBlock(ModRegistry.Phasic, RenderType.translucent());

        BlockRenderLayerMap.INSTANCE.putBlock(ModRegistry.GrassStairs, RenderType.cutoutMipped());
        BlockRenderLayerMap.INSTANCE.putBlock(ModRegistry.DirtStairs, RenderType.cutoutMipped());
        BlockRenderLayerMap.INSTANCE.putBlock(ModRegistry.GrassSlab, RenderType.cutoutMipped());
        BlockRenderLayerMap.INSTANCE.putBlock(ModRegistry.DirtSlab, RenderType.cutoutMipped());
        BlockRenderLayerMap.INSTANCE.putBlock(ModRegistry.GrassWall, RenderType.cutoutMipped());
        BlockRenderLayerMap.INSTANCE.putBlock(ModRegistry.DirtWall, RenderType.cutoutMipped());
        BlockRenderLayerMap.INSTANCE.putBlock(ModRegistry.LightSwitch, RenderType.cutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModRegistry.DarkLamp, RenderType.cutoutMipped());
    }

    /**
     * This is called within a Mixin as the BlockColors and ItemColors classes are otherwise null when this class is called.
     */
    public static void RegisterBlockRenderer() {
        // Register the block renderer.
        Minecraft.getInstance()
                 .getBlockColors()
                 .register((state, worldIn, pos, tintIndex) -> worldIn != null && pos != null
                ? BiomeColors.getAverageGrassColor(worldIn, pos)
                : GrassColor.get(0.5D, 1.0D), ModRegistry.GrassWall, ModRegistry.GrassSlab, ModRegistry.GrassStairs);

        // Register the item renderer.
        Minecraft.getInstance().itemColors.register((stack, tintIndex) -> {
            // Get the item for this stack.
            Item item = stack.getItem();

            if (item instanceof BlockItem itemBlock && isPaintBlock(itemBlock)) {
                // Get the block for this item and determine if it's a grass stairs.
                    BlockPos pos = Minecraft.getInstance().player.blockPosition();
                    ClientLevel world = Minecraft.getInstance().level;
                    return BiomeColors.getAverageGrassColor(world, pos);
            }

            return -1;
        }, new Block[]{ModRegistry.GrassWall, ModRegistry.GrassSlab, ModRegistry.GrassStairs});
    }

    private static boolean isPaintBlock(BlockItem itemBlock) {
        boolean paintBlock = false;

        if (itemBlock.getBlock() instanceof BlockCustomWall customWall) {

            if (customWall.BlockVariant == BlockCustomWall.EnumType.GRASS) {
                paintBlock = true;
            }
        } else if (itemBlock.getBlock() instanceof BlockGrassSlab) {
            paintBlock = true;
        } else if (itemBlock.getBlock() instanceof BlockGrassStairs) {
            paintBlock = true;
        }
        return paintBlock;
    }

    /**
     * Adds all the Mod Guis to the HasMap.
     */
    private static void RegisterGuis() {
        ModRegistry.guiRegistrations.forEach(consumer -> consumer.accept(null));
    }

    private static void registerKeyBindings() {
        // TODO: Create translation keys.
        keyBinding = KeyBindingHelper.registerKeyBinding(new KeyMapping(
                "Build Current Structure", // The translation key of the keybinding's name
                InputConstants.Type.KEYSYM,
                GLFW.GLFW_KEY_B,
                "Prefab - Structure Preview" // The translation key of the keybinding's category.
        ));
    }
}
