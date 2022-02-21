package fr.aldraziel.aldracore.api.utils;

import org.bukkit.inventory.ItemStack;

public enum AldraMaterial {

    IRON(5, 6),
    GOLDEN(8, 6),
    DIAMOND(6, 7),
    NETHERITE(7, 7);

    private final int maxWeaponLevel;
    private final int maxArmorLevel;

    AldraMaterial(int maxWeaponLevel, int maxArmorLevel) {
        this.maxWeaponLevel = maxWeaponLevel;
        this.maxArmorLevel = maxArmorLevel;
    }

    public int getMaxWeaponLevel() {
        return this.maxWeaponLevel;
    }

    public int getMaxArmorLevel() {
        return this.maxArmorLevel;
    }

    public static AldraMaterial valueOf(ItemStack stack) {
        return AldraMaterial.valueOf(stack.getType().name().split("_")[0]);
    }
}
