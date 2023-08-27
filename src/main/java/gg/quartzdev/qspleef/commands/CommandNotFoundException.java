package gg.quartzdev.qspleef.commands;

import gg.quartzdev.qspleef.util.Language;

public class CommandNotFoundException extends Exception {

    private static final long serialVersionUID = 1L;

    public CommandNotFoundException(final String cmd) {
        super(Language.ERROR_COMMAND_NOT_FOUND.getMessage());
    }

    public CommandNotFoundException(final String cmd, final Throwable throwable) {
        super(Language.ERROR_COMMAND_NOT_FOUND.getMessage(), throwable);
    }
}
