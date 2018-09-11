package seedu.addressbook.commands;

import seedu.addressbook.data.person.ReadOnlyPerson;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;


/**
 * Sorts list of all persons in the address book to the user.
 */
public class SortCommand extends Command{

    public static final String COMMAND_WORD = "sort";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Displays all in persons in the address book as a sorted list with index numbers.\n"
            + "Example: " + COMMAND_WORD;

    @Override
    public CommandResult execute() {
        List<ReadOnlyPerson> allPersons = addressBook.getAllPersons().immutableListView();
        System.out.println(allPersons);
        allPersons.sort(new Comparator<>() {
            @Override
            public int compare(ReadOnlyPerson o1, ReadOnlyPerson o2) {
                List<String> fullName1 = o1.getName().getWordsInName();
                List<String> fullName2 = o2.getName().getWordsInName();
                String name1 = fullName1.get(0);
                String name2 = fullName2.get(0);

                return name1.compareTo(name2);

            }
        });
        System.out.println(allPersons);



        return new CommandResult(getMessageForPersonListShownSummary(allPersons), allPersons);
    }
}
