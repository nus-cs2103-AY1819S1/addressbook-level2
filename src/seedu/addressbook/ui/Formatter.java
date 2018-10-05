package seedu.addressbook.ui;

import java.util.ArrayList;
import java.util.List;

import seedu.addressbook.data.person.ReadOnlyPerson;

public class Formatter {
    /**
     * A decorative prefix added to the beginning of lines printed by AddressBook
     */
    static final String LINE_PREFIX = "|| ";
    /**
     * A platform independent line separator.
     */
    static final String LS = System.lineSeparator();

    /** Format of indexed list item */
    private static final String MESSAGE_INDEXED_LIST_ITEM = "\t%1$d. %2$s";

    public Formatter() {
    }

    String formatMessage(String m) {
        return addLinePrefix(replaceNewline(m));
    }

    String replaceNewline(String m) {
        return m.replace("\n", LS + LINE_PREFIX);
    }

    String addLinePrefix(String message) {
        return LINE_PREFIX + message;
    }

    List<String> getFormattedPersons(List<? extends ReadOnlyPerson> persons) {
        final List<String> formattedPersons = new ArrayList<String>();
        for (ReadOnlyPerson person : persons) {
            formattedPersons.add(person.getAsTextHidePrivate());
        }
        return formattedPersons;
    }

    static StringBuilder getFormattedIndexedList(List<String> listItems) {
        final StringBuilder formatted = new StringBuilder();
        int displayIndex = 0 + TextUi.DISPLAYED_INDEX_OFFSET;
        for (String listItem : listItems) {
            formatted.append(getIndexedListItem(displayIndex, listItem)).append("\n");
            displayIndex++;
        }
        return formatted;
    }

    /**
     * Formats a string as a viewable indexed list item.
     *
     * @param visibleIndex visible index for this listing
     */
    private static String getIndexedListItem(int visibleIndex, String listItem) {
        return String.format(MESSAGE_INDEXED_LIST_ITEM, visibleIndex, listItem);
    }

    String formatCommand(String fullInputLine) {
        return "[Command entered:" + fullInputLine + "]";
    }
}