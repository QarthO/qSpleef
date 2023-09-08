package gg.quartzdev.qspleef.storage;

import gg.quartzdev.qspleef.qSpleef;
import gg.quartzdev.qspleef.util.Language;
import gg.quartzdev.qspleef.util.Logger;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public abstract class qYML {

    final qSpleef plugin;
    File file;
    YamlConfiguration config;
    String schemaVersion = "1.0";

    public qYML(qSpleef plugin, String name){
        this.plugin = plugin;
        this.file = loadFile(name + ".yml");
    }

    private File loadFile(String name){
        Logger.log("- inside loadFile" + Thread.currentThread().getName());
        File file = new File(plugin.getDataFolder(), name);
        try {
            if(file.createNewFile()){
                Logger.log("<green>Creating '<yellow>" + name + "<green>'");
            }
            this.config = YamlConfiguration.loadConfiguration(file);
            List<String> notes = new ArrayList<>();
            notes.add("Loaded with " + plugin.getName() + " v" + plugin.getVersion());
            if(this.config.get("schema-version") == null)
                this.config.set("schema-version", this.schemaVersion);
            this.config.setComments("schema-version", notes);
            this.config.save(file);
        } catch(IOException e){
//            Logger.error(e.getStackTrace());
            Logger.error(Language.ERROR_CREATE_FILE.setFile(file.getName()));
            return null;
        }
        return file;
    }
    public void saveFile() {
        Bukkit.getAsyncScheduler().runNow(plugin, scheduledTask -> {
            try {
                this.config.save(this.file);
            } catch(IOException e) {
                Logger.error(Language.ERROR_SAVE_FILE.setFile(this.file.getName()));
            }
        });
    }

}
