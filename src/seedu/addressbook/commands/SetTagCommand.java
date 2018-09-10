package seedu.addressbook.commands;

import java.util.HashSet;
import java.util.Set;

import seedu.addressbook.common.Messages;
import seedu.addressbook.data.exception.IllegalValueException;
import seedu.addressbook.data.person.ReadOnlyPerson;
import seedu.addressbook.data.tag.Tag;


/**
 * Sets the tags of the person identified using the last displayed index.
 * Modifies and displays the edited person
 */
public class SetTagCommand extends Command {

    public static final String COMMAND_WORD = "settags";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Sets the tags of the person "
            + "identified by the index number in the last shown person listing.\n"
            + "Parameters: INDEX [t/TAG]\n"
            + "Example: " + COMMAND_WORD + " 1 t/friend t/evil";

    static final String MESSAGE_VIEW_NEW_TAGS = "New Tags: %1$s";

    private Set<Tag> tags;

    public SetTagCommand(int targetVisibleIndex, Set<String> tags) throws IllegalValueException {
        super(targetVisibleIndex);

        // change tags from strings to Tag objects
        final Set<Tag> tagSet = new HashSet<>();
        for (String tagName : tags) {
            tagSet.add(new Tag(tagName));
        }
        this.tags = tagSet;
    }


    @Override
    public CommandResult execute() {
        try {
            final ReadOnlyPerson target = getTargetPerson(); // we must do data change through addressBook
            if (!addressBook.containsPerson(target)) {
                return new CommandResult(Messages.MESSAGE_PERSON_NOT_IN_ADDRESSBOOK);
            }
            boolean success = addressBook.setTags(target, this.tags); //TODO
            return new CommandResult(String.format(MESSAGE_VIEW_NEW_TAGS, target.getAsTextHidePrivate()));
        } catch (IndexOutOfBoundsException ie) {
            return new CommandResult(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }
    }

}