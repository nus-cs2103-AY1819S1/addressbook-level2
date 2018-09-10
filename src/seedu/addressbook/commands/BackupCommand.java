package seedu.addressbook.commands;
import seedu.addressbook.storage.StorageFile;
import seedu.addressbook.storage.StorageFile.InvalidStorageFilePathException;

/**
 * Cache the current state of the address book in a backup file.
 */
public class BackupCommand extends Command{

    public static final String DEFAULT_BACKUP_FILEPATH = "backup.txt";

    public static final String COMMAND_WORD = "backup";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Backup current state of the address book.\n"
            + "Example: " + COMMAND_WORD;

    public static final String MESSAGE_SUCCESS = "Backup successful!";
    
    private StorageFile storage;

    @Override
    public CommandResult execute() throws InvalidStorageFilePathException, StorageFile.StorageOperationException { 
        storage = new StorageFile(DEFAULT_BACKUP_FILEPATH);
        storage.save(addressBook);
        return new CommandResult(MESSAGE_SUCCESS);
    }
}


