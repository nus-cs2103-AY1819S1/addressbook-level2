package seedu.addressbook.common;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;

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
    public void elementsContainsNull() {
        Object nullObj = null;
        // some object contains null
        assertContainsNull("", 1, new ArrayList<>(), nullObj, "cc");
        assertContainsNull("", 1, new ArrayList<>(), nullObj);
        assertContainsNull(nullObj);
        assertContainsNull("a", nullObj);

        // all objects must not contain null
        assertNoNull("");
        assertNoNull(1);
        assertNoNull(1, "abc", new ArrayList<>(), 2, 1.0f, "bbb");
    }

    private void assertAreUnique(Object... objects) {
        assertTrue(Utils.elementsAreUnique(Arrays.asList(objects)));
    }

    private void assertNotUnique(Object... objects) {
        assertFalse(Utils.elementsAreUnique(Arrays.asList(objects)));
    }

    private void assertContainsNull(Object ... objects) {
        assertTrue(Utils.isAnyNull(objects));
    }

    private void assertNoNull(Object ... objects) {
        assertFalse(Utils.isAnyNull(objects));
    }
}
