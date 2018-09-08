package seedu.addressbook.commands;

import seedu.addressbook.data.exception.IllegalValueException;
import seedu.addressbook.data.person.Name;
import seedu.addressbook.data.person.Person;
import seedu.addressbook.data.person.UniquePersonList;

import java.util.*;

public class StarCommand extends Command {
    public static final String COMMAND_WORD = "star";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Marks the selected person with a star. " +
            "The name (case-sensitive) should be the full name.\n" +
            "Parameter: NAME\n" +
            "Example: " + COMMAND_WORD + " John Doe";
    public static final String MESSAGE_SUCCESS = "New person starred: %1$s";
    public static final String MESSAGE_NAME_INVALID = "Name is invalid.";
    public static final String MESSAGE_PERSON_NOT_FOUND = "Unable to find %1$s";

    private final String name;
    private Person toStar;

    public StarCommand(String name) {
        this.name = name;
    }

    public CommandResult execute() {
        try {
            toStar = addressBook.findPerson(new Name(name));
            addressBook.removePerson(toStar);
            toStar.addStar();
            addressBook.addPerson(toStar);
        } catch (IllegalValueException ive) {
            return new CommandResult(MESSAGE_NAME_INVALID);
        } catch (UniquePersonList.PersonNotFoundException nfe) {
            return new CommandResult(String.format(MESSAGE_PERSON_NOT_FOUND, name));
        }

        return new CommandResult(String.format(MESSAGE_SUCCESS, toStar));
    }
}
