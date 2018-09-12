package seedu.addressbook.commands;

import seedu.addressbook.common.Messages;
import seedu.addressbook.data.exception.IllegalValueException;
import seedu.addressbook.data.person.ReadOnlyPerson;
import seedu.addressbook.data.person.UniquePersonList;

public class TagCommand extends Command {
    public static final String COMMAND_WORD = "tag";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Tags the person identified by the index number used in the last person listing with the input tag.\n"
            + "Parameters: INDEX TAG\n"
            + "Example: " + COMMAND_WORD + " 1 friend";

    public static final String MESSAGE_TAG_PERSON_SUCCESS = "Tagged Person: %1$s as %2$s";

    public static final String MESSAGE_TAG_PERSON_ALREADY_EXISTS = "The person already been tagged with the " +
            "given tag.";

    private String newTagString;

    public TagCommand(int targetVisibleIndex, String newTagString) {
        super(targetVisibleIndex);
        this.newTagString = newTagString;
    }

    @Override
    public CommandResult execute() {
        try {
            final ReadOnlyPerson target = getTargetPerson();
            if (addressBook.tagPerson(target, newTagString)) {
                return new CommandResult(String.format(MESSAGE_TAG_PERSON_SUCCESS, target, newTagString));
            } else {
                return new CommandResult(MESSAGE_TAG_PERSON_ALREADY_EXISTS);
            }
        } catch (IndexOutOfBoundsException ie) {
            return new CommandResult(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        } catch (UniquePersonList.PersonNotFoundException pnfe) {
            return new CommandResult(Messages.MESSAGE_PERSON_NOT_IN_ADDRESSBOOK);
        } catch (IllegalValueException ive) {
            return new IncorrectCommand(ive.getMessage()).execute();
        }
    }
}
