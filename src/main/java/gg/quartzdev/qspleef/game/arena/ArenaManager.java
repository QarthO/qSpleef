package gg.quartzdev.qspleef.game.arena;

import gg.quartzdev.qspleef.qSpleef;
import gg.quartzdev.qspleef.storage.YMLarenas;

import java.io.File;
import java.util.concurrent.ConcurrentHashMap;
public class ArenaManager {

    qSpleef plugin;
    YMLarenas storage;
    File file;
    private ConcurrentHashMap<String, Arena> arenasMap;

    public ArenaManager(qSpleef plugin){
        this.plugin = plugin;
        arenasMap = new ConcurrentHashMap<>();
        this.storage = new YMLarenas(plugin);

    }
    public Arena getArena(String name){
        return this.arenasMap.get(name);
    }

}
