package seedu.addressbook.commands;

import seedu.addressbook.data.person.ReadOnlyPerson;
import seedu.addressbook.common.Messages;

import java.util.List;


/**
 * Lists all persons in the address book to the user.
 */
public class ListCommand extends Command {

    public static final String COMMAND_WORD = "list";
    public static final int PAGE_CONTAIN = 3;

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Displays all persons or persons in one page in the address book as a list with index numbers.\n"
            + "Example: " + COMMAND_WORD + "\n"
            + "Example: " + COMMAND_WORD + " 1";

    public ListCommand(int targetPage) { super(targetPage); }
    public ListCommand() {}

    @Override
    public CommandResult execute() {
        List<ReadOnlyPerson> allPersons = addressBook.getAllPersons().immutableListView();
        final int targetPage = getTargetIndex();
        if(targetPage > 0) {
            List<ReadOnlyPerson> displayedPersons = personOfPage(targetPage, allPersons);
            return new CommandResult(getMessageForPersonListShownSummary(displayedPersons), displayedPersons);
        } else {
            return new CommandResult(getMessageForPersonListShownSummary(allPersons), allPersons);
        }
    }

    public List<ReadOnlyPerson> personOfPage(int pageNumber, List<ReadOnlyPerson> allPersons) {
        int size = allPersons.size();
        if (pageNumber <= size / PAGE_CONTAIN) {
            int lastIndex = pageNumber * PAGE_CONTAIN > size ? size : pageNumber * PAGE_CONTAIN;
            return allPersons.subList((pageNumber - 1) * PAGE_CONTAIN, lastIndex);
        } else {
            return allPersons;
        }
    }
}
