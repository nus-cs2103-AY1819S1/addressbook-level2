package seedu.addressbook.commands;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import seedu.addressbook.data.person.ReadOnlyPerson;

/**
 * Finds and lists all persons in address book whose name is similar with any of the argument keywords.
 * "similar" means that levenshtein distance of two words is less than or equal to threshold whose default value is 2.
 */

public class FuzzyFindCommand extends Command {

    public static final String COMMAND_WORD = "fuzzyFind";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds all persons whose names contain any of "
            + "the specified keywords (case-sensitive) and displays them as a list with index numbers.\n"
            + "Parameters: KEYWORD [MORE_KEYWORDS]...\n"
            + "Example: " + COMMAND_WORD + " alice bob charlie";

    private final Set<String> keywords;

    public FuzzyFindCommand(Set<String> keywords) {
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
        final List<ReadOnlyPerson> personsFound = getPersonsWhoseNameIsSimilarToAnyKeyword(keywords);
        return new CommandResult(getMessageForPersonListShownSummary(personsFound), personsFound);
    }

    /**
     * Retrieves all persons in the address book whose names contain some of the specified keywords.
     *
     * @param keywords for searching
     * @return list of persons found
     */
    private List<ReadOnlyPerson> getPersonsWhoseNameIsSimilarToAnyKeyword(Set<String> keywords) {
        final List<ReadOnlyPerson> matchedPersons = new ArrayList<>();
        final int THRESHOLD = 2;


        for (ReadOnlyPerson person : addressBook.getAllPersons()) {
            boolean hasThisOne = false;
            final Set<String> wordsInName = new HashSet<>(person.getName().getWordsInName());
            for (String wordInName : wordsInName) {
                for (String keyword : keywords) {
                    if (isLevenshteinDistanceLessThanOrEqualToThreshold(wordInName, keyword, THRESHOLD)) {
                        matchedPersons.add(person);
                        hasThisOne = true;
                    }
                    if(hasThisOne) {
                        break;
                    }
                }
                if (hasThisOne){
                    break;
                }
            }
        }
        return matchedPersons;
    }

    private boolean isLevenshteinDistanceLessThanOrEqualToThreshold(String wordInName, String keyword, int Threshold) {
        if (Math.abs(wordInName.length() - keyword.length()) > Threshold) {
            return false;
        }

        //prepare data
        int[][] matrix = new int[wordInName.length() + 1][keyword.length() + 1];

        //get levenshtein distance
        setLevenshteinDistance(matrix, wordInName, keyword);
        return (matrix[wordInName.length()][keyword.length()] <= Threshold);
    }

    private int setLevenshteinDistance(int[][] matrix, String wordInName, String keyword) {

        int lengthX = keyword.length();
        int lengthY = wordInName.length();
        //initialize matrix
        for (int i = 1; i <= lengthX; i++) {
            matrix[0][i] = i;
        }
        for (int j = 1; j <= lengthY; j++) {
            matrix[j][0] = j;
        }

        //Dynamic Programming
        for (int j = 1; j <= lengthY; j++) {
            for (int i = 1; i <= lengthX; i++) {
                int temp = wordInName.charAt(j - 1) == keyword.charAt(i - 1) ? 0 : 1;
                matrix[j][i] = Math.min(matrix[j - 1][i - 1] + temp,
                                        Math.min(matrix[j][i - 1] + 1, matrix[j - 1][i] + 1));
            }
        }
        return matrix[lengthY][lengthX];
    }
}
