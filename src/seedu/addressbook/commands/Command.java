package seedu.addressbook.commands;

import seedu.addressbook.common.Messages;
import seedu.addressbook.data.AddressBook;
import seedu.addressbook.data.person.Person;
import seedu.addressbook.data.person.ReadOnlyPerson;

import java.util.List;

import static seedu.addressbook.ui.TextUi.DISPLAYED_INDEX_OFFSET;

/**
 * Represents an executable command.
 */
public class Command {

    protected AddressBook addressBook;
    protected List<? extends ReadOnlyPerson> relevantPersons;
    private int targetIndex = -1;

    /**
     * Constructor to keep last visible listing index.
     *
     * @param targetIndex last visible listing index of the target person
     */
    public Command(int targetIndex) {
        this.setTargetIndex(targetIndex);
    }

    /**
     * Default constructor.
     */
    protected Command() {
    }

    /**
     * Constructs a feedback message to summarise an operation that displayed a listing of persons.
     *
     * @param personsDisplayed used to generate summary
     * @return summary message for persons displayed
     */
    public static String getMessageForPersonListShownSummary(
            List<? extends ReadOnlyPerson> personsDisplayed) {
        return String.format(Messages.MESSAGE_PERSONS_LISTED_OVERVIEW, personsDisplayed.size());
    }

    /**
     * Constructs a feedback message to summarise an operation that sort the address book.
     *
     * @param personsDisplayed used to generate summary
     * @return summary message for address book sorted.
     */
    public static String getMessageForAddressBookSortedSummary(
            List<? extends ReadOnlyPerson> personsDisplayed) {
        return String.format("%s %s", Messages.MESSAGE_PERSONS_SORTED_OVERVIEW,
                getMessageForPersonListShownSummary(personsDisplayed));
    }

    /**
     * Executes the command and returns the result.
     */
    public CommandResult execute() {
        throw new UnsupportedOperationException("This method is to be implemented by child classes");
    }

    ;

    /**
     * Supplies the data the command will operate on.
     */
    public void setData(AddressBook addressBook, List<? extends ReadOnlyPerson> relevantPersons) {
        this.addressBook = addressBook;
        this.relevantPersons = relevantPersons;
    }

    /**
     * Extracts the the target person in the last shown list from the given arguments.
     *
     * @throws IndexOutOfBoundsException if the target index is out of bounds of the last viewed listing
     */
    protected ReadOnlyPerson getTargetPerson() throws IndexOutOfBoundsException {
        return relevantPersons.get(getTargetIndex() - DISPLAYED_INDEX_OFFSET);
    }

    /**
     * Returns the last visible target index.
     */
    public int getTargetIndex() {
        return targetIndex;
    }

    /**
     * Set the last visible target index.
     *
     * @param targetIndex last visible target index.
     */
    public void setTargetIndex(int targetIndex) {
        this.targetIndex = targetIndex;
    }
}
