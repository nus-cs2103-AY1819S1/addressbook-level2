package seedu.addressbook.commands;

import seedu.addressbook.data.exception.IllegalValueException;
import seedu.addressbook.data.person.*;
import seedu.addressbook.data.tag.Tag;

import java.util.Collections;
import java.util.HashSet;


/**
 * Generates 4 persons into the address book.
 */
public class GenerateCommand extends Command {

    public static final String COMMAND_WORD = "generate";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Generates 4 persons into the address book.\n"
            + "Example: " + COMMAND_WORD;

    public static final String MESSAGE_DUPLICATE_PEOPLE = "These people already exist in the address book";

    /**
     * Creates 4 persons and add them to the address book.
     *
     * @throws IllegalValueException if any of the raw values are invalid.
     */
    private void createPeople() throws IllegalValueException {
        Person person1 = new Person(
                new Name("Logan Paul"),
                new Phone("98881919", false),
                new Email("lp@gmail.com", false),
                new Address("Japan Forest", false),
                Collections.emptySet());
        addressBook.addPerson(person1);

        Person person2 = new Person(
                new Name("Jake Paul"),
                new Phone("91020221", false),
                new Email("jp@gmail.com", false),
                new Address("121 Texas Road", true),
                Collections.emptySet());
        addressBook.addPerson(person2);

        Person person3 = new Person(
                new Name("Paul Frank"),
                new Phone("92320221", false),
                new Email("pf@gmail.com", false),
                new Address("121 Sembawang Road", true),
                Collections.emptySet());
        addressBook.addPerson(person3);

        final HashSet<Tag> tagSet = new HashSet<>();
        tagSet.add(new Tag("Singaporean"));
        tagSet.add(new Tag("Male"));
        Person person4 = new Person(
                new Name("Ah Beng"),
                new Phone("68320221", false),
                new Email("aaf@smail.com", true),
                new Address("121 Yishun Road", false),
                tagSet);
        addressBook.addPerson(person4);
    }

    @Override
    public CommandResult execute() {
        try {
            createPeople();
            return new CommandResult("Successfully added 4 persons.");
        } catch (IllegalValueException ive) {
            return new CommandResult(MESSAGE_DUPLICATE_PEOPLE);
        }

    }
}
