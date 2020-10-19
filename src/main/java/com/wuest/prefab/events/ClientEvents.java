package com.wuest.prefab.events;

import com.wuest.prefab.structures.events.StructureClientEventHandler;

public class ClientEvents {
	public static void registerClientEvents() {

		StructureClientEventHandler.registerStructureServerSideEvents();
	}
}
