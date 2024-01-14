package com.theomenden.prefabricated.structures.messages;

import com.theomenden.prefabricated.network.message.TagMessage;
import com.theomenden.prefabricated.structures.config.*;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.FriendlyByteBuf;

import java.util.Arrays;

/**
 * @author WuestMan
 */
public class StructureTagMessage extends TagMessage {
    private EnumStructureConfiguration structureConfig;

    /**
     * Initializes a new instance of the StructureTagMessage class.
     */
    public StructureTagMessage() {
    }

    /**
     * Initializes a new instance of the StructureTagMessage class.
     *
     * @param tagMessage The message to send.
     */
    public StructureTagMessage(CompoundTag tagMessage, EnumStructureConfiguration structureConfig) {
        super(tagMessage);

        this.structureConfig = structureConfig;
    }

    public static StructureTagMessage decode(FriendlyByteBuf buf) {
        // This class is very useful in general for writing more complex objects.
        CompoundTag tag = buf.readNbt();
        StructureTagMessage returnValue = new StructureTagMessage();

        returnValue.structureConfig = EnumStructureConfiguration.getFromIdentifier(tag.getInt("config"));

        returnValue.tagMessage = tag.getCompound("dataTag");

        return returnValue;
    }

    public static void encode(StructureTagMessage message, FriendlyByteBuf buf) {
        CompoundTag tag = new CompoundTag();
        tag.putInt("config", message.structureConfig.identifier);
        tag.put("dataTag", message.tagMessage);

        buf.writeNbt(tag);
    }

    public EnumStructureConfiguration getStructureConfig() {
        return this.structureConfig;
    }

    /**
     * This enum is used to contain the structures which will be used in message handling.
     *
     * @author WuestMan
     */
    public enum EnumStructureConfiguration {
        Basic(0, new BasicStructureConfiguration()),
        StartHouse(1, new HouseConfiguration()),
        ModerateHouse(2, new HouseImprovedConfiguration()),
        Bulldozer(3, new BulldozerConfiguration()),
        InstantBridge(4, new InstantBridgeConfiguration()),
        AdvancedHouse(5, new HouseAdvancedConfiguration());

        public int identifier;
        public StructureConfiguration structureConfig;

        <T extends StructureConfiguration> EnumStructureConfiguration(int identifier, T structureConfig) {
            this.identifier = identifier;
            this.structureConfig = structureConfig;
        }

        public static EnumStructureConfiguration getFromIdentifier(int identifier) {
            return Arrays
                    .stream(EnumStructureConfiguration.values())
                    .filter(config -> config.identifier == identifier)
                    .findFirst()
                    .orElse(EnumStructureConfiguration.Basic);

        }

        public static EnumStructureConfiguration getByConfigurationInstance(StructureConfiguration structureConfig) {
            return Arrays
                    .stream(EnumStructureConfiguration.values())
                    .filter(configuration -> configuration.structureConfig
                            .getClass()
                            .equals(structureConfig.getClass()))
                    .findFirst()
                    .orElse(null);

        }
    }
}