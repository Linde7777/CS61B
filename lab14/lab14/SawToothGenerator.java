package lab14;

import lab14lib.Generator;

public class SawToothGenerator implements Generator {
    private int period;
    private int state;

    public SawToothGenerator(int period) {
        state = 0;
        this.period = period;
    }

    @Override
    public double next() {
        state = (state + 1);
        return normalize();
    }

    private double normalize() {
        int minState = 0;
        int maxState = this.period - 1;
        if (state > maxState) {
            state = state % period;
        }
        double minValue = -1.0;
        double maxValue = 1.0;
        double magnification = (maxState - minState) / (maxValue - minValue);
        double offset = 1;

        return (state / magnification) - offset;
    }
}
