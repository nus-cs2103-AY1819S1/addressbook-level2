package seedu.addressbook.commands;

        import seedu.addressbook.data.person.ReadOnlyPerson;

        import java.util.Collections;
        import java.util.List;


/**
 * Lists all persons in the address book to the user in reverse.
 */
public class ListReverseCommand extends Command {

    public static final String COMMAND_WORD = "listr";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Displays all persons in the address book as a list with index numbers in reverse.\n"
            + "Example: " + COMMAND_WORD;


    @Override
    public CommandResult execute() {
        List<ReadOnlyPerson> allPersons = addressBook.getAllPersons().immutableListView();
        Collections.reverse(allPersons);
        return new CommandResult(getMessageForPersonListShownSummary(allPersons), allPersons);
    }
}
