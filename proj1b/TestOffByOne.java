import org.junit.Test;

import static org.junit.Assert.*;

public class TestOffByOne {
    /*
    // You must use this CharacterComparator and not instantiate
    // new ones, or the autograder might be upset.
    static CharacterComparator offByOne = new OffByOne();

    // Your tests go here.

     */
    @Test
    public void testOffByOne() {
        CharacterComparator offByOne = new OffByOne();

        assertTrue(offByOne.equalChars('a', 'b'));
        assertTrue(offByOne.equalChars('e', 'd'));

        assertFalse(offByOne.equalChars('a', 'a'));
        assertFalse(offByOne.equalChars('a', 'f'));
    }
}
