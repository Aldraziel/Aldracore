package fr.aldraziel.aldracore.listener;

import fr.aldraziel.aldracore.AldraCore;
import fr.aldraziel.aldracore.api.armors.ArmorBonusType;
import fr.aldraziel.aldracore.api.cache.IAldraCacheManager;
import fr.aldraziel.aldracore.api.event.player.PlayerStatsUpdateEvent;
import fr.aldraziel.aldracore.utils.PlayerUtils;
import fr.aldraziel.aldracore.utils.StuffUtils;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityRegainHealthEvent;

public class StatsListener implements Listener {

    private final IAldraCacheManager cache;

    public StatsListener(AldraCore core) {
        this.cache = core.getApi().getCacheManager();
    }

    @EventHandler
    public void onUpdate(PlayerStatsUpdateEvent e) {
        //Velocity part
        PlayerUtils.setBonusAttribute(e.getBukkitPlayer(), Attribute.GENERIC_MOVEMENT_SPEED, e.getPlayer().getVelocity());
    }

    @EventHandler
    public void onRegen(EntityRegainHealthEvent e) {
        if (e.getEntity() instanceof Player player && e.getRegainReason() == EntityRegainHealthEvent.RegainReason.SATIATED) {
            final double regen = StuffUtils.getStatFromPlayerArmor(player.getInventory(), ArmorBonusType.REGEN);
            e.setAmount(e.getAmount() + (e.getAmount() * (this.cache.getPlayer(player.getUniqueId()).getRegen() + regen)));
        }
    }
}
