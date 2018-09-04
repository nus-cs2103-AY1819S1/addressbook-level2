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

    @Test
    public void isAnyNull() throws Exception {
        Object x = null;
        
        // no arguments
        assertFalse(Utils.isAnyNull());
        
        // one argument
        assertFalse(Utils.isAnyNull("a"));
        assertFalse(Utils.isAnyNull(1));
        assertFalse(Utils.isAnyNull(new Object()));
        assertTrue(Utils.isAnyNull(x));

        // two arguments
        assertFalse(Utils.isAnyNull("abs", "abd"));
        assertFalse(Utils.isAnyNull(14, 553));
        assertFalse(Utils.isAnyNull(3000, "asdf"));
        assertTrue(Utils.isAnyNull(null, 1555));
        assertTrue(Utils.isAnyNull("lflflf", null));
        assertTrue(Utils.isAnyNull(new int[5], null));

        // three arguments
        assertFalse(Utils.isAnyNull("btbtbt", "lflfl", 5555));
        assertFalse(Utils.isAnyNull(14, 553, "abcd"));
        assertFalse(Utils.isAnyNull(3000, "asdf", new float[4]));
        assertTrue(Utils.isAnyNull(null, 1555, null));
        assertTrue(Utils.isAnyNull("lflflf", 15353, null));
        assertTrue(Utils.isAnyNull(new int[5], null, "abcde"));
    }
    
    private void assertAreUnique(Object... objects) {
        assertTrue(Utils.elementsAreUnique(Arrays.asList(objects)));
    }

    private void assertNotUnique(Object... objects) {
        assertFalse(Utils.elementsAreUnique(Arrays.asList(objects)));
    }
}
