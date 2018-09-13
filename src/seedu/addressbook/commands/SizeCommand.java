package seedu.addressbook.commands;

public class SizeCommand extends Command {

    public static final String COMMAND_WORD = "size";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Returns the list size.\n"
            + "Example: " + COMMAND_WORD;
    public static final String MESSAGE_SIZE_ACKNOWEDGEMENT = "Number of contacts: %1$s";

    @Override
    public CommandResult execute() {
        return new CommandResult(MESSAGE_SIZE_ACKNOWEDGEMENT + addressBook.returnSize());
    }
    
}
