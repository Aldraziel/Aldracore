package fr.aldraziel.aldracore.listener;

import fr.aldraziel.aldracore.AldraCore;
import fr.aldraziel.aldracore.api.armors.ArmorBonusType;
import fr.aldraziel.aldracore.api.cache.IAldraCacheManager;
import fr.aldraziel.aldracore.api.player.IAldraPlayer;
import fr.aldraziel.aldracore.api.utils.AldraBonusItem;
import fr.aldraziel.aldracore.api.utils.AldraMaterial;
import fr.aldraziel.aldracore.api.utils.IAldraBonus;
import fr.aldraziel.aldracore.api.weapons.AldraWeaponBonus;
import fr.aldraziel.aldracore.api.weapons.WeaponBonusType;
import fr.aldraziel.aldracore.utils.NbtUtils;
import fr.aldraziel.aldracore.utils.StuffUtils;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.inventory.ItemStack;

public class AttackListener implements Listener {

    private final IAldraCacheManager cache;

    public AttackListener(AldraCore core) {
        this.cache = core.getApi().getCacheManager();
    }

    @EventHandler(priority = EventPriority.LOWEST)
    public void onDamage(EntityDamageByEntityEvent e) {
        if (e.getDamager() instanceof final Player damager && StuffUtils.isItemASword(damager.getInventory().getItemInMainHand())) {
            final IAldraPlayer aldraDamager = this.cache.getPlayer(damager.getUniqueId());
            boolean damagesCancelled = false;

            //Damage item part
            final ItemStack damageItem = damager.getInventory().getItemInMainHand();
            final AldraMaterial damageMaterial = AldraMaterial.valueOf(damageItem);
            final int damageLevel = NbtUtils.readNbt(damageItem, AldraBonusItem.SWORD.getNbt(), int.class);

            //Final stats
            double damage = 0;
            double defense = 0;

            if (e.getEntity() instanceof Player damaged) {
                final IAldraPlayer aldraDamaged = this.cache.getPlayer(damaged.getUniqueId());

                damagesCancelled = (Math.random() <= (aldraDamaged.getDodge() + StuffUtils.getStatFromPlayerArmor(damaged.getInventory(), ArmorBonusType.ESQ)));

                if (!damagesCancelled) {
                    defense = (aldraDamaged.getDefense() + StuffUtils.getStatFromPlayerArmor(damaged.getInventory(), ArmorBonusType.DEF));
                }
            }

            if (damagesCancelled) {
                e.setCancelled(true);
                e.setDamage(0);
                return;
            }

            damage = e.getDamage() + (e.getDamage() * StuffUtils.getStatFromPlayerArmor(damager.getInventory(), ArmorBonusType.TEM));

            final double critical = this.getCritical(aldraDamager, damage, damageMaterial, damageLevel);
            final double damagedTypeBonus = this.getDamagedTypeBonus(e.getEntity(), damage, damageMaterial, damageLevel);

            e.setDamage((damage + (damage * critical) + (damage * damagedTypeBonus)) - (damage * defense));
        }
    }

    private double getCritical(IAldraPlayer player, double damage, AldraMaterial material, int level) {
        final double cc = IAldraBonus.getHighestByType(AldraWeaponBonus.class, material, WeaponBonusType.CC, level) / 100;
        final double dcc = IAldraBonus.getHighestByType(AldraWeaponBonus.class, material, WeaponBonusType.DCC, level) / 100;

        if ((Math.random()) <= (cc + player.getCritical())) {
            return (damage * (dcc + player.getCriticalDamage()));
        }
        return 0;
    }

    private double getDamagedTypeBonus(Entity entity, double damage, AldraMaterial material, int level) {
        final double typeBonus = IAldraBonus.getHighestByType(AldraWeaponBonus.class, material, (entity instanceof Player ? WeaponBonusType.DJ : WeaponBonusType.DM), level) / 100;
        return (damage * typeBonus);
    }
}
