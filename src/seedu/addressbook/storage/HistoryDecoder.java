package seedu.addressbook.storage;

import seedu.addressbook.data.exception.IllegalValueException;
import seedu.addressbook.data.history.AddHistory;
import seedu.addressbook.data.history.ClearAllHistory;
import seedu.addressbook.data.history.History;
import seedu.addressbook.data.history.RemoveHistory;
import seedu.addressbook.data.person.*;

import java.time.LocalTime;
import java.util.LinkedList;
import java.util.List;

public class HistoryDecoder {

    public static LinkedList<History> decodeHistories(List<String> encodedHistories, UniquePersonList allpersons)
            throws IllegalValueException {
        LinkedList<History> allHistories = new LinkedList<>();
        encodedHistories.forEach(encodedHistory -> {
            try {
                allHistories.add(decodeHisotoryFromString(encodedHistory, allpersons));
            } catch (IllegalValueException e) {
                e.printStackTrace();
            }
        });
        return allHistories;
    }

    private static History decodeHisotoryFromString(String encodedHistory, UniquePersonList allPersons) throws IllegalValueException {
        String[] words = encodedHistory.split(" ");
        String firstWord = words[0];
        boolean isAdd = false;
        switch(firstWord) {
            case "Add":
                isAdd = true;
            case "Remove":
                String firstName = words[3];
                String lastName = words[4];
                String emailAddress = words[5].split("\\(")[1].split("\\)")[0];
                Name name = new Name(firstName + " " + lastName);
                Email email = new Email(emailAddress, true);
                LocalTime time = LocalTime.parse(words[7]);
                if(isAdd) {
                    return new AddHistory(time, name, email);
                }
                return new RemoveHistory(time, name, email);
            case "All":
                int number = Integer.parseInt(encodedHistory.split("\\(")[1].split("\\)")[0]);
                time = LocalTime.parse(words[7]);
                return new ClearAllHistory(time, number);
        }
        return null;
    }

}
