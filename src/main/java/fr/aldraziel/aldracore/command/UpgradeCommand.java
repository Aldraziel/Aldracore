package fr.aldraziel.aldracore.command;

import de.tr7zw.changeme.nbtapi.NBTItem;
import fr.aldraziel.aldracore.AldraCore;
import fr.aldraziel.aldracore.api.event.IAldraEventManager;
import fr.aldraziel.aldracore.api.event.weapon.UpgradeWeaponEvent;
import fr.aldraziel.aldracore.api.player.IAldraPlayerManager;
import fr.aldraziel.aldracore.api.weapons.WeaponBonusType;
import fr.aldraziel.aldracore.utils.SwordUtils;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

public class UpgradeCommand implements CommandExecutor {

    private static final String PREFIX = "[" + ChatColor.AQUA + "AldraCore" + ChatColor.RESET + "] ";

    private final IAldraPlayerManager pm;
    private final IAldraEventManager event;

    public UpgradeCommand(AldraCore core) {
        this.pm = core.getApi().getPlayerManager();
        this.event = core.getApi().getEventManager();
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (sender instanceof final Player player && SwordUtils.isItemWantedSword(player.getInventory().getItemInMainHand())) {
            final ItemStack item = player.getInventory().getItemInMainHand();
            final NBTItem nbt = new NBTItem(item);
            int level = nbt.getInteger(WeaponBonusType.NBT_NAME) != 0 ? nbt.getInteger(WeaponBonusType.NBT_NAME) : 1;

            if (level == SwordUtils.getMaxLevelForMaterial(item)) {
                sender.sendMessage(PREFIX + ChatColor.RED + "Your sword is already maxed !");
            } else {
                final int xpLevel = player.getLevel();
                final int xpCost = SwordUtils.getXpCostForMaterial(item, level);

                if (xpLevel >= xpCost) {
                    player.setLevel(xpLevel - xpCost);
                    nbt.setInteger(WeaponBonusType.NBT_NAME, level += 1);
                    nbt.applyNBT(item);

                    this.event.callEvent(new UpgradeWeaponEvent(this.pm.getPlayer(player.getUniqueId()), item, level));
                    player.sendMessage(PREFIX + "Your sword has been " + ChatColor.AQUA + "upgraded" + ChatColor.RESET + " to level " + ChatColor.RED + level);
                } else {
                    player.sendMessage(PREFIX + ChatColor.RED + "You dont have enough levels ! You need " + xpCost + " levels to upgrade your sword");
                }
            }
        } else {
            sender.sendMessage(PREFIX + ChatColor.RED + "You need to hold a sword in your hand !");
        }
        return true;
    }
}
