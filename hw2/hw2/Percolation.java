package hw2;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    private int N;
    WeightedQuickUnionUF wqu;
    private final int blockedSite = 0;
    private final int fullOpenSite = 1;
    private final int emptyOpenSite = 2;


    public Percolation(int N) {// create N-by-N grid, with all sites initially blocked
        if (N <= 0) {
            throw new java.lang.IllegalArgumentException();
        }

        this.N = N;
        wqu = new WeightedQuickUnionUF(N * N);
    }

    private void testXyTo1D() {
        if (xyTo1D(4, 2, 3) == 14) {
            System.out.println("test 1 passed");
        }
        if (xyTo1D(4, 3, 0) == 3) {
            System.out.println("test 2 passed");
        }
        if(xyTo1D(4,0,3)==12){
            System.out.println("test 3 passed");
        }
        if(xyTo1D(2,1,1)==3){
            System.out.println("test 4 passed");
        }
    }

    public int xyTo1D(int x, int y) {
        return xyTo1D(this.N, x, y);
    }

    private int xyTo1D(int N, int x, int y) {
        return x + y * N;
    }

    private void checkRowAndCol(int row, int col) {
        if (row > N - 1 || col > N - 1 ||
                row < 0 || col < 0) {
            throw new java.lang.IndexOutOfBoundsException();
        }
    }

    public void open(int row, int col) {
        checkRowAndCol(row, col);
    }       // open the site (row, col) if it is not open already

    public boolean isOpen(int row, int col) {
        // is the site (row, col) open?
        checkRowAndCol(row, col);

        //TODO
        return false;
    }

    public boolean isFull(int row, int col) {
        // is the site (row, col) full?
        checkRowAndCol(row, col);

        //TODO
        return false;
    }

    public int numberOfOpenSites() {
        //TODO
        return 0;
    }          // number of open sites

    public boolean percolates() {
        // does the system percolate?

        //TODO
        return false;
    }

    public static void main(String[] args) {
        Percolation percolation=new Percolation(4);
        percolation.testXyTo1D();

    }
}
