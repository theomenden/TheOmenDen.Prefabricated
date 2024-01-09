package com.theomenden.prefabricated.gui;

import com.theomenden.prefabricated.Prefab;
import com.theomenden.prefabricated.Utils;
import com.theomenden.prefabricated.blocks.FullDyeColor;
import net.minecraft.client.resources.language.I18n;
import net.minecraft.core.Direction;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.DyeColor;

import java.lang.annotation.Annotation;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.Field;

@SuppressWarnings("unused")
public class GuiLangKeys {
    @Unlocalized(name = "House Style")
    public static final String HOUSE_STYLE = "prefabricated.gui.starter.style.label";

    @Unlocalized(name = "Basic House")
    public static final String HOUSE_BASIC_DISPLAY = "prefabricated.gui.starter.basic.display";

    @Unlocalized(name = "Ranch Style")
    public static final String HOUSE_RANCH_DISPLAY = "prefabricated.gui.house.ranch.display";

    @Unlocalized(name = "Loft Style")
    public static final String HOUSE_LOFT_DISPLAY = "prefabricated.gui.house.loft.display";

    @Unlocalized(name = "Hobbit Style")
    public static final String HOUSE_HOBBIT_DISPLAY = "prefabricated.gui.house.hobbit.display";

    @Unlocalized(name = "Desert Style")
    public static final String HOUSE_DESERT_DISPLAY = "prefabricated.gui.house.desert.display";

    @Unlocalized(name = "Desert Style 2")
    public static final String HOUSE_DESERT_DISPLAY2 = "prefabricated.gui.house.desert.display2";

    @Unlocalized(name = "Snowy Style")
    public static final String HOUSE_SNOWY_DISPLAY = "prefabricated.gui.house.snowy.display";

    @Unlocalized(name = "Subaquatic Style")
    public static final String HOUSE_SUBAQUATIC_DISPLAY = "prefabricated.gui.house.subaquatic.display";

    @Unlocalized(name = "Camping Style")
    public static final String HOUSE_CAMPING_DISPLAY = "prefabricated.gui.house.camping.display";

    @Unlocalized(name = "Izba Style")
    public static final String HOUSE_IZBA_DISPLAY = "prefabricated.gui.house.izba.display";

    @Unlocalized(name = "Tower Style")
    public static final String HOUSE_TOWER_DISPLAY = "prefabricated.gui.house.tower.display";

    @Unlocalized(name = "Cabin Style")
    public static final String HOUSE_CABIN_DISPLAY = "prefabricated.gui.house.cabin.display";

    @Unlocalized(name = "Treehouse Style")
    public static final String HOUSE_TREE_HOUSE_DISPLAY = "prefabricated.gui.house.tree.display";

    @Unlocalized(name = "Mushroom Style")
    public static final String HOUSE_MUSHROOM_HOUSE_DISPLAY = "prefabricated.gui.house.mushroom.display";

    @Unlocalized(name = "Modern Style")
    public static final String HOUSE_MODERN_DISPLAY = "prefabricated.gui.house.modern.display";

    @Unlocalized(name = "A house designed for modern life.")
    public static final String HOUSE_MODERN_NOTES = "prefabricated.gui.house.modern.notes";

    @Unlocalized(name = "Add Torches")
    public static final String HOUSE_ADD_TORCHES = "prefabricated.gui.house.addtorches";

    @Unlocalized(name = "Add Bed")
    public static final String HOUSE_ADD_BED = "prefabricated.gui.house.addbed";

    @Unlocalized(name = "Add Crafting Table")
    public static final String HOUSE_ADD_CRAFTING_TABLE = "prefabricated.gui.house.addcraftingtable";

    @Unlocalized(name = "Add Furnace")
    public static final String HOUSE_ADD_FURNACE = "prefabricated.gui.house.addfurnace";

    @Unlocalized(name = "Add Chest")
    public static final String HOUSE_ADD_CHEST = "prefabricated.gui.house.addchest";

    @Unlocalized(name = "Add Chest Contents")
    public static final String HOUSE_ADD_CHEST_CONTENTS = "prefabricated.gui.house.addchestcontents";

