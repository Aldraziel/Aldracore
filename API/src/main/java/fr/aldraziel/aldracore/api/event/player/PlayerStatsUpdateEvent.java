package fr.aldraziel.aldracore.api.event.player;

import fr.aldraziel.aldracore.api.event.AldraEvent;
import fr.aldraziel.aldracore.api.player.IAldraPlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.NotNull;

public class PlayerStatsUpdateEvent extends AldraEvent {

    private static final HandlerList HANDLERS = new HandlerList();

    private final IAldraPlayer player;
    private final Player bukkitPlayer;
    private boolean isCancelled;

    public PlayerStatsUpdateEvent(IAldraPlayer player, Player bukkitPlayer) {
        this.player = player;
        this.bukkitPlayer = bukkitPlayer;

        this.isCancelled = false;
    }

    public IAldraPlayer getPlayer() {
        return this.player;
    }

    public Player getBukkitPlayer() {
        return this.bukkitPlayer;
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
