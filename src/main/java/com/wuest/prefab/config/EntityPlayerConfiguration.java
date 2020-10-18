package com.wuest.prefab.config;

import com.wuest.prefab.mixins.SavePlayerDataMixin;
import com.wuest.prefab.structures.config.StructureConfiguration;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.CompoundTag;

import java.util.HashMap;
import java.util.UUID;

public class EntityPlayerConfiguration {
	public static final String PLAYER_ENTITY_TAG = "IsPlayerNew";
	public static final String GIVEN_HOUSEBUILDER_TAG = "givenHousebuilder";
	public static final String Built_Starter_house_Tag = "builtStarterHouse";
	public boolean givenHouseBuilder = false;
	public boolean builtStarterHouse = false;
	private HashMap<String, StructureConfiguration> clientConfigurations = new HashMap<String, StructureConfiguration>();

	public EntityPlayerConfiguration() {
	}

	public static EntityPlayerConfiguration loadFromEntity(PlayerEntity playerEntity) {
		EntityPlayerConfiguration returnValue = new EntityPlayerConfiguration();

		if (SavePlayerDataMixin.playerTagData.containsKey(playerEntity.getUuid())) {
			returnValue = SavePlayerDataMixin.playerTagData.get(playerEntity.getUuid());
		} else {
			SavePlayerDataMixin.playerTagData.put(playerEntity.getUuid(),returnValue);
		}

		return returnValue;
	}

	public static EntityPlayerConfiguration loadFromTag(UUID playerUUID, CompoundTag tag) {
		EntityPlayerConfiguration returnValue = new EntityPlayerConfiguration();

		returnValue.loadFromNBTTagCompound(tag);

		if (SavePlayerDataMixin.playerTagData.containsKey(playerUUID)) {
			SavePlayerDataMixin.playerTagData.replace(playerUUID, returnValue);
		} else {
			SavePlayerDataMixin.playerTagData.put(playerUUID,returnValue);
		}

		return returnValue;
	}

	/**
	 * Loads specific properties from saved NBTTag data.
	 *
	 * @param tag The tag to load the data from.
	 */
	public void loadFromNBTTagCompound(CompoundTag tag) {
		this.givenHouseBuilder = tag.getBoolean(EntityPlayerConfiguration.GIVEN_HOUSEBUILDER_TAG);
		this.builtStarterHouse = tag.getBoolean(EntityPlayerConfiguration.Built_Starter_house_Tag);
	}

	/**
	 * Saves this instance's data to the player tag.
	 */
	public CompoundTag createPlayerTag() {
		CompoundTag compoundTag = new CompoundTag();

		compoundTag.putBoolean(EntityPlayerConfiguration.Built_Starter_house_Tag, this.builtStarterHouse);
		compoundTag.putBoolean(EntityPlayerConfiguration.GIVEN_HOUSEBUILDER_TAG, this.givenHouseBuilder);

		return compoundTag;
	}

	/**
	 * Gets the client config for this gui screen.
	 *
	 * @param guiName                The name of the gui screen class.
	 * @param structureConfiguration The structure configuration class.
	 * @return A default instance of the structure configuration or the existing one found.
	 */
	public <T extends StructureConfiguration> T getClientConfig(String guiName, Class<T> structureConfiguration) {
		T config = (T) this.clientConfigurations.get(guiName);

		if (config == null) {
			try {
				config = structureConfiguration.newInstance();
				this.clientConfigurations.put(guiName, config);
			} catch (InstantiationException | IllegalAccessException e) {
				e.printStackTrace();
			}
		}

		return config;
	}

	/**
	 * This is for clearing out non-persisted objects so when a player changes worlds that the client-side config is
	 * cleared.
	 */
	public void clearNonPersistedObjects() {
		this.clientConfigurations.clear();
	}
}
