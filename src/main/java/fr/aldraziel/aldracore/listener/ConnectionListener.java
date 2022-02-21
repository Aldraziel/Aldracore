package fr.aldraziel.aldracore.listener;

import fr.aldraziel.aldracore.AldraCore;
import fr.aldraziel.aldracore.api.cache.IAldraCacheManager;
import fr.aldraziel.aldracore.api.player.IAldraPlayerManager;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeInstance;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import java.util.UUID;

public class ConnectionListener implements Listener {

    private final IAldraPlayerManager pm;
    private final IAldraCacheManager cache;

    public ConnectionListener(AldraCore core) {
        this.pm = core.getApi().getPlayerManager();
        this.cache = core.getApi().getCacheManager();
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        final Player player = e.getPlayer();
        if (this.pm.getPlayer(player.getUniqueId()) == null) {
            this.cache.updatePlayer(this.pm.createPlayer(player.getUniqueId(), player.getDisplayName()));
        }

        final AttributeInstance attackSpeed = player.getAttribute(Attribute.GENERIC_ATTACK_SPEED);
        if (attackSpeed != null) {
            attackSpeed.setBaseValue(24D);
        }

        final AttributeInstance moveSpeed = player.getAttribute(Attribute.GENERIC_MOVEMENT_SPEED);
        if (moveSpeed != null) {
            moveSpeed.setBaseValue(moveSpeed.getBaseValue() + this.cache.getPlayer(player.getUniqueId()).getVelocity());
        }
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent e) {
        final UUID uuid = e.getPlayer().getUniqueId();
        this.pm.savePlayer(this.pm.getPlayer(uuid));
        this.cache.removePlayer(uuid);
    }
}
