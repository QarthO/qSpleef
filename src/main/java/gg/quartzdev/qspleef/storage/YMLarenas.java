package gg.quartzdev.qspleef.storage;

import gg.quartzdev.qspleef.game.arena.Arena;
import gg.quartzdev.qspleef.qSpleef;
import gg.quartzdev.qspleef.util.Language;
import gg.quartzdev.qspleef.util.Logger;
import org.bukkit.configuration.ConfigurationSection;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public class YMLarenas extends qYML {

    public YMLarenas(qSpleef plugin){
        super(plugin, "arenas");
    }

    public Set<Arena> loadAll(){
        Set<Arena> arenas = new HashSet<>();
        ConfigurationSection configArenaSection = this.config.getConfigurationSection("arenas");
        if(configArenaSection == null) {
            Logger.error(Language.ERROR_READ_FILE.setFile(file.getName()));
            return null;
        }
        Set<String> arenaIDsList = (Set<String>) configArenaSection.getKeys(false);
        if(arenaIDsList.isEmpty()) {
            return null;
        }
        for(String id : arenaIDsList){
            Object arenaData = config.get("arenas." + id);
            if(!(arenaData instanceof Arena)){
//                    TODO: Add to language file
                Logger.error("<red>Corrupt 'arenas.yml'");
                return null;
            }
            Arena arena = (Arena) arenaData;
            arena.initID(id);
            arenas.add(arena);
        }
        return arenas;
    }

    public void save(Arena arena){
        String id = arena.getID().toString();
        config.set("schema-version", this.schemaVersion);
        config.set("arenas." + id, arena);
        this.saveFile();
    }

    public void load(UUID id){

    }

}
