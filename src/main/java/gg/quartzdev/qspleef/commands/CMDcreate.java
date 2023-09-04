package gg.quartzdev.qspleef.commands;

import gg.quartzdev.qspleef.game.arena.Arena;
import gg.quartzdev.qspleef.qSpleef;
import org.bukkit.command.CommandSender;

import java.util.ArrayList;
import java.util.List;

public class CMDcreate extends qCMD {
    public CMDcreate(String label, String name, qSpleef plugin) {
        super(label, name, plugin);
        this.commandSyntax = "<arena-name>";
        this.permissionNode = "qspleef.admin.create";
    }
    @Override
    public List<String> getTabCompletes(CommandSender sender, String[] args) {
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
//        this.plugin.getArenaManager().saveArena(newArena);
        util.sendMessage(sender, "<green>Sucessfully created arena: <aqua><arena>");
        util.sendMessage(sender, "...");
        return true;
    }
}
