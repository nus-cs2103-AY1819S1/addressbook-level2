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

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds all persons whose names are similar to any of "
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
     * Retrieves all persons in the address book whose names are similar to some of the specified keywords.
     *
     * @param keywords for searching
     * @return list of persons found
     */
    private List<ReadOnlyPerson> getPersonsWhoseNameIsSimilarToAnyKeyword(Set<String> keywords) {
        final List<ReadOnlyPerson> matchedPersons = new ArrayList<>();
        final int THRESHOLD = 2;

        /*
        go through all combination between keywords and wordsInName
        if a person was added into the return list, break from the two inner loops immediately
        */
        for (ReadOnlyPerson person : addressBook.getAllPersons()) {
            boolean hasThisOne = false;
            final Set<String> wordsInName = new HashSet<>(person.getName().getWordsInName());
            for (String wordInName : wordsInName) {
                for (String keyword : keywords) {
                    if (isLevenshteinDistanceLessThanOrEqualToThreshold(wordInName, keyword, THRESHOLD)) {
                        matchedPersons.add(person);
                        hasThisOne = true;
                        break;
                    }
                }
                if (hasThisOne) {
                    break;
                }
            }
        }
        return matchedPersons;
    }

    /**
     * Judges if the levenshtein distance between wordInName and keyword is less than or equal to threshold.
     *
     * @param wordInName
     * @param keyword
     * @param Threshold the number that levenshtein distance need to be less than or equal to
     * @return Returns true if levenshtein distance is less than or equal to threshold
     */
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

    /**
     * Gets the levenshtein distance between two strings using dynamic programming.
     *
     * @param matrix two-dimensional array to store the values during dynamic computing
     * @param s1 string 1
     * @param s2 string 2
     * @return levenshtain distance between string 1 and string 2
     */
    private int setLevenshteinDistance(int[][] matrix, String s1, String s2) {
        int lengthX = s2.length();
        int lengthY = s1.length();
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
                int temp = s1.charAt(j - 1) == s2.charAt(i - 1) ? 0 : 1;
                matrix[j][i] = Math.min(matrix[j - 1][i - 1] + temp,
                        Math.min(matrix[j][i - 1] + 1, matrix[j - 1][i] + 1));
            }
        }
        return matrix[lengthY][lengthX];
    }
}
