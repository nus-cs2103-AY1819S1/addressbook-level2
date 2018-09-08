package seedu.addressbook.commands;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import seedu.addressbook.data.person.ReadOnlyPerson;

public class SortCommand extends Command {

    public static final String COMMAND_WORD = "sort";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Displays all persons in the address book as a list, according to their names in"
            + " alphabetical order, with index numbers.\n"
            + "Example: " + COMMAND_WORD;


    @Override
    public CommandResult execute() {
        List<ReadOnlyPerson> allPersons = addressBook.getAllPersons().immutableListView();
        ArrayList<ReadOnlyPerson> sortAllPersons = new ArrayList<>((allPersons));
        sortAllPersonsInAlphabeticalOrder(sortAllPersons);
        return new CommandResult(getMessageForPersonListShownSummary(sortAllPersons), sortAllPersons);
    }

    private static void sortAllPersonsInAlphabeticalOrder(List<ReadOnlyPerson> allPersons) {
        Collections.sort(allPersons, new Comparator<>() {
            @Override
            public int compare(ReadOnlyPerson o1, ReadOnlyPerson o2) {
                return String.CASE_INSENSITIVE_ORDER.
                        compare(o1.getName().fullName,
                                o2.getName().fullName);
            }
        });
    }

}
