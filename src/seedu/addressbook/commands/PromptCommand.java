package seedu.addressbook.commands;

/**
 * Prompts the user due to invalid command.
 */
public class PromptCommand extends Command {
    public static final String COMMAND_WORD = "prompt";
    private static String PROMPT;

    public PromptCommand(String closestCommand) {
        PROMPT = String.format("%s: Did you mean \"%s\" instead?", COMMAND_WORD, closestCommand);
    }

    @Override
    public CommandResult execute() {
        return PROMPT != null ? new CommandResult(PromptCommand.PROMPT) : null;
    }
}
