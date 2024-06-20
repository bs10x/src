import algorithms.*;

class Main {
    public static void main(String[] args) {
        // Uncomment this line if you want to read from a file
        In.open("public/test1.in");
        Out.compareTo("public/test1.out");

        int t = In.readInt();
        for (int i = 0; i < t; i++) {
            testCase();
        }
        
        // Uncomment this line if you want to read from a file
        In.close();
    }

    public static void testCase() {
        int n = In.readInt();
        Out.println(fibMod1e6(n));
    }
    
    public static long fibMod1e6(int n) {
      long[] DP = new long[n + 1];
      DP[1] = 1;
      if (n < 2) return DP[n]; 
      for (int i = 2; i <= n; i++) {
        DP[i] = (DP[i - 1] + DP[i - 2]) % 1000000;
      }
      return DP[n];
    }
}
