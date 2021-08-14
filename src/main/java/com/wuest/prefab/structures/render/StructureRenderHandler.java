package com.wuest.prefab.structures.render;

import com.mojang.blaze3d.platform.GlStateManager;
import com.mojang.blaze3d.systems.RenderSystem;
import com.wuest.prefab.ClientModRegistry;
import com.wuest.prefab.Prefab;
import com.wuest.prefab.Tuple;
import com.wuest.prefab.blocks.BlockStructureScanner;
import com.wuest.prefab.config.StructureScannerConfig;
import com.wuest.prefab.gui.GuiLangKeys;
import com.wuest.prefab.structures.base.BuildBlock;
import com.wuest.prefab.structures.base.Structure;
import com.wuest.prefab.structures.config.StructureConfiguration;
import net.minecraft.block.Block;
import net.minecraft.block.BlockRenderType;
import net.minecraft.block.BlockState;
import net.minecraft.block.Material;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.*;
import net.minecraft.client.render.block.BlockRenderManager;
import net.minecraft.client.render.model.BakedModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.text.Style;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.Formatting;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.ArrayList;

/**
 * @author WuestMan
 * This class was derived from Botania's MultiBlockRenderer.
 * Most changes are for extra comments for myself as well as to use my blocks class structure.
 * http://botaniamod.net/license.php
 */
@SuppressWarnings({"WeakerAccess", "ConstantConditions"})
public class StructureRenderHandler {
    // player's overlapping on structures and other things.
    public static StructureConfiguration currentConfiguration;
    public static Structure currentStructure;
    public static Direction assumedNorth;
    public static boolean rendering = false;
    public static boolean showedMessage = false;
    private static int dimension;

    /**
     * Resets the structure to show in the world.
     *
     * @param structure     The structure to show in the world, pass null to clear out the client.
     * @param assumedNorth  The assumed norther facing for this structure.
     * @param configuration The configuration for this structure.
     */
    public static void setStructure(Structure structure, Direction assumedNorth, StructureConfiguration configuration) {
        StructureRenderHandler.currentStructure = structure;
        StructureRenderHandler.assumedNorth = assumedNorth;
        StructureRenderHandler.currentConfiguration = configuration;
        StructureRenderHandler.showedMessage = false;

        MinecraftClient mc = MinecraftClient.getInstance();

        if (mc.world != null) {
            StructureRenderHandler.dimension = mc.world.getDimension().getLogicalHeight();
        }
    }

