import algorithms.*;

class Main {
    public static void main(String[] args) {
      In.open("public/test1.in");
      Out.compareTo("public/test1.out");
      int t = In.readInt();
      for (int i = 0; i < t; i++) {
        testCase();
      }
    In.close();
    }

    public static void testCase() {
      int n = In.readInt();
      double p = In.readDouble();
      
      double Expected = choose(n, 3) * power(p, 3);
      double Variance = choose(n, 3) * power(p, 3)
        + (12 * choose(n, 4) * power(p, 5))
        + (30 * choose(n, 5) * power(p, 6))
        + (20 * choose(n, 6) * power(p, 6))
        - power(Expected, 2)
      ;
      Out.print(Expected + " ");
      Out.println(Variance);
    }
    
    public static long choose(int n, int k) {
      return Algorithms.binom(n, k);
    }
    public static double power(double base, double exponent) {
      return Math.pow(base, exponent);
    }
    
    /*
    number of edges (mean) in G_{n, p} = p * n(n-1) / 2
    number of possible edges in G = n(n-1) / 2
    number of possible triangles in G = binom(n, 3)
    
    
    */
}
