package seedu.addressbook.commands;

/**
 * Sorts all persons in the address book to the user.
 */
public class SortCommand extends Command{

    public static final String COMMAND_WORD = "sort";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Sorts all persons either by name or phone "
            + "Example: " + COMMAND_WORD + " name\n"
            + "Example: " + COMMAND_WORD + " phone\n";

    public static final String MESSAGE_SUCCESS = "List sorted";

    private static final String SORT_BY_NAME_COMMAND_WORD = "name";
    private static final String SORT_BY_PHONE_COMMAND_WORD = "phone";

    private String sortBy = "";

    public SortCommand(String sortBy) {
        this.sortBy = sortBy;
    }

    @Override
    public CommandResult execute() {
        switch(sortBy) {

        case SORT_BY_NAME_COMMAND_WORD:
            addressBook.sortByName();
            break;
        case SORT_BY_PHONE_COMMAND_WORD:
            addressBook.sortByPhone();
            break;
        default:
            new HelpCommand();
        }
        return new CommandResult(MESSAGE_SUCCESS);
    }

}
