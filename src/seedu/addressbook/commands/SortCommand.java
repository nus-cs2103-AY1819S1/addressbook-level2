package seedu.addressbook.commands;

//import seedu.addressbook.data.person.Person;
//import seedu.addressbook.data.person.UniquePersonList;

//import java.util.Comparator;
//import java.util.List;

/**
 * Sorts all persons in the address book to the user.
 */
public class SortCommand extends Command {

    public static final String COMMAND_WORD = "sort";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Sorts all persons in the address book alphabetically according to their name.\n"
            + "Example: " + COMMAND_WORD;

    public static final String MESSAGE_SUCCESS = "Address book has been sorted!";

    @Override
    public CommandResult execute() {
        // clone
//        UniquePersonList allPersons = addressBook.getAllPersons();
        // sort
//        Comparator<Person> byName = new Comparator.comparing(person -> person.getName());
//        List<Person> sortedAllPersons = new List<>();
        // clear adb
//        addressBook.clear();
        // add 1 by 1
//        for (Person person : sortedAllPersons) {
//            addressBook.addPerson(person);
//        }
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
