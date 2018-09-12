package seedu.addressbook.commands;

import seedu.addressbook.data.person.ReadOnlyPerson;

import java.util.*;

/**
 * Find and lists all persons in address book who is tagged as friend
 * Keyword matching is case sensitive.
 */
public class FindFriendsCommand extends Command{

    public static final String COMMAND_WORD = "findFriends";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds all persons who is tagged as friends "
            + "Example: " + COMMAND_WORD ;

    @Override
    public CommandResult execute() {
        final List<ReadOnlyPerson> personsFound = getPersonsWhoAreFriends();
        return new CommandResult(getMessageForPersonListShownSummary(personsFound), personsFound);
    }

    private List<ReadOnlyPerson> getPersonsWhoAreFriends() {
        final List<ReadOnlyPerson> matchedPersons = new ArrayList<>();
        for (ReadOnlyPerson person : addressBook.getAllPersons()) {

            for (int i = 0; i < person.getTags().size(); i++){
                if(person.getTags().toString().equals("[[friends]]")){
                    matchedPersons.add(person);
                }
            }
        }
        return matchedPersons;
    }
}
