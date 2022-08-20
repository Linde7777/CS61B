import edu.princeton.cs.algs4.Picture;

import java.awt.Color;

import static java.lang.Math.pow;

public class SeamCarver {
    private Picture picture;

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

    private void checkIndexOutOfBounds(int x, int y) {
        if (x < 0 || x > width() - 1
                || y < 0 || y > height() - 1) {
            throw new java.lang.IndexOutOfBoundsException();
        }
    }

    public double energy(int x, int y) {
        checkIndexOutOfBounds(x, y);
        /*
        The reason I use coordinate(x,y) to initialize the variables
        is I don't know the type of picture.get(x,y).
        Though Intellij tell me its type is 'Color',
        when i change 'var' into 'Color',
        Intellij recognize 'Color' is from java.awt
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
        int xGradient = (int) (pow(xRedAbsoluteDifference, 2)
                + pow(xGreenAbsoluteDifference, 2)
                + pow(xBlueAbsoluteDifference, 2));

        int yRedAbsoluteDifference =
                Math.abs(yUpperNeighbor.getRed()
                        - yLowerNeighbor.getRed());
        int yGreenAbsoluteDifference =
                Math.abs(yUpperNeighbor.getGreen()
                        - yLowerNeighbor.getGreen());
        int yBlueAbsoluteDifference =
                Math.abs(yUpperNeighbor.getBlue()
                        - yLowerNeighbor.getBlue());
        int yGradient = (int) (pow(yRedAbsoluteDifference, 2)
                + pow(yGreenAbsoluteDifference, 2)
                + pow(yBlueAbsoluteDifference, 2));

        int energy = xGradient + yGradient;
        return energy;
    }

    public int[] findVerticalSeam() {

        int[] result = new int[height()];

        int x = 0;
        // modify the line below
        int y = height()-1;
        int minEnergy = Integer.MAX_VALUE;
        int indexOfResult = 0;
        for (int xHelper = 0; xHelper <= width() - 1; xHelper++) {
            int currentNodeEnergy = (int) energy(x + xHelper, y);
            if (currentNodeEnergy < minEnergy) {
                minEnergy = currentNodeEnergy;
                result[indexOfResult] = x + xHelper;
            }
        }
        indexOfResult += 1;

        //TODO: grandscope test say I should scan from bottom
        /*
        we have scanned the first line, and we continue to
        scan from bottom to top: (?,y-1)->(?,y-2)->(?,y-3)
        (recall that y=0, and y will not be changed,
        we use y+yHelper to walk through the picture)
         */
        for (int yHelper = -1; yHelper > -(height()-1); yHelper--) {
            /*
            result[indexOfResult-1] store the x from the (x,y) which have the smallest
            energy in the upper line(recall that we scan from top to bottom)
             */
            x = result[indexOfResult - 1];

            /*
            Ideally, for each row(horizontal) we scan (x-1,?), (x,?) and (x+1,?),
            but in some cases we need to deal with the boundary.
             */
            int xHelper;
            int xHelperBoundary;
            // if x is the leftmost, we can not scan (x-1,?)
            if (x == 0) {
                xHelper = 0;
            } else {
                xHelper = -1;
            }
            // if x is the rightmost, we can not scan (x+1,?)
            if (x == width() - 1) {
                xHelperBoundary = 0;
            } else {
                xHelperBoundary = 1;
            }

            minEnergy = Integer.MAX_VALUE;
            for (; xHelper <= xHelperBoundary; xHelper++) {
                int currentNodeEnergy = (int) energy(x + xHelper, y + yHelper);
                if (currentNodeEnergy < minEnergy) {
                    minEnergy = currentNodeEnergy;
                    result[indexOfResult] = x + xHelper;
                }
            }

            indexOfResult += 1;

        }

        // reverse the result
        /*
        for(int i=0;i<result.length/2;i++){
            int temp=result[i];
            result[i]=result[result.length-1-i];
            result[result.length-1-i]=temp;
        }

         */

        return result;
    }

    public int[] findHorizontalSeam() {
        // prevent this.picture from being recycled
        Picture originalPictureKeeper = this.picture;
        this.picture = generateTransposedPicture();
        int[] result = findVerticalSeam();
        // transpose this.picture back
        this.picture = originalPictureKeeper;
        return result;
    }

    private Picture generateTransposedPicture() {
        Picture transposedPicture = new Picture(this.picture.height(), this.picture.width());
        for (int tranY = 0; tranY < transposedPicture.height(); tranY++) {
            for (int tranX = 0; tranX < transposedPicture.width(); tranX++) {
                transposedPicture.set(tranX, tranY, getCorrespondingCoordinatesColor(tranX, tranY));
            }
        }

        return transposedPicture;
    }

    private Color getCorrespondingCoordinatesColor(int tranX, int tranY) {
        /*
        there is a relation between transposed (x,y) and original (x,y):
        (x,y) -> (y,x)

        We first set a horizontal axis of symmetry at the top of
        original picture, then flip up along this axis of symmetry,
        and then flip the resulting picture clockwise by ninety
        degrees to obtain the transformed picture.

        for example:

        original picture:
        A B C D
        E F G H
        I J K L

        flip up along the axis of symmetry:
        I J K L
        E F G H
        A B C D

        flip ninety degrees, get transformed picture:
        A E I
        B F J
        C G K
        D H L
         */
        Color returnItem = this.picture.get(tranY, tranX);
        return returnItem;
    }

    public void removeVerticalSeam(int[] seam) {
        // TODO: if the array is not a valid seam
        //  (i.e., two consecutive entries differ by more than 1)?
        if (seam.length != this.picture.height()) {
            throw new IllegalArgumentException();
        }
        SeamRemover.removeVerticalSeam(this.picture, seam);
    }

    public void removeHorizontalSeam(int[] seam) {
        if (seam.length != this.picture.width()) {
            throw new IllegalArgumentException();
        }
        SeamRemover.removeHorizontalSeam(this.picture, seam);
    }


}
