import static org.junit.Assert.*;

import org.junit.Test;

public class IntListTest {

    /**
     * Example test that verifies correctness of the IntList.of static
     * method. The main point of this is to convince you that
     * assertEquals knows how to handle IntLists just fine.
     */
    @Test
    public void testReverse() {
        IntList input = new IntList(3, null);
        input = new IntList(2, input);
        input = new IntList(1, input);

        IntList expected = new IntList();
        expected = new IntList(1, null);
        expected = new IntList(2, expected);
        expected = new IntList(3, expected);

        input = IntList.reverse(input);

        IntList p1, p2;
        p1 = input;
        p2 = expected;

        System.out.println("Input: ");
        while (p1 != null) {
            System.out.print(" " + p1.first);
            p1 = p1.rest;
        }
        System.out.println();

        System.out.println("Expected: ");
        while (p2 != null) {
            System.out.print(" " + p2.first);
            p2 = p2.rest;
        }
        System.out.println();

        boolean isPass = true;
        IntList p3, p4;
        p3 = input;
        p4 = expected;
        while (p4 != null) {
            if (p3.first != p4.first) {
                System.out.println("Mismatch, input: "
                        + p3.first
                        + " expected: "
                        + p4.first);
                isPass = false;
            }
            p3 = p3.rest;
            p4 = p4.rest;
        }

        if (isPass) {
            System.out.println("Test1 passed");
        }


        IntList input1 = new IntList();
        IntList expected1 = new IntList();

        IntList.reverse(input1);
        if (input1.first == expected1.first) {
            System.out.println("Test2 passed");
        } else {
            System.out.println("Mismatch, input: "
                    + input1.first
                    + " expected: "
                    + expected1.first);
        }


        IntList input2 = new IntList(1, null);
        IntList expected2 = new IntList(1, null);

        IntList.reverse(input2);
        if (input2.first == expected2.first) {
            System.out.println("Test3 passed");
        } else {
            System.out.println("Mismatch, input: "
                    + input2.first
                    + " expected: "
                    + expected2.first);
        }
    }

    @Test
    public void testList() {
        IntList one = new IntList(1, null);
        IntList twoOne = new IntList(2, one);
        IntList threeTwoOne = new IntList(3, twoOne);

        IntList x = IntList.of(3, 2, 1);
        assertEquals(threeTwoOne, x);
    }

    @Test
    public void testdSquareList() {
        IntList L = IntList.of(1, 2, 3);
        IntList.dSquareList(L);
        assertEquals(IntList.of(1, 4, 9), L);
    }

    /**
     * Do not use the new keyword in your tests. You can create
     * lists using the handy IntList.of method.
     * <p>
     * Make sure to include test cases involving lists of various sizes
     * on both sides of the operation. That includes the empty list, which
     * can be instantiated, for example, with
     * IntList empty = IntList.of().
     * <p>
     * Keep in mind that dcatenate(A, B) is NOT required to leave A untouched.
     * Anything can happen to A.
     */

    @Test
    public void testSquareListRecursive() {
        IntList L = IntList.of(1, 2, 3);
        IntList res = IntList.squareListRecursive(L);
        assertEquals(IntList.of(1, 2, 3), L);
        assertEquals(IntList.of(1, 4, 9), res);
    }

    @Test
    public void testDcatenate() {
        IntList A = IntList.of(1, 2, 3);
        IntList B = IntList.of(4, 5, 6);
        IntList exp = IntList.of(1, 2, 3, 4, 5, 6);
        assertEquals(exp, IntList.dcatenate(A, B));
        assertEquals(IntList.of(1, 2, 3, 4, 5, 6), A);
    }

    @Test
    public void testCatenate() {
        IntList A = IntList.of(1, 2, 3);
        IntList B = IntList.of(4, 5, 6);
        IntList exp = IntList.of(1, 2, 3, 4, 5, 6);
        assertEquals(exp, IntList.catenate(A, B));
        assertEquals(IntList.of(1, 2, 3), A);
    }

    /** If you're running this from the command line, you'll need
     * to add a main method. See ArithmeticTest.java for an
     * example. */

}
