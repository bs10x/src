import algorithms.*;
import java.util.*;

class Main {
    public static void main(String[] args) {
      In.open("public/custom.in");
      Out.compareTo("public/custom.out");
      int t = In.readInt();
      for (int i = 0; i < t; i++) testCase();
      In.close();
    }
    
    public static final long b = 10_000_000_000L;
    
    public static void testCase() {
        int n = In.readInt();   // #A
        long[] A = new long[n]; // A
        for (int i = 0; i < n; i++) A[i] = In.readInt();  // populate A w/ a_i
        long result = b;    // #z !/ a
        for (int i = 1; i < (1 << n); i++) {  // i --> {0, 1}^n == subset representations
          long x = 1;   // lcm( #z !/ k#a)
          int k = 0;    // #b1
          for (int j = 0; j <= n; j++) {
            if (((i >> j) & 1) == 1) {  // b@j == b1
              x = lcm(x, A[j]);
              k++;
            }
          }
          result += (k % 2 == 0) ? +(b / x) : -(b / x);
        }
        Out.println(result);
    }
    public static long lcm(long a, long b) {
      return a * b / Algorithms.gcd(a, b);
    }
}
