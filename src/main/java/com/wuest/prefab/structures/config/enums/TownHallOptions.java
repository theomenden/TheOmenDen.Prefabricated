package com.wuest.prefab.structures.config.enums;

public class TownHallOptions extends BaseOption {
	public static TownHallOptions Default = new TownHallOptions("item.prefab.town_hall", "assets/prefab/structures/town_hall.zip", "textures/gui/town_hall_topdown.png", 173, 89);

	protected TownHallOptions(String translationString, String assetLocation, String pictureLocation, int imageWidth, int imageHeight) {
		super(translationString, assetLocation, pictureLocation, imageWidth, imageHeight);
	}
}
