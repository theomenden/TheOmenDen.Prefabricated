package com.wuest.prefab.blocks;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.MapColor;
import net.minecraft.block.Material;
import net.minecraft.block.SlabBlock;
import net.minecraft.sound.BlockSoundGroup;

public class BlockGrassSlab extends SlabBlock {
    public BlockGrassSlab() {
        super(AbstractBlock.Settings.of(Material.SOLID_ORGANIC, MapColor.PALE_GREEN).sounds(BlockSoundGroup.GRASS)
                .strength(0.5f, 0.5f));
    }
}
