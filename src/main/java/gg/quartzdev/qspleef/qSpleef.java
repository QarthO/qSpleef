package gg.quartzdev.qspleef;

import gg.quartzdev.qspleef.game.GameManager;
import gg.quartzdev.qspleef.game.arena.Arena;
import gg.quartzdev.qspleef.game.arena.ArenaManager;
import gg.quartzdev.qspleef.metrics.Metrics;
import gg.quartzdev.qspleef.storage.qConfig;
import gg.quartzdev.qspleef.util.CommandManager;
import gg.quartzdev.qspleef.util.Logger;
import gg.quartzdev.qspleef.util.Util;
import org.bukkit.configuration.serialization.ConfigurationSerialization;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Locale;
import java.util.concurrent.CompletableFuture;

public final class qSpleef extends JavaPlugin {

    private qConfig config;
    private CommandManager commandManager;
    private GameManager gameManager;
    private ArenaManager arenaManger;

    private Util util;

    @Override
    public void onEnable() {
        ConfigurationSerialization.registerClass(Arena.class);
//        bStats Metrics
        Logger.log("Enabling bStats Metrics");
        int pluginId = 18477;
        Metrics metrics = new Metrics(this, pluginId);
//        Config
        this.config = new qConfig(this);
//        Utility Class
        this.util = new Util(this);
//        Spleef Arena Manager
        this.arenaManger = new ArenaManager(this);
//        Spleef Games Manager
        this.gameManager = new GameManager(this);
        
//        Command Handler
        this.commandManager = new CommandManager(getName().toLowerCase(Locale.ROOT), this);
//        Spleef Arena Manager
    }

    @Override
    public void onDisable() {
//        Saves all files
//        storage.saveAll();
//        Unregisters commands
//        commandManager.disable();
    }

    public qConfig getConfigData(){
        return this.config;
    }

    public ArenaManager getArenaManager(){
//        Initializes the arena manager if it hasn't already
//        if(this.arenaManger == null)
//            this.arenaManger = new ArenaManager(this);
//        Returns the arena manager
        return this.arenaManger;
    }

    public GameManager getGameManager(){
//        Initializes the game manager if it hasn't already
//        if(this.gameManager == null)
//            this.gameManager = new GameManager(this);
//        Returns the game manager
        return this.gameManager;
    }

    public Util getUtil(){
//        Initializes the utility class if it hasn't already
        if(this.util == null)
            this.util = new Util(this);
//        Returns the utility class
        return this.util;
    }

    /**
     * Get the version of the plugin
     * Uses PaperMC's 'experimental' API to get the version
     * @return Plugin's version from the 'paper-plugin.yml'
     */
    @SuppressWarnings("UnstableApiUsage")
    public String getVersion() {
        return this.getPluginMeta().getVersion();
    }

    private CompletableFuture<Void> setupDataFolder(){
        return CompletableFuture.supplyAsync(() -> {
            this.config = new qConfig(this);
            return null;
        });
    };


}
