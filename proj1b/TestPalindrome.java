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
    public void testIsPalindrome() {
        assertFalse(palindrome.isPalindrome("persiflage"));
        assertTrue(palindrome.isPalindrome("noon"));
        assertTrue(palindrome.isPalindrome("a"));
        assertTrue(palindrome.isPalindrome(""));
        assertFalse(palindrome.isPalindrome("NoOn"));
        assertTrue(palindrome.isPalindrome("racecar"));
    }

    @Test
    public void testIsPalindromeOffByOne() {
        CharacterComparator offByOne = new OffByOne();
        assertFalse(palindrome.isPalindrome("persiflage", offByOne));
        assertTrue(palindrome.isPalindrome("acdb", offByOne));
        assertTrue(palindrome.isPalindrome("flake", offByOne));
        assertTrue(palindrome.isPalindrome("", offByOne));
        assertTrue(palindrome.isPalindrome("a", offByOne));
        assertFalse(palindrome.isPalindrome("flakf", offByOne));
    }

    @Test
    public void testIsPalindromeOffByN() {
        CharacterComparator offByN = new OffByN(5);
        assertFalse(palindrome.isPalindrome("persiflage", offByN));
        assertTrue(palindrome.isPalindrome("bidding", offByN));
        assertTrue(palindrome.isPalindrome("af", offByN));
        assertTrue(palindrome.isPalindrome("", offByN));
        assertTrue(palindrome.isPalindrome("a", offByN));
        assertFalse(palindrome.isPalindrome("flakf", offByN));
    }
}
