package fr.aldraziel.aldracore;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import fr.aldraziel.aldracore.api.AldraCoreAPI;
import fr.aldraziel.aldracore.api.AldraCoreImpl;
import fr.aldraziel.aldracore.api.utils.AldraCommand;
import fr.aldraziel.aldracore.command.StatusCommand;
import fr.aldraziel.aldracore.command.UpgradeCommand;
import fr.aldraziel.aldracore.listener.AttackListener;
import fr.aldraziel.aldracore.listener.ConnectionListener;
import fr.aldraziel.aldracore.listener.StatsListener;
import fr.flowarg.aldraziel.aldraredis.AldraRedis;
import fr.flowarg.aldraziel.aldraredis.RedisDataStorage;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.PluginCommand;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

public class AldraCore extends JavaPlugin {

    public static final Gson GSON = new GsonBuilder().serializeNulls().create();

    private AldraCoreAPI api;
    private RedisDataStorage redis;

    @Override
    public void onEnable() {
        this.getLogger().info("Loading configuration...");
        this.saveDefaultConfig();

        this.getLogger().info("Connecting to Redis...");
        this.redis = AldraRedis.getInstance().createNewRedisDataStorage();

        this.getLogger().info("Loading API...");
        this.api = new AldraCoreImpl(this);

        this.getLogger().info("Registering events...");
        this.registerEvent(this.api.getCacheManager());
        this.registerEvent(new StatsListener(this));
        this.registerEvent(new AttackListener(this));
        this.registerEvent(new ConnectionListener(this));

        this.getLogger().info("Registering commands...");
        this.setExecutor(AldraCommand.STATUS, new StatusCommand(this));
        this.setExecutor(AldraCommand.UPGRADE, new UpgradeCommand(this));

        this.api.getCacheManager().clearAndInit();
    }

    public AldraCoreAPI getApi() {
        return this.api;
    }

    public RedisDataStorage getRedis() {
        return this.redis;
    }

    private void registerEvent(Listener listener) {
        this.getServer().getPluginManager().registerEvents(listener, this);
    }

    private void setExecutor(AldraCommand command, CommandExecutor executor) {
        final PluginCommand cmd = this.getCommand(command.getCommand());
        if (cmd != null) {
            cmd.setExecutor(executor);
        }
    }
}
