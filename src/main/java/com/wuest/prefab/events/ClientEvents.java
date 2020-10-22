package com.wuest.prefab.events;

import com.wuest.prefab.structures.events.StructureClientEventHandler;

public class ClientEvents {
	/**
	 * Determines how long a shader has been running.
	 */
	public static int ticksInGame;

	public static void registerClientEvents() {
		StructureClientEventHandler.registerStructureClientSideEvents();
	}
}
