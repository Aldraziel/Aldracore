package fr.aldraziel.aldracore.api.cache;

import fr.aldraziel.aldracore.api.event.player.PlayerStatsUpdateEvent;
import fr.aldraziel.aldracore.api.player.IAldraPlayer;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import java.util.UUID;

public interface IAldraCacheManager extends Listener {

    /**
     * Clear the cache and init it with all online players
     */
    void clearAndInit();

    /**
     * Update a player from Redis with his UUID
     * @param uuid The UUID of the player
     */
    void updatePlayer(UUID uuid);
    /**
     * Update multiple players from Redis with their UUID
     * @param uuids The UUIDs of the players
     */
    void updatePlayers(UUID... uuids);

    /**
     * Update a player from Redis
     * @param player The player to update
     */
    void updatePlayer(IAldraPlayer player);
    /**
     * Update multiple players from Redis
     * @param players The players to update
     */
    void updatePlayers(IAldraPlayer... players);

    /**
     * Remove a player from the cache
     * @param uuid The UUID of the player
     */
    void removePlayer(UUID uuid);
    /**
     * Get a player from the cache
     * @param uuid The UUID of the player
     * @return The player from the cache
     */
    IAldraPlayer getPlayer(UUID uuid);

    /**
     * Listen for {@link PlayerStatsUpdateEvent} to stay cache updated
     * @param event The {@link PlayerStatsUpdateEvent} to listen
     */
    @EventHandler
    void onPlayerStatsUpdate(PlayerStatsUpdateEvent event);
}
