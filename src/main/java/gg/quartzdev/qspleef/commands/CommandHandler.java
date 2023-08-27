package gg.quartzdev.qspleef.commands;

import gg.quartzdev.qspleef.util.Util;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Player;
import org.bukkit.util.StringUtil;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.*;

public class CommandHandler implements TabExecutor {

    private static CommandHandler instance;

    private final String name = "qspleef";
    private final String description = "";
    private final String usageMessage = "/spleef <command>";
    private List<String> aliases = new ArrayList<String>(){{ add("spleef");}};



//    private Command baseCmd = new Command(name, description, usageMessage, aliases);

    public CommandHandler(){
        Bukkit.getCommandMap().register("qspleef", new qCommand("qspleef", description, usageMessage, aliases));
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
    public @Nullable List<String> onTabComplete(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        List<String> completions = new ArrayList<>();
        List<String> commands = new ArrayList<>();

        commands.add("test");
        commands.add("nether");

        if(command.getLabel().equalsIgnoreCase("qspleef")){
            Util.sendMessage(commandSender, "qspleef command");
        }
        if(command.getLabel().equalsIgnoreCase("spleef")){
            Util.sendMessage(commandSender, "spleef command");
        }

//        if(!label.equalsIgnoreCase("spleef")) return null;

        String[] cmdsNeedArenaList = {"join", "getinfo", "set", "spec", "spectate", "reset", "delete"};
        List<String> test = Arrays.asList(cmdsNeedArenaList);

        if(args.length == 2) {
            if(test.contains(args[0])) {
//                am.getArenaList().forEach((n) -> commands.add(n));
                StringUtil.copyPartialMatches(args[1], commands, completions);
            }
        }

        Collections.sort(completions);
        return completions;
    }
}
