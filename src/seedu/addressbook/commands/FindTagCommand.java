package seedu.addressbook.commands;

import seedu.addressbook.data.person.ReadOnlyPerson;
import seedu.addressbook.data.tag.Tag;
import seedu.addressbook.data.exception.IllegalValueException;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.List;
import java.util.Collections;


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
        final List<ReadOnlyPerson> personsFound = getPersonsWithTagsContainingAnyKeyword(keywords);
        return new CommandResult(getMessageForPersonListShownSummary(personsFound), personsFound);
    }

    private List<ReadOnlyPerson> getPersonsWithTagsContainingAnyKeyword (Set<String> keywords) {
        // convert the keywords into tags
        final Set<Tag> keywordTags = new HashSet<>();
        for (String keyword : keywords) {
            try {
                keywordTags.add(new Tag(keyword));
            } catch (IllegalValueException e) {
                // if an invalid string is provided as argument, just ignore, since
                // the string will not be able to be the tag of a Person.
                continue;
            }
        }

        // compare tags
        final List<ReadOnlyPerson> matchedPersons = new ArrayList<>();
        for (ReadOnlyPerson person : addressBook.getAllPersons()) {
            final Set<Tag> personsTags = person.getTags();
            if (!Collections.disjoint(keywordTags, personsTags)) {
                matchedPersons.add(person);
            }
        }
        return matchedPersons;
    }
}
