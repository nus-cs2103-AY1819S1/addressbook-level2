package seedu.addressbook.commands;

import org.junit.Test;
import seedu.addressbook.data.AddressBook;
import seedu.addressbook.data.exception.IllegalValueException;
import seedu.addressbook.data.person.*;
import seedu.addressbook.util.TestUtil;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import static seedu.addressbook.ui.TextUi.DISPLAYED_INDEX_OFFSET;

public class ListByOrderTest {
    private static final String[] testName = {"Apple", "Sally", "Jasmine", "Katrice", "Katniss"};
    private static int currentNamePointer = 0;
    private static final List<ReadOnlyPerson> EMPTY_PERSON_LIST = Collections.emptyList();
    /** Format of indexed list item */
    private static final String MESSAGE_INDEXED_LIST_ITEM = "\t%1$d. %2$s";

    private List<? extends ReadOnlyPerson> shownToUser;
    @Test
    public void listByOrder_testSortingAccuracy() throws Exception {
        // generate 5 people
        Person p = generateATestPerson();
        Person p2 = generateATestPerson();
        Person p3 = generateATestPerson();
        Person p4 = generateATestPerson();
        Person p5 = generateATestPerson();

        AddressBook book = new AddressBook();
        book.addPerson(p);
        book.addPerson(p2);
        book.addPerson(p3);
        book.addPerson(p4);
        book.addPerson(p5);


        ListByOrderCommand command = new ListByOrderCommand();
        command.setData(book, EMPTY_PERSON_LIST);
        CommandResult result = command.execute();

        final List<String> formattedPersons = new ArrayList<>();

        result.getRelevantPersons().ifPresent( x -> {
            shownToUser = x;
        });

        for (ReadOnlyPerson person : shownToUser) {
            formattedPersons.add(person.getAsTextHidePrivate());
        }

        // get the string output shown to user, and assert that the string output
        // is properly formatted alphabetically.
        assertShownStringIsAlphabeticallyOrdered(getIndexedListForViewing(formattedPersons));
    }

    /** Formats a list of strings as a viewable indexed list. */
    private static String getIndexedListForViewing(List<String> listItems) {
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

    // add a new person. This method can be called maximum of 5 times
    private Person generateATestPerson() throws Exception {
        try {
            String name = testName[currentNamePointer];
            currentNamePointer++;
            return new Person(new Name(name), new Phone(Phone.EXAMPLE, false),
                    new Email(Email.EXAMPLE, true), new Address(Address.EXAMPLE, false), Collections.emptySet());
        } catch (IllegalValueException e) {
            fail("test person data should be valid by definition");
            return null;
        }
    }

    // assert the shown string is of the right order, alphabetically sorted by the person's name.
    private void assertShownStringIsAlphabeticallyOrdered(String shownString) {
        int indexName_1st = shownString.indexOf(testName[0]);
        int indexName_2nd = shownString.indexOf(testName[1]);
        int indexName_3rd = shownString.indexOf(testName[2]);
        int indexName_4th = shownString.indexOf(testName[3]);
        int indexName_5th = shownString.indexOf(testName[4]);
        // 1st name must be first, alphabetically
        assertTrue(indexName_1st < indexName_2nd && indexName_1st < indexName_3rd &&
                indexName_1st < indexName_4th && indexName_1st < indexName_5th);

        // 3rd name must be 2nd, alphabetically
        assertTrue(indexName_3rd < indexName_2nd && indexName_3rd < indexName_4th &&
                indexName_3rd < indexName_5th);

        // 5th name must be 3rd, alphabetically
        assertTrue(indexName_5th < indexName_4th && indexName_5th < indexName_2nd);

        // 4th name must be 4th, alphabetically
        assertTrue(indexName_4th < indexName_2nd);
    }
}
