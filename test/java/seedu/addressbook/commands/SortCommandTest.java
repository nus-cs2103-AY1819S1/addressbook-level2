package seedu.addressbook.commands;

import org.junit.Test;
import seedu.addressbook.data.AddressBook;
import seedu.addressbook.data.exception.IllegalValueException;
import seedu.addressbook.data.person.*;
import seedu.addressbook.data.tag.Tag;
import seedu.addressbook.util.TestUtil;

import java.util.*;

import static org.junit.Assert.*;

public class SortCommandTest {

    @Test
    // Test if sort actually sorts the persons list properly in ascending order
    public void sortCommand_validOrder() throws IllegalValueException {
        final String[] UnOrderedNames = { "Bahahaha", "Amamam", "Cdcdccc" };
        final String[] OrderdNames = { "Amamam", "Bahahaha", "Cdcdccc" };
        UniquePersonList persons = new UniquePersonList();
        Set<Tag> tagSet = new HashSet<>();
        for (String i : UnOrderedNames){
            Person p = new Person(
                    new Name(i),
                    new Phone(Phone.EXAMPLE, true),
                    new Email(Email.EXAMPLE, false),
                    new Address(Address.EXAMPLE, true),
                    tagSet
            );
            persons.add(p);
        }

        persons.sort();

        Iterator<Person> litr = persons.iterator();

        int count = 0;
        while(litr.hasNext()) {
            Person element = litr.next();
            if (!element.getName().fullName.equals(OrderdNames[count])) {
                fail("Person order is wrong");
            }
            count++;
        }
    }

}
