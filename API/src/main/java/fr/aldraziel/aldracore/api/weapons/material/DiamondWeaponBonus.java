package fr.aldraziel.aldracore.api.weapons.material;

import fr.aldraziel.aldracore.api.weapons.WeaponBonusType;

public enum DiamondWeaponBonus {

    LEVEL_2(2, WeaponBonusType.CC, 12, 18),
    LEVEL_3(3, WeaponBonusType.DCC, 24, 29),
    LEVEL_4(4, WeaponBonusType.CC, 42, 48),
    LEVEL_5(5, WeaponBonusType.DM, 13, 62),
    LEVEL_6(6, WeaponBonusType.DJ, 6, 84);

    public static final int MAX_LEVEL = 6;

    private final int level;
    private final WeaponBonusType type;
    private final int bonus;
    private final int xp;

    DiamondWeaponBonus(int level, WeaponBonusType type, int bonus, int xp) {
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

    public static DiamondWeaponBonus getByLevel(int level) {
        if (level > MAX_LEVEL) {
            level = MAX_LEVEL;
        }
        for (DiamondWeaponBonus bonus : DiamondWeaponBonus.values()) {
            if (bonus.getLevel() == level) {
                return bonus;
            }
        }
        return null;
    }

    public static int getXpCost(int level) {
        final DiamondWeaponBonus bonus = getByLevel(level);
        return bonus != null ? bonus.getXp() : 0;
    }

    public static double getHighestByType(WeaponBonusType type, int level) {
        DiamondWeaponBonus max = null;
        for (DiamondWeaponBonus bonus : DiamondWeaponBonus.values()) {
            if (bonus.getLevel() <= level) {
                if (bonus.getType() == type) {
                    max = bonus;
                }
            }
        }
        return max != null ? max.getBonus() : 0;
    }
}
