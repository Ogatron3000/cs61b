public class OffByN implements CharacterComparator {
    private int N;

    public OffByN(int N) {
        this.N = N;
    }

    @Override
    public boolean equalChars(char x, char y) {
        if (N == Math.abs(x - y)) {
            return true;
        }
        return false;
    }
}
