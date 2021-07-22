package com.wuest.prefab.config;

import com.wuest.prefab.base.BaseConfig;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtHelper;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;

public class StructureScannerConfig extends BaseConfig {
    public int blocksToTheLeft = 0;
    public int blocksParallel = 1;
    public int blocksDown = 0;
    public int blocksWide = 1;
    public int blocksLong = 1;
    public int blocksTall = 1;
    public String structureZipName = "";
    public Direction direction;

    public BlockPos blockPos = null;

    public StructureScannerConfig() {
        this.direction = Direction.NORTH;
        this.blockPos = new BlockPos(0, 0, 0);
    }

    @Override
    public void WriteToNBTCompound(NbtCompound compound) {
        compound.putInt("blocksToTheLeft", this.blocksToTheLeft);
        compound.putInt("blocksDown", this.blocksDown);
        compound.putInt("blocksWide", this.blocksWide);
        compound.putInt("blocksLong", this.blocksLong);
        compound.putInt("blocksTall", this.blocksTall);
        compound.putString("structureZipName", this.structureZipName);
        compound.putInt("direction", this.direction.getId());
        compound.putInt("blocksParallel", this.blocksParallel);

        if (this.blockPos != null) {
            compound.put("pos", NbtHelper.fromBlockPos(this.blockPos));
        }
    }

    @Override
    public StructureScannerConfig ReadFromCompoundNBT(NbtCompound compound) {
        this.blocksToTheLeft = compound.getInt("blocksToTheLeft");
        this.blocksDown = compound.getInt("blocksDown");
        this.blocksWide = compound.getInt("blocksWide");
        this.blocksLong = compound.getInt("blocksLong");
        this.blocksTall = compound.getInt("blocksTall");
        this.structureZipName = compound.getString("structureZipName");
        this.direction = Direction.byId(compound.getInt("direction"));
        this.blocksParallel = compound.getInt("blocksParallel");

        if (compound.contains("pos")) {
            this.blockPos = NbtHelper.toBlockPos(compound.getCompound("pos"));
        }

        return this;
    }
}
