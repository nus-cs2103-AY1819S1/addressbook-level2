package seedu.addressbook.commands;


/**
 * Shows help instructions.
 */
public class HelpCommand extends Command {

    public static final String COMMAND_WORD = "help";

    public static final String MESSAGE_USAGE = Command.getMessageUsage(COMMAND_WORD,
        "Shows program usage instructions.",
        "none",
        "");

    @Override
    public CommandResult execute() {
        return new CommandResult(
                AddCommand.MESSAGE_USAGE
                + DeleteCommand.MESSAGE_USAGE
                + ClearCommand.MESSAGE_USAGE
                + FindCommand.MESSAGE_USAGE
                + ListCommand.MESSAGE_USAGE
                + ViewCommand.MESSAGE_USAGE
                + ViewAllCommand.MESSAGE_USAGE
                + HelpCommand.MESSAGE_USAGE
                + ExitCommand.MESSAGE_USAGE
        );
    }
}
