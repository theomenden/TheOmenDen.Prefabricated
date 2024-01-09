package com.theomenden.prefabricated.structures.base;

import com.google.gson.annotations.Expose;
import lombok.Getter;
import lombok.Setter;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;

/**
 * This class represents the offsets for a particular position.
 *
 * @author WuestMan
 */
@Setter
@Getter
@SuppressWarnings({"unused", "WeakerAccess"})
public class PositionOffset {
	@Expose
	private int northOffset;

	@Expose
	private int southOffset;

	@Expose
	private int eastOffset;

	@Expose
	private int westOffset;

	@Expose
	private int heightOffset;

	public PositionOffset() {
		this.Initialize();
	}

	public void setHorizontalOffset(Direction direction, int value) {
		switch (direction) {
			case EAST: {
				this.setEastOffset(value);
				break;
			}
			case SOUTH: {
				this.setSouthOffset(value);
				break;
			}
			case WEST: {
				this.setWestOffset(value);
				break;
			}
			case NORTH: {
				this.setNorthOffset(value);
				break;
			}
		}
	}

	public int getHorizontalOffset(Direction direction) {
        return switch (direction) {
            case EAST -> this.getEastOffset();
            case SOUTH -> this.getSouthOffset();
            case WEST -> this.getWestOffset();
            case NORTH -> this.getNorthOffset();
            default -> 0;
        };

    }

	protected void Initialize() {
		this.northOffset = 0;
		this.southOffset = 0;
		this.eastOffset = 0;
		this.westOffset = 0;
		this.heightOffset = 0;
	}

	public int getOffSetValueForFacing(Direction facing) {
        return switch (facing) {
            case DOWN, UP -> this.heightOffset;
            case EAST -> this.eastOffset;
            case SOUTH -> this.southOffset;
            case WEST -> this.westOffset;
            default -> this.northOffset;
        };
	}

	public BlockPos getRelativePosition(BlockPos pos, Direction assumedNorth, Direction configurationFacing) {
		configurationFacing = configurationFacing.getOpposite();
		Direction originalDirection = assumedNorth;

		for (int i = 0; i < 4; i++) {
			int offSetValue = this.getOffSetValueForFacing(originalDirection);

			pos = pos.relative(configurationFacing, offSetValue);

			originalDirection = originalDirection.getClockWise();
			configurationFacing = configurationFacing.getClockWise();
		}

		pos = pos.relative(Direction.UP, this.heightOffset);

		return pos;
	}

}