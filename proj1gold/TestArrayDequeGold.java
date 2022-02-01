import static org.junit.Assert.*;

import org.junit.Test;

public class TestArrayDequeGold {

    @Test
    public void test() {
        StudentArrayDeque<Integer> sad = new StudentArrayDeque<>();

        String message1="";
        for (int i = 0; i < 20; i++) {
            double numberBetweenZeroAndOne = StdRandom.uniform();

            if (numberBetweenZeroAndOne < 0.5) {
                sad.addFirst(i);
                message1="addFirst("+i+")\n";
            } else {
                sad.addLast(i);
                message1="addLast("+i+")\n";
            }
        }

        String message2="";
        for (int i = 0; i < 19; i++) {
            double numberBetweenZeroAndOne = StdRandom.uniform();

            if (numberBetweenZeroAndOne < 0.5) {
                sad.removeFirst();
                message2="removeFirst()\n";
            } else {
                sad.removeLast();
                message2="removeLast()\n";
            }
        }

        String finalMessage=message1+message2;
        Integer expected=1;
        Integer actual=sad.size();
        sad.printDeque();
        assertEquals(finalMessage,expected,actual);
    }


}
