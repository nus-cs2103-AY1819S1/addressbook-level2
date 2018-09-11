package seedu.addressbook.commands;

public class TotalCommand extends Command {

    public static final String COMMAND_WORD = "total";
    public static final String MESSAGE_USAGE = "Returns the total number of entries in the address book.\n"
            + "Example: " + COMMAND_WORD;
    public static final String MESSAGE_SINGULAR = " entry found!";
    public static final String MESSAGE_PLURAL = " entries found!";

    @Override
    public CommandResult execute() {
        int size = addressBook.getSize();
        return getCommandResult(size);
    }

    private CommandResult getCommandResult(int size) {
        if (size == 1) {
            return new CommandResult(size + MESSAGE_SINGULAR);
        } else {
            return new CommandResult(size + MESSAGE_PLURAL);
        }
    }
}