    @Unlocalized(name = "Build Mineshaft")
    public static final String HOUSE_BUILD_MINESHAFT = "prefabricated.gui.house.buildmineshaft";

    @Unlocalized(name = "Stonebrick")
    public static final String CEILING_BLOCK_TYPE_STONE = "prefabricated.ceiling.block.type.stone";

    @Unlocalized(name = "Brick")
    public static final String CEILING_BLOCK_TYPE_BRICK = "prefabricated.ceiling.block.type.brick";

    @Unlocalized(name = "Sandstone")
    public static final String CEILING_BLOCK_TYPE_SAND = "prefabricated.ceiling.block.type.sand";

    @Unlocalized(name = "Oak")
    public static final String WALL_BLOCK_TYPE_OAK = "prefabricated.wall.block.type.oak";

    @Unlocalized(name = "Spruce")
    public static final String WALL_BLOCK_TYPE_SPRUCE = "prefabricated.wall.block.type.spruce";

    @Unlocalized(name = "Birch")
    public static final String WALL_BLOCK_TYPE_BIRCH = "prefabricated.wall.block.type.birch";

    @Unlocalized(name = "Jungle")
    public static final String WALL_BLOCK_TYPE_JUNGLE = "prefabricated.wall.block.type.jungle";

    @Unlocalized(name = "Acacia")
    public static final String WALL_BLOCK_TYPE_ACACIA = "prefabricated.wall.block.type.acacia";

    @Unlocalized(name = "Dark Oak")
    public static final String WALL_BLOCK_TYPE_DARK_OAK = "prefabricated.wall.block.type.darkoak";

    @Unlocalized(name = "Facing")
    public static final String GUI_STRUCTURE_FACING = "prefabricated.gui.structure.facing";

    @Unlocalized(name = "Glass Color")
    public static final String GUI_STRUCTURE_GLASS = "prefabricated.gui.structure.glass";

    @Unlocalized(name = "Bed Color")
    public static final String GUI_STRUCTURE_BED_COLOR = "prefabricated.gui.structure.bed_color";

    @Unlocalized(name = "The red box in the image on the right shows the block you clicked on.")
    public static final String GUI_BLOCK_CLICKED = "prefabricated.gui.structure.block.clicked";

    @Unlocalized(name = "Cannot build structure due to protected blocks/area or unbreakable blocks are in the area. Block Name: %1$s Block Position: x=%2$s, y=%3$s, z=%4$s")
    public static final String GUI_STRUCTURE_NOBUILD = "prefabricated.gui.structure.nobuild";

    @Unlocalized(name = "Build!")
    public static final String GUI_BUTTON_BUILD = "prefabricated.gui.button.build";

    @Unlocalized(name = "Cancel")
    public static final String GUI_BUTTON_CANCEL = "prefabricated.gui.button.cancel";

    @Unlocalized(name = "Preview!")
    public static final String GUI_BUTTON_PREVIEW = "prefabricated.gui.button.preview";

    @Unlocalized(name = "Structure Complete!")
    public static final String GUI_PREVIEW_COMPLETE = "prefabricated.gui.preview.complete";

    @Unlocalized(name = "Right-click on any block in the world to remove the preview.")
    public static final String GUI_PREVIEW_NOTICE = "prefabricated.gui.preview.notice";

    @Unlocalized(name = "north")
    public static final String GUI_NORTH = "prefabricated.gui.north";

    @Unlocalized(name = "south")
    public static final String GUI_SOUTH = "prefabricated.gui.south";

    @Unlocalized(name = "east")
    public static final String GUI_EAST = "prefabricated.gui.east";

    @Unlocalized(name = "west")
    public static final String GUI_WEST = "prefabricated.gui.west";

    @Unlocalized(name = "white")
    public static final String GUI_WHITE = "prefabricated.gui.white";

    @Unlocalized(name = "orange")
    public static final String GUI_ORANGE = "prefabricated.gui.orange";

    @Unlocalized(name = "magenta")
    public static final String GUI_MAGENTA = "prefabricated.gui.magenta";

