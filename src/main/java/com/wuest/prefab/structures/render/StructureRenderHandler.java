package com.wuest.prefab.structures.render;


import com.mojang.blaze3d.systems.RenderSystem;
import com.wuest.prefab.Prefab;
import com.wuest.prefab.Tuple;
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
import net.minecraft.client.util.math.Vector3d;
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
    private static int overlay = OverlayTexture.packUv(5, 10);

    /**
     * Resets the structure to show in the world.
     *
     * @param structure     The structure to show in the world, pass null to clear out the client.
     * @param assumedNorth  The assumed norther facing for this structure.
     * @param configuration The configuration for this structure.
     */
    public static void setStructure(Structure structure, Direction assumedNorth, StructureConfiguration configuration) {
        VertexConsumerProvider.Immediate entityVertexConsumer = MinecraftClient.getInstance().getBufferBuilders().getEntityVertexConsumers();

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
    public static void renderPlayerLook(PlayerEntity player, HitResult src, MatrixStack matrixStack) {
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
                StructureRenderHandler.showedMessage = true;
            }

            if (didAny) {
                if (StructureRenderHandler.currentStructure != null) {
                }
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

        if (blockRenderType == BlockRenderType.MODEL) {
            // getColor function.
            int color = minecraft.getBlockColors().getColor(state, world, blockPos, 50);
            float r = (float) (color >> 16 & 255) / 255.0F;
            float g = (float) (color >> 8 & 255) / 255.0F;
            float b = (float) (color & 255) / 255.0F;

            int uvValue = OverlayTexture.DEFAULT_UV;
            int lightLevel = 0xF000F0;

            VertexConsumer consumer = entityVertexConsumer.getBuffer(TexturedRenderLayers.getItemEntityTranslucentCull());

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

    private static void renderModel(MatrixStack matrixStack, Vector3d pos, BlockState state, VertexConsumerProvider entityVertexConsumer) {

    }

    private static void RenderTest(World worldIn, MatrixStack matrixStack) {
        BlockPos originalPos = StructureRenderHandler.currentConfiguration.pos.up();
        // This makes the block north and in-line with the player's line of sight.
        double blockXOffset = originalPos.getX();
        double blockZOffset = originalPos.getZ();
        double blockStartYOffset = originalPos.getY();
        double blockEndYOffset = originalPos.up().getY();

        StructureRenderHandler.drawBox(matrixStack, blockXOffset, blockZOffset, blockStartYOffset, blockEndYOffset);
    }

    private static void drawBox(MatrixStack matrixStack, double blockXOffset, double blockZOffset, double blockStartYOffset, double blockEndYOffset) {
        //RenderSystem._pushMatrix();
        //RenderSystem.multMatrix(matrixStack.peek().getModel());

        final Vec3d view = MinecraftClient.getInstance().getEntityRenderDispatcher().camera.getPos();

        RenderSystem.enableDepthTest();
        RenderSystem.setShader(GameRenderer::getPositionColorShader);

        // RenderSystem.shadeModel(7425);
        // RenderSystem.enableAlphaTest();
        // RenderSystem.defaultAlphaFunc();

        Tessellator tessellator = Tessellator.getInstance();
        BufferBuilder vertexBuffer = tessellator.getBuffer();

        RenderSystem.disableTexture();
        RenderSystem.disableBlend();

        double translatedX = blockXOffset - view.getX();
        double translatedY = blockStartYOffset - view.getY();
        double translatedYEnd = translatedY + 1;
        double translatedZ = blockZOffset - view.getZ();

        vertexBuffer.begin(VertexFormat.DrawMode.LINES, VertexFormats.POSITION_COLOR);

        RenderSystem.lineWidth(2.0f);

        // Draw the verticals of the box.
        for (int k = 1; k < 2; k += 1) {
            vertexBuffer.vertex(translatedX, translatedY, translatedZ).color(1.0F, 1.0F, 0.0F, 1.0F).next();
            vertexBuffer.vertex(translatedX, translatedYEnd, translatedZ).color(1.0F, 1.0F, 0.0F, 1.0F).next();

            vertexBuffer.vertex(translatedX + (double) k, translatedY, translatedZ).color(1.0F, 1.0F, 0.0F, 1.0F).next();
            vertexBuffer.vertex(translatedX + (double) k, translatedYEnd, translatedZ).color(1.0F, 1.0F, 0.0F, 1.0F).next();

            vertexBuffer.vertex(translatedX, translatedY, translatedZ + (double) k).color(1.0F, 1.0F, 0.0F, 1.0F).next();
            vertexBuffer.vertex(translatedX, translatedYEnd, translatedZ + (double) k).color(1.0F, 1.0F, 0.0F, 1.0F).next();

            vertexBuffer.vertex(translatedX + 1.0D, translatedY, translatedZ + (double) k).color(1.0F, 1.0F, 0.0F, 1.0F).next();
            vertexBuffer.vertex(translatedX + 1.0D, translatedYEnd, translatedZ + (double) k).color(1.0F, 1.0F, 0.0F, 1.0F).next();
        }

        // All horizontals.
        for (double i1 = translatedY; i1 <= translatedYEnd; i1 += 1) {
            // RED
            vertexBuffer.vertex(translatedX, i1, translatedZ).color(1.0F, 1.0F, 0.0F, 1.0F).next();
            vertexBuffer.vertex(translatedX, i1, translatedZ + 1.0D).color(1.0F, 1.0F, 0.0F, 1.0F).next();

            vertexBuffer.vertex(translatedX + 1.0D, i1, translatedZ + 1.0D).color(1.0F, 1.0F, 0.0F, 1.0F).next();
            vertexBuffer.vertex(translatedX + 1.0D, i1, translatedZ).color(1.0F, 1.0F, 0.0F, 1.0F).next();

            // BLUE
            vertexBuffer.vertex(translatedX, i1, translatedZ).color(1.0F, 1.0F, 0.0F, 1.0F).next();
            vertexBuffer.vertex(translatedX + 1, i1, translatedZ).color(1.0F, 1.0F, 0.0F, 1.0F).next();

            // Purple
            vertexBuffer.vertex(translatedX + 1, i1, translatedZ + 1).color(1.0F, 1.0F, 0.0F, 1.0F).next();
            vertexBuffer.vertex(translatedX, i1, translatedZ + 1).color(1.0F, 1.0F, 0.0F, 1.0F).next();
        }

        tessellator.draw();

        RenderSystem.lineWidth(1.0F);
        RenderSystem.enableBlend();
        RenderSystem.enableTexture();

        // RenderSystem.shadeModel(7424);
        // RenderSystem.popMatrix();
    }
}
