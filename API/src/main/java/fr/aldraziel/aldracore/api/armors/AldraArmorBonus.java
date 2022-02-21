package fr.aldraziel.aldracore.api.armors;

import fr.aldraziel.aldracore.api.utils.AldraMaterial;
import fr.aldraziel.aldracore.api.utils.IAldraBonus;

public enum AldraArmorBonus implements IAldraBonus<ArmorBonusType> {

    IRON_LEVEL_2(AldraMaterial.IRON, 2, ArmorBonusType.DEF, 3, 12),
    IRON_LEVEL_3(AldraMaterial.IRON, 3, ArmorBonusType.VEL, 3, 25),
    IRON_LEVEL_4(AldraMaterial.IRON, 4, ArmorBonusType.REGEN, 3, 38),
    IRON_LEVEL_5(AldraMaterial.IRON, 5, ArmorBonusType.VEL, 6, 52),
    IRON_LEVEL_6(AldraMaterial.IRON, 5, ArmorBonusType.TEM, 1, 64),

    GOLDEN_LEVEL_2(AldraMaterial.GOLDEN, 2, ArmorBonusType.DEF, 5, 12),
    GOLDEN_LEVEL_3(AldraMaterial.GOLDEN, 3, ArmorBonusType.VEL, 5, 25),
    GOLDEN_LEVEL_4(AldraMaterial.GOLDEN, 4, ArmorBonusType.REGEN, 5, 38),
    GOLDEN_LEVEL_5(AldraMaterial.GOLDEN, 5, ArmorBonusType.VEL, 10, 52),
    GOLDEN_LEVEL_6(AldraMaterial.GOLDEN, 5, ArmorBonusType.TEM, 3, 64),

    DIAMOND_LEVEL_2(AldraMaterial.DIAMOND, 2, ArmorBonusType.DEF, 6, 18),
    DIAMOND_LEVEL_3(AldraMaterial.DIAMOND, 3, ArmorBonusType.VEL, 6, 29),
    DIAMOND_LEVEL_4(AldraMaterial.DIAMOND, 4, ArmorBonusType.REGEN, 6, 48),
    DIAMOND_LEVEL_5(AldraMaterial.DIAMOND, 5, ArmorBonusType.VEL, 10, 62),
    DIAMOND_LEVEL_6(AldraMaterial.DIAMOND, 5, ArmorBonusType.ESQ, 6, 64),
    DIAMOND_LEVEL_7(AldraMaterial.DIAMOND, 5, ArmorBonusType.TEM, 5, 84),

    NETHERITE_LEVEL_2(AldraMaterial.NETHERITE, 2, ArmorBonusType.DEF, 9, 45),
    NETHERITE_LEVEL_3(AldraMaterial.NETHERITE, 3, ArmorBonusType.VEL, 9, 86),
    NETHERITE_LEVEL_4(AldraMaterial.NETHERITE, 4, ArmorBonusType.REGEN, 9, 105),
    NETHERITE_LEVEL_5(AldraMaterial.NETHERITE, 5, ArmorBonusType.VEL, 15, 112),
    NETHERITE_LEVEL_6(AldraMaterial.NETHERITE, 5, ArmorBonusType.ESQ, 8, 143),
    NETHERITE_LEVEL_7(AldraMaterial.NETHERITE, 5, ArmorBonusType.TEM, 9, 185);

    private final AldraMaterial material;
    private final int level;
    private final ArmorBonusType type;
    private final int bonus;
    private final int xp;

    AldraArmorBonus(AldraMaterial material, int level, ArmorBonusType type, int bonus, int xp) {
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
    public ArmorBonusType getType() {
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
