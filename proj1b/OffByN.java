public class OffByN implements CharacterComparator {

    private int N;

    public OffByN(int N) {
        this.N = N;
    }

    @Override
    public boolean equalChars(char x, char y) {
        int c1 = x;
        int c2 = y;
        if (c1 - c2 == N || c1 - c2 == -N) {
            return true;
        }
        return false;
    }

}
