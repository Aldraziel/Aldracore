package fr.aldraziel.aldracore.api.event;

import fr.aldraziel.aldracore.AldraCore;

public class AldraEventManager implements IAldraEventManager {

    private final AldraCore core;

    public AldraEventManager(AldraCore core) {
        this.core = core;
    }

    @Override
    public void callEvent(AldraEvent event) {
        this.core.getServer().getPluginManager().callEvent(event);
    }
}
