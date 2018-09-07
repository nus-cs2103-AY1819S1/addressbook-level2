package seedu.addressbook.common;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;

public class UtilsTest {

    @Test
    public void isAnyNull() throws Exception {
        Object nullObj = null;

        //contains some null objects
        assertIsAnyNull(nullObj);
        assertIsAnyNull(nullObj, 1, Integer.valueOf(1));
        assertIsAnyNull(nullObj, "a", "b", nullObj);
        assertIsAnyNull(nullObj, nullObj);

        //contains no null objects
        assertNotNull(1);
        assertNotNull("");
        assertNotNull("abc");
        assertNotNull("abc", "ab", "a");
        assertNotNull(1, 2);
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

    private void assertIsAnyNull(Object... objects) { assertTrue(Utils.isAnyNull(objects)); }

    private void assertNotNull(Object... objects) { assertFalse(Utils.isAnyNull(objects)); }
}
