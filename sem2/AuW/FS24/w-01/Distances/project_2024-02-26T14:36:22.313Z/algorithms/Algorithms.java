package algorithms;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Arrays;

public class Algorithms {
    // Memoization table
    private static ArrayList<ArrayList<Long>> bin;
    
    /**
    * Computes the binomial coefficient `n` choose `k`, that is:
    * n! / ((n-k)! k!).
    * 
    * Note: This value gets large very quickly and 
    * might not fit into long even for relatively small `n` and `k`.
    * 
    * Runtime: O(n*k)
    * Memory usage: O(n*k)
    * 
    * Important note: This function uses memoization, which means that if called
    * multiple times, the time complexity may be way less than proportional to 
    * n*k each time it is called. Values which have been computed once are never
    * computed again. The same applies to the memory. Thus, the cumulative
    * time and memory complexity are proportional to N*K, where N and K are the
    * largest values of n and k, with which the function is ever called, plus 
    * some constant time and memory cost for each call.
    * 
    * @param  n   value of n, should be non-negative
    * @param  k   value of k, should be non-negative and no bigger than n
    * 
    * @return   n choose k
    */
    public static long binom(int n, int k) {
        if (k > n || n < 0 || k < 0) {
            return 0;
        }

        if (bin == null)
            bin = new ArrayList<ArrayList<Long>>();

        if (bin.size() <= n) {
            for (int i = bin.size(); i <= n; i++) {
                ArrayList<Long> newList = new ArrayList<Long>();
                bin.add(newList);
            }
        }

        if (bin.get(n).size() <= k) {
            for (int i = 0; i <= n; i++) {
                if (bin.get(i).size() <= k && bin.get(i).size() <= i) {
                    for (int j = bin.get(i).size(); j <= k && j <= i; j++) {
                        bin.get(i).add(Long.valueOf(0));
                    }
                }
            }
        }

        return recBinom(n, k);
    }

    /**
    * Helper function that computes the binomial coefficient `n` choose `k`
    * recursively, using the array bin for memoization.
    * 
    * @return    n choose k
    */
    private static long recBinom(int n, int k) {
        if (bin.get(n).get(k) > 0) {
            return bin.get(n).get(k).longValue();
        }
        
        if (k == 0 || k == n) {
            bin.get(n).set(k, Long.valueOf(1));
            return 1;
        } 

        long result = recBinom(n - 1, k - 1) + recBinom(n - 1, k);
        bin.get(n).set(k, Long.valueOf(result));

        return result;
    }

    /**
    * Computes `n` factorial (n!).
    * If n <= 0, returns 1.
    * 
    * Note: This value gets large very quickly and
    * might not fit into long even for relatively small `n`.
    * 
    * Runtime: O(n)
    * Memory usage: O(1)
    * 
    * @param     n   the number, the factorial of which is to be
    *                computed
    * 
    * @return    n!
    */
    public static long fact(int n) {
        if (n <= 0) return 1;
        long f = 1;
        for (int i = 1; i <= n; i++) {
            f *= i;
        }
        return f;
    }
}
