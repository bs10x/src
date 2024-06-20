import java.lang.Math;


class Main {
  public static void main(String[] args) {
    // Uncomment the following two lines if you want to read from a file.
    In.open("public/example.in");
    Out.compareTo("public/example.out");

    int tests = In.readInt(); // number of tests
    for (int t = 0; t < tests; t++) {
      int n = In.readInt(); // length of A
      int[] A = new int[n]; // A
      for (int i = 0; i < n; i++) {
        A[i] = In.readInt();
      }
      Out.println(getMaximumSum(n, A));
    }
    
    // Uncomment this line if you want to read from a file
    In.close();
  }
  
  
  public static int getMaximumSum(int n, int[] A) {
    int[] DP = new int[n + 1];
    for (int i = 1; i < n; i++) {
      DP[i] = DP[i - 1];
      if (i - 3 >= 0) DP[i] = Math.max( DP[i], DP[i - 3] + A[i] + A[i - 1] );
      else DP[i] = Math.max( DP[i], A[i] + A[i - 1] );
    }
    return DP[n - 1];
  }
}