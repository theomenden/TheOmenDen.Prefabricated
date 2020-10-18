package com.wuest.prefab.structures.config.enums;;

public class AquaBaseOptions extends BaseOption {
	public static AquaBaseOptions Default = new AquaBaseOptions("item.prefab.aqua_base", "assets/prefab/structures/aqua_base.zip", "textures/gui/aqua_base_topdown.png", 119, 160);

	protected AquaBaseOptions(String translationString, String assetLocation, String pictureLocation, int imageWidth, int imageHeight) {
		super(translationString, assetLocation, pictureLocation, imageWidth, imageHeight);
	}
}
