package seedu.addressbook.commands;
/**
 * Return the size of the address book
 */
public class SizeCommand extends Command {
    public static final String COMMAND_WORD = "size";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Output the size of the address "
            + "book "
            + "Example: " + COMMAND_WORD;
    public static final String MESSAGE_SUCCESS = "You have %d person(s) in your address book.";
    @Override
    public CommandResult execute() {
        // Get the size of the addressbook
        int size = addressBook.size();
        // Return the command result
        return new CommandResult(String.format(MESSAGE_SUCCESS, size));
    }
}