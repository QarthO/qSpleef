package gg.quartzdev.qspleef;

import gg.quartzdev.qspleef.metrics.Metrics;
import gg.quartzdev.qspleef.util.Logger;
import org.bukkit.plugin.java.JavaPlugin;

public final class qSpleef extends JavaPlugin {

    private qSpleef plugin;

    @Override
    public void onEnable() {
        // Plugin startup logic

//        bStats Metrics
        getLogger().info("Enabling bStats Metrics");
        int pluginId = 18477;
        Metrics metrics = new Metrics(this, pluginId);

        Logger.log(Language.ERROR_COMMAND_NOT_FOUND);

//        register listeners
//


    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic

//
    }

}
