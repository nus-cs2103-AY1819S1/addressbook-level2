package seedu.addressbook.common;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import static org.junit.Assert.*;

public class UtilsTest {

    @Test
    public void isAnyNull() throws Exception {
        // empty list
        assertNotAnyNull();

        // only one object
        assertIsAnyNull((Object) null);
        assertIsAnyNull(1);
        assertIsAnyNull("");
        assertIsAnyNull("abc");

        // all objects unique
        assertIsAnyNull("abc", "ab", "a");
        assertIsAnyNull(1, 2);

        // some identical objects
        assertNotAnyNull("abc", "abc");
        assertNotAnyNull("abc", "", "abc", "ABC");
        assertNotAnyNull("", "abc", "a", "abc");
        assertNotAnyNull(1, Integer.valueOf(1));
        assertNotAnyNull(null, 1, Integer.valueOf(1));
        assertNotAnyNull(null, null);
        assertNotAnyNull(null, "a", "b", null);
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

    private void assertIsAnyNull(Object... objects) {
        assertTrue(Utils.isAnyNull(Arrays.asList(objects)));
    }

    private void assertNotAnyNull(Object... objects) {
        assertFalse(Utils.isAnyNull(Arrays.asList(objects)));
    }
}
