package seedu.addressbook.commands;

import seedu.addressbook.data.exception.IllegalValueException;
import seedu.addressbook.data.person.*;

public class ContainCommand extends Command {
    private Name name;

    public static final String COMMAND_WORD = "contain";
    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Searches the address book with given name" ;
    public static final String MESSAGE_DUPLICATE = "This person is already in the address book";
    public static final String MESSAGE_UNIQUE = "This person is not in the address book";

    public ContainCommand(String name) throws IllegalValueException {
        this.name = new Name(name);
    }

    @Override
    public CommandResult execute() {
        boolean personFound = checkDuplicate(name);
        if (personFound) {
            return new CommandResult(MESSAGE_DUPLICATE);
        }
        return new CommandResult(MESSAGE_UNIQUE);
    }

    private boolean checkDuplicate(Name name) {
        for (ReadOnlyPerson person : addressBook.getAllPersons()) {
            if (person.getName().equals(name)) {
                return true;
            }
        }
        return false;
    }
}
