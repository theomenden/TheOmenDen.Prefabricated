package com.wuest.prefab;

import com.wuest.prefab.network.message.TagMessage;
import com.wuest.prefab.structures.messages.StructureTagMessage;
import io.netty.buffer.Unpooled;
import io.netty.util.internal.StringUtil;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.text.LiteralText;
import net.minecraft.util.math.Direction;
import org.apache.commons.lang3.text.WordUtils;

import java.util.ArrayList;

public class Utils {
    public static String[] WrapString(String value) {
        return Utils.WrapString(value, 50);
    }

    public static String[] WrapString(String value, int width) {
        String result = WordUtils.wrap(value, width);
        String[] results = result.split("\n");

        String[] returnValue = new String[results.length];

        for (int i = 0; i < results.length; i++) {
            returnValue[i] = results[i].trim();
        }

        return returnValue;
    }

    public static ArrayList<LiteralText> WrapStringToLiterals(String value) {
        return Utils.WrapStringToLiterals(value, 50);
    }

    /**
     * This is a wrapper method to make sure that when minecraft changes the name of the StringTextComponent again it's a single place update.
     * @param value The text to create the object from.
     * @return A StringTextComponent object.
     */
    public static LiteralText createTextComponent(String value) {
        return new LiteralText(value);
    }

    public static ArrayList<LiteralText> WrapStringToLiterals(String value, int width) {
        String[] values = Utils.WrapString(value, width);
        ArrayList<LiteralText> returnValue = new ArrayList<>();

        for (String stringValue : values) {
            returnValue.add(Utils.createTextComponent (stringValue));
        }

        return returnValue;
    }

    public static PacketByteBuf createMessageBuffer(NbtCompound tag) {
        TagMessage message = new TagMessage(tag);
        PacketByteBuf byteBuf = new PacketByteBuf(Unpooled.buffer());
        TagMessage.encode(message, byteBuf);

        return byteBuf;
    }

    public static PacketByteBuf createStructureMessageBuffer(NbtCompound tag, StructureTagMessage.EnumStructureConfiguration structureConfiguration) {
        StructureTagMessage message = new StructureTagMessage(tag, structureConfiguration);
        PacketByteBuf byteBuf = new PacketByteBuf(Unpooled.buffer());
        StructureTagMessage.encode(message, byteBuf);

        return byteBuf;
    }

    public static Direction getDirectionByName(String name) {
        if (!StringUtil.isNullOrEmpty(name)) {
            for (Direction direction : Direction.values()) {
                if (direction.asString().toLowerCase().equals(name.toLowerCase())) {
                    return direction;
                }
            }
        }

        return Direction.NORTH;
    }
}
