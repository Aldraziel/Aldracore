package fr.aldraziel.aldracore.api.player;

import java.util.UUID;

public interface IAldraPlayerManager {

    /** Database interactions */

    /**
     * Get a player from Redis
     * @param uuid The player's UUID
     * @return The player matching the given UUID
     */
    IAldraPlayer getPlayer(UUID uuid);
    /**
     * Save a player to Redis
     * @param player The player to save
     */
    void savePlayer(IAldraPlayer player);
    /**
     * Create a player in Redis and save it
     * @param uuid The player's UUID
     * @param name The player's name
     * @return The created player
     */
    IAldraPlayer createPlayer(UUID uuid, String name);

    /** Player shortcuts */

    /**
     * Get a player from Redis and clear his stats
     * @param uuid The player's UUID
     */
    void clearAttributes(UUID uuid);
}
