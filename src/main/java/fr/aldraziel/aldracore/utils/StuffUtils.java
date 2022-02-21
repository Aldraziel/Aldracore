package fr.aldraziel.aldracore.utils;

import fr.aldraziel.aldracore.api.armors.AldraArmorBonus;
import fr.aldraziel.aldracore.api.armors.ArmorBonusType;
import fr.aldraziel.aldracore.api.utils.AldraMaterial;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

public class StuffUtils {

    public static boolean isItemASword(ItemStack item) {
        if (item != null) {
            final Material material = item.getType();
            return material == Material.IRON_SWORD || material == Material.GOLDEN_SWORD || material == Material.DIAMOND_SWORD || material == Material.NETHERITE_SWORD;
        } else return false;
    }

    public static boolean isItemAnArmor(ItemStack item) {
        if (item != null) {
            final String name = item.getType().name();
            return name.endsWith("_HELMET") || name.endsWith("_CHESTPLATE") || name.endsWith("_LEGGINGS") || name.endsWith("_BOOTS");
        } else return false;
    }

    public static double getStatFromPlayerArmor(PlayerInventory inv, ArmorBonusType bonus) {
        double stat = 0;
        for (ItemStack item : inv.getArmorContents()) {
            stat += StuffUtils.getStatFromPlayerArmor(item, bonus);
        }
        return stat;
    }

    public static double getStatFromPlayerArmor(ItemStack item, ArmorBonusType bonus) {
        double stat = 0;
        if (StuffUtils.isItemAnArmor(item)) {
            final AldraMaterial material = AldraMaterial.valueOf(item);
            final int level = NbtUtils.readNbt(item, ArmorBonusType.NBT_NAME, int.class);
            stat = AldraArmorBonus.getHighestByType(material, bonus, level) / 100;
        }
        return stat;
    }
}
