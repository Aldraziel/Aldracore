package fr.aldraziel.aldracore.utils;

import fr.aldraziel.aldracore.api.weapons.WeaponBonusType;
import org.apache.commons.lang.StringUtils;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import java.util.logging.Level;

public class SwordUtils {

    public static boolean isItemWantedSword(ItemStack item) {
        if (item != null) {
            final Material material = item.getType();
            return material == Material.IRON_SWORD || material == Material.GOLDEN_SWORD || material == Material.DIAMOND_SWORD || material == Material.NETHERITE_SWORD;
        } else return false;
    }

    public static String getBonusClassNameFromMaterial(ItemStack item) {
        return "fr.aldraziel.aldracore.api.weapons.material." + StringUtils.capitalize(item.getType().name().substring(0, item.getType().name().length() - 6).toLowerCase()) + "WeaponBonus";
    }

    public static int getMaxLevelForMaterial(ItemStack item) {
        try {
            return (int) Class.forName(getBonusClassNameFromMaterial(item)).getField("MAX_LEVEL").get(null);
        } catch (Exception e) {
            Bukkit.getLogger().log(Level.SEVERE, e.getMessage());
            return 0;
        }
    }

    public static int getXpCostForMaterial(ItemStack item, int level) {
        try {
            return (int) Class.forName(getBonusClassNameFromMaterial(item)).getMethod("getXpCost", int.class).invoke(null, level);
        } catch (Exception e) {
            Bukkit.getLogger().log(Level.SEVERE, e.getMessage());
            return 0;
        }
    }

    public static double getHighestBonusForMaterial(ItemStack item, WeaponBonusType type, int level) {
        try {
            return (double) Class.forName(getBonusClassNameFromMaterial(item)).getMethod("getHighestByType", WeaponBonusType.class, int.class).invoke(null, type, level);
        } catch (Exception e) {
            Bukkit.getLogger().log(Level.SEVERE, e.getMessage());
            return 0;
        }
    }
}
