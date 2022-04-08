package fr.aldraziel.aldracore.api.event.player;

import fr.aldraziel.aldracore.api.event.AldraEvent;
import fr.aldraziel.aldracore.api.player.IAldraPlayer;
import org.bukkit.entity.Player;

public class PlayerStatsUpdateEvent extends AldraEvent {

    private final IAldraPlayer player;
    private final Player bukkitPlayer;

    public PlayerStatsUpdateEvent(IAldraPlayer player, Player bukkitPlayer) {
        this.player = player;
        this.bukkitPlayer = bukkitPlayer;
    }

    public IAldraPlayer getPlayer() {
        return this.player;
    }

    public Player getBukkitPlayer() {
        return this.bukkitPlayer;
    }
}
