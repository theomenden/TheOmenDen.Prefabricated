package com.wuest.prefab.items;

import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.SwordItem;
import net.minecraft.item.ToolMaterial;

/**
 * This class is used to create a sword which has the same speed as pre-1.9
 * swords.
 *
 * @author WuestMan
 */
public class ItemSwiftBlade extends SwordItem {
    /*
     * Initializes a new instance of the ItemSwiftBlade class.
     */
    public ItemSwiftBlade(ToolMaterial tier, int attackDamageIn, float attackSpeedIn) {
        super(tier, attackDamageIn, attackSpeedIn,
                new Item.Settings().maxCount(1).maxDamage(tier.getDurability()).group(ItemGroup.COMBAT));
    }

    /**
     * Returns the amount of damage this item will deal. One heart of damage is
     * equal to 2 damage points.
     */
    @Override
    public float getAttackDamage() {
        return this.getMaterial().getAttackDamage();
    }

    /**
     * Return the name for this tool's material.
     */
    public String getToolMaterialName() {
        return this.getMaterial().toString();
    }

    /**
     * Return the enchantability factor of the item, most of the time is based on
     * material.
     */
    @Override
    public int getEnchantability() {
        return this.getMaterial().getEnchantability();
    }

}