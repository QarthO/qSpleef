package gg.quartzdev.qspleef.storage;

import gg.quartzdev.qspleef.qSpleef;
import org.bukkit.configuration.file.FileConfiguration;

public class qConfig {
    qSpleef plugin;
    FileConfiguration config;
    public qConfig(qSpleef plugin){
        this.plugin = plugin;
        this.config = plugin.getConfig();
        this.config.options().copyDefaults(true);
        this.saveConfig();
    }
    public void saveConfig(){
        this.plugin.saveConfig();
    }
}
