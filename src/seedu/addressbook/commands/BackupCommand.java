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

    public static final String MESSAGE_FAILURE = "Backup failed";
    
    private StorageFile storage;

    @Override
    public CommandResult execute() {
        try {
            storage = new StorageFile(DEFAULT_BACKUP_FILEPATH);
            storage.save(addressBook);            
        } catch (StorageFile.StorageOperationException | InvalidStorageFilePathException e) {
            System.out.println(e.getMessage());
            return new CommandResult(MESSAGE_FAILURE);
        } 
        return new CommandResult(MESSAGE_SUCCESS);
    }
}


