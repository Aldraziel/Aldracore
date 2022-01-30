package fr.aldraziel.aldracore.api.event.weapon;

import fr.aldraziel.aldracore.api.event.AldraEvent;
import fr.aldraziel.aldracore.api.player.IAldraPlayer;
import org.bukkit.event.HandlerList;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

public class UpgradeWeaponEvent extends AldraEvent {

    private static final HandlerList HANDLERS = new HandlerList();

    private final IAldraPlayer player;
    private final ItemStack item;
    private final int level;
    private boolean isCancelled;

    public UpgradeWeaponEvent(IAldraPlayer player, ItemStack item, int level) {
        this.player = player;
        this.item = item;
        this.level = level;

        this.isCancelled = false;
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

    @Override
    public boolean isCancelled() {
        return this.isCancelled;
    }

    @Override
    public void setCancelled(boolean cancel) {
        this.isCancelled = cancel;
    }

    @NotNull
    @Override
    public HandlerList getHandlers() {
        return HANDLERS;
    }
}
