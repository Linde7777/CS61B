package hw3.hash;

import java.util.List;

public class OomageTestUtility {
    public static boolean haveNiceHashCodeSpread(List<Oomage> oomages, int M) {

        int[] buckets = new int[M];
        for (Oomage o : oomages) {
            int num1 = o.hashCode();
            int num2 = num1 & 0x7FFFFFFF;
            int bucketNum = (num2) % M;
            buckets[bucketNum] += 1;
        }
        int low = oomages.size() / 50;
        int high = (int) (oomages.size() / 2.5);
        for (int bucket : buckets) {
            if (bucket < low || bucket > high) {
                return false;
            }
        }
        return true;
    }
}
