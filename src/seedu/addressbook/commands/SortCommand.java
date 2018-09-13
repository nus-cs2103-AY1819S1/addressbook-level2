package seedu.addressbook.commands;

import seedu.addressbook.data.person.Person;
import seedu.addressbook.data.person.ReadOnlyPerson;

import java.util.*;

//Sorts contact list of persons by alphabetical order
public class SortCommand extends Command{

    public static final String COMMAND_WORD = "sort";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + "Find all persons with case-sensitive words and "
            + "displays all persons in the address book as a sorted list with index numbers.\n"
            + "Example: " + COMMAND_WORD + "daniel grace salah";

    private final Set<String> keywords;

    public SortCommand(Set<String> keywords){
        this.keywords = keywords;
    }

    public Set<String> getKeywords() {
        return new HashSet<>(keywords);
    }

    @Override
    public CommandResult execute() {
        final List<ReadOnlyPerson> sortPersons = getPersonsWithNameContainingAnyKeyword(keywords);

        return new CommandResult(getMessageForPersonListShownSummary(sortPersons), sortPersons);
    }
    /**
     * Retrieves all persons in the address book whose names contain some of the specified keywords.
     *
     * @param keywords for searching
     * @return list of persons found
     */
    private List<ReadOnlyPerson> getPersonsWithNameContainingAnyKeyword(Set<String> keywords) {
        final List<ReadOnlyPerson> matchedPersons = new ArrayList<>();
        for (ReadOnlyPerson person : addressBook.getAllPersons()) {
            final Set<String> wordsInName = new HashSet<>(person.getName().getWordsInName());
            if (!Collections.disjoint(wordsInName, keywords)) {
                matchedPersons.add(person);
            }
        }
        return matchedPersons;
    }

}
