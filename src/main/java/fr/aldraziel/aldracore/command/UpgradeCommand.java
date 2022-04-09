package fr.aldraziel.aldracore.command;

import fr.aldraziel.aldracore.AldraCore;
import fr.aldraziel.aldracore.api.armors.AldraArmorBonus;
import fr.aldraziel.aldracore.api.cache.IAldraCacheManager;
import fr.aldraziel.aldracore.api.event.IAldraEventManager;
import fr.aldraziel.aldracore.api.event.armor.UpgradeArmorEvent;
import fr.aldraziel.aldracore.api.event.weapon.UpgradeWeaponEvent;
import fr.aldraziel.aldracore.api.utils.AldraBonusItem;
import fr.aldraziel.aldracore.api.utils.AldraMaterial;
import fr.aldraziel.aldracore.api.utils.IAldraBonus;
import fr.aldraziel.aldracore.api.weapons.AldraWeaponBonus;
import fr.aldraziel.aldracore.utils.NbtUtils;
import fr.aldraziel.aldracore.utils.StuffUtils;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

public class UpgradeCommand implements CommandExecutor {

    private static final String PREFIX = "[" + ChatColor.AQUA + "AldraCore" + ChatColor.RESET + "] ";

    private final IAldraCacheManager cache;
    private final IAldraEventManager event;

    public UpgradeCommand(AldraCore core) {
        this.cache = core.getApi().getCacheManager();
        this.event = core.getApi().getEventManager();
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (sender instanceof final Player player) {
            final ItemStack item = player.getInventory().getItemInMainHand();

            if (StuffUtils.isItemASword(item)){
                final AldraMaterial material = AldraMaterial.valueOf(item);
                int level = this.getLevel(item, AldraBonusItem.SWORD.getNbt());

                if (material == null) {
                    return false;
                }

                level = this.upgradeItem(player, item, AldraBonusItem.SWORD.getNbt(), AldraBonusItem.SWORD, level, material.getMaxWeaponLevel(),
                        IAldraBonus.getXpCost(AldraWeaponBonus.class, AldraBonusItem.SWORD, material, level));

                if (level != 0) {
                    this.event.callEvent(new UpgradeWeaponEvent(this.cache.getPlayer(player.getUniqueId()), item, level));
                }
                return true;
            }

            if (StuffUtils.isItemAnArmor(item)) {
                final AldraMaterial material = AldraMaterial.valueOf(item);
                int level = this.getLevel(item, AldraBonusItem.ARMOR.getNbt());

                if (material == null) {
                    return false;
                }

                level = this.upgradeItem(player, item, AldraBonusItem.ARMOR.getNbt(), AldraBonusItem.ARMOR, level, material.getMaxArmorLevel(),
                        IAldraBonus.getXpCost(AldraArmorBonus.class, AldraBonusItem.ARMOR, material, level));

                if (level != 0) {
                    this.event.callEvent(new UpgradeArmorEvent(this.cache.getPlayer(player.getUniqueId()), item, level));
                }
                return true;
            }

        } else {
            sender.sendMessage(PREFIX + ChatColor.RED + "You need to hold a sword in your hand !");
        }
        return true;
    }

    private int getLevel(ItemStack item, String nbtKey) {
        return NbtUtils.readNbt(item, nbtKey, int.class) != 0 ? NbtUtils.readNbt(item, nbtKey, int.class) : 1;
    }

    private int upgradeItem(Player player, ItemStack item, String nbtKey, AldraBonusItem bonus, int level, int maxLevel, int xpCost) {
        if (level == maxLevel) {
            player.sendMessage(PREFIX + ChatColor.RED + "Your" + bonus.name().toLowerCase() + "is already maxed !");
            return 0;
        }

        final int xpLevel = player.getLevel();

        if (xpLevel >= xpCost) {
            player.setLevel(xpLevel - xpCost);
            NbtUtils.writeNbt(item, nbtKey, level += 1);
            player.sendMessage(PREFIX + "Your" + bonus.name().toLowerCase() + "has been " + ChatColor.AQUA + "upgraded" + ChatColor.RESET + " to level " + ChatColor.RED + level);
            return level;

        } else {
            player.sendMessage(PREFIX + ChatColor.RED + "You dont have enough levels ! You need " + xpCost + " levels to upgrade your " + bonus.name().toLowerCase() + ".");
            return 0;
        }
    }
}
