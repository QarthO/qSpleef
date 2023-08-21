package gg.quartzdev.qspleef;

public enum Language {
    CHAT_PREFIX("[HELLO]"),

    ERROR_COMMAND_NOT_FOUND("<red>Command not found: <reset>");

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
