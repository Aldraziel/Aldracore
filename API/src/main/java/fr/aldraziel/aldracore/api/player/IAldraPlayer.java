package fr.aldraziel.aldracore.api.player;

import java.util.UUID;

/**
 * All values are between 0 and 1.
 */
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
}
