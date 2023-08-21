package gg.quartzdev.qspleef.commands;

import gg.quartzdev.qspleef.util.Util;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class CommandHandler implements TabExecutor {

    private static CommandHandler instance;

    private final String name = "qspleef";
    private final String description = "";
    private final String usageMessage = "/spleef <command>";
    private List<String> aliases = new ArrayList<String>(){{ add("spleef");}};



//    private Command baseCmd = new Command(name, description, usageMessage, aliases);

    public CommandHandler(){
        Bukkit.getCommandMap().register("qspleef", new qCommand("qspleef", "", "/spleef <command>", new List<String>()));
    }

    public void load(CommandSender sender, String[] args) throws CommandNotFoundException {
//        Bukkit.getCommandMap().register(args[0])
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {

        String cmdName = command.getName().toLowerCase(Locale.ROOT);

        if(!cmdName.equals("spleef") && !cmdName.equals("qspleef")) return false;

        if(!(sender instanceof Player)) {
//            can only run command if sender is a player (doesnt work in console)
            Util.sendMessage(sender, "Messages");
        }

        if(args.length == 0){
//            show help menu
            Util.sendMessage(sender, "");
        }

        return true;
    }

    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        return null;
    }
}
