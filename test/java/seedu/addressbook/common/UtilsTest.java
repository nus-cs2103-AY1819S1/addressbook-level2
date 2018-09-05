package seedu.addressbook.common;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;

public class UtilsTest {

    @Test
    public void isAnyNull() throws Exception {
        //empty list
        assertNotHasNull();

        //only one object
        assertNotHasNull("");
        assertNotHasNull(1);
        assertHasNull((Object) null);


        //other tests having null
        assertHasNull(null, 1, Integer.valueOf(1));
        assertHasNull(null, null);
        assertHasNull(null, "a", "b", null);

        //other tests not having null
        assertNotHasNull("abc", "abc");
        assertNotHasNull(1, Integer.valueOf(1));
        assertNotHasNull("", "abc", "a", "abc");
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

    private void assertNotHasNull(Object... objects){
        assertFalse(Utils.isAnyNull(objects));
    }

    private void assertHasNull(Object... objects){
        assertTrue(Utils.isAnyNull(objects));
    }
}
