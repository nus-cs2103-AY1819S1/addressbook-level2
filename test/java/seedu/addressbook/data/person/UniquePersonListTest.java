package seedu.addressbook.data.person;

import org.junit.Test;
import seedu.addressbook.data.tag.Tag;
import seedu.addressbook.util.TestUtil;

import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;


public class UniquePersonListTest {

    private Tag tagMathematician;
    private Person eliceBetsy;
    private Person bobChaplin;

    @Test
    public void sortImmutableListView() throws Exception {

        tagMathematician = new Tag("mathematician");

        eliceBetsy     = new Person(new Name("Elice Betsy"),
                new Phone("91235468", false),
                new Email("alice@nushackers.org", false),
                new Address("8 Computing Drive, Singapore", false),
                Collections.singleton(tagMathematician));

        bobChaplin     = new Person(new Name("Bob Chaplin"),
                new Phone("94321500", false),
                new Email("bob@nusgreyhats.org", false),
                new Address("9 Computing Drive", false),
                Collections.singleton(tagMathematician));

        UniquePersonList uniquePersonList = new UniquePersonList(eliceBetsy, bobChaplin);

        List<ReadOnlyPerson> unsortedList = uniquePersonList.immutableListView();
        List<ReadOnlyPerson> checkSortedList = (new UniquePersonList(bobChaplin, eliceBetsy)).immutableListView();
        List<ReadOnlyPerson> sortedList = uniquePersonList.sortImmutableListView();

        assertFalse(TestUtil.isIdentical(sortedList, unsortedList));
        assertTrue(TestUtil.isIdentical(checkSortedList, sortedList));
    }

}