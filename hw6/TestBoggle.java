import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class TestBoggle {
    @Test
    public void testBasics() {
        Boggle boggle = new Boggle();
        String boardFilePath = "exampleBoard.txt";
        List<String> list = new ArrayList<>();
        list = boggle.solve(7, boardFilePath);
        System.out.println(list.toString());
    }

}
