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
        return normalize(state);
    }

    private double normalize(int state) {
        int minState = 0;
        int maxState = this.period - 1;
        if (state > maxState) {
            state = minState;
        }

        double minValue = -1.0;
        double maxValue = 1.0;
        double helper = (maxState - minState + 1) / (maxValue - minValue);

        /*
        how to implement 0~period -> -1~1 ?
        for example:
        period=1000, state varies from 0~999, there are 1000 states
        we want 0~999 -> 0~9
        999-0+1/9-0+1=100, we set helper=100
        if the input state=500, 500/helper=5,
        now we successfully convert 0~999 to 0~9,
        but how to convert 0~999 to -4~5?
        output = inputState/helper - offset
        we set offset=5, if inputState=300
        then 300/helper=3, output=3-offset=-2
         */
        double offset = 1;

        return (state / helper) - offset;
    }
}
