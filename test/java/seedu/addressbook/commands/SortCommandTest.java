package seedu.addressbook.commands;

import org.junit.Before;
import org.junit.Test;
import seedu.addressbook.data.AddressBook;
import seedu.addressbook.data.person.*;
import seedu.addressbook.util.TestUtil;
import seedu.addressbook.util.TypicalPersons;

import java.util.Collections;
import java.util.List;

import static org.junit.Assert.*;

public class SortCommandTest {

    private AddressBook emptyAddressBook;
    private AddressBook addressBook;

    private List<ReadOnlyPerson> listSorted;


    @Before
    public void setUp() throws Exception {
        Person abc = new Person(new Name("abc"), new Phone("61234567", false),
                new Email("john@doe.com", false), new Address("395C Ben Road", false), Collections.emptySet());
        Person def = new Person(new Name("def"), new Phone("91234567", false),
                new Email("jane@doe.com", false), new Address("33G Ohm Road", false), Collections.emptySet());
        Person ghi = new Person(new Name("ghi"), new Phone("63345566", false),
                new Email("sam@doe.com", false), new Address("55G Abc Road", false), Collections.emptySet());
        Person jkl = new Person(new Name("jkl"), new Phone("61121122", false),
                new Email("david@grant.com", false), new Address("44H Define Road", false),
                Collections.emptySet());

        emptyAddressBook = TestUtil.createAddressBook();
        addressBook = TestUtil.createAddressBook(abc, ghi, jkl, def);
        listSorted = TestUtil.createList(abc, def, ghi, jkl);

    }

    @Test
    public void execute_sorted_test() {
        assertEquals(addressBook.getAllPersons().sortPersonList(), listSorted);
    }
}