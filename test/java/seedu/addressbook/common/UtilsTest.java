package seedu.addressbook.common;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import seedu.addressbook.commands.ClearCommand;

public class UtilsTest {

    @Test (expected = NullPointerException.class)
    public void testAnyObjectNull() {
        // 1 null
        assertTrue(Utils.isAnyNull(null));
        // multiple null
        assertTrue(Utils.isAnyNull(null, null, null));

        // 1 object
        assertFalse(Utils.isAnyNull(new Object()));
        // multiple objects
        assertFalse(Utils.isAnyNull(new Object(), new Object(), new Object()));
        // mixed null and objects
        assertTrue(Utils.isAnyNull(null, new Object(), new Object()));
    }

    @Test
    public void elementsAreUnique() throws Exception {
        // empty list
        assertAreUnique();

        // only one object
        assertAreUnique((Object) null);
        assertAreUnique(1);
        assertAreUnique("");
        assertAreUnique("abc");

        // all objects unique
        assertAreUnique("abc", "ab", "a");
        assertAreUnique(1, 2);

        // some identical objects
        assertNotUnique("abc", "abc");
        assertNotUnique("abc", "", "abc", "ABC");
        assertNotUnique("", "abc", "a", "abc");
        assertNotUnique(1, Integer.valueOf(1));
        assertNotUnique(null, 1, Integer.valueOf(1));
        assertNotUnique(null, null);
        assertNotUnique(null, "a", "b", null);
    }

    private void assertAreUnique(Object... objects) {
        assertTrue(Utils.elementsAreUnique(Arrays.asList(objects)));
    }

    private void assertNotUnique(Object... objects) {
        assertFalse(Utils.elementsAreUnique(Arrays.asList(objects)));
    }
}
