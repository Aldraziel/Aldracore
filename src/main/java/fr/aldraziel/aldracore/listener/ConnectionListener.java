package fr.aldraziel.aldracore.listener;

import fr.aldraziel.aldracore.AldraCore;
import fr.aldraziel.aldracore.api.cache.IAldraCacheManager;
import fr.aldraziel.aldracore.api.player.IAldraPlayerManager;
import fr.aldraziel.aldracore.utils.PlayerUtils;
import org.bukkit.attribute.Attribute;
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

        this.cache.updatePlayer(player.getUniqueId());

        PlayerUtils.setAttribute(player, Attribute.GENERIC_ATTACK_SPEED, 24D);
        PlayerUtils.setBonusAttribute(player, Attribute.GENERIC_MOVEMENT_SPEED, this.cache.getPlayer(player.getUniqueId()).getVelocity());
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent e) {
        final UUID uuid = e.getPlayer().getUniqueId();
        this.pm.savePlayer(this.pm.getPlayer(uuid));
        this.cache.removePlayer(uuid);
    }
}
