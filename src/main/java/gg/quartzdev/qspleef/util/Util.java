package gg.quartzdev.qspleef.util;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Util {
    public static String PREFIX = "<gray>[<light_purple>q<aqua>Spleef<gray>]<reset>";

    /**
     * Sends a message to the player
     * Supports MiniMessage format
     * @param player The player to send the message to
     * @param msg The message to send to the player
     */
    public static void sendMessage(Player player, String msg){
        MiniMessage mm = MiniMessage.miniMessage();
        Component parse = mm.deserialize(PREFIX + " " + msg);
        player.sendMessage(parse);
    }

    public static void sendMessage(CommandSender sender, String msg){
        MiniMessage mm = MiniMessage.miniMessage();
        Component parse = mm.deserialize(PREFIX + " " + msg);
        sender.sendMessage(parse);
    }

    /**
     * Sends a message to the player
     * Supports MiniMessage format
     * @param player The player to send the message to
     * @param msg The message to send to the player
     * @param prefix Whether the plugin prefix should be sent
     */
    public static void sendMessage(Player player, String msg, boolean prefix){
        MiniMessage mm = MiniMessage.miniMessage();
        Component parse = mm.deserialize((prefix ? PREFIX : "") + " " + msg);
        player.sendMessage(parse);
    }
}
