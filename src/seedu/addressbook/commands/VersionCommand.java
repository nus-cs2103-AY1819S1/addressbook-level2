package seedu.addressbook.commands;
import seedu.addressbook.Main;

public class VersionCommand extends Command{
    public static final String COMMAND_WORD = "version";
    public static final String MESSAGE_SUCCESS = "Current Version : " + Main.VERSION;

    public CommandResult execute() {
        return new CommandResult(String.format(MESSAGE_SUCCESS));
    }
}
