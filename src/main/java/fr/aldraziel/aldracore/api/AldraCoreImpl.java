package fr.aldraziel.aldracore.api;

import fr.aldraziel.aldracore.AldraCore;
import fr.aldraziel.aldracore.api.event.AldraEventManager;
import fr.aldraziel.aldracore.api.event.IAldraEventManager;
import fr.aldraziel.aldracore.api.player.AldraPlayerManager;
import fr.aldraziel.aldracore.api.player.IAldraPlayerManager;

public class AldraCoreImpl extends AldraCoreAPI {

    private final IAldraEventManager eventManager;
    private final IAldraPlayerManager playerManager;

    public AldraCoreImpl(AldraCore core) {
        this.eventManager = new AldraEventManager(core);
        this.playerManager = new AldraPlayerManager(core);
    }

    @Override
    public IAldraEventManager getEventManager() {
        return this.eventManager;
    }

    @Override
    public IAldraPlayerManager getPlayerManager() {
        return this.playerManager;
    }
}
