package seedu.addressbook.commands;

/**
 * Shows user the total number of persons in the address book.
 */
public class CountCommand extends Command {
    public static final String COMMAND_WORD = "count";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Displays the total number of persons in the address book.\n"
            + "Example: " + COMMAND_WORD;

    public static final String MESSAGE_COUNT_PERSONS = "The total number of contacts in your address book is %1$d!";

    @Override
    public CommandResult execute() {
        int totalCount = addressBook.getAllPersons().immutableListView().size();
        return new CommandResult(String.format(MESSAGE_COUNT_PERSONS, totalCount));
    }
}