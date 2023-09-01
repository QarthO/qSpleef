package gg.quartzdev.qspleef.util;

import gg.quartzdev.qspleef.commands.CommandNotFoundException;
import gg.quartzdev.qspleef.commands.qCommand;
import gg.quartzdev.qspleef.game.arena.Arena;
import gg.quartzdev.qspleef.game.arena.ArenaSign;
import gg.quartzdev.qspleef.game.arena.SignType;
import gg.quartzdev.qspleef.qSpleef;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.sign.Side;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class CommandManager extends Command {
    qSpleef plugin;
    List<String> aliases = new ArrayList<>();

    Util util;


    public CommandManager(String name, qSpleef plugin){
        super(name);
        this.plugin = plugin;
        this.util = plugin.getUtil();
        aliases.add("spleef");
        super.setPermission("qspleef.player");
        this.setAliases(aliases);
        Bukkit.getCommandMap().register(name, this);
    }

    public boolean loadCommand(CommandSender sender, String labelOrAlias, String[] args) throws CommandNotFoundException {
        return this.loadCommandClass(labelOrAlias, args[0]).run(sender, args);
    }

    public void loadTabs(CommandSender sender, String labelOrAlias, String[] args) throws CommandNotFoundException {
        this.loadCommandClass(labelOrAlias, args[0]).getTabCompletes(sender, args);
    }

    @Override
    public boolean execute(@NotNull CommandSender sender, @NotNull String labelOrAlias, @NotNull String[] args) {
        if(args.length == 0){
            util.sendMessage(sender, Language.INFO_PLUGIN);
            return true;
        }
        try {
            return this.loadCommand(sender, labelOrAlias, args);
        } catch(CommandNotFoundException e){
            util.sendMessage(sender, Language.ERROR_COMMAND_NOT_FOUND);
        }
        return false;
    }

    @Override
    public @NotNull List<String> tabComplete(@NotNull CommandSender sender, @NotNull String alias, String[] args) throws IllegalArgumentException {
        List<String> temp = new ArrayList<>();

        return temp;
    }

    public void disable(){
        this.unregister(Bukkit.getCommandMap());
    }

    public qCommand loadCommandClass(String labelOrAlias, String name) throws CommandNotFoundException {
        Class<?> loaded;
        qCommand qcommand;
        try {
            loaded = qSpleef.class.getClassLoader().loadClass("gg.quartzdev.qspleef.commands.Command" + name.toLowerCase(Locale.ROOT));
            qcommand = (qCommand) loaded.getConstructor(String.class, String.class, qSpleef.class).newInstance(labelOrAlias, name.toLowerCase(Locale.ROOT), this.plugin);
        } catch (Exception e) {
            throw(new CommandNotFoundException(name));
        }
        return qcommand;
    }
}
