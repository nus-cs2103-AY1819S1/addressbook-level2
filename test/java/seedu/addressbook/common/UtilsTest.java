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

        isAnyNull("abc", 555, null);
        isAnyNull((Object) null);
        isAnyNull(Integer.valueOf(-123), 1, null);
        isAnyNull(null, null, null);

        isAllNonNull("abc", "def");
        isAllNonNull(new Object());
        isAllNonNull(Integer.valueOf(1));
        isAllNonNull(1, 2, 3, 0.4f, 1.2);
    }

    private void assertAreUnique(Object... objects) {
        assertTrue(Utils.elementsAreUnique(Arrays.asList(objects)));
    }

    private void assertNotUnique(Object... objects) {
        assertFalse(Utils.elementsAreUnique(Arrays.asList(objects)));
    }

    private void isAnyNull(Object... objects) {
        assertTrue(Utils.isAnyNull(objects));
    }

    private void isAllNonNull(Object... objects) {
        assertFalse(Utils.isAnyNull(objects));
    }
}
