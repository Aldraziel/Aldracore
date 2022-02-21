package fr.aldraziel.aldracore.api.event;

public interface IAldraEventManager {

    /**
     * Call an event in Bukkit EventBus
     * @param event The event to call
     */
    void callEvent(AldraEvent event);
}
