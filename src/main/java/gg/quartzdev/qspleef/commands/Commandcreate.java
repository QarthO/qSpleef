package gg.quartzdev.qspleef.commands;

import gg.quartzdev.qspleef.qSpleef;
import gg.quartzdev.qspleef.util.Language;
import org.bukkit.command.CommandSender;

public class Commandcreate extends qCommand {
    public Commandcreate(String label, String name, qSpleef plugin) {
        super(label, name, plugin);
        this.commandSyntax = "<arena-name>";
        this.permissionNode = "qspleef.admin.create";
    }
    @Override
    public void getTabCompletes(CommandSender sender, String[] args) {

    }

    @Override
    public boolean logic(CommandSender sender, String[] args) {
        this.util.sendMessage(sender,this.name + " logic!");
        return false;
    }
}
