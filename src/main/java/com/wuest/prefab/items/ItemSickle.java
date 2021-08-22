package com.wuest.prefab.items;

import com.wuest.prefab.ModRegistry;
import com.wuest.prefab.gui.GuiLangKeys;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.block.*;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.*;
import net.minecraft.tag.BlockTags;
import net.minecraft.text.LiteralText;
import net.minecraft.text.Text;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.HashSet;
import java.util.List;

public class ItemSickle extends ToolItem {
    public static HashSet<Block> effectiveBlocks = new HashSet<>();
    protected int breakRadius = 0;
    protected ToolMaterial toolMaterial;

    public ItemSickle(ToolMaterial toolMaterial) {
        super(toolMaterial, new Item.Settings().group(ModRegistry.PREFAB_GROUP));
        this.breakRadius = 1 + toolMaterial.getMiningLevel();
        this.toolMaterial = toolMaterial;
    }

    public static void setEffectiveBlocks() {
        effectiveBlocks.clear();

        effectiveBlocks.addAll(BlockTags.LEAVES.values());
        effectiveBlocks.addAll(BlockTags.SMALL_FLOWERS.values());
        effectiveBlocks.add(Blocks.TALL_GRASS);
        effectiveBlocks.add(Blocks.DEAD_BUSH);
        effectiveBlocks.add(Blocks.ROSE_BUSH);
        effectiveBlocks.add(Blocks.PEONY);
        effectiveBlocks.add(Blocks.GRASS);
        effectiveBlocks.add(Blocks.SEAGRASS);
        effectiveBlocks.add(Blocks.TALL_SEAGRASS);
    }

    @Override
    public float getMiningSpeedMultiplier(ItemStack stack, BlockState state) {
        Block block = state.getBlock();

        if (!ItemSickle.effectiveBlocks.contains(block) && block != Blocks.COBWEB && state.getMaterial() != Material.LEAVES) {
            return super.getMiningSpeedMultiplier(stack, state);
        } else {
            return 15.0F;
        }
    }

    /**
     * Called when a Block is destroyed using this Item. Return true to trigger the
     * "Use Item" statistic.
     */
    @Override
    public boolean postMine(ItemStack stack, World worldIn, BlockState state, BlockPos pos,
                            LivingEntity entityLiving) {
        if (!worldIn.isClient) {
            stack.damage(1, entityLiving, (livingEntity) -> livingEntity.sendEquipmentBreakStatus(EquipmentSlot.MAINHAND));

            if ((double) state.getHardness(worldIn, pos) != 0.0D && !(state.getBlock() instanceof LeavesBlock)) {
                stack.damage(1, entityLiving, (livingEntity) -> livingEntity.sendEquipmentBreakStatus(EquipmentSlot.MAINHAND));
            } else if ((state.getBlock() instanceof PlantBlock || state.getBlock() instanceof LeavesBlock)
                    && entityLiving instanceof PlayerEntity) {
                BlockPos corner1 = pos.north(this.breakRadius).east(this.breakRadius).up(this.breakRadius);
                BlockPos corner2 = pos.south(this.breakRadius).west(this.breakRadius).down(this.breakRadius);

                for (BlockPos currentPos : BlockPos.iterate(corner1, corner2)) {
                    BlockState currentState = worldIn.getBlockState(currentPos);

                    if (currentState != null && ItemSickle.effectiveBlocks.contains(currentState.getBlock())) {
                        worldIn.breakBlock(currentPos, true);
                    }
                }
            }
        }

        return true;
    }

    /**
     * allows items to add custom lines of information to the mouseover description
     */
    @Environment(EnvType.CLIENT)
    @Override
    public void appendTooltip(ItemStack stack, @Nullable World worldIn, List<Text> tooltip,
                              TooltipContext advanced) {
        super.appendTooltip(stack, worldIn, tooltip, advanced);

        boolean advancedKeyDown = Screen.hasShiftDown();

        if (!advancedKeyDown) {
            tooltip.add(GuiLangKeys.translateToComponent(GuiLangKeys.SHIFT_TOOLTIP));
        } else {
            tooltip.add(GuiLangKeys.translateToComponent(GuiLangKeys.SICKLE_DESC));
        }
    }
}
