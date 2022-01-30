package fr.aldraziel.aldracore.api.weapons.material;

import fr.aldraziel.aldracore.api.weapons.WeaponBonusType;

public enum IronWeaponBonus {

    LEVEL_2(2, WeaponBonusType.CC, 15, 12),
    LEVEL_3(3, WeaponBonusType.CC, 25, 25),
    LEVEL_4(4, WeaponBonusType.DCC, 10, 38),
    LEVEL_5(5, WeaponBonusType.CC, 35, 52);

    public static final int MAX_LEVEL = 5;

    private final int level;
    private final WeaponBonusType type;
    private final int bonus;
    private final int xp;

    IronWeaponBonus(int level, WeaponBonusType type, int bonus, int xp) {
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

    public static IronWeaponBonus getByLevel(int level) {
        if (level > MAX_LEVEL) {
            level = MAX_LEVEL;
        }
        for (IronWeaponBonus bonus : IronWeaponBonus.values()) {
            if (bonus.getLevel() == level) {
                return bonus;
            }
        }
        return null;
    }

    public static int getXpCost(int level) {
        final IronWeaponBonus bonus = getByLevel(level);
        return bonus != null ? bonus.getXp() : 0;
    }

    public static double getHighestByType(WeaponBonusType type, int level) {
        IronWeaponBonus max = null;
        for (IronWeaponBonus bonus : IronWeaponBonus.values()) {
            if (bonus.getLevel() <= level) {
                if (bonus.getType() == type) {
                    max = bonus;
                }
            }
        }
        return max != null ? max.getBonus() : 0;
    }
}
