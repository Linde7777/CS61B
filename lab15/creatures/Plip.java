package creatures;

import huglife.*;

import java.awt.Color;
import java.util.Map;
import java.util.List;
import java.util.Random;

/**
 * An implementation of a motile pacifist photosynthesizer.
 *
 * @author Josh Hug
 */
public class Plip extends Creature {

    /**
     * red color.
     */
    private int r;
    /**
     * green color.
     */
    private int g;
    /**
     * blue color.
     */
    private int b;

    /**
     * creates plip with energy equal to E.
     */
    public Plip(double e) {
        super("plip");
        r = 0;
        g = 0;
        b = 0;
        energy = e;
        checkIfEnergyOutOfBoundary();
    }

    /**
     * creates a plip with energy equal to 1.
     */
    public Plip() {
        this(1);
    }

    /**
     * Should return a color with red = 99, blue = 76, and green that varies
     * linearly based on the energy of the Plip. If the plip has zero energy,
     * it should have a green value of 63. If it has max energy, it should
     * have a green value of 255. The green value should vary with energy
     * linearly in between these two extremes. It's not absolutely vital
     * that you get this exactly correct.
     */
    public Color color() {
        r = 99;
        b = 76;

        int magnification = (255 - 63) / (2 - 0);
        g = (int) (energy * magnification) + 63;
        return color(r, g, b);
    }


    /**
     * Do nothing with C, Plips are pacifists.
     */
    public void attack(Creature c) {
    }

    /**
     * ensure Plip won't have energy more than 2
     */
    private void checkIfEnergyOutOfBoundary() {
        if (energy > 2) {
            energy = 2;
        }
    }


    /**
     * Plips should lose 0.15 units of energy when moving. If you want to
     * to avoid the magic number warning, you'll need to make a
     * private static final variable. This is not required for this lab.
     */
    public void move() {
        energy -= 0.15;
    }


    /**
     * Plips gain 0.2 energy when staying due to photosynthesis.
     */
    public void stay() {
        energy += 0.2;
        checkIfEnergyOutOfBoundary();
    }

    /**
     * Plips and their offspring each get 50% of the energy, with none
     * lost to the process. Now that's efficiency! Returns a baby
     * Plip.
     */
    public Plip replicate() {
        Plip offspring = new Plip(0.5 * energy);
        this.energy *= 0.5;
        return offspring;
    }

    /**
     * Plips take exactly the following actions based on NEIGHBORS:
     * 1. If no empty adjacent spaces, STAY.
     * 2. Otherwise, if energy >= 1, REPLICATE.
     * 3. Otherwise, if any Cloruses, MOVE with 50% probability.
     * 4. Otherwise, if nothing else, STAY
     * <p>
     * Returns an object of type Action. See Action.java for the
     * scoop on how Actions work. See SampleCreature.chooseAction()
     * for an example to follow.
     */
    public Action chooseAction(Map<Direction, Occupant> neighbors) {
        List<Direction> empties = getNeighborsOfType(neighbors, "empty");

        // Sorry for writing four nested if statements
        if (empties.size() != 0) {
            if (energy < 1.0) {
                if (neighbors.containsValue("Clorus")) {
                    if (decideToRun()) {
                        return new Action(Action.ActionType.MOVE, chooseDirectionRandomly(empties));
                    } else {
                        return new Action(Action.ActionType.STAY);
                    }
                } else {
                    return new Action(Action.ActionType.STAY);
                }
            } else {
                return new Action(Action.ActionType.REPLICATE, chooseDirectionRandomly(empties));
            }
        } else {
            return new Action(Action.ActionType.STAY);
        }

    }

    private Direction chooseDirectionRandomly(List<Direction> empties) {
        Random random = new Random();
        int randomIndexOfDirection=random.nextInt(empties.size());
        return empties.get(randomIndexOfDirection);
    }

    private boolean decideToRun() {
        Random random = new Random();
        if (random.nextInt(2) == 0) {
            return true;
        } else {
            return false;
        }
    }

    public String name() {
        return "plip";
    }


}
