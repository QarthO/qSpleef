package gg.quartzdev.qspleef.commands;

import gg.quartzdev.qspleef.util.Util;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.Locale;
import java.util.concurrent.ConcurrentHashMap;

public class CommandHandler implements CommandExecutor {

    private static CommandHandler instance;

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
}
