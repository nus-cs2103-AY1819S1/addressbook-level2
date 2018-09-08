package seedu.addressbook.common;

import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class UtilsTest {


    @Test
    public void elementsAreUnique() {
        // empty list
        assertAreUnique();
        assertContainsNoNull();

        // only one object
        assertAreUnique((Object) null);
        assertContainsNull((Object) null);
        assertAreUnique(1);
        assertContainsNoNull(1);
        assertAreUnique("");
        assertContainsNoNull("");
        assertAreUnique("abc");
        assertContainsNoNull("abc");

        // all objects unique
        assertAreUnique("abc", "ab", "a");
        assertContainsNoNull("abc", "ab", "a");
        assertAreUnique(1, 2);
        assertContainsNoNull(1, 2);

        // some identical objects
        assertNotUnique("abc", "abc");
        assertContainsNoNull("abc", "abc");
        assertNotUnique("abc", "", "abc", "ABC");
        assertContainsNoNull("abc", "", "abc", "ABC");
        assertNotUnique("", "abc", "a", "abc");
        assertContainsNoNull("", "abc", "a", "abc");
        assertNotUnique(1, Integer.valueOf(1));
        assertContainsNoNull(1, Integer.valueOf(1));
        assertNotUnique(null, 1, Integer.valueOf(1));
        assertContainsNull(null, 1, Integer.valueOf(1));
        assertNotUnique(null, null);
        assertContainsNull(null, null);
        assertNotUnique(null, "a", "b", null);
        assertContainsNull(null, "a", "b", null);
    }

    private void assertAreUnique(Object... objects) {
        assertTrue(Utils.elementsAreUnique(Arrays.asList(objects)));
    }

    private void assertNotUnique(Object... objects) {
        assertFalse(Utils.elementsAreUnique(Arrays.asList(objects)));
    }

    private void assertContainsNull(Object... objects) {
        assertTrue(Utils.isAnyNull(objects));
    }

    private void assertContainsNoNull(Object... objects) {
        assertFalse(Utils.isAnyNull(objects));
    }
}
