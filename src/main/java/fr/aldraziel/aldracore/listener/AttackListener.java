package fr.aldraziel.aldracore.listener;

import de.tr7zw.changeme.nbtapi.NBTItem;
import fr.aldraziel.aldracore.AldraCore;
import fr.aldraziel.aldracore.api.event.IAldraEventManager;
import fr.aldraziel.aldracore.api.event.weapon.CriticalDamageEvent;
import fr.aldraziel.aldracore.api.player.IAldraPlayer;
import fr.aldraziel.aldracore.api.player.IAldraPlayerManager;
import fr.aldraziel.aldracore.api.weapons.WeaponBonusType;
import fr.aldraziel.aldracore.utils.SwordUtils;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.inventory.ItemStack;

public class AttackListener implements Listener {

    private final IAldraPlayerManager pm;
    private final IAldraEventManager event;

    public AttackListener(AldraCore core) {
        this.pm = core.getApi().getPlayerManager();
        this.event = core.getApi().getEventManager();
    }

    @EventHandler
    public void onDamage(EntityDamageByEntityEvent e) {
        if (e.getDamager() instanceof final Player damager && SwordUtils.isItemWantedSword(damager.getInventory().getItemInMainHand())) {
            final IAldraPlayer aldraDamager = this.pm.getPlayer(damager.getUniqueId());
            final ItemStack item = damager.getInventory().getItemInMainHand();
            final NBTItem nbt = new NBTItem(item);

            double damage = e.getDamage();
            final int level = nbt.getInteger(WeaponBonusType.NBT_NAME);

            final double cc = SwordUtils.getHighestBonusForMaterial(item, WeaponBonusType.CC, level);
            final double dcc = SwordUtils.getHighestBonusForMaterial(item, WeaponBonusType.DCC, level) / 100;
            final double bonus = SwordUtils.getHighestBonusForMaterial(item, (e.getEntity() instanceof Player ? WeaponBonusType.DJ : WeaponBonusType.DM), level) / 100;

            damage = damage + (damage * bonus);

            if ((Math.random() * 100) <= cc) {
                damage = damage + (damage * dcc);
                this.event.callEvent(new CriticalDamageEvent(aldraDamager, e.getEntity(), damage));
            }

            //TODO Make part with bonus defense
            /*if (e.getEntity() instanceof Player damaged) {
                final IAldraPlayer aldraDamaged = this.pm.getPlayer(damaged.getUniqueId());
            }*/

            e.setDamage(damage);
        }
    }
}
