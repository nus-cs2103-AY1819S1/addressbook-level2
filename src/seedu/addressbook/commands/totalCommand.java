package seedu.addressbook.commands;

/**
 * display the total number of persons stored in the address book
 */
public class TotalCommand extends Command {

    public static final String COMMAND_WORD = "total";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": display the total number of persons stored in the address book.\n"
            + "Example: " + COMMAND_WORD;

    public static final String MESSAGE_TOTAL_IN_ADDRESSBOOK = "There is/are %1$s person(s) in the address book";

    @Override
    public CommandResult execute() {
        return new CommandResult(String.format(MESSAGE_TOTAL_IN_ADDRESSBOOK, addressBook.getTotal()));
    }

}
