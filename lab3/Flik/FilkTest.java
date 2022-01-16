import org.junit.Test;

public class FilkTest {

    @Test
    public void testFilk() {
        Integer a = 3;
        Integer b = 5;
        boolean input;
        boolean expected = false;

        input = Flik.isSameNumber(a, b);
        if (input == expected) {
            System.out.println("Test1 passed");
        } else {
            System.out.println("Test1 failed");
        }


        Integer c = 129;
        Integer d = 129;
        boolean input1;
        boolean expected1 = true;

        input1 = Flik.isSameNumber(c, d);
        if (input1 == expected1) {
            System.out.println("Test2 passed");
        } else {
            System.out.println("Test2 failed");
        }
    }

}
