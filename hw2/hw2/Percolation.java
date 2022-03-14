package hw2;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    private int N;
    WeightedQuickUnionUF wqu;
    private int[][] openHelper;
    final int isBlocked = 0;
    final int isOpened = 1;
    private int openedCount=0;


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

    public void testXyTo1D(){
        //when N==4
        if(xyTo1D(2,1)==6){
            System.out.println("test 1 passed");
        }
        if(xyTo1D(0,0)==0){
            System.out.println("test 2 passed");
        }
        if(xyTo1D(3,3)==15){
            System.out.println("test 3 passed");
        }
    }

    private int xyTo1D(int x, int y) {
        return x + y * this.N;
    }

    private void checkRowAndCol(int row, int col) {
        if (row > N - 1 || col > N - 1 ||
                row < 0 || col < 0) {
            throw new java.lang.IndexOutOfBoundsException();
        }
    }

    public void testOpen() {
        //when N == 4
        open(0, 1);
        open(3, 2);
        open(2, 3);
        open(2, 2);

        if (wqu.connected(xyTo1D(0, 1), xyTo1D(3, 2)) == false) {
            System.out.println("test 1 passed");
        }
        if (wqu.connected(xyTo1D(3, 2), xyTo1D(2, 3)) == true) {
            System.out.println("test 2 passed");
        }
    }

    public void open(int row, int col) {
        // open the site (row, col) if it is not open already
        checkRowAndCol(row, col);
        openHelper[row][col] = isOpened;
        openedCount+=1;

        /*
        Using a 2D array to store the info of opened component
        if there are two adjacent component, union them
        */
        for (int i = 0; i < N - 1; i++) {
            for (int j = 0; j < N - 1; j++) {

                if (openHelper[i][j] == isOpened) {
                    int index1 = xyTo1D(i, j);

                    if (openHelper[i + 1][j] == isOpened) {
                        int index2 = xyTo1D(i + 1, j);
                        wqu.union(index1, index2);
                    }

                    if (openHelper[i][j + 1] == isOpened) {
                        int index2 = xyTo1D(i, j + 1);
                        wqu.union(index1, index2);
                    }
                    /*
                    since we scan the array from left to right
                    then scan the array from up to down
                    we only need to care about the right and down direction

                    for example, if arr[1][2] and arr[1][3] are both 'opened'
                    after we scan arr[1][2], we have union arr[1][2] and arr[1][3]
                    so when we scan arr[1][3], we don't need to care about arr[1][2]
                    */
                }
            }
        }

    }

    public boolean isOpen(int row, int col) {
        // is the site (row, col) open?
        checkRowAndCol(row, col);
        return openHelper[row][col] == isOpened;
    }

    public void testIsFull(){
        open(2,2);
        open(3,2);
        open(2,3);
        if(isFull(2,2)==false){
            System.out.println("test 1 passed");
        }

        open(2,0);
        open(2,1);
        if(isFull(2,2)==true){
            System.out.println("test 2 passed");
        }

        open(0,0);
        if(isFull(0,0)==true){
            System.out.println("test 3 passed");
        }
    }

    public boolean isFull(int row, int col) {
        // is the site (row, col) full?
        checkRowAndCol(row, col);

        for(int i=0;i<N-1;i++){//i refer to the first row element
            if(wqu.connected(i,xyTo1D(row,col))){
                return true;
            }
        }

        return false;
    }

    public void testNumberOfOpenSites(){
        if(numberOfOpenSites()==0){
            System.out.println("test 1 passed");
        }
        open(2,2);
        open(1,2);
        open(3,1);
        open(1,1);
        if(numberOfOpenSites()==4){
            System.out.println("test 2 passed");
        }
    }

    public int numberOfOpenSites() {
        // number of open sites

        return openedCount;
    }

    public boolean percolates() {
        // does the system percolate?

        //TODO
        return false;
    }

    public static void main(String[] args) {
        Percolation percolation = new Percolation(4);
        //percolation.testXyTo1D();
        //percolation.testOpen();
        //percolation.testIsFull();
        percolation.testNumberOfOpenSites();
    }
}
