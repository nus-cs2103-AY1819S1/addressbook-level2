package seedu.addressbook.commands;

import seedu.addressbook.common.Messages;
import seedu.addressbook.data.person.ReadOnlyPerson;

import java.security.InvalidParameterException;
import java.util.List;


/**
 * Lists all persons in the address book to the user.
 */
public class SortCommand extends Command {

    public static final String COMMAND_WORD = "sort";

    public static final String MESSAGE_USAGE = COMMAND_WORD
        + ": Sorts all persons in the address book according to the specifier (name, phone, email, address) and " +
        "display in a sorted list.\n"
        + "Example: " + COMMAND_WORD + " address";

    private final String keyword;
    
    public SortCommand(String keyword) {
        this.keyword = keyword.trim();
    }
    
    @Override
    public CommandResult execute() {
        try {
            addressBook.sort((ReadOnlyPerson p1, ReadOnlyPerson p2) -> {
                switch (keyword) {
                    case "name": return p1.getName().toString().compareTo(p2.getName().toString());
                    case "phone": return p1.getPhone().toString().compareTo(p2.getPhone().toString());
                    case "email": return p1.getEmail().toString().compareTo(p2.getEmail().toString());
                    case "address": return p1.getAddress().toString().compareTo(p2.getAddress().toString());
                    default:
                        throw new InvalidParameterException();
                }
            });
            List<ReadOnlyPerson> allPersons = addressBook.getAllPersons().immutableListView();
            return new CommandResult(getMessageForPersonListShownSummary(allPersons), allPersons);
        } catch (InvalidParameterException e) {
            return new CommandResult(Messages.MESSAGE_INVALID_SORT);
        }
    }
}