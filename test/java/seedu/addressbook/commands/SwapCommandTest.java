package seedu.addressbook.commands;

import org.junit.Test;
import seedu.addressbook.data.AddressBook;
import seedu.addressbook.data.person.ReadOnlyPerson;
import seedu.addressbook.util.TypicalPersons;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class SwapCommandTest {

    /**
     * Populate address book with 4 contacts
     * @see TypicalPersons
     */
    private final AddressBook addressBook = new TypicalPersons().getTypicalAddressBook();

    private final int ARRAY_OFFSET = 1;

    //The position of the 4 people in the address book.
    private final int AMY_POS = 1;
    private final int BILL_POS = 2;
    private final int CANDY_POS = 3;
    private final int DAN_POS = 4;

    @Test
    public void execute() {
        assertSwapSuccessful(AMY_POS, BILL_POS);
        assertSwapSuccessful(CANDY_POS, DAN_POS);
        assertSwapFailed(AMY_POS,AMY_POS);
        assertSwapFailed(AMY_POS,9999);
        assertSwapFailed(0,BILL_POS);
    }

    public void assertSwapSuccessful(int target1, int target2) {

        //Swap command already handles the offset
        //No need to minus ARRAY_OFFSET.
        Command command = new SwapCommand(target1, target2);
        command.addressBook = addressBook;


        //Adjust for offset of target.
        int target1Index = target1 - ARRAY_OFFSET;
        int target2Index = target2 - ARRAY_OFFSET;

        //Retrieve information of both target to swap
        List<ReadOnlyPerson> personList = addressBook.getAllPersons().immutableListView();
        ReadOnlyPerson person1 = personList.get(target1Index);
        ReadOnlyPerson person2 = personList.get(target2Index);

        //Execute command
        CommandResult result = command.execute();

        //Get updated addressbook
        personList = addressBook.getAllPersons().immutableListView();

        //Check if result output is same
        assertEquals(SwapCommand.MESSAGE_SWAP_SUCCESS, result.feedbackToUser);
        //Check if the person is swapped in address book.
        assertEquals(person1, personList.get(target2Index));
        assertEquals(person2, personList.get(target1Index));
    }

    public void assertSwapFailed(int target1, int target2) {
        Command command = new SwapCommand(target1, target2);
        command.addressBook = addressBook;
        CommandResult result = command.execute();
        assertNotEquals(SwapCommand.MESSAGE_SWAP_SUCCESS, result.feedbackToUser);
    }

}
