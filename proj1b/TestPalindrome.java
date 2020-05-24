import org.junit.Test;
import static org.junit.Assert.*;

public class TestPalindrome {
    // You must use this palindrome, and not instantiate
    // new Palindromes, or the autograder might be upset.
    static Palindrome palindrome = new Palindrome();

    @Test
    public void testWordToDeque() {
        Deque d = palindrome.wordToDeque("persiflage");
        String actual = "";
        for (int i = 0; i < "persiflage".length(); i++) {
            actual += d.removeFirst();
        }
        assertEquals("persiflage", actual);
    }

    @Test
    public void testIsPalindrome1() {
        assertTrue(palindrome.isPalindrome("racecar"));
        assertTrue(palindrome.isPalindrome("aabbbbaa"));
    }

    @Test
    public void testIsPalindrome2() {
        assertFalse(palindrome.isPalindrome("cat"));
        assertFalse(palindrome.isPalindrome("abcd"));
    }

    @Test
    public void testIsPalindrome3() {
        CharacterComparator cc = new OffByOne();
        assertFalse(palindrome.isPalindrome("abc", cc));
        assertFalse(palindrome.isPalindrome("aca", cc));
        assertTrue(palindrome.isPalindrome("acb", cc));
        assertTrue(palindrome.isPalindrome("%e&", cc));
    }

    @Test
    public void testIsPalindrome4() {
        CharacterComparator cc = new OffByN(2);
        assertFalse(palindrome.isPalindrome("abd", cc));
        assertFalse(palindrome.isPalindrome("aca", cc));
        assertTrue(palindrome.isPalindrome("acc", cc));
        assertFalse(palindrome.isPalindrome("%e&", cc));
    }
}