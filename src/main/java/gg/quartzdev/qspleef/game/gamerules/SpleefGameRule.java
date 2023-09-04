package gg.quartzdev.qspleef.game.gamerules;

import gg.quartzdev.qspleef.qSpleef;
import org.bukkit.event.Listener;

public abstract class SpleefGameRule {

    String name;

    qSpleef plugin;

    public SpleefGameRule(qSpleef plugin){
        this.plugin = plugin;
    }

    public void registerEvent(Listener listener){
        this.plugin.getServer().getPluginManager().registerEvents(listener, this.plugin);
    }

    public void run(){
        this.logic();
    }

    public abstract void logic();

}
