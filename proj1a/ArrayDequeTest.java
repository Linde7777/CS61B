public class ArrayDequeTest {

    public static void testAddFirst() {
        ArrayDeque<Integer> ad = new ArrayDeque<Integer>();

        for (int i = 1; i < 20; i++) {
            ad.addFirst(i);
        }

        ad.printDeque();
    }

    public static void testRemoveFirst(){
        ArrayDeque<Integer> ad = new ArrayDeque<Integer>();
        for (int i = 1; i < 20; i++) {
            ad.addFirst(i);
        }
        for (int i = 1; i < 15; i++) {
            ad.removeFirst();
        }

        ad.printDeque();
    }



    public static void main(String[] args) {
        testAddFirst();
    }
}
