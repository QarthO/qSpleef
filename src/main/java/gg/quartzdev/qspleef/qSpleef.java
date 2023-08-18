package gg.quartzdev.qspleef;

import net.kyori.adventure.audience.Audience;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;
import gg.quartzdev.qspleef.metrics.Metrics;

public final class qSpleef extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic

//        bStats Metrics
        getLogger().info("Enabling bStats Metrics");
        int pluginId = 18477;
        Metrics metrics = new Metrics(this, pluginId);

//        register listeners
//


    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic

//
    }

}
