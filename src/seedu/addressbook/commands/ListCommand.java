package seedu.addressbook.commands;

import seedu.addressbook.data.person.Person;
import seedu.addressbook.data.person.ReadOnlyPerson;
import seedu.addressbook.data.person.UniquePersonList;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;


/**
 * Lists all persons in the address book, sorted by name, to the user.
 * If specified, persons are sorted by sort key.
 */
public class ListCommand extends Command {

    public static final String COMMAND_WORD = "list";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Displays all persons in the address book as a list with index numbers.\n"
            + "Example: " + COMMAND_WORD;

    private final String sortKey;

    public ListCommand() {
        this.sortKey = "Name";
    }

    public ListCommand(String sortKey) {
        this.sortKey = sortKey;
    }

    @Override
    public CommandResult execute() {
        List<ReadOnlyPerson> allPersons = sortPersonsBy(this.sortKey);
        return new CommandResult(getMessageForPersonListShownSummary(allPersons), allPersons);
    }

    /**
     * Sorts the persons list in AddressBook by the given key
     *
     * @param sortKey
     * @return list of ReadOnlyPersons in sorted order
     */
    public List<ReadOnlyPerson> sortPersonsBy(String sortKey) {
        List<ReadOnlyPerson> persons = new ArrayList<>();
        UniquePersonList allPersons = addressBook.getAllPersons();
        for(ReadOnlyPerson person : allPersons) {
            persons.add(person);
        }
        persons.sort(new Comparator<ReadOnlyPerson>() {
            @Override
            public int compare(ReadOnlyPerson person1, ReadOnlyPerson person2) {
                String person1FieldValue = person1.getFieldToFieldValueMap().get(sortKey);
                String person2FieldValue = person2.getFieldToFieldValueMap().get(sortKey);
                return person1FieldValue.compareTo(person2FieldValue);
            }
        });

        return persons;
    }
}
