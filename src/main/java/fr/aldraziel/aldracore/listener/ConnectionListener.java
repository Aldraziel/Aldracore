package fr.aldraziel.aldracore.listener;

import fr.aldraziel.aldracore.AldraCore;
import fr.aldraziel.aldracore.api.player.IAldraPlayerManager;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeInstance;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class ConnectionListener implements Listener {

    private final IAldraPlayerManager pm;

    public ConnectionListener(AldraCore core) {
        this.pm = core.getApi().getPlayerManager();
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        final Player player = e.getPlayer();
        if (this.pm.getPlayer(player.getUniqueId()) == null) {
            this.pm.createPlayer(player.getUniqueId(), player.getDisplayName());
        }

        final AttributeInstance attackSpeed = player.getAttribute(Attribute.GENERIC_ATTACK_SPEED);
        if (attackSpeed != null) {
            attackSpeed.setBaseValue(24D);
        }
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent e) {
        this.pm.savePlayer(this.pm.getPlayer(e.getPlayer().getUniqueId()));
    }
}
