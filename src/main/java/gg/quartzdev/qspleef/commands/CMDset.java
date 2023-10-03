package gg.quartzdev.qspleef.commands;

import gg.quartzdev.qspleef.qSpleef;
import org.bukkit.command.CommandSender;

import java.util.List;

public class CMDset extends qCMD {
    public CMDset(String label, String name, qSpleef plugin) {
        super(label, name, plugin);
    }

    @Override
    public List<String> getTabCompletes(CommandSender sender, String[] args) {
        return null;
    }

    @Override
    public boolean logic(CommandSender sender, String[] args) {
        return false;
    }
}
