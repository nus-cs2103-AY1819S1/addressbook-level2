package seedu.addressbook.commands;

import seedu.addressbook.data.person.Person;
import seedu.addressbook.data.person.UniquePersonList;

import java.util.*;

public class SortCommand extends Command {
    public static final String COMMAND_WORD = "sort";

    public static final String MESSAGE_SUCCESS = "Successfully sorted.";
    public static final String MESSAGE_EMPTY_BOOK = "Nothing to sort.";

    @Override
    public CommandResult execute() {
        PriorityQueue<Person> arrangedPersons = new PriorityQueue<>(new Comparator<Person>() {
            public int compare(Person p1, Person p2) {
                return p2.getName().fullName.compareTo(p1.getName().fullName);
            }
        });
        try {
            for (Person person : addressBook.getAllPersons()) {
                arrangedPersons.add(person);
            }
            addressBook.clear();
            while (!arrangedPersons.isEmpty()) {
                addressBook.addPerson(arrangedPersons.poll());
            }
            return new CommandResult(MESSAGE_SUCCESS);
        } catch (NullPointerException err) {
            return new CommandResult(MESSAGE_EMPTY_BOOK);
        } catch (UniquePersonList.DuplicatePersonException dpe) {
            // not possible to reach here
            return new CommandResult("");
        }
    }
}
