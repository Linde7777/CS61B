package hw2;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    private int N;
    private WeightedQuickUnionUF wqu;
    private int[][] openHelper;
    private final int isBlocked = 0;
    private final int isOpened = 1;
    private int openedCount = 0;

    public Percolation(int N) {// create N-by-N grid, with all sites initially blocked
        if (N <= 0) {
            throw new java.lang.IllegalArgumentException();
        }
        this.N = N;
        wqu = new WeightedQuickUnionUF(N * N);

        openHelper = new int[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                openHelper[i][j] = isBlocked;
            }
        }

    }

    public void testXyTo1D() {
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
        if (row > N - 1 || col > N - 1 ||
                row < 0 || col < 0) {
            throw new java.lang.IndexOutOfBoundsException();
        }
    }

    public void testOpen() {
        //when N == 4

        open(0, 2);
        open(1, 2);
        open(2, 2);
        open(2, 3);
        open(2, 0);
        open(3, 3);
        if (wqu.connected(xyTo1D(0, 2), xyTo1D(3, 3)) == true) {
            System.out.println("test 1 passed");
        }
        if (wqu.connected(xyTo1D(2, 2), xyTo1D(3, 3)) == true) {
            System.out.println("test 2 passed");
        }
        if (wqu.connected(xyTo1D(2, 0), xyTo1D(3, 3)) == false) {
            System.out.println("test 3 passed");
        }
        if (wqu.connected(xyTo1D(1, 2), xyTo1D(2, 2)) == true) {
            System.out.println("test 4 passed");
        }
    }

    public void open(int row, int col) {
        // open the site (row, col) if it is not open already
        checkRowAndCol(row, col);
        openHelper[row][col] = isOpened;
        openedCount += 1;

        /*
        Using a 2D array to store the info of opened component
        if there are two adjacent component, union them
        */

        /*
        Do not scan the 2D array in parallel and vertical direction
        at the same time. Watch the boundary of i and j in the for loop,
        you will know why.
         */
        for (int i = 0; i < N; i++) {//scan in the parallel direction
            for (int j = 0; j < N - 1; j++) {
                if (openHelper[i][j] == isOpened &&
                        openHelper[i][j + 1] == isOpened) {
                    wqu.union(xyTo1D(i, j), xyTo1D(i, j + 1));
                }
            }
        }

        for (int i = 0; i < N - 1; i++) {//scan in the vertical direction
            for (int j = 0; j < N; j++) {
                if (openHelper[i][j] == isOpened &&
                        openHelper[i + 1][j] == isOpened) {
                    wqu.union(xyTo1D(i, j), xyTo1D(i + 1, j));
                }
            }
        }

    }

    public boolean isOpen(int row, int col) {
        // is the site (row, col) open?
        checkRowAndCol(row, col);
        return openHelper[row][col] == isOpened;
    }

    public void testIsFull() {
        System.out.println("There are 5 tests");
        open(2, 2);
        open(3, 2);
        open(2, 3);
        if (isFull(2, 2) == false) {
            System.out.println("test 1 passed");
        }

        open(2, 0);
        open(2, 1);
        if (isFull(2, 2) == false) {
            System.out.println("test 2 passed");
        }

        open(0, 0);
        if (isFull(0, 0) == true) {
            System.out.println("test 3 passed");
        }

        open(1, 0);
        if (isFull(2, 2) == true) {
            System.out.println("test 4 passed");
        }

        open(1, 3);
        if (isFull(1, 3) == true) {
            System.out.println("test 5 passed");
        }

    }

    public boolean isFull(int row, int col) {
        // is the site (row, col) full?
        checkRowAndCol(row, col);

        for (int i = 0; i < N - 1; i++) {//i refer to the first row element
            if (wqu.connected(i, xyTo1D(row, col))) {
                return true;
            }
        }

        return false;
    }

    public void testNumberOfOpenSites() {
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

    public int numberOfOpenSites() {
        // number of open sites
        return openedCount;
    }

    public void testPercolates() {
        //when N==4
        open(0, 2);
        open(1, 2);
        open(2, 2);
        open(2, 3);
        open(2, 0);
        if (percolates() == false) {
            System.out.println("test 1 passed");
        }

        open(3, 3);
        if (percolates() == true) {
            System.out.println("test 2 passed");
        }

    }

    public boolean percolates() {
        // does the system percolate?

        for (int i = 0; i <= N - 1; i++) {//the first row
            for (int j = 0 + (N - 1) * N; j <= (N - 1) + (N - 1) * N; j++) {//the last row
                if (wqu.connected(i, j) == true) {
                    return true;
                }
            }
        }

        return false;
    }

    public static void main(String[] args) {
        Percolation percolation = new Percolation(4);
        //percolation.testXyTo1D();
        //percolation.testOpen();
        percolation.testIsFull();
        //percolation.testNumberOfOpenSites();
        //percolation.testPercolates();
    }
}
