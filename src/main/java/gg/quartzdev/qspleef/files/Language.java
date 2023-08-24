package gg.quartzdev.qspleef.files;

public enum Language {
    CHAT_PREFIX("[HELLO]"),

    ERROR_COMMAND_NOT_FOUND("<red>Command not found: <reset>"),
    ERROR_CREATE_ARENAS_FILE("<red>Failed to create 'arenas.yml'"),
    ERROR_SAVE_ARENAS_FILE("<red>Failed to save 'arenas.yml'");

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
