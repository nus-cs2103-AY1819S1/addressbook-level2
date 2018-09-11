package seedu.addressbook.commands;

import org.junit.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import static org.junit.Assert.fail;

public class ImportCommandTest {

    @Test
    public void importCommand_invalidFileName_throwsException() {
        String invalidFileName = "123asd";

        try {
            Path pathToImportAB = Paths.get(invalidFileName);
            List<String> importedEntry = Files.readAllLines(pathToImportAB);
        } catch (IOException e) {
            return;
        }
        String error = String.format(
            "An import command was successfully constructed with invalid " +
                "filename " +
                "input: %s %s %s %s %s %s %s %s", invalidFileName);
        fail(error);
    }


}
