package gg.quartzdev.qspleef.game.arena;

import gg.quartzdev.qspleef.qSpleef;
import gg.quartzdev.qspleef.storage.YMLarenas;

import java.util.Collection;
import java.util.HashSet;
import java.util.Locale;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
public class ArenaManager {

    qSpleef plugin;
    YMLarenas storage;
    private ConcurrentHashMap<String, Arena> arenasMap;

    public ArenaManager(qSpleef plugin){
        this.plugin = plugin;
        arenasMap = new ConcurrentHashMap<>();
        this.storage = new YMLarenas(plugin);
        this.reloadArenas();

    }

    public void createArena(String name){
        Arena arena = new Arena(name);
        this.storage.save(arena);
        arenasMap.put(name, arena);
    }

    public Arena getArena(String name){
        return this.arenasMap.get(name.toLowerCase(Locale.ROOT));
    }

    public void reloadArenas(){
        Set<Arena> arenas = this.storage.loadAll();
        arenasMap.clear();
        for(Arena arena : arenas){
            arenasMap.put(arena.getName().toLowerCase(Locale.ROOT), arena);
        }
    }

    public Set<String> getList() {
        Set<String> arenaNames = new HashSet<>();
        Collection<Arena> arenas = arenasMap.values();
        if(!arenas.isEmpty())
            for(Arena arena : arenas)
                arenaNames.add(arena.getName());
        return arenaNames;
    }

}
