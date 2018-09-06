package seedu.addressbook.common;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static seedu.addressbook.common.Utils.isAnyNull;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import seedu.addressbook.data.person.Name;

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
    public void isAnyNullTest() throws Exception {
        assertFalse(isAnyNull());
        assertFalse(isAnyNull(1, 2));
        assertTrue(isAnyNull(null, null));
        assertTrue(isAnyNull(1, 2, null));
    }

    @Test
    public void nameSimilarityTest() throws Exception {
        Name name = new Name("John Smith");
        // Two identical names should be similar.
        assertTrue(name.isSimilar(new Name("John Smith")));

        // Two very different names should not be similar.
        assertFalse(name.isSimilar(new Name("Toodles McDoodles")));

        // A name with one different letter should be considered as similar.
        assertTrue(name.isSimilar(new Name("John Smitt")));

        // A name with two different letters should be considered as similar.
        assertTrue(name.isSimilar(new Name("Jhon Smith")));

        // A name with three different letters should not be considered as similar.
        assertFalse(name.isSimilar(new Name("Shon Smith")));
    }

    private void assertAreUnique(Object... objects) {
        assertTrue(Utils.elementsAreUnique(Arrays.asList(objects)));
    }

    private void assertNotUnique(Object... objects) {
        assertFalse(Utils.elementsAreUnique(Arrays.asList(objects)));
    }
}
