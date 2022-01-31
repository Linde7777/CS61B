public class OffByOne implements CharacterComparator {

    @Override
    public boolean equalChars(char x, char y) {
        int c1 = x;
        int c2 = y;
        if (c1 - c2 == 1 || c1 - c2 == -1) {
            return true;
        }
        return false;
    }

}
