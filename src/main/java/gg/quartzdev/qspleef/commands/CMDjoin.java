package gg.quartzdev.qspleef.commands;

import gg.quartzdev.qspleef.qSpleef;
import gg.quartzdev.qspleef.util.Language;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.util.StringUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class CMDjoin extends qCMD{
    public CMDjoin(String label, String name, qSpleef plugin) {
        super(label, name, plugin);
//        /spleef join {commandSyntax}
        this.commandSyntax = "<arena>";
        this.permissionGroup = "qspleef.basic";
        this.permissionNode = "qspleef.command.join";
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
        if(!(sender instanceof Player)){
            this.util.sendMessage(sender, Language.ERROR_CONSOLE_JOIN);
            return false;
        }
        return this.plugin.getGameManager().join((Player) sender, args[1]);
    }
}
