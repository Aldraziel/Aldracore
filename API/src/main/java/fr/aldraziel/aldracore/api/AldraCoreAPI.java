package fr.aldraziel.aldracore.api;

import fr.aldraziel.aldracore.api.event.IAldraEventManager;
import fr.aldraziel.aldracore.api.player.IAldraPlayerManager;

public abstract class AldraCoreAPI {

    /** The Event Manager */
    public abstract IAldraEventManager getEventManager();

    /** The Player Manager */
    public abstract IAldraPlayerManager getPlayerManager();
}
