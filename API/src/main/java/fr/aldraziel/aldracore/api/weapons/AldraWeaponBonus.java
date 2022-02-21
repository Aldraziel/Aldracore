package fr.aldraziel.aldracore.api.weapons;

import fr.aldraziel.aldracore.api.utils.AldraMaterial;
import fr.aldraziel.aldracore.api.utils.IAldraBonus;

public enum AldraWeaponBonus implements IAldraBonus<WeaponBonusType> {

    IRON_LEVEL_2(AldraMaterial.IRON, 2, WeaponBonusType.CC, 15, 12),
    IRON_LEVEL_3(AldraMaterial.IRON, 3, WeaponBonusType.CC, 25, 25),
    IRON_LEVEL_4(AldraMaterial.IRON, 4, WeaponBonusType.DCC, 10, 38),
    IRON_LEVEL_5(AldraMaterial.IRON, 5, WeaponBonusType.CC, 35, 52),

    GOLDEN_LEVEL_2(AldraMaterial.GOLDEN, 2, WeaponBonusType.CC, 10, 12),
    GOLDEN_LEVEL_3(AldraMaterial.GOLDEN, 3, WeaponBonusType.CC, 25, 25),
    GOLDEN_LEVEL_4(AldraMaterial.GOLDEN, 4, WeaponBonusType.DCC, 10, 38),
    GOLDEN_LEVEL_5(AldraMaterial.GOLDEN, 5, WeaponBonusType.CC, 35, 52),
    GOLDEN_LEVEL_6(AldraMaterial.GOLDEN, 6, WeaponBonusType.DJ, 5, 64),
    GOLDEN_LEVEL_7(AldraMaterial.GOLDEN, 7, WeaponBonusType.DM, 15, 75),
    GOLDEN_LEVEL_8(AldraMaterial.GOLDEN, 8, WeaponBonusType.DJ, 8, 87),

    DIAMOND_LEVEL_2(AldraMaterial.DIAMOND, 2, WeaponBonusType.CC, 12, 18),
    DIAMOND_LEVEL_3(AldraMaterial.DIAMOND, 3, WeaponBonusType.DCC, 24, 29),
    DIAMOND_LEVEL_4(AldraMaterial.DIAMOND, 4, WeaponBonusType.CC, 42, 48),
    DIAMOND_LEVEL_5(AldraMaterial.DIAMOND, 5, WeaponBonusType.DM, 13, 62),
    DIAMOND_LEVEL_6(AldraMaterial.DIAMOND, 6, WeaponBonusType.DJ, 6, 84),

    NETHERITE_LEVEL_2(AldraMaterial.NETHERITE, 2, WeaponBonusType.CC, 56, 45),
    NETHERITE_LEVEL_3(AldraMaterial.NETHERITE, 3, WeaponBonusType.DCC, 43, 86),
    NETHERITE_LEVEL_4(AldraMaterial.NETHERITE, 4, WeaponBonusType.DM, 30, 105),
    NETHERITE_LEVEL_5(AldraMaterial.NETHERITE, 5, WeaponBonusType.DJ, 12, 112),
    NETHERITE_LEVEL_6(AldraMaterial.NETHERITE, 6, WeaponBonusType.DCC, 53, 143),
    NETHERITE_LEVEL_7(AldraMaterial.NETHERITE, 7, WeaponBonusType.CC, 61, 185);

    private final AldraMaterial material;
    private final int level;
    private final WeaponBonusType type;
    private final int bonus;
    private final int xp;

    AldraWeaponBonus(AldraMaterial material, int level, WeaponBonusType type, int bonus, int xp) {
        this.material = material;
        this.level = level;
        this.type = type;
        this.bonus = bonus;
        this.xp = xp;
    }

    @Override
    public AldraMaterial getMaterial() {
        return this.material;
    }

    @Override
    public int getLevel() {
        return this.level;
    }

    @Override
    public WeaponBonusType getType() {
        return this.type;
    }

    @Override
    public int getBonus() {
        return this.bonus;
    }

    @Override
    public int getXp() {
        return this.xp;
    }
}
