package seedu.addressbook.commands;

import static junit.framework.TestCase.assertEquals;

import org.junit.Test;
import seedu.addressbook.data.AddressBook;
import seedu.addressbook.data.person.ReadOnlyPerson;
import seedu.addressbook.data.person.UniquePersonList;

import java.util.Collections;
import java.util.List;

public class GenerateCommandTest {
    private static final List<ReadOnlyPerson> EMPTY_PERSON_LIST = Collections.emptyList();

    private AddressBook book;
    private UniquePersonList people;

    @Test
    public void generateCommand_addressBookAlreadyContainsNames() {
        book = new AddressBook();
        GenerateCommand command = new GenerateCommand();
        command.setData(book, EMPTY_PERSON_LIST);
        CommandResult result = command.execute();

        CommandResult duplicateResult = command.execute();
        assertEquals(GenerateCommand.MESSAGE_DUPLICATE_PEOPLE, duplicateResult.feedbackToUser);
    }

}
