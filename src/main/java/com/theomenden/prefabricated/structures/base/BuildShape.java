package com.theomenden.prefabricated.structures.base;

import com.google.gson.annotations.Expose;
import com.theomenden.prefabricated.Utils;
import lombok.Getter;
import lombok.Setter;
import net.minecraft.core.Direction;

/**
 * This class holds the general shape of the structure.
 *
 * @author WuestMan
 */
public class BuildShape {
	@Setter
	@Getter
	@Expose
	private int width;

	@Setter
	@Getter
	@Expose
	private int height;

	@Setter
	@Getter
	@Expose
	private int length;

	@Expose
	private String direction;

	public BuildShape() {
		this.Initialize();
	}

	public Direction getDirection() {
		if (this.direction != null && !this.direction.trim().isEmpty()) {
			return Utils.getDirectionByName(this.direction);
		}

		return Direction.DOWN;
	}

	public void setDirection(Direction value) {
		this.direction = value.toString();
	}

	public void Initialize() {
		this.width = 0;
		this.height = 0;
		this.length = 0;
		this.direction = Direction.NORTH.toString();
	}

	/**
	 * Clones this instance.
	 *
	 * @return A new instance of this class with the same property values.
	 */
	public BuildShape Clone() {
		BuildShape clone = new BuildShape();
		clone.direction = this.direction;
		clone.setHeight(this.height);
		clone.setLength(this.length);
		clone.setWidth(this.width);

		return clone;
	}
}
