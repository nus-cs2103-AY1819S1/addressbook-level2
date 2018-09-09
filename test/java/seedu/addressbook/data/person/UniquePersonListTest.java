package seedu.addressbook.data.person;

import org.junit.Test;
import seedu.addressbook.data.tag.Tag;
import seedu.addressbook.util.TestUtil;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class UniquePersonListTest {
    private Tag tagMathematician;

    private Person aliceBetsy;
    private Person bobChaplin;


    @Test
    public void sortedListView() throws Exception {
        tagMathematician = new Tag("mathematician");

        aliceBetsy     = new Person(new Name("Alice Betsy"),
                new Phone("91235468", false),
                new Email("alice@nushackers.org", false),
                new Address("8 Computing Drive, Singapore", false),
                Collections.singleton(tagMathematician));

        bobChaplin     = new Person(new Name("Bob Chaplin"),
                new Phone("94321500", false),
                new Email("bob@nusgreyhats.org", false),
                new Address("9 Computing Drive", false),
                Collections.singleton(tagMathematician));

        UniquePersonList uniquePersonList = new UniquePersonList(bobChaplin, aliceBetsy);

        List<ReadOnlyPerson> unsortedList = uniquePersonList.immutableListView();
        List<ReadOnlyPerson> sortedList = uniquePersonList.sortedListView();

        assertListSorted(sortedList);
        assertListUnsorted(unsortedList);
    }

    private void assertListSorted(List<ReadOnlyPerson> sortedList) {
        List<ReadOnlyPerson> sorted = sort(sortedList);
        assertTrue(TestUtil.isIdentical(sortedList, sorted));
    }

    private void assertListUnsorted(List<ReadOnlyPerson> unsortedList) {
        List<ReadOnlyPerson> sorted = sort(unsortedList);
        assertFalse(TestUtil.isIdentical(unsortedList, sorted));
    }

    private List<ReadOnlyPerson> sort (List<ReadOnlyPerson> unsortedList) {
        List<ReadOnlyPerson> sortedList = new ArrayList<>(unsortedList);
        sortedList.sort((personOne, personTwo) -> {
            String personOneName = personOne.getName().toString().toLowerCase();
            String personTwoName = personTwo.getName().toString().toLowerCase();
            return personOneName.compareTo(personTwoName);
        });
        return sortedList;
    }
}