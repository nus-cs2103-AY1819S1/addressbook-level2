package seedu.addressbook.commands;

import seedu.addressbook.data.person.ReadOnlyPerson;
import seedu.addressbook.data.person.UniquePersonList;

import java.util.List;

/**
 * Generates a random 'lucky person' that the user should contact for fun!
 */
public class LuckyCommand extends Command{

    public static final String COMMAND_WORD = "lucky";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Generates a random 'lucky person' that the "
            + "user should contact for fun!\n"
            + "Parameters: -"
            + "Example: " + COMMAND_WORD;

    public static final String MESSAGE_SUCCESS = "Feeling lucky today? Why not give this person a call!\n"
            + "Lucky Person: %1$s";

    public static final String MESSAGE_DUPLICATE_PERSON = "This person already exists in the address book";

    /**
     * An empty constructor for LuckyCommand
     */
    public LuckyCommand() { }

    @Override
    public CommandResult execute() {
        List<ReadOnlyPerson> allPersons = addressBook.getAllPersons().immutableListView();
        int numberOfContacts = allPersons.size();
        int luckyNumber = (int)(Math.random() * numberOfContacts);
        ReadOnlyPerson luckyPerson = allPersons.get(luckyNumber);
        return new CommandResult(String.format(MESSAGE_SUCCESS, luckyPerson));
    }
}
