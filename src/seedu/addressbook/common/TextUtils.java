package seedu.addressbook.common;

import org.apache.commons.text.similarity.JaroWinklerDistance;
import seedu.addressbook.commands.CommandType;

public class TextUtils {

    private static final double SIMILARITY = 0.75;

    /**
     * Calculates the hamming distance between 2 strings, s1 and s2.
     * Returns the hamming distance.
     * @param s1
     * @param s2
     * @return
     */
    private static int getHammingDistance(String s1, String s2) {
        char[] str1 = s1.toCharArray();
        char[] str2 = s2.toCharArray();
        int hammingDist = 0;

        if (str1.length != str2.length) {
            return 0;
        }

        for (int i = 0; i < str1.length; i++) {
            int dist = str1[i] == str2[i] ? 1 : 0;
            hammingDist += dist;
        }
        return hammingDist;
    }

    /**
     * Calculates the similarity between the 2 strings, s1 and s2.
     * Similarity is calculated based on hamming distance.
     * @param s1
     * @param s2
     * @return
     */
    private static double getSimilarity(String s1, String s2) {
        double hamming = getHammingDistance(s1, s2);
        double total = Math.max(s1.length(), s2.length());
        double similarity = hamming / total;
        return similarity;
    }

    /**
     * Checks if 2 strings are similar.
     * @param s1
     * @param s2
     * @return
     */
    public static boolean isSimilar(String s1, String s2) {
        return getSimilarity(s1, s2) >= SIMILARITY;
    }

import org.apache.commons.text.similarity.JaroWinklerDistance;
import seedu.addressbook.commands.CommandType;

import static java.util.Objects.requireNonNull;

/**
 * Methods to calculate similarity between 2 strings.
 */
public class TextUtils {

    public static final double THRESHOLD = 0.85;

    /**
     * Calculates and returns the Jaro Winkler distance between 2 words.
     * @param userInput
     * @param commandType
     * @return
     */
    public static double calculateDistance(String userInput, CommandType commandType) {
        requireNonNull(userInput);
        requireNonNull(commandType);
        return calculateDistance(userInput, commandType.commandName());
    }

    /**
     * @see #calculateDistance(String, CommandType)
     * @param userInput
     * @param cmd
     * @return
     */
    public static double calculateDistance(String userInput, String cmd) {
        JaroWinklerDistance jaroWinklerDistance = new JaroWinklerDistance();
        return jaroWinklerDistance.apply(userInput, cmd);
    }

    /**
     * Finds the closest command above the threshold
     * by comparing {@code userInput} against a command string
     * and returns it, if one exists.
     * @param userInput
     * @return
     */
    public static String getClosestCommand(String userInput) {
        CommandType closestCmd = null;
        double highScore = 0;
        for (CommandType commandType : CommandType.values()) {
            double score = calculateDistance(userInput, commandType);
            if (score > THRESHOLD && score > highScore) {
                closestCmd = commandType;
                highScore = score;
            }
        }
        return getClosestCommand(closestCmd);
    }

    /**
     * @see #getClosestCommand(String)
     * @param closestCommand
     * @return
     */
    public static String getClosestCommand(CommandType closestCommand) {
        if (closestCommand != null) return closestCommand.commandName();
        return null;
    }

}
