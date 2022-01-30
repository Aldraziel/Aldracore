package fr.aldraziel.aldracore.api.event.weapon;

import fr.aldraziel.aldracore.api.event.AldraEvent;
import fr.aldraziel.aldracore.api.player.IAldraPlayer;
import org.bukkit.entity.Entity;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.NotNull;

public class CriticalDamageEvent extends AldraEvent {

    private static final HandlerList HANDLERS = new HandlerList();

    private final IAldraPlayer damager;
    private final Entity damaged;
    private final double damage;
    private boolean isCancelled;

    public CriticalDamageEvent(IAldraPlayer damager, Entity damaged, double damage) {
        this.damager = damager;
        this.damaged = damaged;
        this.damage = damage;

        this.isCancelled = false;
    }

    public IAldraPlayer getDamager() {
        return this.damager;
    }

    public Entity getDamaged() {
        return this.damaged;
    }

    public double getDamage() {
        return this.damage;
    }

    @Override
    public boolean isCancelled() {
        return this.isCancelled;
    }

    @Override
    public void setCancelled(boolean cancel) {
        this.isCancelled = cancel;
    }

    @NotNull
    @Override
    public HandlerList getHandlers() {
        return HANDLERS;
    }
}
