package fr.aldraziel.aldracore.api.utils;

public enum AldraBonusItem {

    SWORD("AldraWeaponLevel"),
    ARMOR("AldraArmorLevel");

    private final String nbt;

    AldraBonusItem(String nbt) {
        this.nbt = nbt;
    }

    public String getNbt() {
        return this.nbt;
    }
}
