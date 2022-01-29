package fr.aldraziel.aldracore.command;

import fr.aldraziel.aldracore.AldraCore;
import fr.aldraziel.aldracore.api.player.IAldraPlayer;
import fr.aldraziel.aldracore.api.player.IAldraPlayerManager;
import fr.aldraziel.aldracore.api.utils.AldraPermission;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class StatusCommand implements CommandExecutor {

    private static final String PREFIX = "[" + ChatColor.DARK_RED + "AldraCore" + "] " + ChatColor.RESET;

    private final IAldraPlayerManager pm;

    public StatusCommand(AldraCore core) {
        this.pm = core.getApi().getPlayerManager();
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (sender.hasPermission(AldraPermission.STATUS.getPermission())) {
            if (args.length == 1) {
                final Player bukkitPlayer = Bukkit.getPlayer(args[0]);
                if (bukkitPlayer != null) {
                    final IAldraPlayer player = this.pm.getPlayer(bukkitPlayer.getUniqueId());
                    sender.sendMessage(PREFIX + "----------Status for " + player.getName() + ": ----------\n" +
                            "|  - " + ChatColor.GREEN + "Defense " + player.getDefense() + "%" + ChatColor.RESET + "\n" +
                            "|  - " + ChatColor.BLUE + "Critical Chance " + player.getCritical() + "%" + ChatColor.RESET + "\n" +
                            "|  - " + ChatColor.BLUE + "Critical Damage " + player.getCriticalDamage() + "%" + ChatColor.RESET + "\n" +
                            "|  - " + ChatColor.WHITE + "Wisdom " + player.getWisdom() + "%" + ChatColor.RESET + "\n" +
                            "|  - " + ChatColor.GOLD + "Dodge " + player.getDodge() + "%" + ChatColor.RESET + "\n" +
                            "|  - " + ChatColor.GRAY + "Velocity " + player.getVelocity() + "%" + ChatColor.RESET + "\n" +
                            "|  - " + ChatColor.DARK_RED + "Temerity " + player.getTemerity() + "%" + ChatColor.RESET + "\n" +
                            "|  - " + ChatColor.AQUA + "Magic " + player.getMagic() + "%" + ChatColor.RESET + "\n" +
                            "|  - " + ChatColor.RED + "Regen " + player.getRegen() + "%" + ChatColor.RESET + "\n" +
                            "|  - " + ChatColor.WHITE + "Bonus Wisdom " + player.getBonusWisdom() + "%" + ChatColor.RESET + "\n" +
                            "|  - " + ChatColor.AQUA + "Mana " + player.getMana() + ChatColor.RESET);
                    return true;
                } else {
                    sender.sendMessage(PREFIX + ChatColor.RED + "Cannot find a player with the name '" + args[0] + "'");
                }
            } else {
                sender.sendMessage(PREFIX + ChatColor.RED + "Missing args ! Required 1 with usage " + command.getUsage());
            }
        } else {
            sender.sendMessage(PREFIX + ChatColor.RED + "You don't have the permission to execute this command !");
        }
        return false;
    }
}
