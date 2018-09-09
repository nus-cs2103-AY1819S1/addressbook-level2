package seedu.addressbook.commands;


/**
 * Swaps the index of 2 person in the address book.
 */
public class SwapCommand extends Command {

    public static final String COMMAND_WORD = "swap";
    public static final String MESSAGE_USAGE = "Swap index of 2 person in address book.\n"
            + "Example: " + COMMAND_WORD +" 1 2";

    public static final String MESSAGE_SWAP_SUCCESS = "Index successfully swapped.";

    @Override
    public CommandResult execute() {

        //swap contacts here


        return new CommandResult(MESSAGE_SWAP_SUCCESS);
    }
}
