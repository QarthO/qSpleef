package gg.quartzdev.qspleef.util;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import net.kyori.adventure.text.minimessage.tag.resolver.Placeholder;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Util {

    /**
     * Sends a message to the player
     * Supports MiniMessage format
     * @param player The player to send the message to
     * @param msg The message to send to the player
     */
    public static void sendMessage(Player player, String msg){
        MiniMessage mm = MiniMessage.miniMessage();
        player.sendMessage(parse(msg));
    }

    public static void sendMessage(CommandSender sender, String msg){
        MiniMessage mm = MiniMessage.miniMessage();
        sender.sendMessage(parse(msg));
    }

    public static void sendMessage(Player player, Language msg){
        MiniMessage mm = MiniMessage.miniMessage();
        player.sendMessage(parse(msg.getMessage()));
    }
    public static void sendMessage(CommandSender sender, Language msg){
        MiniMessage mm = MiniMessage.miniMessage();
        sender.sendMessage(parse(msg.getMessage()));
    }

    public static Component parse(String msg){
        MiniMessage mm = MiniMessage.miniMessage();
        Component parse = mm.deserialize(msg,
                Placeholder.parsed("prefix", Language.CHAT_PREFIX.getMessage()),
                Placeholder.parsed("version", Bukkit.getPluginManager().getPlugin("qspleef").getPluginMeta().getVersion())
        );
        return parse;
    }
}
