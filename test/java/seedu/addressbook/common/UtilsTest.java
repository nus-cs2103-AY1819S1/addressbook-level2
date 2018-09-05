package seedu.addressbook.common;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static seedu.addressbook.common.Utils.isAnyNull;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;

public class UtilsTest {

    @Test
    public void testIsAnyNull(){
        // test one null arg
        assertTrue(isAnyNull((Object)null));

        // test first arg null
        Object[] items1 = {null, "not empty"};
        assertTrue(isAnyNull(items1));

        // test middle arg null
        Object[] items2 = {"not empty", null, "not empty"};
        assertTrue(isAnyNull(items2));

        // test last arg null
        Object[] items3 = {"not empty", null};
        assertTrue(isAnyNull(items3));

        // test multiple nulls
        Object[] items4 = {"not empty", null, null};
        assertTrue(isAnyNull(items4));
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
}
