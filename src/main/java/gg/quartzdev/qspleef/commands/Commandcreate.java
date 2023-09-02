package gg.quartzdev.qspleef.commands;

import gg.quartzdev.qspleef.game.arena.Arena;
import gg.quartzdev.qspleef.qSpleef;
import gg.quartzdev.qspleef.util.Logger;
import org.bukkit.command.CommandSender;

import java.util.ArrayList;
import java.util.List;

public class Commandcreate extends qCommand {
    public Commandcreate(String label, String name, qSpleef plugin) {
        super(label, name, plugin);
        this.commandSyntax = "<arena-name>";
        this.permissionNode = "qspleef.admin.create";
    }
    @Override
    public List<String> getTabCompletes(CommandSender sender, String[] args) {
        Logger.log("<green>Commandcreate#getTabCompletes: " + Thread.currentThread().getId() + " - " + Thread.currentThread().getName());
        List<String> rawCompletions = new ArrayList<>();

        if(args.length < 2) return rawCompletions;

        if(args.length == 2)
            rawCompletions.add("<arena-name>");

        return rawCompletions;
    }

    @Override
    public boolean logic(CommandSender sender, String[] args) {
        String arenaName = args[1];
        Arena newArena = new Arena(arenaName);
        this.plugin.getStorage().saveArena(newArena);
        return true;
    }
}
