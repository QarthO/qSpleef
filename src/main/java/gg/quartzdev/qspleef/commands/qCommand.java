package gg.quartzdev.qspleef.commands;

import gg.quartzdev.qspleef.qSpleef;
import org.bukkit.plugin.Plugin;

public abstract class qCommand {

    String permissionNode;
    String commandSyntax;
    qSpleef plugin;

    public qCommand(qSpleef plugin){
        this.plugin = plugin;
    }
    public abstract void run();
    public abstract void getTabCompletes();


}
