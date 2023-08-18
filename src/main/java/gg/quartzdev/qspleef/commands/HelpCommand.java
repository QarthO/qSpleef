package gg.quartzdev.qspleef.commands;

import gg.quartzdev.qspleef.util.Util;
import org.bukkit.command.CommandSender;

public class HelpCommand implements Command {

    String permission = "spleef.command";
    String usage = Util.PREFIX + " <red>Syntax: /spleef ...";

    @Override
    public void run(CommandSender sender, String[] args) {
        Util.sendMessage();
    }
}
