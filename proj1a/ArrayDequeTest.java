public class ArrayDequeTest {

    public static void testAddFirst() {
        System.out.println("\nTest addFirst()");
        ArrayDeque<Integer> ad = new ArrayDeque<Integer>();

        for (int i = 1; i < 5; i++) {
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

    public static void testAddRemoveFirstOverlap() {
        ArrayDeque<Integer> ad = new ArrayDeque<Integer>();

        for (int i = 1; i < 7; i++) {
            ad.addFirst(i);
        }

        for (int i = 1; i < 10; i++) {
            ad.removeFirst();
        }

    }

    public static void testArrExtend1() {
        //when nextFirst > nextLast
        ArrayDeque<Integer> ad = new ArrayDeque<Integer>();
        for (int i = 0; i < 4; i++) {
            ad.addFirst(i);
        }
        for (int i = 0; i < 4; i++) {
            ad.addLast(i);
        }

        ad.printDeque();
    }

    public static void testArrExtend2() {
        //when nextFirst < nextLast
        ArrayDeque<Integer> ad = new ArrayDeque<Integer>();
        for (int i = 0; i < 10; i++) {
            ad.addLast(i);
        }

        ad.printDeque();
    }

    public static void testArrReduce1() {
        //When nextLast < nextFirst
        ArrayDeque<Integer> ad = new ArrayDeque<Integer>();
        for (int i = 1; i < 10; i++) {
            ad.addFirst(i);
        }
        for (int i = 10; i < 19; i++) {
            ad.addLast(i);
        }
        for (int i = 1; i < 9; i++) {
            ad.removeFirst();
        }
        for (int i = 1; i < 9; i++) {
            ad.removeLast();
        }

        ad.printDeque();
    }

    public static void testArrReduce2() {
        //When nextFirst < nextLast
        ArrayDeque<Integer> ad = new ArrayDeque<Integer>();
        for (int i = 1; i < 20; i++) {
            ad.addLast(i);
        }
        for (int i = 1; i < 18; i++) {
            ad.removeLast();
        }

        ad.printDeque();
    }

    public static void testGet() {
        ArrayDeque<Integer> ad = new ArrayDeque<Integer>();
        for (int i = 1; i < 20; i++) {
            ad.addLast(i);
        }
        System.out.println(ad.get(0));
    }

    public static void testRemoveLast2() {
        ArrayDeque<Integer> ad = new ArrayDeque<Integer>();
        ad.addFirst(0);
        ad.addFirst(1);
        System.out.println(ad.removeLast());
    }

    public static void testFillUpTwice() {
        ArrayDeque<Integer> ad = new ArrayDeque<Integer>();
        for (int i = 0; i < 8; i++) {
            ad.addFirst(i);
        }
        for (int i = 0; i < 8; i++) {
            ad.removeFirst();
        }

        for (int i = 0; i < 8; i++) {
            ad.addFirst(i);
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
        //testAddRemove();
        //testArrExtend1();
        //testArrExtend2();
        //testArrReduce1();
        //testArrReduce2();
        //testGet();
        //testRemoveLast2();
        testFillUpTwice();
    }
}
