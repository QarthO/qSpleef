package gg.quartzdev.qspleef;

import gg.quartzdev.qspleef.metrics.Metrics;
import gg.quartzdev.qspleef.util.CommandManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Locale;

public final class qSpleef extends JavaPlugin {

    private qSpleef plugin;
    private CommandManager commandManager;

    @Override
    public void onEnable() {
        // Plugin startup logic

//        bStats Metrics
        getLogger().info("Enabling bStats Metrics");
        int pluginId = 18477;
        Metrics metrics = new Metrics(this, pluginId);

        commandManager = new CommandManager(getName(), this);




    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic

//
    }

}
