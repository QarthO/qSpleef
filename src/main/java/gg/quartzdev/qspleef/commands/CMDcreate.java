package gg.quartzdev.qspleef.commands;

import gg.quartzdev.qspleef.qSpleef;
import org.bukkit.command.CommandSender;

import java.util.ArrayList;
import java.util.List;

public class CMDcreate extends qCMD {
    public CMDcreate(String label, String name, qSpleef plugin) {
        super(label, name, plugin);
        this.commandSyntax = "<arena-name>";
        this.permissionGroup = "qspleef.basic";
        this.permissionNode = "qspleef.command.create";
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
        this.plugin.getArenaManager().createArena(args[1]);
        util.sendMessage(sender, "<green>Response");
        return true;
    }
}