    /**
     * This is to render the currently bound structure.
     *
     * @param player The player to render the structure for.
     * @param src    The ray trace for where the player is currently looking.
     */
    public static void renderPlayerLook(PlayerEntity player, HitResult src, MatrixStack matrixStack, CallbackInfo callbackInfo) {
        if (StructureRenderHandler.currentStructure != null
                && StructureRenderHandler.dimension == player.world.getDimension().getLogicalHeight()
                && StructureRenderHandler.currentConfiguration != null
                && Prefab.serverConfiguration.enableStructurePreview) {
            rendering = true;
            boolean didAny = false;

            VertexConsumerProvider.Immediate entityVertexConsumer = MinecraftClient.getInstance().getBufferBuilders().getEntityVertexConsumers();

            ArrayList<Tuple<BlockState, BlockPos>> entityModels = new ArrayList<>();

            for (BuildBlock buildBlock : StructureRenderHandler.currentStructure.getBlocks()) {
                Block foundBlock = Registry.BLOCK.get(buildBlock.getResourceLocation());

                if (foundBlock != null) {
                    // Get the unique block state for this block.
                    BlockState blockState = foundBlock.getDefaultState();
                    buildBlock = BuildBlock.SetBlockState(
                            StructureRenderHandler.currentConfiguration,
                            player.world,
                            StructureRenderHandler.currentConfiguration.pos,
                            StructureRenderHandler.assumedNorth,
                            buildBlock,
                            foundBlock,
                            blockState,
                            StructureRenderHandler.currentStructure);

                    // In order to get the proper relative position I also need the structure's original facing.
                    BlockPos pos = buildBlock.getStartingPosition().getRelativePosition(
                            StructureRenderHandler.currentConfiguration.pos,
                            StructureRenderHandler.currentStructure.getClearSpace().getShape().getDirection(),
                            StructureRenderHandler.currentConfiguration.houseFacing);

                    BlockRenderType blockRenderType = blockState.getRenderType();

                    if (blockRenderType == BlockRenderType.ENTITYBLOCK_ANIMATED) {
                        if (ShaderHelper.hasIncompatibleMods) {
                            entityModels.add(new Tuple<>(buildBlock.getBlockState(), pos));
                        }

                        continue;
                    }

                    if (StructureRenderHandler.renderComponentInWorld(player.world, buildBlock, entityVertexConsumer, matrixStack, pos, blockRenderType)) {
                        didAny = true;
                    }
                }
            }

            // Need to keep this shader stuff, it keeps the blocks from bouncing around while the player moves.
            ShaderHelper.useShader(ShaderHelper.alphaShader, shader -> {
                // getUniformLocation
                int alpha = GlStateManager._glGetUniformLocation(shader, "alpha");
                ShaderHelper.FLOAT_BUF.position(0);
                ShaderHelper.FLOAT_BUF.put(0, 0.4F);

                // uniform1
                GlStateManager._glUniform1(alpha, ShaderHelper.FLOAT_BUF);
            });

            // Draw function.
            entityVertexConsumer.draw(TexturedRenderLayers.getItemEntityTranslucentCull());

            ShaderHelper.releaseShader();

            if (!didAny) {
                // Nothing was generated, tell the user this through a chat message and re-set the structure information.
                StructureRenderHandler.setStructure(null, Direction.NORTH, null);

                TranslatableText message = new TranslatableText(GuiLangKeys.GUI_PREVIEW_COMPLETE);
                message.setStyle(Style.EMPTY.withColor(Formatting.GREEN));
                player.sendMessage(message, false);
            } else if (!StructureRenderHandler.showedMessage) {
                TranslatableText message = new TranslatableText(GuiLangKeys.GUI_PREVIEW_NOTICE);
                message.setStyle(Style.EMPTY.withColor(Formatting.GREEN));
                player.sendMessage(message, false);

                message = new TranslatableText(GuiLangKeys.GUI_BLOCK_CLICKED);
                message.setStyle(Style.EMPTY.withColor(Formatting.YELLOW));
                player.sendMessage(message, false);

                StructureRenderHandler.showedMessage = true;
            }
        }
    }

    private static boolean renderComponentInWorld(World world, BuildBlock buildBlock, VertexConsumerProvider entityVertexConsumer, MatrixStack matrixStack, BlockPos pos, BlockRenderType blockRenderType) {
        // Don't render this block if it's going to overlay a non-air/water block.
        BlockState targetBlock = world.getBlockState(pos);
        if (targetBlock.getMaterial() != Material.AIR && targetBlock.getMaterial() != Material.WATER) {
            return false;
        }

        StructureRenderHandler.doRenderComponent(world, buildBlock, pos, entityVertexConsumer, matrixStack, blockRenderType);

        if (buildBlock.getSubBlock() != null) {
            Block foundBlock = Registry.BLOCK.get(buildBlock.getSubBlock().getResourceLocation());
            BlockState blockState = foundBlock.getDefaultState();

            BuildBlock subBlock = BuildBlock.SetBlockState(
                    StructureRenderHandler.currentConfiguration,
                    world, StructureRenderHandler.currentConfiguration.pos,
                    assumedNorth,
                    buildBlock.getSubBlock(),
                    foundBlock,
                    blockState,
                    StructureRenderHandler.currentStructure);

            BlockPos subBlockPos = subBlock.getStartingPosition().getRelativePosition(
                    StructureRenderHandler.currentConfiguration.pos,
                    StructureRenderHandler.currentStructure.getClearSpace().getShape().getDirection(),
                    StructureRenderHandler.currentConfiguration.houseFacing);

            BlockRenderType subBlockRenderType = subBlock.getBlockState().getRenderType();

            return StructureRenderHandler.renderComponentInWorld(world, subBlock, entityVertexConsumer, matrixStack, subBlockPos, subBlockRenderType);
        }

        return true;
    }

