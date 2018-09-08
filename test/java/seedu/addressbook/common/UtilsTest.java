package seedu.addressbook.common;

import java.util.Arrays;

import org.junit.Test;

import static org.junit.Assert.*;

public class UtilsTest {

    @Test
    public void isAnyNull() throws Exception {
        // no objects
        assertIsNotNull();

        // null object(s)
        assertIsNull((Object) null);
        assertIsNull((Object) null, (Object) null);
        assertIsNull((Object) null, (Object) null, (Object) null);

        // one non-null object
        assertIsNotNull(1);
        assertIsNotNull("");
        assertIsNotNull("abc");

        // one or more null object amongst a few objects
        assertIsNull((Object) null, 1, "abc");
        assertIsNull("", 1, (Object) null);
        assertIsNull(1, (Object) null, "");
        assertIsNull((Object) null, (Object) null, 1);
        assertIsNull((Object) null, "abc", (Object) null);
        assertIsNull("", (Object) null, (Object) null);


        // few non-null objects
        assertIsNotNull(1, "abc");
        assertIsNotNull("abc", "");
        assertIsNotNull("", 1);
        assertIsNotNull(1, "abc", "");
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

    private void assertIsNull(Object... objects) { assertTrue(Utils.isAnyNull(objects)); }

    private void assertIsNotNull(Object... objects) { assertFalse(Utils.isAnyNull(objects)); }

    private void assertAreUnique(Object... objects) {
        assertTrue(Utils.elementsAreUnique(Arrays.asList(objects)));
    }

    private void assertNotUnique(Object... objects) {
        assertFalse(Utils.elementsAreUnique(Arrays.asList(objects)));
    }
}

