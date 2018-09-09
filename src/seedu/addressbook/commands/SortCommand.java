package seedu.addressbook.commands;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import seedu.addressbook.data.person.Person;

public class SortCommand extends Command {

    public static final String COMMAND_WORD = "sort";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Displays all persons in the address book as sorted list.\n"
            + "Example: " + COMMAND_WORD;

    private final String sortMethod;

    public SortCommand(String sortMethod) {
        this.sortMethod = sortMethod.trim();
    }

    private static class ByName implements Comparator<Person> {
        @Override
        public int compare(Person p1, Person p2) {
            return p1.getName().toString().compareTo(p2.getName().toString());
        }
    }

    // TODO - remove sort by phone as it violates privacy
    private static class ByPhone implements Comparator<Person> {
        @Override
        public int compare(Person p1, Person p2) {
            return p1.getPhone().toString().compareTo(p2.getPhone().toString());
        }
    }

    private static void sort(List<Person> allPersons, String sortMethod) {
        switch (sortMethod) {
            case "name":
                Collections.sort(allPersons, new ByName());
                break;
            case "phone":
                Collections.sort(allPersons, new ByPhone());
                break;
        }
    }

    @Override
    public CommandResult execute() {
        List<Person> allPersons = addressBook.getAllPersons().sortableListView();
        sort(allPersons, sortMethod);
        return new CommandResult(getMessageForSortedListShownSummary(allPersons, sortMethod), allPersons);
    }
}
