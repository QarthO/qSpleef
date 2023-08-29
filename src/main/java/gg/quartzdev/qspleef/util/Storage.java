package gg.quartzdev.qspleef.util;

import gg.quartzdev.qspleef.arena.Arena;
import gg.quartzdev.qspleef.qSpleef;
import gg.quartzdev.qspleef.util.Language;
import gg.quartzdev.qspleef.util.Logger;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;

import java.io.File;
import java.io.IOException;

public class Storage {

    private Plugin plugin = qSpleef.getPlugin(qSpleef.class);
    FileConfiguration config;
    FileConfiguration arenasStorage;
    File arenasFile;

    public Storage(qSpleef plugin){

//        Sets up data folder and config
        config = plugin.getConfig();
        config.options().copyDefaults(true);
        plugin.saveConfig();

//        Sets up arenas storage
        arenasFile = this.setup("arenas.yml");
    }

    private File setup(String name){
        Logger.log("" + plugin.getDataFolder().getAbsolutePath());
        File file = new File(plugin.getDataFolder(), name);
        try {
            file.createNewFile();
            arenasStorage = YamlConfiguration.loadConfiguration(file);
        } catch(IOException e){
            Logger.error(e.getStackTrace());
            Logger.error(Language.ERROR_CREATE_ARENAS_FILE);
        }
        return file;
    }

    public void saveArena(Arena arena){
        String id = arena.getID().toString();
//        TODO: check storage schema version, and convert/update if needed
        arenasStorage.set("version", plugin.getPluginMeta().getVersion());
        arenasStorage.set("arenas." + id + ".name", arena.getName());
        arenasStorage.set("arenas." + id + ".state", arena.getState());
        arenasStorage.set("arenas." + id + ".tp-locations", arena.getName());
        arenasStorage.set("arenas." + id + ".bounds", arena.getName());
        arenasStorage.set("arenas." + id + ".floor-material", arena.getName());
        arenasStorage.set("arenas." + id + ".min-players", arena.getName());
        arenasStorage.set("arenas." + id + ".max-players", arena.getName());
        arenasStorage.set("arenas." + id + ".max-players", arena.getName());
        arenasStorage.set("arenas." + id + ".max-players", arena.getName());
        arenasStorage.set("arenas." + id + ".max-players", arena.getName());
//        Save Gamerules
//        - Snowballs
        arenasStorage.set("arenas." + id + ".gamerules.snowballs.enabled", arena.getName());
        arenasStorage.set("arenas." + id + ".gamerules.snowballs.amount", arena.getName());
//        -
        arenasStorage.set("arenas." + id + ".gamerules", arena.getName());


        Bukkit.getAsyncScheduler().runNow(plugin, scheduledTask -> {
            try {
                arenasStorage.save(arenasFile);
            } catch (IOException e) {
                Logger.error(Language.ERROR_SAVE_ARENAS_FILE);
            }
        });
    }

    public void saveAll(){

    }

}
