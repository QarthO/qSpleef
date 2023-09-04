package gg.quartzdev.qspleef.storage;

import gg.quartzdev.qspleef.qSpleef;
import org.bukkit.configuration.file.FileConfiguration;

public class YMLconfig {
    qSpleef plugin;
    FileConfiguration config;
    public YMLconfig(qSpleef plugin){
        this.config = plugin.getConfig();
        this.config.options().copyDefaults(true);
//        this.storage = storage;
    }

    public void saveConfig(){
        plugin.saveConfig();
    }
}
