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
    public void isAnyNull() throws Exception {
        // objects with no null
        assertIsNotNull(1);
        assertIsNotNull("abc");
        assertIsNotNull(Integer.valueOf(1));

        // objects with one null
        assertIsNull((Object) null);
        assertIsNull(1, null);
        assertIsNull("abc", null);
        assertIsNull(Integer.valueOf(1), null);

        // objects with more than one null
        assertIsNull(null, null, null);
        assertIsNull(null, 1, null);
        assertIsNull(null, "abc", null);
        assertIsNull(Integer.valueOf(1), null, null);
    }

    private void assertIsNull(Object... objects) {
        assertTrue(Utils.isAnyNull(objects));
    }

    private void assertIsNotNull(Object... objects) {
        assertFalse(Utils.isAnyNull(objects));
    }
    private void assertAreUnique(Object... objects) {
        assertTrue(Utils.elementsAreUnique(Arrays.asList(objects)));
    }

    private void assertNotUnique(Object... objects) {
        assertFalse(Utils.elementsAreUnique(Arrays.asList(objects)));
    }
}
