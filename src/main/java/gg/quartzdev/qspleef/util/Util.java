package gg.quartzdev.qspleef.util;

import gg.quartzdev.qspleef.qSpleef;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import net.kyori.adventure.text.minimessage.tag.resolver.Placeholder;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * Utility class for sending messages to a Player or Console
 */
public class Util {
    qSpleef plugin;
    public Util(qSpleef plugin){
        this.plugin = plugin;
    }

    /**
     * Sends a message to the player
     * Supports MiniMessage format
     * @param player The player to send the message to
     * @param msg The message to send to the player
     */
    public void sendMessage(Player player, String msg){
        player.sendMessage(parse(player, msg));
    }

    public void sendMessage(Player player, Language msg){
        this.sendMessage(player, msg.getMessage());
    }

    public void sendMessage(CommandSender sender, String msg){
        sender.sendMessage(parse(sender, msg));
    }

    public void sendMessage(CommandSender sender, Language msg){
        this.sendMessage(sender, msg.getMessage());
    }

    public void sendSyntax(CommandSender sender, String syntax){
        MiniMessage mm = MiniMessage.miniMessage();
        Component parse =  mm.deserialize(Language.ERROR_COMMAND_SYNTAX.getMessage(),
            Placeholder.parsed("prefix", Language.CHAT_PREFIX.getMessage()),
            Placeholder.parsed("syntax", syntax)
        );
        sender.sendMessage(parse);
    }

    private Component parse(CommandSender sender, String msg){
        MiniMessage mm = MiniMessage.miniMessage();

        if(sender instanceof Player)
            return mm.deserialize(msg,
                    Placeholder.parsed("prefix", Language.CHAT_PREFIX.getMessage()),
                    Placeholder.parsed("version", this.plugin.getVersion()),
                    Placeholder.parsed("arena", this.getArenaName((Player) sender)),
                    Placeholder.parsed("player", sender.getName())
            );
        return mm.deserialize(msg,
                Placeholder.parsed("prefix", Logger.PREFIX),
                Placeholder.parsed("version", this.plugin.getVersion())
        );
    }

    private String getArenaName(Player player){
//        Checks to make sure the player is in a game
        if(!plugin.getGameManager().isPlaying(player)) return Language.ERROR_PLACEHOLDER_ARENA.getMessage();
//        Returns the name of the arena the player is in
        return plugin.getGameManager().getSpleefPlayer(player).getArena().getName();

    }
}
