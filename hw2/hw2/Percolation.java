package hw2;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    private final int N;
    private final WeightedQuickUnionUF wqu;
    private final int[] openHelper;
    private final int isBlocked = 0;
    private final int isOpened = 1;
    private int openedCount = 0;

    // create N-by-N grid, with all sites initially blocked
    public Percolation(int N) {
        if (N <= 0) {
            throw new java.lang.IllegalArgumentException();
        }
        this.N = N;
        wqu = new WeightedQuickUnionUF(N * N);

        openHelper = new int[N * N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                openHelper[xyTo1D(i, j)] = isBlocked;
            }
        }

    }

    private void testXyTo1D() {
        //when N==4
        if (xyTo1D(2, 1) == 6) {
            System.out.println("test 1 passed");
        }
        if (xyTo1D(0, 0) == 0) {
            System.out.println("test 2 passed");
        }
        if (xyTo1D(3, 3) == 15) {
            System.out.println("test 3 passed");
        }
        if (xyTo1D(0, 1) == 1) {
            System.out.println("test 4 passed");
        }
        if (xyTo1D(0, 3) == 3) {
            System.out.println("test 5 passed");
        }
        if (xyTo1D(1, 3) == 7) {
            System.out.println("test 6 passed");
        }
    }

    private int xyTo1D(int x, int y) {
        return x * this.N + y;
    }

    private void checkRowAndCol(int row, int col) {
        if (row > N - 1
                || col > N - 1
                || row < 0
                || col < 0) {
            throw new java.lang.IndexOutOfBoundsException();
        }
    }

    private void testOpen() {
        //when N == 4

        open(0, 2);
        open(1, 2);
        open(2, 2);
        open(2, 3);
        open(2, 0);
        open(3, 3);
        if (wqu.connected(xyTo1D(0, 2), xyTo1D(3, 3))) {
            System.out.println("test 1 passed");
        }
        if (wqu.connected(xyTo1D(2, 2), xyTo1D(3, 3))) {
            System.out.println("test 2 passed");
        }
        if (!wqu.connected(xyTo1D(2, 0), xyTo1D(3, 3))) {
            System.out.println("test 3 passed");
        }
        if (wqu.connected(xyTo1D(1, 2), xyTo1D(2, 2))) {
            System.out.println("test 4 passed");
        }
    }

    // open the site (row, col) if it is not open already
    public void open(int row, int col) {
        checkRowAndCol(row, col);

        /*
        Using a 2D array to store the info of opened component
        if there are two adjacent component, union them
        */
        if (openHelper[xyTo1D(row, col)] == isBlocked) {
            openHelper[xyTo1D(row, col)] = isOpened;
            openedCount += 1;
        }

        /*
        Scanning the 2D array in parallel and vertical direction
        at the same for loop will cause issues.
        Watch the boundary of i and j in the following two for loop,
        you will know why.
         */

        //scan in the parallel direction
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N - 1; j++) {
                if (openHelper[xyTo1D(i, j)] == isOpened
                        && openHelper[xyTo1D(i, j + 1)] == isOpened) {
                    wqu.union(xyTo1D(i, j), xyTo1D(i, j + 1));
                }
            }
        }

        //scan in the vertical direction
        for (int i = 0; i < N - 1; i++) {
            for (int j = 0; j < N; j++) {
                if (openHelper[xyTo1D(i, j)] == isOpened
                        && openHelper[xyTo1D(i + 1, j)] == isOpened) {
                    wqu.union(xyTo1D(i, j), xyTo1D(i + 1, j));
                }
            }
        }

    }

    // is the site (row, col) open?
    public boolean isOpen(int row, int col) {
        checkRowAndCol(row, col);
        return openHelper[xyTo1D(row, col)] == isOpened;
    }

    private void testIsFull() {
        System.out.println("There are 5 tests");
        open(2, 2);
        open(3, 2);
        open(2, 3);
        if (!isFull(2, 2)) {
            System.out.println("test 1 passed");
        }

        open(2, 0);
        open(2, 1);
        if (!isFull(2, 2)) {
            System.out.println("test 2 passed");
        }

        open(0, 0);
        if (isFull(0, 0)) {
            System.out.println("test 3 passed");
        }

        open(1, 0);
        if (isFull(2, 2)) {
            System.out.println("test 4 passed");
        }

        open(1, 3);
        if (isFull(1, 3)) {
            System.out.println("test 5 passed");
        }
    }

    // is the site (row, col) full?
    public boolean isFull(int row, int col) {
        checkRowAndCol(row, col);

        for (int i = 0; i < N; i++) {
            //i refer to the first row element
            if (openHelper[xyTo1D(0, i)] == isOpened
                    && wqu.connected(i, xyTo1D(row, col))) {
                return true;
            }
        }

        return false;
    }

    private void testNumberOfOpenSites() {
        if (numberOfOpenSites() == 0) {
            System.out.println("test 1 passed");
        }
        open(2, 2);
        open(1, 2);
        open(3, 1);
        open(1, 1);
        if (numberOfOpenSites() == 4) {
            System.out.println("test 2 passed");
        }
    }

    // number of open sites
    public int numberOfOpenSites() {
        return openedCount;
    }

    private void testPercolates() {
        //when N==4
        open(0, 2);
        open(1, 2);
        open(2, 2);
        open(2, 3);
        open(2, 0);
        if (!percolates()) {
            System.out.println("test 1 passed");
        }

        open(3, 3);
        if (percolates()) {
            System.out.println("test 2 passed");
        }

    }

    // does the system percolate?
    public boolean percolates() {

        //the first row
        for (int i = 0; i <= N - 1; i++) {

            //the last row
            for (int j = (N - 1) * N; j <= (N - 1) + (N - 1) * N; j++) {
                if (wqu.connected(i, j)) {
                    return true;
                }
            }
        }

        return false;
    }

    public static void main(String[] args) {
        //Percolation percolation = new Percolation(10);
        //percolation.testXyTo1D();
        //percolation.testOpen();
        //percolation.testIsFull();
        //percolation.testNumberOfOpenSites();
        //percolation.testPercolates();
    }
}
