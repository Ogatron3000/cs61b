import org.junit.Test;
import static org.junit.Assert.*;

public class TestOffByN {
    static CharacterComparator offByN = new OffByN(5);

    @Test
    public void testEqualChars() {
        assertTrue(offByN.equalChars('a', 'f'));
        assertTrue(offByN.equalChars('f', 'k'));
        assertFalse(offByN.equalChars('a', 'e'));
        assertFalse(offByN.equalChars('a', 'a'));
        assertFalse(offByN.equalChars('z', 'a'));
        assertFalse(offByN.equalChars('%', '&'));
    }
}
