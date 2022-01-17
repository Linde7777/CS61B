public class ArrayDequeTest {

    public static void testAdd(){
        ArrayDeque<Integer> ad=new ArrayDeque<Integer>();

        for(int i=1;i<9;i++){
            ad.addFirst(i);
        }
        for(int i=1;i<9;i++){
            ad.addLast(i);
        }
        ad.printDeque();
    }


    public static void main(String[] args) {
        testAdd();
    }
}
