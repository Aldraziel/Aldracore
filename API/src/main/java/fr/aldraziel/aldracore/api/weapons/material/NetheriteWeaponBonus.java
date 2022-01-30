package fr.aldraziel.aldracore.api.weapons.material;

import fr.aldraziel.aldracore.api.weapons.WeaponBonusType;

public enum NetheriteWeaponBonus {

    LEVEL_2(2, WeaponBonusType.CC, 56, 45),
    LEVEL_3(3, WeaponBonusType.DCC, 43, 86),
    LEVEL_4(4, WeaponBonusType.DM, 30, 105),
    LEVEL_5(5, WeaponBonusType.DJ, 12, 112),
    LEVEL_6(6, WeaponBonusType.DCC, 53, 143),
    LEVEL_7(7, WeaponBonusType.CC, 61, 185);

    public static final int MAX_LEVEL = 7;

    private final int level;
    private final WeaponBonusType type;
    private final int bonus;
    private final int xp;

    NetheriteWeaponBonus(int level, WeaponBonusType type, int bonus, int xp) {
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

    public static NetheriteWeaponBonus getByLevel(int level) {
        if (level > MAX_LEVEL) {
            level = MAX_LEVEL;
        }
        for (NetheriteWeaponBonus bonus : NetheriteWeaponBonus.values()) {
            if (bonus.getLevel() == level) {
                return bonus;
            }
        }
        return null;
    }

    public static int getXpCost(int level) {
        final NetheriteWeaponBonus bonus = getByLevel(level);
        return bonus != null ? bonus.getXp() : 0;
    }

    public static double getHighestByType(WeaponBonusType type, int level) {
        NetheriteWeaponBonus max = null;
        for (NetheriteWeaponBonus bonus : NetheriteWeaponBonus.values()) {
            if (bonus.getLevel() <= level) {
                if (bonus.getType() == type) {
                    max = bonus;
                }
            }
        }
        return max != null ? max.getBonus() : 0;
    }
}
