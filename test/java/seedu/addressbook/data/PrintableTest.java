package seedu.addressbook.data;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import seedu.addressbook.data.exception.IllegalValueException;
import seedu.addressbook.data.person.Name;

public class PrintableTest {

    @Test
    public void print_correctNameOutput() throws IllegalValueException {
        Name name = new Name("Ang Test");
        assertEquals(name.getPrintableString(), "Full Name: Ang Test");
    }
}
