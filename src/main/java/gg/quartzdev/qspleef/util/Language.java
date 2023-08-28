package gg.quartzdev.qspleef.util;

public enum Language {
    CHAT_PREFIX("<gray>[<light_purple>q<aqua>Spleef<gray>]<reset>"),

    INFO_PLUGIN("<prefix> <green>This server is running version <gray><version>"),

    ERROR_COMMAND_NOT_FOUND("<prefix> <red>Command not found: <reset>"),
    ERROR_CREATE_ARENAS_FILE("<prefix> <red>Failed to create 'arenas.yml'"),
    ERROR_SAVE_ARENAS_FILE("<prefix> <red>Failed to save 'arenas.yml'"),
    ERROR_NO_PERMISSION("<prefix> <red>Insufficient permissions");


    private String message;

    Language(String msg){
        this.message = msg;
    }

    @Override
    public String toString(){
        return message;
    }

    public String getMessage(){
        return message;
    }

    public void setValue(String newMsg){
        this.message = newMsg;
    }
}
