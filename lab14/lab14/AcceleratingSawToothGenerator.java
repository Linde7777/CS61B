package lab14;

import lab14lib.Generator;

public class AcceleratingSawToothGenerator implements Generator {
    private int state;
    private int period;
    private final double factor;
    private boolean firstRun = true;

    public AcceleratingSawToothGenerator(int period, double factor) {
        state = 0;
        this.period = period;
        this.factor = factor;
    }

    @Override
    public double next() {
        state = (state + 1);
        return normalize();
    }

    private double normalize() {
        if (this.state > this.period - 1) {
            this.state = this.state % this.period;
            this.period = (int) (this.period * factor);
        }
        int minState = 0;
        int maxState = this.period - 1;
        double minValue = -1.0;
        double maxValue = 1.0;
        double magnification = (maxState - minState) / (maxValue - minValue);
        int offset = 1;
        firstRun = false;

        return (this.state / magnification) - offset;
    }
}
