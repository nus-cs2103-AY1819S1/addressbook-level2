package seedu.addressbook.commands;

/**
 * Displays the total number of addresses.
 */

public class SizeCommand extends Command {

    public static final String COMMAND_WORD = "size";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Displays the total number of persons.\n"
            + "Example: " + COMMAND_WORD;
    private String MESSAGE_RESULT = "Total number of persons: %1$s";

    public int getAddressBookSize() {
        return addressBook.getAllPersons().getTotal();
    }

    @Override
    public CommandResult execute() {
        int addressBookSize = getAddressBookSize();
        return new CommandResult(String.format(MESSAGE_RESULT, addressBookSize));
    }
}
