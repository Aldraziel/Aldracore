package fr.aldraziel.aldracore.redis;

import fr.aldraziel.aldracore.AldraCore;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.util.logging.Level;
import java.util.logging.Logger;

public class RedisManager {

    private final JedisPool pool;

    public RedisManager(AldraCore core) {
        final Logger logger = core.getLogger();
        final FileConfiguration config = core.getConfig();

        this.pool = new JedisPool(new JedisPoolConfig(), config.getString("redis.host"), config.getInt("redis.port"), 2000, config.getString("redis.pass"), config.getInt("redis.db"), "AldraCore");

        try (final Jedis jedis = this.pool.getResource()){
            logger.info("Connection set with Redis, on database " + jedis.getDB());
        } catch (final Exception e) {
            logger.log(Level.SEVERE,"An error occurred during connecting to Redis ! (" + e.getMessage() + "). Shutdown...");
            Bukkit.shutdown();
        }
    }

    public Jedis getJedis() {
        return this.pool.getResource();
    }

    public JedisPool getPool() {
        return this.pool;
    }
}
