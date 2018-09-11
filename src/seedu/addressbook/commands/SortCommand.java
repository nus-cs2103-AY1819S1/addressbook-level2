package seedu.addressbook.commands;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.regex.Pattern;

import seedu.addressbook.data.person.Person;
import seedu.addressbook.data.person.ReadOnlyPerson;

public class SortCommand extends Command {
	public static final String COMMAND_WORD = "sort";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Sorts the Address Book by a given sort key. "
            + "Parameters: [name] [email] [address]\n"
            + "Example: " + COMMAND_WORD
            + " name";
    
    public static final String ILLEGAL_SORTKEY_MESSAGE = "Unsupported sort key indicated!";
    
    private static final String[] KEYWORDS = new String[] {
    		"name",
    		"email",
    		"address"
    };
    
    /** Note the order of the sortKey method names should correspond to the index of the keyword in KEYWORDS */
    private static final String[] SORTKEY_METHOD_NAMES = new String[] {
    		"getName",
    		"getEmail",
    		"getAddress"
    };	
    
    // We create the Pattern to match our keywords (only one of the keywords)
    // based on the keywords we indicate.
    private static final StringBuilder KEYWORDS_ARGS_FORMAT_BUILDER;
    static {
    	KEYWORDS_ARGS_FORMAT_BUILDER = new StringBuilder();
    	KEYWORDS_ARGS_FORMAT_BUILDER.append("(?<keywords>\\b");
    	for (int i = 0; i < KEYWORDS.length; i++) {
    		KEYWORDS_ARGS_FORMAT_BUILDER.append(KEYWORDS[i]);
    		if (i == KEYWORDS.length - 1) {
    			KEYWORDS_ARGS_FORMAT_BUILDER.append("(?!.+)");
    		}
    		KEYWORDS_ARGS_FORMAT_BUILDER.append("(?!.+)|");
    	}
    	KEYWORDS_ARGS_FORMAT_BUILDER.append("\\b)");
    }
    
    // Matches exactly one of the keywords
    public static final Pattern KEYWORDS_ARGS_FORMAT = Pattern.compile(KEYWORDS_ARGS_FORMAT_BUILDER.toString());
    
    private final String keyExtractorName;
    private final String key;
    
    public SortCommand(String key) {
    	this.key = key;
    	keyExtractorName = getKeyExtractorName(key);
    }
    
    public String getKey() {
    	return key;
    }
    
    public String getKeyExtractorName(String key) {
    	try {
    		return SORTKEY_METHOD_NAMES[Arrays.asList(KEYWORDS).indexOf(key)];
    	} catch (ArrayIndexOutOfBoundsException e) {
    		return "";
    	}
    }
    
    @Override
    public CommandResult execute() {
    	try {
	    	Method keyExtractor = Person.class.getMethod(keyExtractorName);
	    	
	    	addressBook.sort(new Comparator<Person>() {
	    		@Override
	    		public int compare(Person p1, Person p2) {
	    			try {
						return (keyExtractor.invoke(p1).toString()).compareTo(keyExtractor.invoke(p2).toString());
					} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
						e.printStackTrace();
						return 0; // arbitrary choice
					}
	    		}
	    	});
    	} catch (NoSuchMethodException e) {
    		return new CommandResult(ILLEGAL_SORTKEY_MESSAGE);
    	}
    	
    	List<ReadOnlyPerson> allPersons = addressBook.getAllPersons().immutableListView();
        return new CommandResult(getMessageForPersonListShownSummary(allPersons), allPersons);
    }
}
