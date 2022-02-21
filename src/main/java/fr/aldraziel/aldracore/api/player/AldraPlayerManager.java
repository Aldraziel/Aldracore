package fr.aldraziel.aldracore.api.player;

import fr.aldraziel.aldracore.AldraCore;
import fr.aldraziel.aldracore.api.event.IAldraEventManager;
import fr.aldraziel.aldracore.api.event.player.PlayerStatsUpdateEvent;
import fr.flowarg.aldraziel.aldraredis.RedisDataStorage;
import org.bukkit.Bukkit;

import java.util.UUID;

public class AldraPlayerManager implements IAldraPlayerManager {

    private final String key;
    private final RedisDataStorage redis;
    private final IAldraEventManager event;

    public AldraPlayerManager(AldraCore core) {
        this.redis = core.getRedis();
        this.event = core.getApi().getEventManager();
        this.key = core.getConfig().getString("redis.key");
    }

    @Override
    public IAldraPlayer getPlayer(UUID uuid) {
        return this.redis.executeAndGet(jedis -> AldraCore.GSON.fromJson(jedis.get(this.getKey(uuid)), AldraPlayer.class));
    }

    @Override
    public void savePlayer(IAldraPlayer player) {
        this.redis.execute(jedis -> jedis.set(this.getKey(player.getUuid()), AldraCore.GSON.toJson(player)));
        this.event.callEvent(new PlayerStatsUpdateEvent(player, Bukkit.getPlayer(player.getUuid())));
    }

    @Override
    public IAldraPlayer createPlayer(UUID uuid, String name) {
        final IAldraPlayer player = new AldraPlayer(uuid, name);
        player.clearAttributes();
        this.savePlayer(player);
        return player;
    }

    @Override
    public void clearAttributes(UUID uuid) {
        this.getPlayer(uuid).clearAttributes();
    }

    private String getKey(UUID uuid) {
        return this.key + uuid.toString();
    }
}
