package seedu.addressbook.commands;

import org.junit.Test;
import seedu.addressbook.data.AddressBook;
import seedu.addressbook.data.person.*;
import seedu.addressbook.data.tag.Tag;
import seedu.addressbook.util.TypicalPersons;
import java.util.Collections;
import java.util.Iterator;

import static org.junit.Assert.*;

public class SortCommandTest {

    private final AddressBook addressBook = new TypicalPersons().getTypicalAddressBook();
    private final TypicalPersons td = new TypicalPersons();

    @Test
    public void execute() {
        assertSorted();
    }

    private void assertSorted(){
        Iterator<Person> iterator = addressBook.getAllPersons().iterator();
        while(iterator.hasNext()){
            assertTrue(iterator.next().getName().fullName.compareTo(iterator.next().getName().fullName) < 0);
        }
    }
}