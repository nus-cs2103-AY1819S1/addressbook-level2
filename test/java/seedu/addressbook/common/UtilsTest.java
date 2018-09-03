package seedu.addressbook.common;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;

public class UtilsTest {


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

    @Test
    public void isAnyNull() {
        // empty argument
        assertIsNotNull();

        // Null in one of the argument
        assertIsNull("abc", null, "def");
        assertIsNull('a', 1, null, 2.1);
        assertIsNull(null, Integer.valueOf(1), 1);
        assertIsNull(1, 2.1, 'a', null);
        assertIsNull(null, null, null);

        // No null supplied
        assertIsNotNull("abc", "def");
        assertIsNotNull('a', "abc", 1, 2.1);
        assertIsNotNull(Double.valueOf(1), Integer.valueOf(2));
    }

    private void assertAreUnique(Object... objects) {
        assertTrue(Utils.elementsAreUnique(Arrays.asList(objects)));
    }

    private void assertNotUnique(Object... objects) {
        assertFalse(Utils.elementsAreUnique(Arrays.asList(objects)));
    }

    private void assertIsNull(Object... objects) {
        assertTrue(Utils.isAnyNull(objects));
    }

    private void assertIsNotNull(Object... objects) {
        assertFalse(Utils.isAnyNull(objects));
    }
}
