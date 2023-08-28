package gg.quartzdev.qspleef.util;

import gg.quartzdev.qspleef.arena.Arena;
import gg.quartzdev.qspleef.qSpleef;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class CommandManager extends Command {
    qSpleef plugin;
    List<String> aliases = new ArrayList<>();


    public CommandManager(String name, qSpleef plugin){
        super(name);
        this.plugin = plugin;
        aliases.add("spleef");
        super.setPermission("qspleef.player");
        this.setAliases(aliases);
        Bukkit.getCommandMap().register(name, this);
    }

    @Override
    public boolean execute(@NotNull CommandSender sender, @NotNull String s, @NotNull String[] args) {
        if(!this.hasPermission(sender)){
            Util.sendMessage(sender, Language.ERROR_NO_PERMISSION);
            return false;
        }
        if(args.length == 0){
            return true;
        }
        if(args.length == 1){
            return true;
        }
        return false;
    }

    @Override
    public List<String> tabComplete(CommandSender sender, String alias, String[] args) throws IllegalArgumentException {
        List<String> temp = new ArrayList<String>();
        return temp;
    }

    public void disable(){
        this.unregister(Bukkit.getCommandMap());
    }

    public boolean hasPermission(CommandSender sender){
//        If sender is console
        if(!(sender instanceof Player)) return true;
//        If player has permission
        if(sender.hasPermission("qspleef.player")) return true;
        if(sender.hasPermission("qspleef.mod")) return true;
        if(sender.hasPermission("qspleef.admin")) return true;
//        If player is op
        if(sender.isOp()) return true;
//        Else
        return false;
    }
}