    private static void doRenderComponent(World world, BuildBlock buildBlock, BlockPos pos, VertexConsumerProvider entityVertexConsumer, MatrixStack matrixStack, BlockRenderType blockRenderType) {
        BlockState state = buildBlock.getBlockState();
        StructureRenderHandler.renderBlock(world, matrixStack, new Vec3d(pos.getX(), pos.getY(), pos.getZ()), state, entityVertexConsumer, blockRenderType, pos);
    }

    private static void renderBlock(World world, MatrixStack matrixStack, Vec3d pos, BlockState state, VertexConsumerProvider entityVertexConsumer, BlockRenderType blockRenderType, BlockPos blockPos) {
        MinecraftClient minecraft = MinecraftClient.getInstance();
        Camera camera = minecraft.getEntityRenderDispatcher().camera;
        Vec3d projectedView = camera.getPos();
        double renderPosX = projectedView.getX();
        double renderPosY = projectedView.getY();
        double renderPosZ = projectedView.getZ();

        // push
        matrixStack.push();

        // Translate function.
        matrixStack.translate(-renderPosX, -renderPosY, -renderPosZ);

        BlockRenderManager renderer = minecraft.getBlockRenderManager();

        // Translate.
        matrixStack.translate(pos.x, pos.y, pos.z);
        BakedModel bakedModel = renderer.getModel(state);

        VertexConsumer consumer = entityVertexConsumer.getBuffer(TexturedRenderLayers.getItemEntityTranslucentCull());

        if (blockRenderType == BlockRenderType.MODEL) {
            // getColor function.
            int color = minecraft.getBlockColors().getColor(state, world, blockPos, 50);
            float r = (float) (color >> 16 & 255) / 255.0F;
            float g = (float) (color >> 8 & 255) / 255.0F;
            float b = (float) (color & 255) / 255.0F;

            int uvValue = OverlayTexture.DEFAULT_UV;
            int lightLevel = 0xF000F0;

            renderer.getModelRenderer().render(
                    matrixStack.peek(),
                    consumer,
                    state,
                    bakedModel,
                    r,
                    g,
                    b,
                    lightLevel,
                    uvValue);
        }

        // pop
        matrixStack.pop();
    }

    public static void RenderTest(World worldIn, MatrixStack matrixStack, double cameraX, double cameraY, double cameraZ) {
        if (StructureRenderHandler.currentStructure != null
                && StructureRenderHandler.dimension == MinecraftClient.getInstance().player.world.getDimension().getLogicalHeight()
                && StructureRenderHandler.currentConfiguration != null
                && Prefab.serverConfiguration.enableStructurePreview) {
            BlockPos originalPos = StructureRenderHandler.currentConfiguration.pos.up();

            double blockXOffset = originalPos.getX();
            double blockZOffset = originalPos.getZ();
            double blockStartYOffset = originalPos.getY();

            StructureRenderHandler.drawBox(
                    matrixStack,
                    blockXOffset,
                    blockZOffset,
                    blockStartYOffset,
                    cameraX,
                    cameraY,
                    cameraZ,
                    1,
                    1,
                    1);
        }
    }

