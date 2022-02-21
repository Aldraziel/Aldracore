package fr.aldraziel.aldracore.api.cache;

import fr.aldraziel.aldracore.AldraCore;
import fr.aldraziel.aldracore.api.event.player.PlayerStatsUpdateEvent;
import fr.aldraziel.aldracore.api.player.IAldraPlayer;
import fr.aldraziel.aldracore.api.player.IAldraPlayerManager;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;

import java.util.Arrays;
import java.util.HashMap;
import java.util.UUID;

public class AldraCacheManager implements IAldraCacheManager {

    private final IAldraPlayerManager pm;
    private final HashMap<UUID, IAldraPlayer> players;

    public AldraCacheManager(AldraCore core) {
        this.players = new HashMap<>();
        this.pm = core.getApi().getPlayerManager();
    }

    @Override
    public void clearAndInit() {
        this.players.clear();
        Bukkit.getOnlinePlayers().forEach(player -> {
            final UUID uuid = player.getUniqueId();
            this.players.put(uuid, this.pm.getPlayer(uuid));
        });
    }

    @Override
    public void updatePlayer(UUID uuid) {
        this.updatePlayer(this.pm.getPlayer(uuid));
    }

    @Override
    public void updatePlayers(UUID... uuids) {
        Arrays.asList(uuids).forEach(this::updatePlayer);
    }

    @Override
    public void updatePlayer(IAldraPlayer player) {
        this.players.remove(player.getUuid());
        this.players.put(player.getUuid(), player);
    }

    @Override
    public void updatePlayers(IAldraPlayer... players) {
        Arrays.asList(players).forEach(this::updatePlayer);
    }

    @Override
    public void removePlayer(UUID uuid) {
        this.players.remove(uuid);
    }

    @Override
    public IAldraPlayer getPlayer(UUID uuid) {
        return this.players.get(uuid);
    }

    @Override
    @EventHandler
    public void onPlayerStatsUpdate(PlayerStatsUpdateEvent event) {
        this.updatePlayer(event.getPlayer());
    }
}
