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
    public void elementsAreNull() throws Exception {

        // Contains null objects
        assertIsAnyNull((Object) null, "hello");
        assertIsAnyNull(1, 2, (Object) null, 4);
        assertIsAnyNull(null, null);
        assertIsAnyNull("v", null, "s", null);

        // Contains no null objects
        assertIsAllNotNull(711);
        assertIsAllNotNull("hello");
        assertIsAllNotNull(7, 11);
        assertIsAllNotNull("v", "s");

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

    private void assertIsAllNotNull(Object... objects) {
        assertFalse(Utils.isAnyNull(objects));
    }

}
