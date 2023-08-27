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
    FileConfiguration arenasStorage;
    File arenasFile;

    public Storage(){
        check(arenasFile);
    }

    private void check(File file){
        arenasFile = new File(plugin.getDataFolder(), "arenas");
        if(file.exists()) return;
        try {
            file.createNewFile();
            arenasStorage = YamlConfiguration.loadConfiguration(file);
        } catch(IOException e){
            Logger.error(Language.ERROR_CREATE_ARENAS_FILE);
        }
    }

    public void saveArena(Arena arena){
        String id = arena.getID().toString();
//        TODO: check storage schema version, and convert/update if needed
        arenasStorage.set("version", plugin.getPluginMeta().getVersion());
        arenasStorage.set("arenas." + id, arena.getName());
        Bukkit.getAsyncScheduler().runNow(plugin, scheduledTask -> {
            try {
                arenasStorage.save(arenasFile);
            } catch (IOException e) {
                Logger.error(Language.ERROR_SAVE_ARENAS_FILE);
            }
        });
    }

}
