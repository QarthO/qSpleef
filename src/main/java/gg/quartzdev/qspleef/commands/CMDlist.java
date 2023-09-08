package gg.quartzdev.qspleef.commands;

import gg.quartzdev.qspleef.qSpleef;
import org.bukkit.command.CommandSender;

import java.util.ArrayList;
import java.util.List;

public class CMDlist extends qCMD{
    public CMDlist(String label, String name, qSpleef plugin) {
        super(label, name, plugin);
        this.commandSyntax = "";
        this.permissionGroup = "qspleef.basic";
        this.permissionNode = "qspleef.command.list";
    }

    @Override
    public List<String> getTabCompletes(CommandSender sender, String[] args) {
        List<String> rawCompletions = new ArrayList<>();
        return rawCompletions;
    }

    @Override
    public boolean logic(CommandSender sender, String[] args) {
        this.util.sendMessage(sender, this.plugin.getGameManager().listAvailableArenas());
        return true;
    }
}
