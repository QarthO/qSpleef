package gg.quartzdev.qspleef.util;

import gg.quartzdev.qspleef.game.arena.Arena;
import gg.quartzdev.qspleef.qSpleef;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class Storage {

    private final qSpleef plugin;
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
        this.loadArenas();
    }

    private File setup(String name){
        File file = new File(plugin.getDataFolder(), name);
        try {
            if(file.createNewFile())
                Logger.log("<green>Creating '<yellow>" + name + "<green>'");
            arenasStorage = YamlConfiguration.loadConfiguration(file);
        } catch(IOException e){
            Logger.error(e.getStackTrace());
            Logger.error(Language.ERROR_CREATE_FILE.setFile(file.getName()));
        }
        return file;
    }

    public void saveArena(Arena arena){
        String id = arena.getID().toString();
//        TODO: check storage schema version, and convert/update if needed
        arenasStorage.set("version", plugin.getVersion());
        arenasStorage.set("arenas." + id, arena);
        saveFile(arenasStorage, arenasFile);
    }

    public void loadArenas(){
        ConcurrentHashMap<UUID, Arena> arenasMap = new ConcurrentHashMap<>();
        try {
            Set<String> f = (Set<String>) arenasStorage.getConfigurationSection("arenas").getKeys(false);
            if(f == null) {
                Logger.log("null f");
                return;
            }
            Logger.log("not null f");

        } catch (Exception e){
            Logger.error(Language.ERROR_READ_FILE.setFile(arenasFile.getName()));
        }
//        if(arenaList == null){
//            Logger.log("null");
//            return;
//        }
//        Logger.log("obj: " + arenaList.getClass().toString());


//        if(arenaList == null)
            return;
//        if(arenaList.isEmpty())
//            Logger.log("is empty");
//        for(Object arena : arenaList){
//            System.out.println(arena.toString());
//        }

    }

    public void saveAll(){

    }

    public void saveFile(FileConfiguration fileConfig, File file) {
        Bukkit.getAsyncScheduler().runNow(plugin, scheduledTask -> {
            try {
                fileConfig.save(file);
            } catch(IOException e) {
                Logger.error(Language.ERROR_SAVE_FILE.setFile(file.getName()));
            }
        });
    }

}
