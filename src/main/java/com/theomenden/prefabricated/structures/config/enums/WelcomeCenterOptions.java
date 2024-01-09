package com.theomenden.prefabricated.structures.config.enums;

public class WelcomeCenterOptions extends BaseOption {
	public static WelcomeCenterOptions Default = new WelcomeCenterOptions(
			"item.prefabricated.welcome_center",
            "assets/prefabricated/structures/welcome_center.zip",
			"textures/gui/welcome_center.png",
			true,
			false);

	protected WelcomeCenterOptions(String translationString,
								   String assetLocation,
								   String pictureLocation,
								   boolean hasBedColor,
								   boolean hasGlassColor) {
		super(
				translationString,
				assetLocation,
				pictureLocation,
				hasBedColor,
				hasGlassColor);
	}
}
