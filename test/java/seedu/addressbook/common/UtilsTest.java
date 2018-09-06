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

    private void assertAreUnique(Object... objects) {
        assertTrue(Utils.elementsAreUnique(Arrays.asList(objects)));
    }

    private void assertNotUnique(Object... objects) {
        assertFalse(Utils.elementsAreUnique(Arrays.asList(objects)));
    }

    @Test
    public void isAnyNull() {
        // no null objects
        assertNoNull("");
        assertNoNull(2, "hello", "");
        assertNoNull("null");

        // have null objects
        assertAnyNull();
        assertAnyNull(null, null, null, null);
        assertAnyNull("hi", "hello", null, "bye", "ciao");
        assertAnyNull(1, 2, 3, 4, 5, 6, null, 8);
        assertAnyNull((Object) null);
    }

    private void assertAnyNull(Object... objects) {
        assertTrue(Utils.isAnyNull(Arrays.asList(objects)));
    }

    private void assertNoNull(Object... objects) {
        assertFalse(Utils.isAnyNull(Arrays.asList(objects)));
    }
}
