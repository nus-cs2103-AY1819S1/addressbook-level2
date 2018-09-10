package seedu.addressbook.commands;
import seedu.addressbook.commands.Command;
import seedu.addressbook.storage.PrevHistory;
import java.util.LinkedList;

/*
* Shows up to 5 previously used commands.
*/


public class HistoryCommand extends Command {

    private PrevHistory commandHistory;

    public HistoryCommand(PrevHistory history) {
        commandHistory = history;
    }

    public static final String COMMAND_WORD = "history";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Shows up to 10 previously used commands.\n"
            + "Example: " + COMMAND_WORD;

    @Override
    public CommandResult execute() {
        return new CommandResult(commandHistory.getHistory());
    }
}
