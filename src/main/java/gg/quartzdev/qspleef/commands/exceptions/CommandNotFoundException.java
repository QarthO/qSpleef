package gg.quartzdev.qspleef.commands.exceptions;

import gg.quartzdev.qspleef.util.Language;

public class CommandNotFoundException extends Exception {

    private static final long serialVersionUID = 1L;

    public CommandNotFoundException(String commandName) {
        super(Language.ERROR_COMMAND_NOT_FOUND.getMessage());
    }

    public CommandNotFoundException(final Throwable throwable) {
        super(Language.ERROR_COMMAND_NOT_FOUND.getMessage(), throwable);
    }
}
