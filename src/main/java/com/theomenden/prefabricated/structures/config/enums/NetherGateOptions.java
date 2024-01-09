package com.theomenden.prefabricated.structures.config.enums;

public class NetherGateOptions extends BaseOption {
    public static NetherGateOptions AncientSkull = new NetherGateOptions(
            "item.prefabricated.item_nether_gate_skull",
            "assets/prefabricated/structures/nether_gate.zip",
            "textures/gui/nether_gate.png",
            false,
            false);

    public static NetherGateOptions CorruptedTree = new NetherGateOptions(
            "item.prefabricated.item_nether_gate_tree",
            "assets/prefabricated/structures/nether_gate_tree.zip",
            "textures/gui/nether_gate_tree.png",
            false,
            false);

    protected NetherGateOptions(String translationString,
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
