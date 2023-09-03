package gg.quartzdev.qspleef.util;

import gg.quartzdev.qspleef.game.arena.Arena;
import gg.quartzdev.qspleef.qSpleef;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class Storage {

    private qSpleef plugin;
    FileConfiguration config;
    FileConfiguration arenasStorage;
    File arenasFile;

    public Storage(qSpleef plugin){
        this.plugin = plugin;
//        Sets up data folder and config
        config = plugin.getConfig();
        config.options().copyDefaults(true);
        plugin.saveConfig();

//        Sets up arenas storage
        arenasFile = this.setup("arenas.yml");
    }

    private File setup(String name){
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
        arenasStorage.set("version", plugin.getVersion());
        arenasStorage.set("arenas." + id + ".name", arena.getName());
        arenasStorage.set("arenas." + id + ".state", arena.getState().name());
//        Tp locations
        arenasStorage.set("arenas." + id + ".tp-locations", arena.getName());
//        arenasStorage.set("arenas." + id + ".bounds", arena.getName());
        arenasStorage.set("arenas." + id + ".floor-material", arena.getFloorMaterial().name());
        arenasStorage.set("arenas." + id + ".min-players", arena.getMinPlayers());
        arenasStorage.set("arenas." + id + ".max-players", arena.getMaxPlayers());
//        Save Gamerules
//        - Snowballs
        arenasStorage.set("arenas." + id + ".gamerules.snowballs.enabled", arena.getName());
        arenasStorage.set("arenas." + id + ".gamerules.snowballs.amount", arena.getName());
//        -
        arenasStorage.set("arenas." + id + ".gamerules", arena.getName());
        saveFile(arenasStorage, arenasFile, Language.ERROR_CREATE_ARENAS_FILE);
    }

    public void saveAll(){

    }

    public void saveFile(FileConfiguration fileConfig, File file, Language failMessage){
        Bukkit.getAsyncScheduler().runNow(plugin, scheduledTask -> {
            try {
                fileConfig.save(file);
            } catch(IOException e) {
                Logger.error(failMessage);
            }
        });
    }

}
