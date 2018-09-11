package seedu.addressbook.commands;

import seedu.addressbook.data.person.ReadOnlyPerson;

import java.util.HashSet;
import java.util.Set;
import java.util.List;


/**
 * Finds and lists all the people in the address book who are tagged with the argument keywords.
 * Keyword matching is case sensitive.
 */
public class FindTagCommand extends Command {

    public static final String COMMAND_WORD = "findtag";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Finds all the people who are tagged with the specified keywords (case-sensitive) and "
            + "displays them as a list with index numbers.\n"
            + "Parameters: KEYWORD [MORE_KEYWORDS]...\n"
            + "Example: " + COMMAND_WORD + " isCute isHandsome";

    private final Set<String> keywords;

    public FindTagCommand(Set<String> keywords) { this.keywords = keywords; }

    /**
     * Returns a copy of keywords in this command.
     */
    public Set<String> getKeywords() {
        return new HashSet<>(keywords);
    }

    @Override
    public CommandResult execute() {
        return new CommandResult("testing");
        /*
        final List<ReadOnlyPerson> personsFound = getPersonsWithTagsContainingAnyKeyword(keywords);
        return new CommandResult(getMessageForPersonListShownSummary(personsFound), personsFound);
         */
    }

}
