package gg.quartzdev.qspleef.util;

import gg.quartzdev.qspleef.files.Language;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.Bukkit;

public class Logger {

    public static String PREFIX = "<gray>[<light_purple>q<aqua>Spleef<gray>]<reset>";

    /**
     * Logs a general message
     * @param msg
     */
    public static void log(String msg){
        MiniMessage mm = MiniMessage.miniMessage();
        Component parse = mm.deserialize(PREFIX + " " + msg);
        Bukkit.getConsoleSender().sendMessage(parse);
    }

    public static void log(Language msg){
        log(msg.getMessage());
    }

    /**
     * Logs a warning
     * @param msg
     */
    public static void warning(String msg){
        log("<yellow>" + msg);
    }

    /**
     * Logs error
     * @param msg
     */
    public static void error(String msg){
        log("<red>" + msg);
//        TODO: log errors to a file
    }

    public static void error(Language langMsg){
        error(langMsg.getMessage());
    }

}
