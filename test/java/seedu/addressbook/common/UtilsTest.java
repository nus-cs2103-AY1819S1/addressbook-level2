package seedu.addressbook.common;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;

import org.junit.Test;

public class UtilsTest {

    @Test
    public void isAnyNull() {
        //test one object
        assertHasNull((Object)null);
        assertNoNull(1);
        assertNoNull("TestString");
        assertNoNull('1');

        //test objects of same type
        assertHasNull(1, 2, null);
        assertHasNull('a', 'b', 'c', null);
        assertHasNull(null, "ABC", "DEF");
        assertHasNull(0.1, null);

        assertNoNull(1, 2, 3, 4, 5);
        assertNoNull('a', 'b', 'c');
        assertNoNull("ABC", "DEF");
        assertNoNull(0.1, 0.23, 0.456);

        //test different object types
        assertHasNull(1, 'a', 0.35, "TEST", null);
        assertNoNull(1, 2, 3, 0.1, "ab", true, '2');
    }

    private void assertHasNull(Object... objects) {
        assertTrue(Utils.isAnyNull(objects));
    }

    private void assertNoNull(Object... objects) {
        assertFalse(Utils.isAnyNull(objects));
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
