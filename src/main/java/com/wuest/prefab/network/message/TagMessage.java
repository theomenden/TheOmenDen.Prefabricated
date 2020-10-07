package com.wuest.prefab.network.message;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.PacketByteBuf;

/**
 * @author WuestMan
 */
@SuppressWarnings("WeakerAccess")
public class TagMessage {
	protected CompoundTag tagMessage;

	protected TagMessage() {
	}

	public TagMessage(CompoundTag tagMessage) {
		this.tagMessage = tagMessage;
	}

	public static <T extends TagMessage> T decode(PacketByteBuf buf, Class<T> clazz) {
		T message = null;

		try {
			message = clazz.newInstance();
		} catch (InstantiationException | IllegalAccessException e) {
			e.printStackTrace();
		}

		assert message != null;
		message.tagMessage = buf.readCompoundTag();
		return message;
	}

	public static <T extends TagMessage> void encode(T message, PacketByteBuf buf) {
		buf.writeCompoundTag(message.tagMessage);
	}

	public CompoundTag getMessageTag() {
		return this.tagMessage;
	}

	public void setMessageTag(CompoundTag value) {
		this.tagMessage = value;
	}
}
