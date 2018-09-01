package seedu.addressbook.common;

public class TextUtils {

    private static final double SIMILARITY = 0.9;

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
            return Math.max(str1.length, str1.length);
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
    private static int getSimilarity(String s1, String s2) {
        int hamming = getHammingDistance(s1, s2);
        return (hamming / Math.max(s1.length(), s2.length()));
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
}
