package seedu.addressbook.commands;


/**
 * Shows help instructions.
 */
public class HelpCommand extends Command {

    public static final String COMMAND_WORD = "help";

    public static final String MESSAGE_USAGE = Command.getMessageUsage(COMMAND_WORD,
        "Shows program usage instructions.");

    @Override
    public CommandResult execute() {
        return new CommandResult(
                AddCommand.MESSAGE_USAGE
                + "\n" + DeleteCommand.MESSAGE_USAGE
                + "\n" + ClearCommand.MESSAGE_USAGE
                + "\n" + FindCommand.MESSAGE_USAGE
                + "\n" + ListCommand.MESSAGE_USAGE
                + "\n" + ViewCommand.MESSAGE_USAGE
                + "\n" + ViewAllCommand.MESSAGE_USAGE
                + "\n" + HelpCommand.MESSAGE_USAGE
                + "\n" + ExitCommand.MESSAGE_USAGE
        );
    }
}
