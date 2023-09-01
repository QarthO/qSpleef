package gg.quartzdev.qspleef.util;

import javax.annotation.meta.When;

public enum Language {
    CHAT_PREFIX("<gray>[<red>q<aqua>Spleef<gray>]"),

    INFO_PLUGIN("<prefix> <green>This server is running version <gray><version>"),

    SIGN_JOIN_LINE_1("<chat-prefix>"),
    SIGN_JOIN_LINE_2("<arena>"),
    SIGN_JOIN_LINE_3("<info>"),
    SIGN_JOIN_LINE_4("<status>"),
    SIGN_LEAVE_LINE_1("<chat-prefix>"),
    SIGN_LEAVE_LINE_2("<arena>"),
    SIGN_LEAVE_LINE_3("<red>Click to leave"),
    SIGN_LEAVE_LINE_4("<status>"),
    SIGN_SPECTATE_LINE_1("<chat-prefix>"),
    SIGN_SPECTATE_LINE_2("<arena>"),
    SIGN_SPECTATE_LINE_3("<info>"),
    SIGN_SPECTATE_LINE_4("<status>"),

//# locked      | When an arena is locked by an admin using '/spleef lock <arena>'
//# ready       | When an arena is ready to be joined
//# starting    | When an arena has started it's countdown to begin playing
//# playing     | When a game is currently active
//# resetting   | After a game is finished, and the floor is being reset


//    Arena signs placeholders
//    - Info
    SIGN_PLACEHOLDER_INFO_LOCKED(" "),
    SIGN_PLACEHOLDER_INFO_READY("<green>Click to join<green>"),
    SIGN_PLACEHOLDER_INFO_STARTING("<green>Click to join</green>"),
    SIGN_PLACEHOLDER_INFO_PLAYING("<green>Click to spectate</green>"),
    SIGN_PLACEHOLDER_INFO_RESETTING(" "),

//    - Status
    SIGN_PLACEHOLDER_STATUS_LOCKED("<dark_red>LOCKED</dark_red>"),
    SIGN_PLACEHOLDER_STATUS_READY("<min-players>/<max-players>"),
    SIGN_PLACEHOLDER_STATUS_STARTING(""),
    SIGN_PLACEHOLDER_STATUS_PLAYING(""),
    SIGN_PLACEHOLDER_STATUS_RESETTING(""),

    ERROR_COMMAND_NOT_FOUND("<prefix> <red>Command not found: <command>"),
    ERROR_COMMAND_SYNTAX("<prefix> <red>Syntax: <yellow><syntax>"),
    ERROR_CREATE_ARENAS_FILE("<prefix> <red>Failed to create 'arenas.yml'"),
    ERROR_SAVE_ARENAS_FILE("<prefix> <red>Failed to save 'arenas.yml'"),

    ERROR_PLACEHOLDER_ARENA("<red><placeholder-arena-error></red>"),
    ERROR_NO_PERMISSION("<prefix> <red>Insufficient permissions"),
    ERROR_ARENA_NOT_FOUND("<prefix> <red>Arena <aqua><arena></aqua> not found</red>"),
    ERROR_JOIN_GAME("<prefix> <red>You're already in a game"),
    ERROR_LEAVE_GAME("<prefix> <red>You're not in a game");


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
