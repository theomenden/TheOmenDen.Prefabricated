package com.theomenden.prefabricated.network.message;

import com.theomenden.prefabricated.Prefab;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.FriendlyByteBuf;

import java.lang.reflect.InvocationTargetException;

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

    public static <T extends TagMessage> T decode(FriendlyByteBuf buf, Class<T> clazz) {
        T message = null;

        try {
            message = clazz.getDeclaredConstructor().newInstance();
        } catch (InstantiationException
                 | IllegalAccessException
                 | InvocationTargetException
                 | NoSuchMethodException e) {
            Prefab.logger.error(e.getLocalizedMessage(), e.getCause());
        }

        assert message != null;
        message.tagMessage = buf.readNbt();
        return message;
    }

    public static <T extends TagMessage> void encode(T message, FriendlyByteBuf buf) {
        buf.writeNbt(message.tagMessage);
    }

    public CompoundTag getMessageTag() {
        return this.tagMessage;
    }

    public void setMessageTag(CompoundTag value) {
        this.tagMessage = value;
    }
}
