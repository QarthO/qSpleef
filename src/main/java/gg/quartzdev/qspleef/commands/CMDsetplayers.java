package gg.quartzdev.qspleef.commands;

import gg.quartzdev.qspleef.qSpleef;
import org.bukkit.command.CommandSender;

import java.util.ArrayList;
import java.util.List;

public class CMDsetplayers extends qCMD{
    public CMDsetplayers(String label, String name, qSpleef plugin) {
        super(label, name, plugin);
        this.commandSyntax = "<arena> <min> <max>";
        this.permissionGroup = "qspleef.admin";
        this.permissionNode = "qspleef.command.setplayers";
    }

    @Override
    public List<String> getTabCompletes(CommandSender sender, String[] args) {
        List<String> rawCompletions = new ArrayList<>();

        if(args.length < 2) return rawCompletions;

        if(args.length == 2)
            rawCompletions.add("<arena>");
        if(args.length == 3)
            rawCompletions.add("<min>");
        if(args.length == 4)
            rawCompletions.add("<max>");

        return rawCompletions;
    }

    @Override
    public boolean logic(CommandSender sender, String[] args) {

        if(args.length != 4) return false;

        try {
            int minPlayers = Integer.parseInt(args[2]);
            int maxPlayers = Integer.parseInt(args[3]);
        } catch(NumberFormatException e){
            util.sendMessage(sender, "<red>min/max must be a valid number");
        }




        return false;
    }
}
