package fr.aldraziel.aldracore.api.event.player;

import fr.aldraziel.aldracore.api.event.AldraEvent;
import fr.aldraziel.aldracore.api.player.IAldraPlayer;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.NotNull;

public class SavingPlayerEvent extends AldraEvent {

    private static final HandlerList HANDLERS = new HandlerList();

    private final IAldraPlayer player;
    private boolean isCancelled;

    public SavingPlayerEvent(IAldraPlayer player) {
        this.player = player;

        this.isCancelled = false;
    }

    public IAldraPlayer getPlayer() {
        return this.player;
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
