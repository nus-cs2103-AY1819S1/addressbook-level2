package seedu.addressbook.ui;

import java.util.List;

/**
 * Formatting text for application UI display.
 */
public class Formatter {

    /** A decorative prefix added to the beginning of lines printed by AddressBook */
    private static final String LINE_PREFIX = "|| ";

    /** A platform independent line separator. */
    private static final String LS = System.lineSeparator();

    public static final String DIVIDER = "===================================================";

    /** Format of indexed list item */
    private static final String MESSAGE_INDEXED_LIST_ITEM = "\t%1$d. %2$s";

    /** Format of a comment input line. Comment lines are silently consumed when reading user input. */
    public static final String COMMENT_LINE_FORMAT_REGEX = "#.*";

    /** Offset required to convert between 1-indexing and 0-indexing.  */
    public static final int DISPLAYED_INDEX_OFFSET = 1;

    /**
     * Appends line prefix to input.
     */
    public static String addLinePrefix(String message) {
        return LINE_PREFIX + message;
    }

    /**
     * Replaces new line characters to platform independent version.
     */
    public static String replaceNewLine(String message) {
        return message.replace("\n", LS + LINE_PREFIX);
    }

    /**
     * Formats the message to the TextUi format.
     */
    public static String formatShowToUserMessage(String message) {
        String formattedM = Formatter.replaceNewLine(message);
        return Formatter.addLinePrefix(formattedM);
    }

    /**
     * Trims and return input.
     */
    public static String trim(String input) {
        return input.trim();
    }

    /** Formats a list of strings as a viewable indexed list. */
    public static String getIndexedListForViewing(List<String> listItems) {
        final StringBuilder formatted = new StringBuilder();
        int displayIndex = 0 + DISPLAYED_INDEX_OFFSET;
        for (String listItem : listItems) {
            formatted.append(getIndexedListItem(displayIndex, listItem)).append("\n");
            displayIndex++;
        }
        return formatted.toString();
    }

    /**
     * Formats a string as a viewable indexed list item.
     *
     * @param visibleIndex visible index for this listing
     */
    private static String getIndexedListItem(int visibleIndex, String listItem) {
        return String.format(MESSAGE_INDEXED_LIST_ITEM, visibleIndex, listItem);
    }


}
