package com.wuest.prefab;

import com.wuest.prefab.blocks.BlockCustomWall;
import com.wuest.prefab.blocks.BlockGrassSlab;
import com.wuest.prefab.blocks.BlockGrassStairs;
import com.wuest.prefab.config.EntityPlayerConfiguration;
import com.wuest.prefab.network.message.ConfigSyncMessage;
import com.wuest.prefab.network.message.PlayerEntityTagMessage;
import com.wuest.prefab.structures.gui.*;
import com.wuest.prefab.structures.items.StructureItem;
import com.wuest.prefab.structures.render.ShaderHelper;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.fabricmc.fabric.api.network.ClientSidePacketRegistry;
import net.minecraft.block.Block;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.color.world.BiomeColors;
import net.minecraft.client.color.world.GrassColors;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.util.InputUtil;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.util.math.BlockPos;
import org.lwjgl.glfw.GLFW;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.function.Consumer;

public class ClientModRegistry {

	public static EntityPlayerConfiguration playerConfig = new EntityPlayerConfiguration();
	public static KeyBinding keyBinding;

	/**
	 * The hashmap of mod guis.
	 */
	public static HashMap<StructureItem, GuiStructure> ModGuis = new HashMap<>();

	public static void registerModComponents() {
		ClientModRegistry.registerKeyBindings();

		ClientModRegistry.registerBlockLayers();

		ClientModRegistry.registerServerToClientMessageHandlers();

		ClientModRegistry.registerRenderers();

		ClientModRegistry.RegisterGuis();
	}

	public static void openGuiForItem(ItemUsageContext itemUseContext) {
		for (Map.Entry<StructureItem, GuiStructure> entry : ClientModRegistry.ModGuis.entrySet()) {
			if (entry.getKey() == itemUseContext.getStack().getItem()) {
				GuiStructure screen = entry.getValue();
				screen.pos = itemUseContext.getBlockPos();

				MinecraftClient.getInstance().setScreen(screen);
			}
		}
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

				ClientModRegistry.playerConfig = EntityPlayerConfiguration.loadFromTag(playerUUID, syncMessage.getMessageTag());
			});
		});
	}

	private static void registerBlockLayers() {
		BlockRenderLayerMap.INSTANCE.putBlock(ModRegistry.GlassStairs, RenderLayer.getCutout());
		BlockRenderLayerMap.INSTANCE.putBlock(ModRegistry.GlassSlab, RenderLayer.getCutout());
		BlockRenderLayerMap.INSTANCE.putBlock(ModRegistry.PaperLantern, RenderLayer.getCutout());
		BlockRenderLayerMap.INSTANCE.putBlock(ModRegistry.Boundary, RenderLayer.getTranslucent());
		BlockRenderLayerMap.INSTANCE.putBlock(ModRegistry.Phasic, RenderLayer.getTranslucent());

		BlockRenderLayerMap.INSTANCE.putBlock(ModRegistry.GrassStairs, RenderLayer.getCutoutMipped());
		BlockRenderLayerMap.INSTANCE.putBlock(ModRegistry.DirtStairs, RenderLayer.getCutoutMipped());
		BlockRenderLayerMap.INSTANCE.putBlock(ModRegistry.GrassSlab, RenderLayer.getCutoutMipped());
		BlockRenderLayerMap.INSTANCE.putBlock(ModRegistry.DirtSlab, RenderLayer.getCutoutMipped());
		BlockRenderLayerMap.INSTANCE.putBlock(ModRegistry.GrassWall, RenderLayer.getCutoutMipped());
		BlockRenderLayerMap.INSTANCE.putBlock(ModRegistry.DirtWall, RenderLayer.getCutoutMipped());
	}

	private static void registerRenderers() {
		ShaderHelper.Initialize();
	}

	public static void RegisterBlockRenderer() {
		// Register the block renderer.
		MinecraftClient.getInstance().getBlockColors().registerColorProvider((state, worldIn, pos, tintIndex) -> worldIn != null && pos != null
				? BiomeColors.getGrassColor(worldIn, pos)
				: GrassColors.getColor(0.5D, 1.0D), ModRegistry.GrassWall, ModRegistry.GrassSlab, ModRegistry.GrassStairs);

		// Register the item renderer.
		MinecraftClient.getInstance().itemColors.register((stack, tintIndex) -> {
			// Get the item for this stack.
			Item item = stack.getItem();

			if (item instanceof BlockItem) {
				// Get the block for this item and determine if it's a grass stairs.
				BlockItem itemBlock = (BlockItem) item;
				boolean paintBlock = false;

				if (itemBlock.getBlock() instanceof BlockCustomWall) {
					BlockCustomWall customWall = (BlockCustomWall) itemBlock.getBlock();

					if (customWall.BlockVariant == BlockCustomWall.EnumType.GRASS) {
						paintBlock = true;
					}
				} else if (itemBlock.getBlock() instanceof BlockGrassSlab) {
					paintBlock = true;
				} else if (itemBlock.getBlock() instanceof BlockGrassStairs) {
					paintBlock = true;
				}

				if (paintBlock) {
					BlockPos pos = MinecraftClient.getInstance().player.getBlockPos();
					ClientWorld world = MinecraftClient.getInstance().world;
					return BiomeColors.getGrassColor(world, pos);
				}
			}

			return -1;
		}, new Block[]{ModRegistry.GrassWall, ModRegistry.GrassSlab, ModRegistry.GrassStairs});
	}

	/**
	 * Adds all of the Mod Guis to the HasMap.
	 */
	private static void RegisterGuis() {
		for (Consumer<Object> consumer : ModRegistry.guiRegistrations) {
			consumer.accept(null);
		}
	}

	private static void registerKeyBindings() {
		keyBinding = KeyBindingHelper.registerKeyBinding(new KeyBinding(
				"Build Current Structure", // The translation key of the keybinding's name
				InputUtil.Type.KEYSYM,
				GLFW.GLFW_KEY_B,
				"Prefab - Structure Preview" // The translation key of the keybinding's category.
		));
	}
}
