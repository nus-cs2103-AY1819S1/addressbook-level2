package seedu.addressbook.commands;

public class SortCommand extends Command {

    public static final String COMMAND_WORD = "sort";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Sorts the address book alphabetically "
            + "\nExample: " + COMMAND_WORD;

    public static final String MESSAGE_SUCCESS = "Successfully sorted the addressbook";

    @Override
    public CommandResult execute() {
        addressBook.sort();
        return new CommandResult(MESSAGE_SUCCESS);
    }



}
