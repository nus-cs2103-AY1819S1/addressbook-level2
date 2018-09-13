package seedu.addressbook.commands;

import seedu.addressbook.data.person.ReadOnlyPerson;
import seedu.addressbook.data.tag.Tag;

import java.util.*;

/**
 * Finds and lists all persons in address book whose name contains any of the argument keywords.
 * Keyword matching is case sensitive.
 */
public class TagCommand extends Command {

    public static final String COMMAND_WORD = "tag";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds all persons whose tags contain any of "
            + "the specified keywords (case-sensitive) and displays them as a list with index numbers.\n"
            + "Parameters: KEYWORD [MORE_KEYWORDS]...\n"
            + "Example: " + COMMAND_WORD + " classmates friends family";

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
        final List<ReadOnlyPerson> personsFound = getPersonsWithNameContainingAnyTag(keywords);
        return new CommandResult(getMessageForPersonListShownSummary(personsFound), personsFound);
    }

    /**
     * Retrieves all persons in the address book whose names contain some of the specified keywords.
     *
     * @param keywords for searching
     * @return list of persons found
     */
    private List<ReadOnlyPerson> getPersonsWithNameContainingAnyTag(Set<String> keywords) {
        final List<ReadOnlyPerson> matchedPersons = new ArrayList<>(); //create object matchedPersons

        // For All persons in addressBook, starting from Index 1
        for (ReadOnlyPerson person : addressBook.getAllPersons()) {
            // Extract tags in Name

            final Set<Tag> tagsInName = new HashSet<>(person.getTags());
            final Set<String> tagsInString = new HashSet<String>();

            for(Tag index: tagsInName){
                tagsInString.add(index.tagName);
            }

            /*
             * Definition: two collections are DISJOINT if they have no elements in common
             * Therefore, !Collections.disjoint(TagsInName, keywords) return TRUE
             * if TagsInName is the same as keywords
             */
            if (!Collections.disjoint(tagsInString, keywords)) {
                matchedPersons.add(person);
            }
        }
        return matchedPersons;
    }

}
