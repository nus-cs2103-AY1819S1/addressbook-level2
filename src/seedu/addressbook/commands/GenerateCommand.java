package seedu.addressbook.commands;

import seedu.addressbook.data.exception.IllegalValueException;
import seedu.addressbook.data.person.*;

import java.util.Collections;
import java.util.List;


/**
 * Generates 5 persons into the address book.
 */
public class GenerateCommand extends Command {

    public static final String COMMAND_WORD = "generate";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Generates 4 persons into the address book.\n"
            + "Example: " + COMMAND_WORD;

    private Person createPeople() throws IllegalValueException {
        Person person1 = new Person(
                new Name("Logan Paul"),
                new Phone("98881919", false),
                new Email("jp@gmail.com", false),
                new Address("Japan Forest", false),
                Collections.emptySet());
        return person1;
    }

    @Override
    public CommandResult execute() {
        try {
            Person p1 = createPeople();
            addressBook.addPerson(p1);
            return new CommandResult("Successfully added 5 persons");
        } catch (IllegalValueException ive) {
            return new CommandResult("Person names in list are  wrongly set.xx");
        }

    }
}
