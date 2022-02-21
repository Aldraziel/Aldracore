package fr.aldraziel.aldracore.api;

import fr.aldraziel.aldracore.api.cache.IAldraCacheManager;
import fr.aldraziel.aldracore.api.event.IAldraEventManager;
import fr.aldraziel.aldracore.api.player.IAldraPlayerManager;

public abstract class AldraCoreAPI {

    private static AldraCoreAPI INSTANCE;

    public AldraCoreAPI() {
        INSTANCE = this;
    }

    /**
     * The Event Manager
     * @return The {@link IAldraEventManager}
     */
    public abstract IAldraEventManager getEventManager();

    /**
     * The Player Manager
     * @return The {@link IAldraPlayerManager}
     */
    public abstract IAldraPlayerManager getPlayerManager();

    /**
     * The Cache Manager
     * @return The {@link IAldraCacheManager}
     */
    public abstract IAldraCacheManager getCacheManager();

    /**
     * Get the instance of the API
     * @return The {@link AldraCoreAPI} instance
     */
    public static AldraCoreAPI get() {
        return INSTANCE;
    }
}
