package seedu.addressbook.common;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class UtilsTest {

    @Test
    public void isAnyNull() throws Exception {
        // empty list
        assertNotAnyNull();

        // only one object
        assertIsAnyNull( (Object)null);
        assertNotAnyNull(1);
        assertNotAnyNull("");
        assertNotAnyNull("abc");

        // all objects unique
        assertNotAnyNull("abc", "ab", "a");
        assertNotAnyNull(1, 2);

        // some identical objects
        assertNotAnyNull("abc", "abc");
        assertNotAnyNull("abc", "", "abc", "ABC");
        assertNotAnyNull("", "abc", "a", "abc");
        assertNotAnyNull(1, Integer.valueOf(1));
        assertIsAnyNull(null, 1, Integer.valueOf(1));
        assertIsAnyNull(null, null);
        assertIsAnyNull(null, "a", "b", null);
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
        assertTrue(Utils.isAnyNull(objects));
    }

    private void assertNotAnyNull(Object... objects) {
        assertFalse(Utils.isAnyNull(objects));
    }
}
