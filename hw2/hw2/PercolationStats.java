package hw2;

import edu.princeton.cs.introcs.StdRandom;
import edu.princeton.cs.introcs.StdStats;

public class PercolationStats {
    public PercolationStats(int N, int T, PercolationFactory pf){
        // perform T independent experiments on an N-by-N grid
        if(N<=0||T<=0){
            throw new java.lang.IllegalArgumentException();
        }

    }
    public double mean(){
        // sample mean of percolation threshold
        return 0;
    }
    public double stddev(){
        // sample standard deviation of percolation threshold
        return 0;
    }
    public double confidenceLow(){
        // low endpoint of 95% confidence interval
        return 0;
    }

    public double confidenceHigh(){
        // high endpoint of 95% confidence interval
        return 0;
    }
}
