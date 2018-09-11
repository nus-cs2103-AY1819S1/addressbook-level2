package seedu.addressbook.commands;

import java.util.*;

import seedu.addressbook.data.exception.IllegalValueException;
import seedu.addressbook.data.person.ReadOnlyPerson;
import seedu.addressbook.data.tag.Tag;

/**
 * Finds and lists all persons in address book whose name contains any of the argument keywords.
 * Keyword matching is case sensitive.
 */
public class FindByTagCommand extends Command {

    public static final String COMMAND_WORD = "findByTag";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds all persons whose tags match any of "
            + "the specified keywords (case-insensitive) and displays them as a list with index numbers.\n"
            + "Parameters: KEYWORD [MORE_KEYWORDS]...\n"
            + "Example: " + COMMAND_WORD + " school work CCA";

    private final Set<Tag> tags;

    public FindByTagCommand(Set<String> tags) {
        final Set<Tag> tagSet = new HashSet<>();
        for (String tagName : tags) {
            try {
                tagSet.add(new Tag(tagName));
            } catch (IllegalValueException ive) {}
        }
        this.tags = tagSet;
    }

    @Override
    public CommandResult execute() {
        final List<ReadOnlyPerson> personsFound = getPersonsWithTag(tags);
        return new CommandResult(getMessageForPersonListShownSummary(personsFound), personsFound);
    }

    /**
     * Retrieves all persons in the address book whose names contain some of the specified keywords.
     *
     * @param keywords for searching
     * @return list of persons found
     */
    private List<ReadOnlyPerson> getPersonsWithTag(Set<Tag> tags) {
        final List<ReadOnlyPerson> matchedPersons = new ArrayList<>();
        for (ReadOnlyPerson person : addressBook.getAllPersons()) {
            final Set<Tag> tagsOfPerson = person.getTags();
            if (!Collections.disjoint(tagsOfPerson, tags)) {
                matchedPersons.add(person);
            }
        }
        return matchedPersons;
    }

}