    public static void drawBox(
            MatrixStack matrixStack,
            double blockXOffset,
            double blockZOffset,
            double blockStartYOffset,
            double cameraX,
            double cameraY,
            double cameraZ,
            int xLength,
            int zLength,
            int height) {
        RenderSystem.enableDepthTest();
        RenderSystem.setShader(GameRenderer::getPositionColorShader);

        Tessellator tessellator = Tessellator.getInstance();
        BufferBuilder bufferBuilder = tessellator.getBuffer();

        RenderSystem.disableTexture();
        RenderSystem.disableBlend();

        double translatedX = blockXOffset - cameraX;
        double translatedY = blockStartYOffset - cameraY + .02;
        double translatedYEnd = translatedY + height - .02D;
        double translatedZ = blockZOffset - cameraZ;
        RenderSystem.lineWidth(2.0f);

        // Draw the verticals of the box.
        bufferBuilder.begin(VertexFormat.DrawMode.DEBUG_LINE_STRIP, VertexFormats.POSITION_COLOR);
        bufferBuilder.vertex(translatedX, translatedY, translatedZ).color(1.0F, 1.0F, 0.0F, 1.0F).next();
        bufferBuilder.vertex(translatedX, translatedYEnd, translatedZ).color(1.0F, 1.0F, 0.0F, 1.0F).next();
        tessellator.draw();

        bufferBuilder.begin(VertexFormat.DrawMode.DEBUG_LINE_STRIP, VertexFormats.POSITION_COLOR);
        bufferBuilder.vertex(translatedX + xLength, translatedY, translatedZ).color(1.0F, 1.0F, 0.0F, 1.0F).next();
        bufferBuilder.vertex(translatedX + xLength, translatedYEnd, translatedZ).color(1.0F, 1.0F, 0.0F, 1.0F).next();
        tessellator.draw();

        bufferBuilder.begin(VertexFormat.DrawMode.DEBUG_LINE_STRIP, VertexFormats.POSITION_COLOR);
        bufferBuilder.vertex(translatedX, translatedY, translatedZ + zLength).color(1.0F, 1.0F, 0.0F, 1.0F).next();
        bufferBuilder.vertex(translatedX, translatedYEnd, translatedZ + zLength).color(1.0F, 1.0F, 0.0F, 1.0F).next();
        tessellator.draw();

        bufferBuilder.begin(VertexFormat.DrawMode.DEBUG_LINE_STRIP, VertexFormats.POSITION_COLOR);
        bufferBuilder.vertex(translatedX + xLength, translatedY, translatedZ + zLength).color(1.0F, 1.0F, 0.0F, 1.0F).next();
        bufferBuilder.vertex(translatedX + xLength, translatedYEnd, translatedZ + zLength).color(1.0F, 1.0F, 0.0F, 1.0F).next();
        tessellator.draw();

        // Draw bottom horizontals.
        bufferBuilder.begin(VertexFormat.DrawMode.DEBUG_LINE_STRIP, VertexFormats.POSITION_COLOR);

        bufferBuilder.vertex(translatedX, translatedY, translatedZ).color(1.0F, 1.0F, 0.0F, 1.0F).next();
        bufferBuilder.vertex(translatedX, translatedY, translatedZ + zLength).color(1.0F, 1.0F, 0.0F, 1.0F).next();

        bufferBuilder.vertex(translatedX + xLength, translatedY, translatedZ + zLength).color(1.0F, 1.0F, 0.0F, 1.0F).next();
        bufferBuilder.vertex(translatedX + xLength, translatedY, translatedZ).color(1.0F, 1.0F, 0.0F, 1.0F).next();

        bufferBuilder.vertex(translatedX, translatedY, translatedZ).color(1.0F, 1.0F, 0.0F, 1.0F).next();
        bufferBuilder.vertex(translatedX + xLength, translatedY, translatedZ).color(1.0F, 1.0F, 0.0F, 1.0F).next();

        bufferBuilder.vertex(translatedX + xLength, translatedY, translatedZ + zLength).color(1.0F, 1.0F, 0.0F, 1.0F).next();
        bufferBuilder.vertex(translatedX, translatedY, translatedZ + zLength).color(1.0F, 1.0F, 0.0F, 1.0F).next();
        tessellator.draw();

        // Draw top horizontals
        bufferBuilder.begin(VertexFormat.DrawMode.DEBUG_LINE_STRIP, VertexFormats.POSITION_COLOR);

        bufferBuilder.vertex(translatedX, translatedYEnd, translatedZ).color(1.0F, 1.0F, 0.0F, 1.0F).next();
        bufferBuilder.vertex(translatedX, translatedYEnd, translatedZ + zLength).color(1.0F, 1.0F, 0.0F, 1.0F).next();

        bufferBuilder.vertex(translatedX + xLength, translatedYEnd, translatedZ + zLength).color(1.0F, 1.0F, 0.0F, 1.0F).next();
        bufferBuilder.vertex(translatedX + xLength, translatedYEnd, translatedZ).color(1.0F, 1.0F, 0.0F, 1.0F).next();

        bufferBuilder.vertex(translatedX, translatedYEnd, translatedZ).color(1.0F, 1.0F, 0.0F, 1.0F).next();
        bufferBuilder.vertex(translatedX + xLength, translatedYEnd, translatedZ).color(1.0F, 1.0F, 0.0F, 1.0F).next();

        bufferBuilder.vertex(translatedX + xLength, translatedYEnd, translatedZ + zLength).color(1.0F, 1.0F, 0.0F, 1.0F).next();
        bufferBuilder.vertex(translatedX, translatedYEnd, translatedZ + zLength).color(1.0F, 1.0F, 0.0F, 1.0F).next();
        tessellator.draw();

        RenderSystem.lineWidth(1.0F);
        RenderSystem.enableBlend();
        RenderSystem.enableTexture();
    }

