package gg.quartzdev.qspleef;

import gg.quartzdev.qspleef.game.GameManager;
import gg.quartzdev.qspleef.game.arena.Arena;
import gg.quartzdev.qspleef.metrics.Metrics;
import gg.quartzdev.qspleef.util.CommandManager;
import gg.quartzdev.qspleef.util.Logger;
import gg.quartzdev.qspleef.util.Storage;
import gg.quartzdev.qspleef.util.Util;
import org.bukkit.configuration.serialization.ConfigurationSerialization;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Locale;

public final class qSpleef extends JavaPlugin {

    private qSpleef plugin;
    private Storage storage;
    private CommandManager commandManager;
    private GameManager gameManager;

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
//        File Storage
        this.storage = new Storage(this);
//        Command Handler
        this.commandManager = new CommandManager(getName().toLowerCase(Locale.ROOT), this);
//        Spleef Games Manager
        this.gameManager = new GameManager(this);

    }

    @Override
    public void onDisable() {
//        Saves all files
        storage.saveAll();
//        Unregisters commands
        commandManager.disable();
    }

    /**
     *
     * @return Storage
     */
    public Storage getStorage(){
        return this.storage;
    }

    public GameManager getGameManager(){
        return this.gameManager;
    }

    public Util getUtil(){
        return this.util;
    }


    public String getVersion() {
        return this.getPluginMeta().getVersion();
    }


}
