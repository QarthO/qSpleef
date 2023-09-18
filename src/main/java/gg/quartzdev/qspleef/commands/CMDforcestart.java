package gg.quartzdev.qspleef.commands;

import gg.quartzdev.qspleef.qSpleef;
import org.bukkit.command.CommandSender;
import org.bukkit.util.StringUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class CMDforcestart  extends qCMD{
    public CMDforcestart(String label, String name, qSpleef plugin) {
        super(label, name, plugin);
        this.commandSyntax = "<arena>";
        this.permissionGroup = "qspleef.admin";
        this.permissionNode = "qspleef.command.forcestart";
    }

    @Override
    public List<String> getTabCompletes(CommandSender sender, String[] args) {
        Set<String> arenas = this.plugin.getArenaManager().getList();
        List<String> rawCompletions = new ArrayList<>();
        if(args.length == 2)
            StringUtil.copyPartialMatches(args[1], arenas, rawCompletions);
        return rawCompletions;
    }

    @Override
    public boolean logic(CommandSender sender, String[] args) {
        this.util.sendMessage(sender, "forcestarting arena: " + args[1]);
        return true;
    }
}
