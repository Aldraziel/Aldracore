package fr.aldraziel.aldracore.api.event.armor;

import fr.aldraziel.aldracore.api.event.AldraEvent;
import fr.aldraziel.aldracore.api.player.IAldraPlayer;
import org.bukkit.inventory.ItemStack;

public class UpgradeArmorEvent extends AldraEvent {

    private final IAldraPlayer player;
    private final ItemStack item;
    private final int level;

    public UpgradeArmorEvent(IAldraPlayer player, ItemStack item, int level) {
        this.player = player;
        this.item = item;
        this.level = level;
    }

    public IAldraPlayer getPlayer() {
        return this.player;
    }

    public ItemStack getItem() {
        return this.item;
    }

    public int getLevel() {
        return this.level;
    }
}
