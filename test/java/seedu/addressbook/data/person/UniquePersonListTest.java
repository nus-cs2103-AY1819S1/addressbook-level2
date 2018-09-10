package seedu.addressbook.data.person;

import static org.junit.Assert.assertTrue;
import static seedu.addressbook.util.TestUtil.isIdentical;

import java.util.Collections;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import seedu.addressbook.data.tag.Tag;

public class UniquePersonListTest {
    private Tag tagEmpty;

    private Person bobby;
    private Person abc;

    private UniquePersonList defaultUniquePersonList;
    private UniquePersonList emptyUniquePersonList;

    @Before
    public void setUp() throws Exception {
        tagEmpty = new Tag("Empty");

        abc =   new Person(new Name("abc"),
                new Phone("1234", false),
                new Email("abc@nushackers.org", false),
                new Address("8 Computing Drive, Singapore", false),
                Collections.singleton(tagEmpty));
        bobby = new Person(new Name("bobby"),
                new Phone("2345", false),
                new Email("bobby@gmail.org", false),
                new Address("312 Clementi Drive", false),
                Collections.singleton(tagEmpty));

        defaultUniquePersonList = new UniquePersonList(bobby, abc);
        emptyUniquePersonList = new UniquePersonList();
    }

    @Test
    public void sortByAlphabetToImmutableList() throws Exception {
        // Empty UniquePersonList
        List<ReadOnlyPerson> emptySortedPersons = emptyUniquePersonList.sortByAlphabetToImmutableList();
        List<ReadOnlyPerson> emptyPersonsToCheck = new UniquePersonList().immutableListView();
        assertTrue(isIdentical(emptySortedPersons, emptyPersonsToCheck));

        // Check Sorted List
        List<ReadOnlyPerson> sortedAllPersons = defaultUniquePersonList.sortByAlphabetToImmutableList();
        List<ReadOnlyPerson> personsToCheck = new UniquePersonList(abc, bobby).immutableListView();
        assertTrue(isIdentical(sortedAllPersons, personsToCheck));
    }
}
