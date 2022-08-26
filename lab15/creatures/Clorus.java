package creatures;

import huglife.*;

import java.awt.*;
import java.util.List;
import java.util.Map;

public class Clorus extends Creature {
    /** red color*/
    private int r;
    /** green color*/
    private int g;
    /** blue color*/
    private int b;
    /** fraction of energy to retain when replicating. */
    private double repEnergyRetained = 0.5;
    /** fraction of energy to bestow upon offspring. */
    private double repEnergyGiven = 0.5;

    /**
     * creates clorus with energy equal to E.
     */
    public Clorus(double e) {
        super("clorus");
        r = 0;
        g = 0;
        b = 0;
        energy = e;
    }

    /**
     * Default constructor: Creatures creature with energy 1.
     */
    public Clorus() {
        this(1);
    }

    @Override
    public Color color() {
        r = 34;
        g = 0;
        b = 231;
        return new Color(r, g, b);
    }

    @Override
    public void move() {
        energy -= 0.03;
    }

    @Override
    public void attack(Creature c) {
        energy += c.energy();
    }

    @Override
    public void stay() {
        energy -= 0.01;
    }

    @Override
    public Creature replicate() {
        energy = energy * repEnergyRetained;
        double babyEnergy = energy * repEnergyGiven;
        return new Clorus(babyEnergy);
    }

    @Override
    public Action chooseAction(Map<Direction, Occupant> neighbors) {
        List<Direction> empties = getNeighborsOfType(neighbors, "empty");
        List<Direction> plips = getNeighborsOfType(neighbors, "plip");

        if (empties.size() != 0) {
            if (plips.size() == 0) {
                if (energy >= 1.0) {
                    return new Action(Action.ActionType.REPLICATE, chooseDirectionRandomly(empties));
                } else {
                    return new Action(Action.ActionType.MOVE, chooseDirectionRandomly(empties));
                }
            } else {
                return new Action(Action.ActionType.ATTACK, choosePlipDirectionRandomly(plips));
            }
        } else {
            return new Action(Action.ActionType.STAY);
        }

    }

    private Direction chooseDirectionRandomly(List<Direction> empties) {
        return HugLifeUtils.randomEntry(empties);
    }

    private Direction choosePlipDirectionRandomly(List<Direction> plips) {
        return HugLifeUtils.randomEntry(plips);
    }

    public String name() {
        return "clorus";
    }
}
