import org.junit.Test;

import static org.junit.Assert.*;

public class TestBordersCalculating {
    @Test
    public void testLeftAndRightBorder(){
        int len=4;
        double minLon=0;
        double maxLon=4;
        double paraUllon=0.5;
        double[] leftBorders = new double[len];
        for (int i = 0; i < len; i++) {
            leftBorders[i] = minLon + (maxLon - minLon) * ((double) i / 4);
        }
        double raster_ul_lon = -1;
        for (int i = 0; i < leftBorders.length - 1; i++) {
            if (paraUllon >= leftBorders[i]
                    && paraUllon < leftBorders[i + 1]) {
                raster_ul_lon = leftBorders[i];
                break;
            }
        }
        double expected=0;
        double actual=raster_ul_lon;
        assertEquals(expected,actual,0.0001);


        double paraLrlon=2.5;
        double[] rightBorders = new double[len];
        for (int i = 0; i < len; i++) {
            rightBorders[i] = leftBorders[i] + (maxLon - minLon) / 4;
        }
        double raster_lr_lon = -1;
        for (int i = 0; i < len - 1; i++) {
            if (paraLrlon >= rightBorders[i] && paraLrlon < rightBorders[i + 1]) {
                raster_lr_lon = rightBorders[i+1];
                break;
            }
        }
        double expected2=3;
        double actual2=raster_lr_lon;
        assertEquals(expected2,actual2,0.0001);
    }
}
