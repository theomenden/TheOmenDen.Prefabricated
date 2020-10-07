package com.wuest.prefab.network.message;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.PacketByteBuf;

/**
 * @author WuestMan
 */
@SuppressWarnings("unused")
public class ConfigSyncMessage extends TagMessage {
	/**
	 * This class is just here to distinguish the configuration sync message from other messages in the mod.
	 *
	 * @param writeToNBTTagCompound The NBTTagCompound to write the data too.
	 */
	public ConfigSyncMessage(CompoundTag writeToNBTTagCompound) {
		super(writeToNBTTagCompound);
	}

	public ConfigSyncMessage() {
		super();
	}

	public static ConfigSyncMessage decode(PacketByteBuf buf) {
		return TagMessage.decode(buf, ConfigSyncMessage.class);
	}
}
