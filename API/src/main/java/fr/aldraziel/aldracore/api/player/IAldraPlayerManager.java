package fr.aldraziel.aldracore.api.player;

import java.util.UUID;

public interface IAldraPlayerManager {

    /** Database interactions */
    IAldraPlayer getPlayer(UUID uuid);
    void savePlayer(IAldraPlayer player);
    void createPlayer(UUID uuid, String name);

    /** Player shortcuts */
    void clearAttributes(UUID uuid);
}
