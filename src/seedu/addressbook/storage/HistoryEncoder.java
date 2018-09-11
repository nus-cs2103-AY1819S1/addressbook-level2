package seedu.addressbook.storage;

import seedu.addressbook.data.history.History;

import java.util.LinkedList;
import java.util.List;

public class HistoriesToString {


    public static List<String> converHistoriesToString(LinkedList<History> allhistories) {
        final List<String> histories= new LinkedList<>();
        allhistories.forEach(history -> histories.add(history.generateMessage()));
        return histories;
    }
}
