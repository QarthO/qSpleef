package gg.quartzdev.qspleef.commands;

import gg.quartzdev.qspleef.game.GameManager;
import gg.quartzdev.qspleef.game.arena.Arena;
import gg.quartzdev.qspleef.qSpleef;
import gg.quartzdev.qspleef.util.Language;
import org.bukkit.command.CommandSender;

import java.util.ArrayList;
import java.util.List;

public class CMDdelete extends qCMD {
    public CMDdelete(String label, String name, qSpleef plugin) {
        super(label, name, plugin);
        this.commandSyntax = "<arena-name>";
        this.permissionNode = "qspleef.admin.delete";
    }
    @Override
    public List<String> getTabCompletes(CommandSender sender, String[] args) {
        List<String> rawCompletions = new ArrayList<>();

        if(args.length < 2) return rawCompletions;

        if(args.length == 2)
            rawCompletions.add("<arena-name>");

        return rawCompletions;
    }

    @Override
    public boolean logic(CommandSender sender, String[] args) {
        String arenaName = args[1];
        Arena newArena = new Arena(arenaName);
        GameManager gm = this.plugin.getGameManager();
        if(gm.getGame(args[1]) == null){
            util.sendMessage(sender, Language.ERROR_ARENA_NOT_FOUND.setArena(args[1]));
        }

//        this.plugin.getStorage().readArenas();
        return true;
    }
}