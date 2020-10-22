package com.wuest.prefab;

import com.wuest.prefab.config.EntityPlayerConfiguration;
import com.wuest.prefab.network.message.ConfigSyncMessage;
import com.wuest.prefab.network.message.PlayerEntityTagMessage;
import com.wuest.prefab.structures.gui.*;
import com.wuest.prefab.structures.items.StructureItem;
import com.wuest.prefab.structures.render.ShaderHelper;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.network.ClientSidePacketRegistry;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.item.ItemUsageContext;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class ClientModRegistry {

	public static EntityPlayerConfiguration playerConfig = new EntityPlayerConfiguration();

	/**
	 * The hashmap of mod guis.
	 */
	public static HashMap<StructureItem, GuiStructure> ModGuis = new HashMap<>();

	public static void registerModComponents() {
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

				MinecraftClient.getInstance().openScreen(screen);
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

	private static void registerRenderers() {
		ShaderHelper.Initialize();
	}

	/**
	 * Adds all of the Mod Guis to the HasMap.
	 */
	private static void RegisterGuis() {
		ClientModRegistry.ModGuis.put(ModRegistry.Warehouse, new GuiWareHouse());
		ClientModRegistry.ModGuis.put(ModRegistry.ChickenCoop, new GuiChickenCoop());
		ClientModRegistry.ModGuis.put(ModRegistry.ProduceFarm, new GuiProduceFarm());
		ClientModRegistry.ModGuis.put(ModRegistry.TreeFarm, new GuiTreeFarm());
		ClientModRegistry.ModGuis.put(ModRegistry.FishPond, new GuiFishPond());
		ClientModRegistry.ModGuis.put(ModRegistry.StartHouse, new GuiStartHouseChooser());
		ClientModRegistry.ModGuis.put(ModRegistry.AdvancedWareHouse, new GuiAdvancedWareHouse());
		ClientModRegistry.ModGuis.put(ModRegistry.MonsterMasher, new GuiMonsterMasher());
		ClientModRegistry.ModGuis.put(ModRegistry.HorseStable, new GuiHorseStable());
		ClientModRegistry.ModGuis.put(ModRegistry.NetherGate, new GuiBasicStructure());
		ClientModRegistry.ModGuis.put(ModRegistry.VillagerHouses, new GuiVillagerHouses());
		ClientModRegistry.ModGuis.put(ModRegistry.ModerateHouse, new GuiModerateHouse());
		ClientModRegistry.ModGuis.put(ModRegistry.Bulldozer, new GuiBulldozer());
		ClientModRegistry.ModGuis.put(ModRegistry.InstantBridge, new GuiInstantBridge());
		ClientModRegistry.ModGuis.put(ModRegistry.StructurePart, new GuiStructurePart());

		ClientModRegistry.ModGuis.put(ModRegistry.Barn, new GuiBasicStructure());
		ClientModRegistry.ModGuis.put(ModRegistry.AdvancedCoop, new GuiBasicStructure());
		ClientModRegistry.ModGuis.put(ModRegistry.AdvancedHorseStable, new GuiBasicStructure());
		ClientModRegistry.ModGuis.put(ModRegistry.MachineryTower, new GuiBasicStructure());
		ClientModRegistry.ModGuis.put(ModRegistry.DefenseBunker, new GuiBasicStructure());
		ClientModRegistry.ModGuis.put(ModRegistry.MineshaftEntrance, new GuiBasicStructure());
		ClientModRegistry.ModGuis.put(ModRegistry.EnderGateway, new GuiBasicStructure());
		ClientModRegistry.ModGuis.put(ModRegistry.AquaBase, new GuiBasicStructure());
		ClientModRegistry.ModGuis.put(ModRegistry.GrassyPlain, new GuiBasicStructure());
		ClientModRegistry.ModGuis.put(ModRegistry.MagicTemple, new GuiBasicStructure());
		ClientModRegistry.ModGuis.put(ModRegistry.GreenHouse, new GuiBasicStructure());
		ClientModRegistry.ModGuis.put(ModRegistry.WatchTower, new GuiBasicStructure());
		ClientModRegistry.ModGuis.put(ModRegistry.WelcomeCenter, new GuiBasicStructure());
		ClientModRegistry.ModGuis.put(ModRegistry.Jail, new GuiBasicStructure());
		ClientModRegistry.ModGuis.put(ModRegistry.Saloon, new GuiBasicStructure());
		ClientModRegistry.ModGuis.put(ModRegistry.SkiLodge, new GuiBasicStructure());
		ClientModRegistry.ModGuis.put(ModRegistry.WindMill, new GuiBasicStructure());
		ClientModRegistry.ModGuis.put(ModRegistry.TownHall, new GuiBasicStructure());
	}
}
