package seedu.addressbook.commands;

import seedu.addressbook.data.person.Person;
import seedu.addressbook.data.person.ReadOnlyPerson;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;


/**
 * Lists all persons in the address book to the user, by alphabetical order.
 */
public class ListByOrderCommand extends Command {

    public static final String COMMAND_WORD = "listbyorder";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Displays all persons in the address book sorted alphabetically, as a list with index numbers.\n"
            + "Example: " + COMMAND_WORD;


    @Override
    public CommandResult execute() {
        List<ReadOnlyPerson> allPersons = addressBook.getAllPersons().immutableListView();

        ArrayList<ReadOnlyPerson> copiedListOfPpl = new ArrayList<>();
        copiedListOfPpl.addAll(allPersons);
        copiedListOfPpl.sort(new Comparator<ReadOnlyPerson>() {
            @Override
            public int compare(ReadOnlyPerson o1, ReadOnlyPerson o2) {
                return o1.getName().fullName.compareToIgnoreCase(o2.getName().fullName);
            }
        });

        return new CommandResult(getMessageForPersonListShownSummary(copiedListOfPpl), copiedListOfPpl);
    }
}
