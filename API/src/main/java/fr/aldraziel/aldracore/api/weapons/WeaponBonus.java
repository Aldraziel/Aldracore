package fr.aldraziel.aldracore.api.weapons;

public class WeaponBonus {

    private final WeaponBonusType type;
    private final int value;

    public WeaponBonus(WeaponBonusType type, int value) {
        this.type = type;
        this.value = value;
    }

    public WeaponBonusType getType() {
        return this.type;
    }

    public int getValue() {
        return this.value;
    }
}
