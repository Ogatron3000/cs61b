public class OffByOne implements CharacterComparator {
    @Override
    public boolean equalChars(char x, char y) {
        if (1 == Math.abs(x - y)) {
            return true;
        }
        return false;
    }
}
