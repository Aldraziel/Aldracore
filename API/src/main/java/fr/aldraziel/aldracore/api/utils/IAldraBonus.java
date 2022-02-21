package fr.aldraziel.aldracore.api.utils;

public interface IAldraBonus<A extends IAldraBonusType> {

    AldraMaterial getMaterial();
    int getLevel();
    A getType();
    int getBonus();
    int getXp();

    static <Z extends IAldraBonusType, T extends IAldraBonus<Z>> T getByLevel(Class<T> aldraBonus, AldraBonusItem item, AldraMaterial material, int level) {
        if (level > material.getMaxLevel(item)) level = material.getMaxLevel(item);

        for (T bonus : aldraBonus.getEnumConstants()) {
            if (bonus.getMaterial() != material || bonus.getLevel() != level)
                continue;

            return bonus;
        }
        return null;
    }

    static <Z extends IAldraBonusType, T extends IAldraBonus<Z>> int getXpCost(Class<T> aldraBonus, AldraBonusItem item, AldraMaterial material, int level) {
        final T bonus = T.getByLevel(aldraBonus, item, material, level);
        return bonus != null ? bonus.getXp() : 0;
    }

    static <Z extends IAldraBonusType, T extends IAldraBonus<Z>> double getHighestByType(Class<T> aldraBonus, AldraMaterial material, Z type, int level) {
        T max = null;
        for (T bonus : aldraBonus.getEnumConstants()) {
            if (bonus.getMaterial() != material || bonus.getLevel() > level || bonus.getType() != type)
                continue;

            max = bonus;
        }
        return max != null ? max.getBonus() : 0;
    }
}
