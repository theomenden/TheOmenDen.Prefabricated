package com.wuest.prefab.base;

import net.minecraft.nbt.NbtCompound;

/**
 * This abstract class is used as the base for CompoundNBT configurations.
 *
 * @author WuestMan
 */
public abstract class BaseConfig {
	/**
	 * Writes to an CompoundNBT given an existing CompoundNBT.
	 *
	 * @param compound The existing tag to add custom values too.
	 */
	public abstract void WriteToNBTCompound(NbtCompound compound);

	/**
	 * Reads data from an CompoundNBT and creates an instance of the configuration class.
	 *
	 * @param compound The compound containing the data to create the configuration class.
	 * @return An instance containing the data held within the CompoundNBT
	 */
	public abstract <T extends BaseConfig> T ReadFromCompoundNBT(NbtCompound compound);

	/**
	 * Gets an CompoundNBT which has this classes information in it.
	 *
	 * @return A {@link NbtCompound} with this classes values.
	 */
	public NbtCompound GetCompoundNBT() {
		NbtCompound compound = new NbtCompound();
		this.WriteToNBTCompound(compound);
		return compound;
	}
}