    public static void renderScanningBoxes(MatrixStack matrixStack,
                                           double cameraX,
                                           double cameraY,
                                           double cameraZ) {
        for (int i = 0; i < ClientModRegistry.structureScanners.size(); i++) {
            StructureScannerConfig config = ClientModRegistry.structureScanners.get(i);

            BlockPos pos = config.blockPos;
            boolean removeConfig = false;
            removeConfig = pos == null;

            // Make sure the block exists in the world at the block pos.
            if (pos != null) {
                removeConfig = !(MinecraftClient.getInstance().world.getBlockState(pos).getBlock() instanceof BlockStructureScanner);
            }

            if (removeConfig) {
                ClientModRegistry.structureScanners.remove(i);
                i--;
                continue;
            }

            Direction leftDirection = config.direction.rotateYCounterclockwise();

            BlockPos startingPosition = config.blockPos
                    .offset(leftDirection, config.blocksToTheLeft)
                    .offset(Direction.DOWN, config.blocksDown)
                    .offset(config.direction, config.blocksParallel);

            int xLength = config.blocksWide;
            int zLength = config.blocksLong;

            // Based on direction, width and length may be need to be modified;

            switch (config.direction) {
                case NORTH: {
                    zLength = -zLength;
                    startingPosition = startingPosition.offset(config.direction.getOpposite());
                    break;
                }

                case EAST: {
                    int tempWidth = xLength;
                    xLength = zLength;
                    zLength = tempWidth;
                    break;
                }

                case SOUTH: {
                    xLength = -xLength;
                    startingPosition = startingPosition.offset(config.direction.rotateYCounterclockwise());
                    break;
                }

                case WEST: {
                    int tempLength = zLength;
                    zLength = -xLength;
                    xLength = -tempLength;

                    startingPosition = startingPosition.offset(config.direction.getOpposite());
                    startingPosition = startingPosition.offset(config.direction.rotateYCounterclockwise());
                    break;
                }
            }

            StructureRenderHandler.drawBox(
                    matrixStack,
                    startingPosition.getX(),
                    startingPosition.getZ(),
                    startingPosition.getY(),
                    cameraX,
                    cameraY,
                    cameraZ,
                    xLength,
                    zLength,
                    config.blocksTall);
        }
    }
}
