package com.theomenden.prefabricated.structures.base;

import com.google.gson.annotations.Expose;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.theomenden.prefabricated.Prefab;
import lombok.Getter;
import lombok.Setter;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.TagParser;
import net.minecraft.resources.ResourceLocation;

/**
 * This class is used to define the necessary properties to describe an entity to be generated when a structure is
 * created in the world.
 *
 * @author WustMan
 */
@SuppressWarnings({"WeakerAccess", "unused"})
public class BuildEntity {
	@Expose
	public double entityXAxisOffset;
	@Expose
	public double entityYAxisOffset;
	@Expose
	public double entityZAxisOffset;
	@Expose
	public Direction entityFacing;
	@Setter
	@Getter
	@Expose
	private int entityId;
	@Expose
	private String entityResourceLocation;
	@Setter
	@Getter
	@Expose
	private PositionOffset startingPosition;
	@Getter
	@Expose
	private String entityNBTData;

	/**
	 * Initializes a new instance of the BuildEntity class.
	 */
	public BuildEntity() {
		this.Initialize();
	}

	public String getEntityResourceString() {
		return this.entityResourceLocation;
	}

	public void setEntityResourceString(String value) {
		this.entityResourceLocation = value;
	}

	public void setEntityResourceString(ResourceLocation value) {
		this.entityResourceLocation = value.toString();
	}

	public ResourceLocation getEntityResource() {
		return new ResourceLocation(this.entityResourceLocation);
	}

	public void setEntityNBTData(CompoundTag tagCompound) {
		this.entityNBTData = tagCompound.toString();
	}

	/**
	 * Initializes any properties to their default properties.
	 */
	public void Initialize() {
		this.entityId = 0;
		this.startingPosition = new PositionOffset();
		this.entityNBTData = "";
		this.entityXAxisOffset = 0;
		this.entityYAxisOffset = 0;
		this.entityZAxisOffset = 0;
		this.entityFacing = Direction.NORTH;
	}

	public CompoundTag getEntityDataTag() {
		CompoundTag tag = null;

		if (!this.entityNBTData.isEmpty()) {
			try {
				tag = TagParser.parseTag(this.entityNBTData);
			} catch (CommandSyntaxException e) {
				Prefab.logger.error(e.getLocalizedMessage(), e.getCause());
			}
		}

		return tag;
	}

}
