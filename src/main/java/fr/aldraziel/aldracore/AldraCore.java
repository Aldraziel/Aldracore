package fr.aldraziel.aldracore;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import fr.aldraziel.aldracore.api.AldraCoreAPI;
import fr.aldraziel.aldracore.api.AldraCoreImpl;
import fr.aldraziel.aldracore.listener.AttackListener;
import fr.aldraziel.aldracore.listener.ConnectionListener;
import fr.aldraziel.aldracore.redis.RedisManager;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class AldraCore extends JavaPlugin {

    public static final Gson GSON = new GsonBuilder().serializeNulls().create();

    private AldraCoreAPI api;
    private RedisManager redis;

    @Override
    public void onEnable() {
        this.getLogger().info("Loading configuration...");
        this.saveDefaultConfig();

        this.getLogger().info("Connecting to Redis...");
        this.redis = new RedisManager(this);

        this.getLogger().info("Loading API...");
        this.api = new AldraCoreImpl(this);

        this.getLogger().info("Registering events...");
        final PluginManager plManager = this.getServer().getPluginManager();
        plManager.registerEvents(new AttackListener(this), this);
        plManager.registerEvents(new ConnectionListener(this), this);
    }

    @Override
    public void onDisable() {
        this.redis.getPool().close();
        this.redis.getPool().destroy();
    }

    public AldraCoreAPI getApi() {
        return this.api;
    }

    public RedisManager getRedis() {
        return this.redis;
    }
}
