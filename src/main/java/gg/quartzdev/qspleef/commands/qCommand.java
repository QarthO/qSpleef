package gg.quartzdev.qspleef.commands;

import gg.quartzdev.qspleef.qSpleef;
import gg.quartzdev.qspleef.util.Language;
import gg.quartzdev.qspleef.util.Util;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

public abstract class qCommand {
    String label;
    String name;
    String permissionGroup;
    String permissionNode;
    String commandSyntax;
    qSpleef plugin;
    Util util;

    public qCommand(String label, String name, qSpleef plugin){
        this.label = label;
        this.name = name;
        this.plugin = plugin;
        this.util = plugin.getUtil();
    }
    public boolean run(CommandSender sender, String[] args){
        //        checks permission
        if(!this.hasPermission(sender)){
            util.sendMessage(sender, Language.ERROR_NO_PERMISSION);
            return false;
        }
//        checks syntax
        if(!this.checkSyntax(sender, args)) return false;
//        runs command
        return logic(sender, args);
    }
    public abstract void getTabCompletes(CommandSender sender, String[] args);
    public abstract boolean logic(CommandSender sender, String[] args);
    public boolean hasPermission(CommandSender sender){
//        If sender is console
        if(!(sender instanceof Player)) return true;
//        If sender is op
        if(sender.isOp()) return true;
//        If sender has permission group
        if(sender.hasPermission(this.permissionGroup)) return true;
//        If sender has permission node
        if(sender.hasPermission(this.permissionNode)) return true;
//        else
        return false;
    }
    public boolean checkSyntax(CommandSender sender, String[] args){
        if(args.length != commandSyntax.split(" ").length-2){
            String syntax = "/" + this.label + " " + this.name + " " + this.commandSyntax;
            this.util.sendSyntax(sender, syntax);
            return false;
        }
        return true;
    }
}
