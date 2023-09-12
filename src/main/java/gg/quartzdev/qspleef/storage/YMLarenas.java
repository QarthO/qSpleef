package gg.quartzdev.qspleef.storage;

import gg.quartzdev.qspleef.game.arena.Arena;
import gg.quartzdev.qspleef.qSpleef;
import gg.quartzdev.qspleef.util.Language;
import gg.quartzdev.qspleef.util.Logger;
import org.bukkit.configuration.ConfigurationSection;
import org.jetbrains.annotations.NotNull;

import java.util.HashSet;
import java.util.Set;

public class YMLarenas extends qYML {

    public YMLarenas(qSpleef plugin){
        super(plugin, "arenas");
    }
    public @NotNull Set<Arena> loadAll(){
        Logger.log("loadAll");
        Set<Arena> arenas = new HashSet<>();
        Set<String> arenaIDsList = this.getArenasSection().getKeys(false);
        Logger.log("break");
        if(arenaIDsList.isEmpty()) return arenas;
        for(String id : arenaIDsList){
            Arena arena = this.load(id);
            if(arena != null)
                arenas.add(arena);
        }
        Logger.log("end loadAll");
        return arenas;
    }
    public void save(Arena arena){
        String id = arena.getID().toString();
        config.set("arenas." + id, arena);
        this.saveFile();
    }
    public Arena load(String id){
        Object arenaData = this.getArenasSection().get(id);
        if(!(arenaData instanceof Arena)){
            Logger.error(Language.ERROR_CORRUPT_FILE.setFile(this.file.getName()));
            return null;
        }
        Arena arena = (Arena) arenaData;
        if(arena.getID() != null)
            Logger.log(arena.getID().toString());
        arena.initID(id);
        return arena;
    }
    private @NotNull ConfigurationSection getArenasSection(){
        ConfigurationSection configArenaSection = this.config.getConfigurationSection("arenas");
        if(configArenaSection == null) {
            Logger.error(Language.ERROR_READ_FILE.setFile(file.getName()));
            ConfigurationSection arenasSection = config.createSection("arenas");
            this.saveFile();
            return arenasSection;
        }
        Set<String> arenaIDsList = (Set<String>) configArenaSection.getKeys(false);
        return configArenaSection;
    }
}