    @Unlocalized(name = "light_blue")
    public static final String GUI_LIGHT_BLUE = "prefabricated.gui.light_blue";

    @Unlocalized(name = "light_gray")
    public static final String GUI_LIGHT_GRAY = "prefabricated.gui.light_gray";

    @Unlocalized(name = "yellow")
    public static final String GUI_YELLOW = "prefabricated.gui.yellow";

    @Unlocalized(name = "lime")
    public static final String GUI_LIME = "prefabricated.gui.lime";

    @Unlocalized(name = "pink")
    public static final String GUI_PINK = "prefabricated.gui.pink";

    @Unlocalized(name = "gray")
    public static final String GUI_GRAY = "prefabricated.gui.gray";

    @Unlocalized(name = "silver")
    public static final String GUI_SILVER = "prefabricated.gui.silver";

    @Unlocalized(name = "cyan")
    public static final String GUI_CYAN = "prefabricated.gui.cyan";

    @Unlocalized(name = "purple")
    public static final String GUI_PURPLE = "prefabricated.gui.purple";

    @Unlocalized(name = "blue")
    public static final String GUI_BLUE = "prefabricated.gui.blue";

    @Unlocalized(name = "brown")
    public static final String GUI_BROWN = "prefabricated.gui.brown";

    @Unlocalized(name = "green")
    public static final String GUI_GREEN = "prefabricated.gui.green";

    @Unlocalized(name = "red")
    public static final String GUI_RED = "prefabricated.gui.red";

    @Unlocalized(name = "black")
    public static final String GUI_BLACK = "prefabricated.gui.black";

    @Unlocalized(name = "clear")
    public static final String GUI_CLEAR = "prefabricated.gui.clear";

    @Unlocalized(name = "Flat Roof")
    public static final String VILLAGER_HOUSE_FLAT_ROOF = "prefabricated.gui.villager.house.flat";

    @Unlocalized(name = "Angled Roof")
    public static final String VILLAGER_HOUSE_ANGLED_ROOF = "prefabricated.gui.villager.house.angled";

    @Unlocalized(name = "Fenced Roof")
    public static final String VILLAGER_HOUSE_FENCED_ROOF = "prefabricated.gui.villager.house.fenced";

    @Unlocalized(name = "Blacksmith")
    public static final String VILLAGER_HOUSE_BLACKSMITH = "prefabricated.gui.villager.blacksmith";

    @Unlocalized(name = "Long House")
    public static final String VILLAGER_HOUSE_LONGHOUSE = "prefabricated.gui.villager.long_house";

    @Unlocalized(name = "Spruce House")
    public static final String IMPROVED_HOUSE_SPRUCE = "prefabricated.gui.house.improved.spruce";

    @Unlocalized(name = "Acacia House")
    public static final String IMPROVED_HOUSE_ACACIA = "prefabricated.gui.house.improved.acacia";

    @Unlocalized(name = "Acacia House 2")
    public static final String IMPROVED_HOUSE_ACACIA_2 = "prefabricated.gui.house.improved.acacia_2";

    @Unlocalized(name = "Modern House")
    public static final String IMPROVED_HOUSE_MODERN = "prefabricated.gui.item_modern_house";

    @Unlocalized(name = "Crimson House")
    public static final String IMPROVED_HOUSE_CRIMSON = "prefabricated.gui.house.improved.crimson_house";

    @Unlocalized(name = "Tower House")
    public static final String IMPROVED_HOUSE_TOWER = "prefabricated.gui.house.improved.tower_house";

    @Unlocalized(name = "Hobbit House")
    public static final String IMPROVED_HOUSE_HOBBIT = "prefabricated.gui.house.improved.hobbit_house";

    @Unlocalized(name = "Cottage House")
    public static final String IMPROVED_HOUSE_COTTAGE = "prefabricated.gui.house.improved.cottage_house";

    @Unlocalized(name = "Earthen Home")
    public static final String IMPROVED_EARTHEN_HOME = "prefabricated.gui.house.improved.earthen";

    @Unlocalized(name = "Jungle Home")
    public static final String IMPROVED_JUNGLE_HOME = "prefabricated.gui.house.improved.jungle";

