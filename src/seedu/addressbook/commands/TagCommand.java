package seedu.addressbook.commands;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import seedu.addressbook.data.person.ReadOnlyPerson;
import seedu.addressbook.data.tag.Tag;

/**
 * Finds and lists all persons in address book whose possess the exact tag presented in argument keywords.
 * Keyword matching is must be letter for letter. Non-case sensitive.
 */
public class TagCommand extends Command {

    public static final String COMMAND_WORD = "tag";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds all persons whose have at least one of the tags specified "
            + "in the keywords typed and displays them as a list with index numbers.\n"
            + "Parameters: KEYWORD [MORE_KEYWORDS]...\n"
            + "Example: " + COMMAND_WORD + " friends";

    private final Set<String> keywords;

    public TagCommand(Set<String> keywords) {
        this.keywords = keywords;
    }

    /**
     * Returns a copy of keywords in this command.
     */
    public Set<String> getKeywords() {
        return new HashSet<>(keywords);
    }

    @Override
    public CommandResult execute() {
        final List<ReadOnlyPerson> personsFound = getPersonsContainingTag(keywords);
        return new CommandResult(getMessageForPersonListShownSummary(personsFound), personsFound);
    }

    /**
     * Retrieves all persons in the address book whose names contain some of the specified tags.
     *
     * @param keywords for searching
     * @return list of persons found
     */
    private List<ReadOnlyPerson> getPersonsContainingTag(Set<String> keywords) {
        final List<ReadOnlyPerson> matchedPersons = new ArrayList<>();
        for (ReadOnlyPerson person : addressBook.getAllPersons()) {
            final Set<Tag> tagsOfPerson = person.getTags();
            for (Tag tag: tagsOfPerson) {
                for (String input: keywords) {
                    if (input.equals(tag.tagName.toLowerCase())) {
                        if (!matchedPersons.contains(person)) {
                            matchedPersons.add(person);
                        }
                        break;
                    }
                }
            }
        }
        return matchedPersons;
    }

}
