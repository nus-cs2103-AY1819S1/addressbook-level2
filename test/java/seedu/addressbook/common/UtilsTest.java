package seedu.addressbook.common;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.List;

import com.sun.jdi.IntegerValue;
import org.junit.Test;

public class UtilsTest {

    @Test
    public void elementsAreNull () {
        assertNotNull();

        // one object
        assertIsNull((Object) null);
        assertNotNull(1);
        assertNotNull("");
        assertNotNull("abc");

        // more than one object
        assertIsNull(1, "abc", "", null);
        assertIsNull(null, null, 2, 5);
        assertNotNull(1, "abc", "", 2);
        assertNotNull(Integer.valueOf(52), "ABC", 6);

    }

    private void assertIsNull(Object... objects) { assertTrue(Utils.isAnyNull(objects)); }
    private void assertNotNull(Object... objects) {
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
