package gg.quartzdev.qspleef.util;

import gg.quartzdev.qspleef.commands.CommandNotFoundException;
import gg.quartzdev.qspleef.commands.qCommand;
import gg.quartzdev.qspleef.qSpleef;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.util.StringUtil;
import org.jetbrains.annotations.NotNull;
import org.reflections.Reflections;

import java.util.*;

public class CommandManager extends Command {
    final String commandPackagePrefix = qCommand.class.getPackage().getName() + ".Command";
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

        Logger.log("<green>CommandManager#execute: " + Thread.currentThread().getId() + " - " + Thread.currentThread().getName());
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
        Logger.log("Tab completing...");
        Logger.log(Arrays.toString(args));
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
        Logger.log(completions.toString());

        return completions;
    }

    public void disable(){
//        this.unregister(Bukkit.getCommandMap());
    }

    public qCommand loadCommandClass(String labelOrAlias, String name) throws CommandNotFoundException {
        Class<?> loaded;
        qCommand qcommand;
        try {
            loaded = qSpleef.class.getClassLoader().loadClass(commandPackagePrefix + name.toLowerCase(Locale.ROOT));
            qcommand = (qCommand) loaded.getConstructor(String.class, String.class, qSpleef.class).newInstance(labelOrAlias, name.toLowerCase(Locale.ROOT), this.plugin);
        } catch (Exception e) {
            throw(new CommandNotFoundException(name));
        }
        return qcommand;
    }

    public List<String> getCommandList(){
        List<String> commandList = new ArrayList<>();

        Reflections reflections = new Reflections(qCommand.class);

        Set<Class<? extends qCommand>> allClasses =
                reflections.getSubTypesOf(qCommand.class);

//        Should never be empty, but just incase
        if(allClasses.isEmpty()){
            Logger.error("No commands found - Report this on the github");
            return commandList;
        }

//
        for(Class<? extends qCommand> clazz : allClasses)
            commandList.add(clazz.getName().replace(commandPackagePrefix, ""));

        return commandList;
    }

}
