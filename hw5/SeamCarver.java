import edu.princeton.cs.algs4.Picture;

import static java.lang.Math.pow;

public class SeamCarver {
    Picture picture;

    public SeamCarver(Picture picture) {
        this.picture = picture;
    }

    public Picture picture() {
        return this.picture;
    }

    public int width() {
        return this.picture.width();
    }

    public int height() {
        return this.picture.height();
    }

    public double energy(int x, int y) {
        /*
        The reason I Initialize the variables using the coordinate(x,y)
        is I don't know the type of picture.get(x,y).
        Though intellij tell me its type is 'Color', when i typing 'Color',
        the intellij recognize 'Color' is from java.awt
         */
        var xLeftNeighbor = picture.get(x, y);
        var xRightNeighbor = picture.get(x, y);
        var yUpperNeighbor = picture.get(x, y);
        var yLowerNeighbor = picture.get(x, y);

        if (x == 0) {
            xLeftNeighbor = picture.get(width() - 1, y);
        } else {
            xLeftNeighbor = picture.get(x - 1, y);
        }

        if (x == width() - 1) {
            xRightNeighbor = picture.get(0, y);
        } else {
            xRightNeighbor = picture.get(x + 1, y);
        }

        if (y == 0) {
            yUpperNeighbor = picture.get(x, height() - 1);
        } else {
            yUpperNeighbor = picture.get(x, y - 1);
        }

        if (y == height() - 1) {
            yLowerNeighbor = picture.get(x, 0);
        } else {
            yLowerNeighbor = picture.get(x, y + 1);
        }


        int xRedAbsoluteDifference =
                Math.abs(xLeftNeighbor.getRed() - xRightNeighbor.getRed());
        int xGreenAbsoluteDifference =
                Math.abs(xLeftNeighbor.getGreen() - xRightNeighbor.getGreen());
        int xBlueAbsoluteDifference =
                Math.abs(xLeftNeighbor.getBlue() - xRightNeighbor.getBlue());
        int xGradient = (int) (pow(xRedAbsoluteDifference, 2) + pow(xGreenAbsoluteDifference, 2) +
                pow(xBlueAbsoluteDifference, 2));

        int yRedAbsoluteDifference =
                Math.abs(yUpperNeighbor.getRed() - yLowerNeighbor.getRed());
        int yGreenAbsoluteDifference =
                Math.abs(yUpperNeighbor.getGreen() - yLowerNeighbor.getGreen());
        int yBlueAbsoluteDifference =
                Math.abs(yUpperNeighbor.getBlue() - yLowerNeighbor.getBlue());
        int yGradient = (int) (pow(yRedAbsoluteDifference, 2) + pow(yGreenAbsoluteDifference, 2) +
                pow(yBlueAbsoluteDifference, 2));

        int energy = xGradient + yGradient;
        return energy;
    }

    public int[] findHorizontalSeam() {
        return null;
    }

    public int[] findVerticalSeam() {
        return null;
    }

    public void removeHorizontalSeam(int[] Seam) {

    }

    public void removeVerticalSeam(int[] seam) {

    }

}
