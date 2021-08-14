package com.wuest.prefab.blocks;

import com.wuest.prefab.Prefab;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.Random;

/**
 * This block is used as an alternate light source to be used in the structures created in this mod.
 *
 * @author WuestMan
 */
@SuppressWarnings("NullableProblems")
public class BlockPaperLantern extends Block {

	/**
	 * Initializes a new instance of the BlockPaperLantern class.
	 */
	public BlockPaperLantern() {
		// The "func_226896_b_" function causes the "isSolid" field on the block to be set to false.
		super(Settings.of(Prefab.SeeThroughImmovable)
				.sounds(BlockSoundGroup.SNOW)
				.strength(0.6f)
				.luminance(value -> 14)
				.nonOpaque());
	}

	/**
	 * Called periodically clientside on blocks near the player to show effects (like furnace fire particles). Note that
	 * this method will always be called regardless of whether the block can receive random update ticks
	 */
	@Environment(EnvType.CLIENT)
	@Override
	public void randomDisplayTick(BlockState stateIn, World worldIn, BlockPos pos, Random rand) {
		double d0 = (double) pos.getX() + 0.5D;
		double d1 = (double) pos.getY() + 0.7D;
		double d2 = (double) pos.getZ() + 0.5D;
		worldIn.addParticle(ParticleTypes.SMOKE, d0, d1, d2, 0.0D, 0.0D, 0.0D);
		worldIn.addParticle(ParticleTypes.FLAME, d0, d1, d2, 0.0D, 0.0D, 0.0D);
	}
}
