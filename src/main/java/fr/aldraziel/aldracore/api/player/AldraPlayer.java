package fr.aldraziel.aldracore.api.player;

import java.util.UUID;

public class AldraPlayer implements IAldraPlayer {

    private final UUID uuid;
    private final String name;

    private double defense;
    private double critical;
    private double criticalDamage;
    private double wisdom;
    private double dodge;
    private double velocity;
    private double temerity;
    private double regen;
    private double bonusWisdom;

    public AldraPlayer(UUID uuid, String name) {
        this.uuid = uuid;
        this.name = name;

        this.clearAttributes();
    }

    @Override
    public UUID getUuid() {
        return this.uuid;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public void clearAttributes() {
        this.defense = 0;
        this.critical = 0;
        this.criticalDamage = 0;
        this.wisdom = 0;
        this.dodge = 0;
        this.velocity = 0;
        this.temerity = 0;
        this.regen = 0;
        this.bonusWisdom = 0;
    }

    @Override
    public double getDefense() {
        return this.defense;
    }

    @Override
    public void setDefense(double defense) {
        this.defense = defense;
    }

    @Override
    public double getCritical() {
        return this.critical;
    }

    @Override
    public void setCritical(double critical) {
        this.critical = critical;
    }

    @Override
    public double getCriticalDamage() {
        return this.criticalDamage;
    }

    @Override
    public void setCriticalDamage(double criticalDamage) {
        this.criticalDamage = criticalDamage;
    }

    @Override
    public double getWisdom() {
        return this.wisdom;
    }

    @Override
    public void setWisdom(double wisdom) {
        this.wisdom = wisdom;
    }

    @Override
    public double getDodge() {
        return this.dodge;
    }

    @Override
    public void setDodge(double dodge) {
        this.dodge = dodge;
    }

    @Override
    public double getVelocity() {
        return this.velocity;
    }

    @Override
    public void setVelocity(double velocity) {
        this.velocity = velocity;
    }

    @Override
    public double getTemerity() {
        return this.temerity;
    }

    @Override
    public void setTemerity(double temerity) {
        this.temerity = temerity;
    }

    @Override
    public double getRegen() {
        return this.regen;
    }

    @Override
    public void setRegen(double regen) {
        this.regen = regen;
    }

    @Override
    public double getBonusWisdom() {
        return this.bonusWisdom;
    }

    @Override
    public void setBonusWisdom(double bonusWisdom) {
        this.bonusWisdom = bonusWisdom;
    }
}
