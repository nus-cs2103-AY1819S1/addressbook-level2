package seedu.addressbook.common;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;

public class UtilsTest {

    @Test
    public void isAnyNull() throws Exception {
        // empty list
        assertNotNULL();

        // only one object
        assertAreNULL((Object) null);
        assertNotNULL(1);
        assertNotNULL("");
        assertNotNULL("abc");

        // all objects unique
        assertNotNULL("abc", "ab", "a");
        assertNotNULL(1, 2);
        assertAreNULL("a", "b", null);
        assertAreNULL(null, "a", "b");

        // some identical objects
        assertNotNULL("abc", "abc");
        assertNotNULL("abc", "", "abc", "ABC");
        assertNotNULL("", "abc", "a", "abc");
        assertAreNULL(null, 1, Integer.valueOf(1));
        assertAreNULL(null, null);
        assertAreNULL(null, "a", "b", null);
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

    private void assertAreNULL(Object... objects) {
        assertTrue(Utils.isAnyNull(objects));
    }

    private void assertNotNULL(Object... objects) {
        assertFalse(Utils.isAnyNull(objects));
    }
}
