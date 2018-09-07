package seedu.addressbook.common;

import org.junit.Test;
import seedu.addressbook.commands.CommandType;

import static org.junit.Assert.*;

public class TextUtilsTest {

    @Test
    public void shouldCalculateJaroWinklerDistance() {
        assertTrue(TextUtils.calculateDistance("hello", "hallo") > TextUtils.THRESHOLD);
        assertTrue(TextUtils.calculateDistance("ABC Corporation", "ABC Corp") > TextUtils.THRESHOLD);
        assertTrue(TextUtils.calculateDistance("fly", "ant") == 0);
    }

    @Test
    public void shouldFindClosestCmd() {
        assertEquals(TextUtils.getClosestCommand("adf"), CommandType.ADD.commandName());
        assertEquals(TextUtils.getClosestCommand("viewll"), CommandType.VIEWALL.commandName());
        assertNull(TextUtils.getClosestCommand(""));
    }
}
