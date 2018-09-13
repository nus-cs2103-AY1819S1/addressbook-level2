package seedu.addressbook.commands;

import seedu.addressbook.common.Messages;
import seedu.addressbook.data.AddressBook;
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
     * @param targetIndex last visible listing index of the target person
     */
    public Command(int targetIndex) {
        this.setTargetIndex(targetIndex);
    }

    protected Command() {
    }

    /**
     * Generates the MESSAGE_USAGE for a command.
     * @param commandWord The command's COMMAND_WORD
     * @param commandDesc A brief description of what the command does
     * @param commandParams The list of parameters the command accepts, or "none" if the command has no parameters
     * @param commandEgParams An example of a set of valid parameter values to pass to the command, give "" if the command has no parameters
     * @return the MESSAGE_USAGE for a command.
     */
    protected static String getMessageUsage(
        String commandWord,
        String commandDesc,
        String commandParams,
        String commandEgParams) {

        return commandWord + ": " + commandDesc + "\n"
            + "    Parameters: " + commandParams + "\n"
            + "    Example: " + commandWord + (commandEgParams.equals("") ? "" : " " + commandEgParams);
    }

    /**
     * Overloaded getMessageUsage() for commands that take in no parameters
     * @param commandWord The command's COMMAND_WORD
     * @param commandDesc A brief description of what the command does
     * @return the MESSAGE_USAGE for a command.
     */
    protected static String getMessageUsage(
        String commandWord,
        String commandDesc) {

        return getMessageUsage(commandWord, commandDesc, "none", "");
    }

    /**
     * Constructs a feedback message to summarise an operation that displayed a listing of persons.
     *
     * @param personsDisplayed used to generate summary
     * @return summary message for persons displayed
     */
    public static String getMessageForPersonListShownSummary(List<? extends ReadOnlyPerson> personsDisplayed) {
        return String.format(Messages.MESSAGE_PERSONS_LISTED_OVERVIEW, personsDisplayed.size());
    }

    /**
     * Executes the command and returns the result.
     */
    public CommandResult execute(){
        throw new UnsupportedOperationException("This method is to be implemented by child classes");
    };

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

    public int getTargetIndex() {
        return targetIndex;
    }

    public void setTargetIndex(int targetIndex) {
        this.targetIndex = targetIndex;
    }
}
