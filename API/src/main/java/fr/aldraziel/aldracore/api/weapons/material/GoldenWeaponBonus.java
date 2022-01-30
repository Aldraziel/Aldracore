package fr.aldraziel.aldracore.api.weapons.material;

import fr.aldraziel.aldracore.api.weapons.WeaponBonusType;

public enum GoldenWeaponBonus {

    LEVEL_2(2, WeaponBonusType.CC, 10, 12),
    LEVEL_3(3, WeaponBonusType.CC, 25, 25),
    LEVEL_4(4, WeaponBonusType.DCC, 10, 38),
    LEVEL_5(5, WeaponBonusType.CC, 35, 52),
    LEVEL_6(6, WeaponBonusType.DJ, 5, 64),
    LEVEL_7(7, WeaponBonusType.DM, 15, 75),
    LEVEL_8(8, WeaponBonusType.DJ, 8, 87);

    public static final int MAX_LEVEL = 8;

    private final int level;
    private final WeaponBonusType type;
    private final int bonus;
    private final int xp;

    GoldenWeaponBonus(int level, WeaponBonusType type, int bonus, int xp) {
        this.level = level;
        this.type = type;
        this.bonus = bonus;
        this.xp = xp;
    }

    public int getLevel() {
        return this.level;
    }

    public WeaponBonusType getType() {
        return this.type;
    }

    public int getBonus() {
        return this.bonus;
    }

    public int getXp() {
        return this.xp;
    }

    public static GoldenWeaponBonus getByLevel(int level) {
        if (level > MAX_LEVEL) {
            level = MAX_LEVEL;
        }
        for (GoldenWeaponBonus bonus : GoldenWeaponBonus.values()) {
            if (bonus.getLevel() == level) {
                return bonus;
            }
        }
        return null;
    }

    public static int getXpCost(int level) {
        final GoldenWeaponBonus bonus = getByLevel(level);
        return bonus != null ? bonus.getXp() : 0;
    }

    public static double getHighestByType(WeaponBonusType type, int level) {
        GoldenWeaponBonus max = null;
        for (GoldenWeaponBonus bonus : GoldenWeaponBonus.values()) {
            if (bonus.getLevel() <= level) {
                if (bonus.getType() == type) {
                    max = bonus;
                }
            }
        }
        return max != null ? max.getBonus() : 0;
    }
}
