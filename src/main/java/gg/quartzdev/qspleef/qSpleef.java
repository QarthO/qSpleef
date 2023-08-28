package gg.quartzdev.qspleef;

import gg.quartzdev.qspleef.arena.Arena;
import gg.quartzdev.qspleef.metrics.Metrics;
import gg.quartzdev.qspleef.util.CommandManager;
import gg.quartzdev.qspleef.util.Logger;
import gg.quartzdev.qspleef.util.Storage;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Locale;

public final class qSpleef extends JavaPlugin {

    private qSpleef plugin;
    private CommandManager commandManager;
    private Storage storage;

    @Override
    public void onEnable() {
//        bStats Metrics
        Logger.log("Enabling bStats Metrics");
        int pluginId = 18477;
        Metrics metrics = new Metrics(this, pluginId);
        storage = new Storage(this);
        commandManager = new CommandManager(getName().toLowerCase(Locale.ROOT), this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        storage.saveAll();
        commandManager.disable();
    }

    public Storage getStorage(){
        return this.storage;
    }

}
