package gg.quartzdev.qspleef.util;

import gg.quartzdev.qspleef.commands.exceptions.CommandNotFoundException;
import gg.quartzdev.qspleef.commands.qCMD;
import gg.quartzdev.qspleef.qSpleef;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.util.StringUtil;
import org.jetbrains.annotations.NotNull;
import org.reflections.Reflections;

import java.util.*;

public class CommandManager extends Command {
    final String commandPackagePrefix = qCMD.class.getPackage().getName() + ".CMD";
    qSpleef plugin;
    List<String> aliases = new ArrayList<>();
    Util util;
    List<String> commandsList;

    public CommandManager(String name, qSpleef plugin){
        super(name);
        this.plugin = plugin;
        this.util = plugin.getUtil();
        aliases.add("spleef");
        super.setPermission("qspleef.player");
        this.setAliases(aliases);
        Bukkit.getCommandMap().register(name, this);

        Bukkit.getAsyncScheduler().runNow(plugin, scheduledTask -> {
            this.commandsList = this.getCommandList();
        });
    }

    public boolean loadCommand(CommandSender sender, String labelOrAlias, String[] args) throws CommandNotFoundException {
        return this.loadCommandClass(labelOrAlias, args[0]).run(sender, args);
    }

    public List<String> loadTabs(CommandSender sender, String labelOrAlias, String[] args) throws CommandNotFoundException {
        return this.loadCommandClass(labelOrAlias, args[0]).getTabCompletes(sender, args);
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
    public @NotNull List<String> tabComplete(@NotNull CommandSender sender, @NotNull String labelOrAlias, String[] args) throws IllegalArgumentException {
        List<String> completions = new ArrayList<>();

        if(args.length == 0) return completions;

        if(args.length == 1){
            StringUtil.copyPartialMatches(args[0], commandsList, completions);
        }

        try {
            completions = this.loadTabs(sender, labelOrAlias, args);

        } catch (CommandNotFoundException e){
            return completions;
        }
        Collections.sort(completions);
        return completions;
    }

    public void disable(){
//        this.unregister(Bukkit.getCommandMap());
    }

    public qCMD loadCommandClass(String labelOrAlias, String name) throws CommandNotFoundException {
        Class<?> loaded;
        qCMD qcommand;
        try {
            loaded = qSpleef.class.getClassLoader().loadClass(commandPackagePrefix + name.toLowerCase(Locale.ROOT));
            qcommand = (qCMD) loaded.getConstructor(String.class, String.class, qSpleef.class).newInstance(labelOrAlias, name.toLowerCase(Locale.ROOT), this.plugin);
        } catch (Exception e) {
            throw(new CommandNotFoundException(name));
        }
        return qcommand;
    }

    public List<String> getCommandList(){
        List<String> commandList = new ArrayList<>();

        Reflections reflections = new Reflections(qCMD.class);

        Set<Class<? extends qCMD>> allClasses =
                reflections.getSubTypesOf(qCMD.class);

//        Should never be empty, but just incase
        if(allClasses.isEmpty()){
            Logger.error("No commands found - Report this on the github");
            return commandList;
        }

//
        for(Class<? extends qCMD> clazz : allClasses)
            commandList.add(clazz.getName().replace(commandPackagePrefix, ""));

        return commandList;
    }

}
