package gg.quartzdev.qspleef.commands;

import gg.quartzdev.qspleef.util.Util;
import org.bukkit.command.CommandSender;
import org.bukkit.permissions.Permission;

public interface Command {
    String permission = "spleef.command";
    String usage = Util.PREFIX + " <red>Syntax: /spleef ...";
    public void run(CommandSender sender, String[] args);
}
