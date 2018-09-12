package seedu.addressbook.common;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

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
    public void isAnyNull() {
        // only 1 object
        assertTrue(Utils.isAnyNull((Object) null));
        assertFalse(Utils.isAnyNull(1));
        assertFalse(Utils.isAnyNull(""));
        assertFalse(Utils.isAnyNull("something"));
        assertFalse(Utils.isAnyNull(new char[20]));
        assertFalse(Utils.isAnyNull(true));

        // multiple objects of same type
        assertTrue(Utils.isAnyNull(null, null, null));
        assertFalse(Utils.isAnyNull(1,2,3));
        assertFalse(Utils.isAnyNull("", "Hello", "test"));
        assertFalse(Utils.isAnyNull(true, false));

        // multiple objects of different type
        assertTrue(Utils.isAnyNull(1, 2, 3, "Hello", null, "test", new int[5], true));
        assertFalse(Utils.isAnyNull(1, 2, 3, "Hello", "test", new int[47], false));
    }

    private void assertAreUnique(Object... objects) {
        assertTrue(Utils.elementsAreUnique(Arrays.asList(objects)));
    }

    private void assertNotUnique(Object... objects) {
        assertFalse(Utils.elementsAreUnique(Arrays.asList(objects)));
    }
}
