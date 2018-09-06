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
        // null object
        assertTrue(Utils.isAnyNull((Object) null));

        // variable nulls within sequence
        assertTrue(Utils.isAnyNull(null, null, null));
        assertTrue(Utils.isAnyNull(null, 1, 2));
        assertTrue(Utils.isAnyNull(1, "hello", null));

        // no nulls
        assertFalse(Utils.isAnyNull(""));
        assertFalse(Utils.isAnyNull(1, 3, 6));
        assertFalse(Utils.isAnyNull("test", 7, -6));
        assertFalse(Utils.isAnyNull("test", false, -6));
    }

    private void assertAreUnique(Object... objects) {
        assertTrue(Utils.elementsAreUnique(Arrays.asList(objects)));
    }

    private void assertNotUnique(Object... objects) {
        assertFalse(Utils.elementsAreUnique(Arrays.asList(objects)));
    }
}
