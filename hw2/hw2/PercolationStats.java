package hw2;

import edu.princeton.cs.introcs.StdRandom;
import edu.princeton.cs.introcs.StdStats;

public class PercolationStats {
    private int N;
    private int T;
    private Percolation percolation;
    private double[] totalThreshold;

    public PercolationStats(int N, int T, PercolationFactory pf) {
        // perform T independent experiments on an N-by-N grid
        if (N <= 0 || T <= 0) {
            throw new java.lang.IllegalArgumentException();
        }
        this.N = N;
        this.T = T;
        percolation = pf.make(N);

    }

    /*
    public void testPerformOneExperiment(){
        double result;
        PercolationFactory percolationFactory = null;
        Percolation percolation=percolationFactory.make(10);
        result=performOneExperiment(percolation);
        System.out.println(result);
    }

     */

    public void performExperiments(Percolation percolation) {
        final int numberOfPossibleIntegers = N - 1;
        double[] totalThreshold=new double[T];

        for(int i=0;i<T;i++){
            //perform one time experiment
            while (!percolation.percolates()) {
                percolation.open(StdRandom.uniform(numberOfPossibleIntegers)
                        , StdRandom.uniform(numberOfPossibleIntegers));
            }
            int openedSites=percolation.numberOfOpenSites();
            double threshold = openedSites / (N * N);
            totalThreshold[i]=threshold;
        }

        this.totalThreshold=totalThreshold;
    }

    public double mean() {
        // sample mean of percolation threshold
        double mean=StdStats.mean(totalThreshold)/T;
        return mean;
    }

    public double stddev() {
        // sample standard deviation of percolation threshold
        //TODO using library
        return 0;
    }

    public double confidenceLow() {
        // low endpoint of 95% confidence interval
        return 0;
    }

    public double confidenceHigh() {
        // high endpoint of 95% confidence interval
        return 0;
    }

}
