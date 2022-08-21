package lab14;

import lab14lib.Generator;


public class StrangeBitwiseGenerator implements Generator {
    private final int period;
    private int state;
    private int weirdState;

    public StrangeBitwiseGenerator(int period) {
        state = 0;
        this.period = period;
    }

    @Override
    public double next() {
        state = (state + 1);
        weirdState = state & (state >>> 3) % period;
        return normalize();
    }

    private double normalize() {
        int minState = 0;
        int maxState = period - 1;
        if (weirdState > maxState) {
            weirdState = weirdState % period;
        }
        double minValue = -1.0;
        double maxValue = 1.0;
        double magnification = (maxState - minState) / (maxValue - minValue);
        double offset = 1;

        return (weirdState / magnification) - offset;
    }
}
