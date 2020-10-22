package com.wuest.prefab.mixins;

import com.wuest.prefab.config.EntityPlayerConfiguration;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.CompoundTag;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.HashMap;
import java.util.UUID;

@Mixin(PlayerEntity.class)
public class SavePlayerDataMixin {

	public static HashMap<UUID, EntityPlayerConfiguration> playerTagData = new HashMap<>();

	//@ModifyVariable(method = "writeCustomDataToTag(Lnet/minecraft/nbt/CompoundTag;)V", at = @At("TAIL"), ordinal = 0)
	@Inject(method = "saveCustomTagData", at = @At("TAIL"))
	private void saveCustomTagData(CompoundTag tag, CallbackInfo ci) {
		UUID playerTag = tag.getUuid("UUID");
		EntityPlayerConfiguration configuration;

		if (!SavePlayerDataMixin.playerTagData.containsKey(playerTag)) {
			configuration = new EntityPlayerConfiguration();

		} else {
			configuration = SavePlayerDataMixin.playerTagData.get(playerTag);
		}

		tag.put("PrefabTag", configuration.createPlayerTag());
	}

	//@ModifyVariable(method = "readCustomDataFromTag(L)V", at = @At("TAIL"), ordinal = 0)
	@Inject(method = "readCustomTagData", at = @At("TAIL"))
	private void readCustomTagData(CompoundTag tag, CallbackInfo ci) {
		UUID playerTag = tag.getUuid("UUID");

		EntityPlayerConfiguration configuration = new EntityPlayerConfiguration();

		if (tag.contains("PrefabTag")) {
			configuration.loadFromNBTTagCompound(tag.getCompound("PrefabTag"));
		}

		if (!SavePlayerDataMixin.playerTagData.containsKey(playerTag)) {
			SavePlayerDataMixin.playerTagData.put(playerTag, configuration);
		} else {
			SavePlayerDataMixin.playerTagData.replace(playerTag, configuration);
		}
	}
}
