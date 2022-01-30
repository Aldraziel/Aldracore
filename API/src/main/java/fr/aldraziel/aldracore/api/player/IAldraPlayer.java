package fr.aldraziel.aldracore.api.player;

import java.util.UUID;

public interface IAldraPlayer {

    /** Base Player info */
    UUID getUuid();
    String getName();

    /** Player Attributes */
    void clearAttributes();

    /** Defense */
    double getDefense();
    void setDefense(double defense);

    /** Critical */
    double getCritical();
    void setCritical(double critical);

    /** Critical Damage */
    double getCriticalDamage();
    void setCriticalDamage(double criticalDamage);

    /** Wisdom */
    double getWisdom();
    void setWisdom(double wisdom);

    /** Dodge */
    double getDodge();
    void setDodge(double dodge);

    /** Velocity */
    double getVelocity();
    void setVelocity(double velocity);

    /** Temerity */
    double getTemerity();
    void setTemerity(double temerity);

    /** Regeneration */
    double getRegen();
    void setRegen(double regen);

    /** Bonus Wisdom */
    double getBonusWisdom();
    void setBonusWisdom(double bonusWisdom);
}
