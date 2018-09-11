package seedu.addressbook.commands;

/**
 * Finds and lists all persons in address book whose name is similar with any of the argument keywords.
 * "similar" means that levenshtein distance of two words is less than or equal to 2.
 */
public class FuzzyFindCommand extends Command {

    public static final String COMMAND_WORD = "fuzzyFind";

    public FuzzyFindCommand() {
    }

    @Override
    public CommandResult execute() {
        return new CommandResult("");
    }

}
