package com.wuest.prefab.structures.events;

import com.wuest.prefab.structures.render.StructureRenderHandler;
import net.fabricmc.fabric.api.event.player.UseBlockCallback;
import net.minecraft.client.MinecraftClient;
import net.minecraft.util.ActionResult;
import net.minecraft.util.math.Direction;

/**
 * @author WuestMan
 */
public final class StructureClientEventHandler {

	public static void registerStructureServerSideEvents() {

		StructureClientEventHandler.registerPlayerUseItemEvent();
	}

	public static void registerPlayerUseItemEvent() {
		StructureClientEventHandler.onPlayerUseBlock();
	}

	/**
	 * The player right-click block event. This is used to stop the structure rendering for the preview.
	 */
	public static void onPlayerUseBlock() {
		UseBlockCallback.EVENT.register((player, world, hand, hitResult) -> {
			if (StructureRenderHandler.currentStructure != null && player == MinecraftClient.getInstance().player) {
				StructureRenderHandler.setStructure(null, Direction.NORTH, null);

				return ActionResult.FAIL;
			}
			
			return ActionResult.PASS;
		});
	}
}
