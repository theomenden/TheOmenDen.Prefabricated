package com.theomenden.prefabricated.base;

import com.theomenden.prefabricated.Prefab;
import lombok.Getter;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * This is the base tile entity used by the mod.
 *
 * @param <T> The base configuration used by this tile entity.
 * @author WuestMan
 */
@Getter
public abstract class TileEntityBase<T extends BaseConfig> extends BlockEntity {
    /**
     * -- GETTER --
     *
     * @return Gets the configuration class used by this tile entity.
     */
    protected T config;

    protected TileEntityBase(BlockEntityType<?> tileEntityTypeIn, BlockPos pos, BlockState state) {
        super(tileEntityTypeIn, pos, state);
    }

    /**
     * Sets the configuration class used by this tile entity.
     *
     * @param value The updated tile entity.
     */
    public void setConfig(T value) {
        this.config = value;
        this.setChanged();
    }

    public Class<T> getTypeParameterClass() {
        Type type = getClass().getGenericSuperclass();
        ParameterizedType paramType = (ParameterizedType) type;
        return (Class<T>) paramType.getActualTypeArguments()[0].getClass();
    }

    /**
     * Allows for a specialized description packet to be created. This is often used
     * to sync tile entity data from the server to the client easily. For example
     * this is used by signs to synchronize the text to be displayed.
     */
    @Override
    public ClientboundBlockEntityDataPacket getUpdatePacket() {
        // Don't send the packet until the position has been set.
        if (this.worldPosition.getX() == 0 && this.worldPosition.getY() == 0 && this.worldPosition.getZ() == 0) {
            return null;
        }

        return ClientboundBlockEntityDataPacket.create(this);
    }

    @Override
    public boolean triggerEvent(int id, int type) {
        return true;
    }

    @Override
    public @NotNull CompoundTag getUpdateTag() {
        CompoundTag tag = new CompoundTag();
        this.saveAdditional(tag);

        return tag;
    }

    @Override
    public void load(CompoundTag compound) {
        super.load(compound);

        if (this.config != null) {
            this.config.WriteToNBTCompound(compound);
        }
    }

    @Override
    public void saveAdditional(CompoundTag compound) {
        super.saveAdditional(compound);

        var configInstance = this.createConfigInstance();

        if(configInstance != null){
            this.config = configInstance.ReadFromCompoundNBT(compound);
        }
    }

    @Nullable
    public T createConfigInstance() {
        try {
            return this.getTypeParameterClass().getDeclaredConstructor().newInstance();
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            Prefab.logger.error(e.getMessage(),e.getCause());
        }

        return null;
    }
}
