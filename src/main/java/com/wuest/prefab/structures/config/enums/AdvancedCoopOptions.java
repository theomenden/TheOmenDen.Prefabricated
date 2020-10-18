package com.wuest.prefab.structures.config.enums;

public class AdvancedCoopOptions extends BaseOption {
	public static AdvancedCoopOptions Default = new AdvancedCoopOptions("item.prefab.advanced.chicken.coop", "assets/prefab/structures/advancedcoop.zip", "textures/gui/advanced_chicken_coop_topdown.png", 156, 121);

	protected AdvancedCoopOptions(String translationString, String assetLocation, String pictureLocation, int imageWidth, int imageHeight) {
		super(translationString, assetLocation, pictureLocation, imageWidth, imageHeight);
	}
}