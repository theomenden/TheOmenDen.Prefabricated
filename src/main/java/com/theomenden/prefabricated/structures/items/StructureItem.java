package com.theomenden.prefabricated.structures.items;

import com.theomenden.prefabricated.ClientModRegistry;
import com.theomenden.prefabricated.Prefab;
import com.theomenden.prefabricated.structures.gui.GuiStructure;
import net.minecraft.core.Direction;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.context.UseOnContext;
import org.jetbrains.annotations.NotNull;

import java.lang.reflect.InvocationTargetException;

/**
 * @author WuestMan
 */
public class StructureItem extends Item {

    /**
     * Initializes a new instance of the StructureItem class.
     */
    public StructureItem() {
        super(new Item.Properties());
        this.Initialize();
    }

    public StructureItem(Item.Properties properties) {
        super(properties);
        this.Initialize();
    }

    /**
     * Does something when the item is right-clicked.
     */
    @Override
    public @NotNull InteractionResult useOn(UseOnContext context) {
        if (context.getLevel().isClientSide) {
            if (context.getClickedFace() == Direction.UP) {
                if (Prefab.useScanningMode) {
                    this.scanningMode(context);
                } else {
                    // Open the client side gui to determine the house options.
                    ClientModRegistry.openGuiForItem(context);
                }

                return InteractionResult.PASS;
            }
        }

        return InteractionResult.FAIL;
    }

    public void scanningMode(UseOnContext context) {
    }

    /**
     * Initializes common fields/properties for this structure item.
     */
    protected void Initialize() {
    }

    protected void RegisterGui(Class<?> classToRegister) {
        try {
            GuiStructure userInterface = (GuiStructure) classToRegister.getDeclaredConstructor().newInstance();
            ClientModRegistry.ModGuis.put(this, userInterface);
        } catch (InstantiationException | IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
            Prefab.logger.error(e.getLocalizedMessage(), e.getCause());
        }
    }
}
