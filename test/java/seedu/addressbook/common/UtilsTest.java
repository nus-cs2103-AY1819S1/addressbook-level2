package seedu.addressbook.common;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;

public class UtilsTest {

    @Test
    public void isAnyNull() {

        // empty list
        assertNotNull();

        // not empty, has 1 null object
        assertIsNull("String", 1, 'c', false, null);

        // not empty, has several null objects
        assertIsNull(null, 1, 'c', "String", false, null);
        assertIsNull(1, null, 'c', "String", null, false);
        assertIsNull(null , null, 'c', "String", null, false, 1);
        assertIsNull(null, null, null, null, null, null, null);

        // not empty, has no null object
        assertNotNull("String", 1, 'c', false);
        assertNotNull("String", "1", "c", "false");
        assertNotNull(0, 1, 2, 3);
        assertNotNull('c');

    }

    private void assertIsNull(Object... objects) { assertTrue(Utils.isAnyNull(objects)); }

    private void assertNotNull(Object... objects) { assertFalse(Utils.isAnyNull(objects)); }


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
