package seedu.addressbook.commands;

import seedu.addressbook.data.person.ReadOnlyPerson;

import java.util.Comparator;
import java.util.List;

public class OrderCommand extends Command {
    public static final String COMMAND_WORD = "order";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Displays all persons in the address book as a list with index numbers by alphabetic order.\n"
            + "Example: " + COMMAND_WORD;

    @Override
    public CommandResult execute() {
        Comparator<? super ReadOnlyPerson> comparator = new Comparator<ReadOnlyPerson>() {
            @Override
            public int compare(ReadOnlyPerson o1, ReadOnlyPerson o2) {
                return o1.getName().fullName.compareToIgnoreCase(o2.getName().fullName);
            }
        };

        List<ReadOnlyPerson> allPersons = addressBook.getAllPersons().sortedListView(comparator);


        return new CommandResult(getMessageForPersonListShownSummary(allPersons), allPersons);
    }
}
