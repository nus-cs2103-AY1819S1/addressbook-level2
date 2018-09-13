package seedu.addressbook.common;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;

public class UtilsTest {
    private static List<Object> objectList = Arrays.asList(1, "", "abc", "ABC", Integer.valueOf(1));

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

    @Test
    public void isAnyNull() throws Exception {
        // empty list (it has no null objects)
        assertHasNoNull();

        // there are null objects
        assertHasNull((Object) null);
        assertHasNull((Object) null, 1);
        assertHasNull((Object) null, "");
        assertHasNull((Object) null, "abc");
        assertHasNull((Object) null, 1, "", "abc");

        // there are no null objects
        assertHasNoNull(objectList);
        assertHasNoNull(1, 2);
        assertHasNoNull("abc", "ab", "a");
        assertHasNoNull(objectList, "def", "ghi", "jkl");

        // some null objects
        assertHasNoNull("abc", "abc");
        assertHasNoNull(1, Integer.valueOf(1));
        assertHasNoNull("", "abc", "a", "abc");

        assertHasNull(null, null);
        assertHasNull(null, 1, Integer.valueOf(1));
        assertHasNull(null, "a", "b", null);
        assertHasNull(objectList, null, "mno", 3, "pqr", "x");
    }

    private void assertHasNull(Object... objects) {
        assertTrue(Utils.isAnyNull(objects));
    }

    private void assertHasNoNull(Object... objects) {
        assertFalse(Utils.isAnyNull(objects));
    }
}
