package seedu.addressbook.commands;

import seedu.addressbook.data.person.Person;
import seedu.addressbook.data.person.ReadOnlyPerson;
import seedu.addressbook.data.person.UniquePersonList;

import java.util.*;

public class StarCommand extends Command {
    public static final String COMMAND_WORD = "star";
    public static final String MESSAGE_USAGE = COMMAND_WORD;
    public static final String MESSAGE_SUCCESS = "New person starred: %1$s";

    private final String name;
    private Person toStar;

    public StarCommand(String name) {
        this.name = name;
    }

    public CommandResult execute() {
        // retrieve person from address book
        Optional<Person> found = find(name);
        if (found.isPresent()) {
            toStar = found.get();
        }

        try {
            addressBook.removePerson(toStar);
        } catch (UniquePersonList.PersonNotFoundException nfe) {
            return new CommandResult("failed");
        }

        toStar.addStar();

        try {
            addressBook.addPerson(toStar);
        } catch (UniquePersonList.DuplicatePersonException dpe) {
            return new CommandResult("failed");
        }

        return new CommandResult(String.format(MESSAGE_SUCCESS, toStar));
    }

    private Optional<Person> find (String name) {
        for (Person person : addressBook.getAllPersons()) {
            if (person.getName().toString().equals(name)) {
                return Optional.of(person);
            }
        }
        return Optional.empty();
    }
}
