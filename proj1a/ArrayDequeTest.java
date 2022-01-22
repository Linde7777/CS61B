public class ArrayDequeTest {

    public static void testAddFirst() {
        System.out.println("\nTest addFirst()");
        ArrayDeque<Integer> ad = new ArrayDeque<Integer>();

        for (int i = 1; i < 7; i++) {
            ad.addFirst(i);
        }

        ad.printDeque();
    }

    public static void testRemoveFirst() {
        System.out.println("\nTest removeFirst()");
        ArrayDeque<Integer> ad = new ArrayDeque<Integer>();
        for (int i = 1; i < 7; i++) {
            ad.addFirst(i);
        }
        for (int i = 1; i < 7; i++) {
            ad.removeFirst();
        }

        ad.printDeque();
    }

    public static void testAddLast() {
        System.out.println("Test addLast()");
        ArrayDeque<Integer> ad = new ArrayDeque<Integer>();

        for (int i = 1; i < 7; i++) {
            ad.addLast(i);
        }

        ad.printDeque();
    }

    public static void testRemoveLast() {
        ArrayDeque<Integer> ad = new ArrayDeque<Integer>();

        for (int i = 1; i < 7; i++) {
            ad.addLast(i);
        }

        for (int i = 1; i < 7; i++) {
            ad.removeLast();
        }

    }

    public static void testAddRemove() {
        ArrayDeque<Integer> ad = new ArrayDeque<Integer>();

        for (int i = 1; i < 3; i++) {
            ad.addFirst(i);
        }

        for (int i = 1; i < 3; i++) {
            ad.addLast(i);
        }

        for (int i = 1; i < 3; i++) {
            ad.removeFirst();
        }

        for (int i = 1; i < 3; i++) {
            ad.removeLast();
        }

        ad.printDeque();
    }

    public static void testAddRemoveFirstOverlap(){
        ArrayDeque<Integer> ad = new ArrayDeque<Integer>();

        for (int i = 1; i < 7; i++) {
            ad.addFirst(i);
        }

        for(int i=1;i<10;i++){
            ad.removeFirst();
        }

    }

    public static void main(String[] args) {
        /*
        Please test along with Java Visualizer
         */

        //testAddFirst();
        //testRemoveFirst();
        //testAddLast();
        //testRemoveLast();
        testAddRemove();
    }
}
