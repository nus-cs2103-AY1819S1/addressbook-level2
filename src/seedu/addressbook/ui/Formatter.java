package seedu.addressbook.ui;

import seedu.addressbook.data.person.ReadOnlyPerson;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static seedu.addressbook.ui.TextUi.LINE_PREFIX;
import static seedu.addressbook.ui.TextUi.LS;
import static seedu.addressbook.ui.TextUi.DISPLAYED_INDEX_OFFSET;
import static seedu.addressbook.ui.TextUi.MESSAGE_INDEXED_LIST_ITEM;

public class Formatter {
    private final Scanner in;
    private final PrintStream out;
    
    public Formatter() {
        this(System.in, System.out);    
    }
    
    public Formatter(InputStream in, PrintStream out) {
        this.in = new Scanner(in);
        this.out = out;
    }

    /** Shows message(s) to the user */
    public String formatMsgForUser(String... message) {
        StringBuilder result = new StringBuilder();
        for (String m : message) {
            result.append(LINE_PREFIX + m.replace("\n", LS + LINE_PREFIX)).append("\n");
        }
        return result.toString();
    }
    
    /**
     * Formats a list of ReadOnlyPerson into a list of string, containing the public information of that person.
     * 
     * @param persons   A list of ReadOnlyPerson to be formatted.
     * @return  A list of string.
     */
    public List<String> formatPersonsIntoListOfString(List<? extends ReadOnlyPerson> persons) {
        final List<String> formattedPersons = new ArrayList<>();
        for (ReadOnlyPerson person : persons) {
            formattedPersons.add(person.getAsTextHidePrivate());
        }
        return formattedPersons;
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
    
    public static String formatConcatResultMsg(String existingMsg, String msgToAdd) {
        if (existingMsg.equals("")) {
            return msgToAdd;
        } else {
            return existingMsg + "\n" + msgToAdd;
        }
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
