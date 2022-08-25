package creatures;
import org.junit.Test;
import static org.junit.Assert.*;
import java.util.HashMap;
import java.awt.Color;
import huglife.Direction;
import huglife.Action;
import huglife.Occupant;
import huglife.Impassible;
import huglife.Empty;

/** Tests the Clorus class
 *  @authr Linde
 */
public class TestClorus {
    @Test
    public void testChooseAction(){
        //test if Clorus will stay
        Clorus c=new Clorus(0.5);
        HashMap<Direction, Occupant> surrounded = new HashMap<Direction, Occupant>();
        surrounded.put(Direction.TOP, new Impassible());
        surrounded.put(Direction.BOTTOM, new Impassible());
        surrounded.put(Direction.LEFT, new Impassible());
        surrounded.put(Direction.RIGHT, new Impassible());
        Action actual = c.chooseAction(surrounded);
        Action expected = new Action(Action.ActionType.STAY);
        assertEquals(expected,actual);

        //----------------------------------------------------------

        //test if Clorus will attack
        Clorus c2=new Clorus(0.5);
        HashMap<Direction, Occupant> surrounded2 = new HashMap<Direction, Occupant>();
        surrounded2.put(Direction.TOP, new Impassible());
        surrounded2.put(Direction.BOTTOM,new Plip());
        surrounded2.put(Direction.LEFT,new Impassible());
        surrounded2.put(Direction.RIGHT,new Impassible());
        Action actual2=c2.chooseAction(surrounded2);
        Action expected2=new Action(Action.ActionType.STAY);
        assertEquals(expected2,actual2);

        Clorus c3=new Clorus(0.5);
        HashMap<Direction, Occupant> surrounded3 = new HashMap<Direction, Occupant>();
        surrounded3.put(Direction.TOP, new Empty());
        surrounded3.put(Direction.BOTTOM,new Plip(1.3));
        surrounded3.put(Direction.LEFT,new Empty());
        surrounded3.put(Direction.RIGHT,new Impassible());
        Action actual3=c3.chooseAction(surrounded3);
        Action expected3=new Action(Action.ActionType.ATTACK,Direction.BOTTOM);
        assertEquals(expected3,actual3);

        //-------------------------------------------------------------

        //test if Clorus will repilcate
        Clorus c4=new Clorus(3.3);
        HashMap<Direction, Occupant> surrounded4 = new HashMap<Direction, Occupant>();
        surrounded4.put(Direction.TOP, new Empty());
        surrounded4.put(Direction.BOTTOM,new Impassible());
        surrounded4.put(Direction.LEFT,new Impassible());
        surrounded4.put(Direction.RIGHT,new Impassible());
        Action actual4=c4.chooseAction(surrounded4);
        Action expected4=new Action(Action.ActionType.REPLICATE,Direction.TOP);
        assertEquals(expected4,actual4);

        //test if Clorus will move
        Clorus c5=new Clorus(0.1);
        HashMap<Direction, Occupant> surrounded5 = new HashMap<Direction, Occupant>();
        surrounded5.put(Direction.TOP, new Empty());
        surrounded5.put(Direction.BOTTOM,new Impassible());
        surrounded5.put(Direction.LEFT,new Impassible());
        surrounded5.put(Direction.RIGHT,new Impassible());
        Action actual5=c5.chooseAction(surrounded5);
        Action expected5=new Action(Action.ActionType.MOVE,Direction.TOP);
        assertEquals(expected5,actual5);

    }
}
