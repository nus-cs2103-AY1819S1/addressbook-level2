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
    public void isAnyNull() throws Exception {

        // Objects to use for testing
        Object object = new Object();
        String string = "Test";
        int integer = 1;

        // empty list test
        assertFalse(Utils.isAnyNull());

        // non empty list test
        assertFalse(Utils.isAnyNull(object, string, integer));

        // non empty list with null at front
        assertTrue(Utils.isAnyNull(null, string, integer));

        // non empty list with null in the middle
        assertTrue(Utils.isAnyNull(object, null, string, integer));

        // non empty list with null at the end
        assertTrue(Utils.isAnyNull(object, string, integer, null));
    }

}