    @Unlocalized(name = "Nether House")
    public static final String IMPROVED_NETHER_HOME = "prefabricated.gui.house.improved.nether";

    @Unlocalized(name = "Mountain House")
    public static final String IMPROVED_MOUNTAIN_HOME = "prefabricated.gui.house.improved.mountain";

    @Unlocalized(name = "Manor")
    public static final String ADVANCED_HOUSE_MANOR= "prefabricated.gui.house.advanced.manor";

    @Unlocalized(name = "Workshop")
    public static final String ADVANCED_HOUSE_WORKSHOP = "prefabricated.gui.house.advanced.workshop";

    @Unlocalized(name = "Estate")
    public static final String ADVANCED_HOUSE_ESTATE = "prefabricated.gui.house.advanced.estate";

    @Unlocalized(name = "Press§9 Shift §7for more information.")
    public static final String SHIFT_TOOLTIP = "prefabricated.gui.tooltip.shift";

    @Unlocalized(name = "Initially invisible, becomes visible when receiving a redstone signal.")
    public static final String BOUNDARY_TOOLTIP = "prefabricated.gui.tooltip.boundary_block";

    @Unlocalized(name = "This item will clear out a 16x16x16 space and provide the drops from each block.")
    public static final String GUI_BULLDOZER_DESCRIPTION = "prefabricated.gui.bulldozer_desc";

    @Unlocalized(name = "The area cleared will be in the direction you are facing.")
    public static final String GUI_CLEARED_AREA = "prefabricated.gui.cleared_area";

    @Unlocalized(name = "Needs to be imbued with the power of the earth and magic. Try combining with tripple compressed stone in an anvil.")
    public static final String BULLDOZER_UNPOWERED_TOOLTIP = "prefabricated.gui.tooltip.bulldozer_unpowered";

    @Unlocalized(name = "Powered with the strength of the earth and magic, you believe a large area can be cleared.")
    public static final String BULLDOZER_POWERED_TOOLTIP = "prefabricated.gui.tooltip.bulldozer_powered";

    @Unlocalized(name = "Bridge Length")
    public static final String BRIDGE_LENGTH = "prefabricated.gui.bridge_length";

    @Unlocalized(name = "Bridge Material")
    public static final String BRIDGE_MATERIAL = "prefabricated.gui.bridge_material";

    @Unlocalized(name = "Include Roof")
    public static final String INCLUDE_ROOF = "prefabricated.gui.bridge_include_roof";

    @Unlocalized(name = "Interior Height")
    public static final String INTERIOR_HEIGHT = "prefabricated.gui.bridge_interior_height";

    @Unlocalized(name = "Wall")
    public static final String WALL = "prefabricated.gui.part_style.wall";

    @Unlocalized(name = "Gate")
    public static final String GATE = "prefabricated.gui.part_style.gate";

    @Unlocalized(name = "Frame")
    public static final String FRAME = "prefabricated.gui.part_style.frame";

    @Unlocalized(name = "Stairs")
    public static final String STAIRS = "prefabricated.gui.part_style.stairs";

    @Unlocalized(name = "Circle")
    public static final String CIRCLE = "prefabricated.gui.part_style.circle";

    @Unlocalized(name = "Style")
    public static final String STYLE = "prefabricated.gui.style";

    @Unlocalized(name = "Material")
    public static final String MATERIAL = "prefabricated.gui.material";

    @Unlocalized(name = "Height")
    public static final String HEIGHT = "prefabricated.gui.height";

    @Unlocalized(name = "Width")
    public static final String WIDTH = "prefabricated.gui.width";

    @Unlocalized(name = "Length")
    public static final String LENGTH = "prefabricated.gui.length";

    @Unlocalized(name = "Stairs Height")
    public static final String STAIRS_HEIGHT = "prefabricated.gui.stairs_height";

    @Unlocalized(name = "Stairs Width")
    public static final String STAIRS_WIDTH = "prefabricated.gui.stairs_width";

    @Unlocalized(name = "Doorway")
    public static final String DOOR_WAY = "prefabricated.gui.part_style.door_way";

