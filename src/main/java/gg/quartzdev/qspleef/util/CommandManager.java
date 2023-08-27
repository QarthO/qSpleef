package gg.quartzdev.qspleef.util;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class CommandManager extends Command {
    Plugin plugin;
    List<String> aliases = new ArrayList<>();


    public CommandManager(String name, Plugin plugin){
        super(name);
        this.plugin = plugin;
        aliases.add("spleef");
        this.setAliases(aliases);
        Bukkit.getCommandMap().register(name, this);
    }

    @Override
    public boolean execute(@NotNull CommandSender commandSender, @NotNull String s, @NotNull String[] args) {


        if(args.length == 0){
            Logger.log(s);

            return true;
        }

        if(args.length == 1){
            Logger.log(s);
            return true;
        }

        return false;
    }

    @Override
    public List<String> tabComplete(CommandSender sender, String alias, String[] args) throws IllegalArgumentException {
        List<String> temp = new ArrayList<String>();
        temp.add("yo");
        temp.add("hi");


        return temp;
    }
}
