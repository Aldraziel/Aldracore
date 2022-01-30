package fr.aldraziel.aldracore.api.player;

import fr.aldraziel.aldracore.AldraCore;
import fr.aldraziel.aldracore.redis.RedisManager;
import redis.clients.jedis.Jedis;

import java.util.UUID;

public class AldraPlayerManager implements IAldraPlayerManager {

    private final String key;
    private final RedisManager redis;

    public AldraPlayerManager(AldraCore core) {
        this.redis = core.getRedis();
        this.key = core.getConfig().getString("redis.key");
    }

    @Override
    public IAldraPlayer getPlayer(UUID uuid) {
        try (final Jedis jedis = this.redis.getJedis()) {
            return AldraCore.GSON.fromJson(jedis.get(this.getKey(uuid)), AldraPlayer.class);
        }
    }

    @Override
    public void savePlayer(IAldraPlayer player) {
        try (final Jedis jedis = this.redis.getJedis()){
            jedis.set(this.getKey(player.getUuid()), AldraCore.GSON.toJson(player));
        }
    }

    @Override
    public void createPlayer(UUID uuid, String name) {
        final IAldraPlayer player = new AldraPlayer(uuid, name);
        player.clearAttributes();
        this.savePlayer(player);
    }

    @Override
    public void clearAttributes(UUID uuid) {
        this.getPlayer(uuid).clearAttributes();
    }

    private String getKey(UUID uuid) {
        return this.key + uuid.toString();
    }
}
