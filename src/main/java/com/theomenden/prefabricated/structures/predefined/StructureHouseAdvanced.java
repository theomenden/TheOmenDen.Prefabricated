package com.theomenden.prefabricated.structures.predefined;

import com.theomenden.prefabricated.ModRegistry;
import com.theomenden.prefabricated.config.EntityPlayerConfiguration;
import com.theomenden.prefabricated.network.message.PlayerEntityTagMessage;
import com.theomenden.prefabricated.structures.base.BuildBlock;
import com.theomenden.prefabricated.structures.base.BuildingMethods;
import com.theomenden.prefabricated.structures.base.Structure;
import com.theomenden.prefabricated.structures.config.HouseAdvancedConfiguration;
import com.theomenden.prefabricated.structures.config.StructureConfiguration;
import io.netty.buffer.Unpooled;
import it.unimi.dsi.fastutil.objects.ReferenceReferencePair;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockState;

import java.util.ArrayList;

/**
 * @author WuestMan
 */
public final class StructureHouseAdvanced extends Structure {
    private BlockPos chestPosition = null;
    private ArrayList<BlockPos> furnacePosition = null;
    private BlockPos trapDoorPosition = null;

    @Override
    protected Boolean CustomBlockProcessingHandled(StructureConfiguration configuration, BuildBlock block, Level world, BlockPos originalPos,
                                                   Block foundBlock, BlockState blockState, Player player) {

        HouseAdvancedConfiguration houseConfiguration = (HouseAdvancedConfiguration) configuration;

        if (foundBlock instanceof FurnaceBlock) {
            if (this.furnacePosition == null) {
                this.furnacePosition = new ArrayList<>();
            }

            this.furnacePosition.add(block.getStartingPosition().getRelativePosition(
                    originalPos,
                    this.getClearSpace().getShape().getDirection(),
                    configuration.houseFacing));
        } else if (foundBlock instanceof ChestBlock && this.chestPosition == null) {
            this.chestPosition = block.getStartingPosition().getRelativePosition(
                    originalPos,
                    this.getClearSpace().getShape().getDirection(),
                    configuration.houseFacing);
        } else if (foundBlock instanceof TrapDoorBlock && this.trapDoorPosition == null) {
            // The trap door will still be added, but the mine shaft may not be
            // built.
            this.trapDoorPosition = block.getStartingPosition().getRelativePosition(
                    originalPos,
                    this.getClearSpace().getShape().getDirection(),
                    configuration.houseFacing);
        } else if (foundBlock == Blocks.SPONGE) {
            // Sponges are sometimes used in-place of trapdoors when trapdoors are used for decoration.
            this.trapDoorPosition = block.getStartingPosition().getRelativePosition(
                    originalPos,
                    this.getClearSpace().getShape().getDirection(),
                    configuration.houseFacing).above();
        } else if (foundBlock instanceof BedBlock) {
            BlockPos bedHeadPosition = block.getStartingPosition().getRelativePosition(originalPos, this.getClearSpace().getShape().getDirection(), configuration.houseFacing);
            BlockPos bedFootPosition = block.getSubBlock().getStartingPosition().getRelativePosition(
                    originalPos,
                    this.getClearSpace().getShape().getDirection(),
                    configuration.houseFacing);

            ReferenceReferencePair<BlockState, BlockState> blockStateTuple = BuildingMethods.getBedState(bedHeadPosition, bedFootPosition, houseConfiguration.bedColor);
            block.setBlockState(blockStateTuple.left());
            block.getSubBlock().setBlockState(blockStateTuple.right());
            this.priorityOneBlocks.add(block);

            return true;
        }

        return false;
    }

    /**
     * This method is used after the main building is build for any additional structures or modifications.
     *
     * @param configuration The structure configuration.
     * @param world         The current world.
     * @param originalPos   The original position clicked on.
     * @param player        The player which initiated the construction.
     */
    @Override
    public void AfterBuilding(StructureConfiguration configuration, ServerLevel world, BlockPos originalPos, Player player) {
        HouseAdvancedConfiguration houseConfig = (HouseAdvancedConfiguration) configuration;
        EntityPlayerConfiguration playerConfig = EntityPlayerConfiguration.loadFromEntity(player);

        BuildingMethods.FillFurnaces(world, this.furnacePosition);

        if (this.chestPosition != null && !playerConfig.builtStarterHouse) {
            // Fill the chest if the player hasn't generated the starting house yet.
            BuildingMethods.FillChest(world, this.chestPosition);
        }

        int minimumHeightForMineshaft = world.getMinBuildHeight() + 21;

        if (this.trapDoorPosition != null && this.trapDoorPosition.getY() > minimumHeightForMineshaft && houseConfig.addMineshaft) {
            // Build the mineshaft.
            BuildingMethods.PlaceMineShaft(world, this.trapDoorPosition.below(), houseConfig.houseFacing, false);
        }

        // Make sure to set this value so the player cannot fill the chest a second time.
        playerConfig.builtStarterHouse = true;

        PlayerEntityTagMessage message = new PlayerEntityTagMessage();
        message.setMessageTag(playerConfig.createPlayerTag());
        FriendlyByteBuf byteBuf = new FriendlyByteBuf(Unpooled.buffer());

        PlayerEntityTagMessage.encode(message, byteBuf);

        // Make sure to send a message to the client to sync up the server player information and the client player
        // information.
        ServerPlayNetworking.send((ServerPlayer) player, ModRegistry.PlayerConfigSync, byteBuf);
    }

}