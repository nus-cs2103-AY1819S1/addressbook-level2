package seedu.addressbook.commands;

import org.junit.Test;
import seedu.addressbook.data.AddressBook;
import seedu.addressbook.data.exception.IllegalValueException;
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
        AddressBook addressBook1 = new AddressBook();
        // add addressBook1 in a different order and sort it.
        try {
            addressBook1.addPerson(new Person(new Name("Amy Buck"), new Phone("91119111", false), new Email("ab@gmail.com", false),
                    new Address("1 Clementi Road", false), Collections.emptySet()));
            addressBook1.addPerson(new Person(new Name("Dan Smith"), new Phone("1234556", true), new Email("ss@tt.com", true),
                    new Address("NUS", true), Collections.singleton(new Tag("test"))));
            addressBook1.addPerson(new Person(new Name("Bill Clint"), new Phone("92229222", false), new Email("bc@gmail.com", false),
                    new Address("2 Clementi Road", true), Collections.emptySet()));
            addressBook1.addPerson(new Person(new Name("Candy Destiny"), new Phone("93339333", true),
                    new Email("cd@gmail.com", false), new Address("3 Clementi Road", true), Collections.emptySet()));
        } catch (IllegalValueException e) {
            e.printStackTrace();
            assert false : "not possible";
        }
        addressBook1.sort();
        assertSorted(addressBook1);
    }

    private void assertSorted(AddressBook addressBook1){
        Iterator<Person> iterator1 = addressBook1.getAllPersons().iterator();
        Iterator<Person> iterator = addressBook.getAllPersons().iterator();
        while(iterator.hasNext()){
            assertTrue(iterator.next().getName().fullName.equals(iterator1.next().getName().fullName));
        }
    }
}