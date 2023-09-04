package gg.quartzdev.qspleef;

import gg.quartzdev.qspleef.game.GameManager;
import gg.quartzdev.qspleef.game.arena.Arena;
import gg.quartzdev.qspleef.game.arena.ArenaManager;
import gg.quartzdev.qspleef.metrics.Metrics;
import gg.quartzdev.qspleef.util.CommandManager;
import gg.quartzdev.qspleef.util.Logger;
import gg.quartzdev.qspleef.storage.qYML;
import gg.quartzdev.qspleef.util.Util;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.configuration.serialization.ConfigurationSerialization;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.util.Locale;

public final class qSpleef extends JavaPlugin {

    private qSpleef plugin;
    private qYML storage;
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
//        Utility Class
        this.util = new Util(this);
//        Command Handler
        this.commandManager = new CommandManager(getName().toLowerCase(Locale.ROOT), this);
//        Spleef Games Manager
        this.gameManager = new GameManager(this);
        this.arenaManger = new ArenaManager(this);

    }

    @Override
    public void onDisable() {
//        Saves all files
//        storage.saveAll();
//        Unregisters commands
//        commandManager.disable();
    }

    /**
     *
     * @return Storage
     */
    public qYML getStorage(){
        return this.storage;
    }

    public ArenaManager getArenaManager(){
        return this.arenaManger;
    }

    public GameManager getGameManager(){
        return this.gameManager;
    }

    public Util getUtil(){
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


}