    @Unlocalized(name = "Roof")
    public static final String ROOF = "prefabricated.gui.part_style.roof";

    @Unlocalized(name = "Floor")
    public static final String FLOOR = "prefabricated.gui.part_style.floor";

    @Unlocalized(name = "Building Options")
    public static final String BUILDING_OPTIONS = "prefabricated.gui.building_options";

    @Unlocalized(name = "Used in the recipes for structures, not for direct storage")
    public static final String COMPRESSED_CHEST = "prefabricated.gui.compressed_chest_desc";

    @Unlocalized(name = "Cut grass, flowers, and leaves with ease!")
    public static final String SICKLE_DESC = "prefabricated.gui.sickle_desc";

    @Unlocalized(name = "Starter House")
    public static final String TITLE_STARTER_HOUSE = "item.prefabricated.item_start_house";

    @Unlocalized(name = "Moderate House")
    public static final String TITLE_MODERATE_HOUSE = "item.prefabricated.item_moderate_house";

    @Unlocalized(name = "Advanced House")
    public static final String TITLE_ADVANCED_HOUSE = "item.prefabricated.item_advanced_house";

    @Unlocalized(name = "Bulldozer")
    public static final String TITLE_BULLDOZER = "item.prefabricated.item_bulldozer";

    @Unlocalized(name = "Instant Bridge")
    public static final String TITLE_INSTANT_BRIDGE = "item.prefabricated.item_instant_bridge";

    @Unlocalized(name = "All options have been disabled for this structure")
    public static final String NO_OPTIONS_PART_1 = "gui.prefabricated.no_options_1";

    @Unlocalized(name = "Please update your configuration or contact your server administrator")
    public static final String NO_OPTIONS_PART_2 = "gui.prefabricated.no_options_2";

    /**
     * Translates the specified language key for the current language.
     *
     * @param translateKey The language key to use.
     * @return The translated language key.
     */
    public static String translateString(String translateKey) {
        if (I18n.exists(translateKey)) {
            return I18n.get(translateKey);
        } else {
            return GuiLangKeys.getUnLocalized(translateKey);
        }
    }

    /**
     * Translates a translation key and puts it in a text component.
     *
     * @param translateKey The key to translate.
     * @return A StringTextComponent representing the translated text.
     */
    public static Component translateToComponent(String translateKey) {
        return Utils.createTextComponent(GuiLangKeys.translateString(translateKey));
    }

    /**
     * Gets the unlocalized version of this translation key. If the translation key does not exist the key is returned
     * instead of the unlocalized value.
     *
     * @param translateKey The translation key to get the unlocalized value for.
     * @return The unlocalized value or the passed in key.
     */
    public static String getUnLocalized(String translateKey) {
        for (Field field : GuiLangKeys.class.getDeclaredFields()) {
            String value = "";

            try {
                value = field.get(null).toString();
            } catch (IllegalArgumentException | IllegalAccessException e) {
                Prefab.logger.error(e.getLocalizedMessage(), e.getCause());
            }

            if (translateKey.equals(value)) {
                Annotation[] annotations = field.getDeclaredAnnotations();

                for (Annotation annotation : annotations) {
                    if (annotation instanceof Unlocalized) {

                        return ((Unlocalized) annotation).name();
                    }
                }
            }
        }

        return translateKey;
    }

    public static String translateFacing(Direction facing) {
        return GuiLangKeys.translateString("prefabricated.gui." + facing.getName());
    }

    public static String translateDye(DyeColor dyeColor) {
        return GuiLangKeys.translateString("prefabricated.gui." + dyeColor.getName());
    }

    public static String translateFullDye(FullDyeColor dyeColor) {
        return GuiLangKeys.translateString("prefabricated.gui." + dyeColor.getName());
    }

    /**
     * An annotation which allows the UI to get the unlocalized name;
     *
     * @author WuestMan
     */
    @Retention(RetentionPolicy.RUNTIME)
    @Target(java.lang.annotation.ElementType.FIELD)
    public @interface Unlocalized {
        String name() default "";
    }
}
