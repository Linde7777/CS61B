public class ArrayDequeTest {

    public static void testAdd(){
        ArrayDeque<Integer> ad=new ArrayDeque<Integer>();
        ad.addFirst(3);
        ad.addFirst(2);
        ad.addLast(4);
        ad.addFirst(9);
        ad.printDeque();
    }


    public static void main(String[] args) {
        testAdd();
    }
}
