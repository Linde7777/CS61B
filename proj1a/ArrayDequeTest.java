public class ArrayDequeTest {

    public static void testAddFirst() {
        System.out.println("\nTest addFirst()");
        ArrayDeque<Integer> ad = new ArrayDeque<Integer>();

        for (int i = 1; i < 20; i++) {
            ad.addFirst(i);
        }

        ad.printDeque();
    }

    public static void testRemoveFirst(){
        System.out.println("\nTest removeFirst()");
        ArrayDeque<Integer> ad = new ArrayDeque<Integer>();
        for (int i = 1; i < 20; i++) {
            ad.addFirst(i);
        }
        for (int i = 1; i < 15; i++) {
            ad.removeFirst();
        }

        ad.printDeque();
    }

    public static void testAddLast(){
        System.out.println("Test addLast()");
        ArrayDeque<Integer> ad = new ArrayDeque<Integer>();

        for(int i=0;i<20;i++){
            ad.addLast(i);
        }

        ad.printDeque();
    }

    public static void main(String[] args) {
        //testAddFirst();
        //testRemoveFirst();
        testAddLast();
    }
}
