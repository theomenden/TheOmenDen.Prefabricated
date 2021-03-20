package com.wuest.prefab.mixins;

import com.wuest.prefab.ModRegistry;
import com.wuest.prefab.structures.items.ItemBulldozer;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.AnvilScreenHandler;
import net.minecraft.screen.Property;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

import java.util.Map;

@Mixin(AnvilScreenHandler.class)
public class AnvilScreenHandlerMixin {
    @Shadow
    private Property levelCost;

    @Inject(method = "updateResult", at = @At(value = "INVOKE", target = "Lnet/minecraft/item/ItemStack;isDamageable()Z", ordinal = 0), cancellable = true, locals = LocalCapture.CAPTURE_FAILEXCEPTION)
    public void AnvilUpdate(CallbackInfo ci, ItemStack itemStack, int i, int j, int k, ItemStack itemStack2, ItemStack itemStack3, Map<Enchantment, Integer> map, boolean bl) {
        // Because this gets injected into the actual class; we can use "this" to represent the AnvilScreenHandler correctly.
        AnvilScreenHandler handler = (AnvilScreenHandler) (Object) this;
        Item tripleCompressedStone = ModRegistry.TripleCompressedStoneItem;
        ItemBulldozer bulldozer = ModRegistry.Bulldozer;

        if (itemStack2.getItem() == tripleCompressedStone || itemStack3.getItem() == tripleCompressedStone) {
            if (itemStack2.getItem() == bulldozer || itemStack3.getItem() == bulldozer) {
                this.levelCost.set(4);

                itemStack3 = new ItemStack(bulldozer);
                bulldozer.setPoweredValue(itemStack3, true);
                itemStack3.setDamage(0);

                // In order to get this to work an "accessWidener" is necessary.
                handler.output.setStack(0, itemStack3);

                ci.cancel();
            }
        }
    }
}
