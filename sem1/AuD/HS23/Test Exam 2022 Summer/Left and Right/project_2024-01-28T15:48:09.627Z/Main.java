// ADDITIONAL IMPORTS ARE NOT ALLOWED

class Main {
  public static void main(String[] args) {
    // Uncomment the following two lines if you want to read from a file
    In.open("public/example.in");
    Out.compareTo("public/example.out");

    int t = In.readInt(); // number of tests
    for (int test = 0; test < t; test++) {
      int n = In.readInt(); // size of A
      int[] A = new int[n];
      for (int i = 0; i < n; i++) {
        A[i] = In.readInt();
      }
      Out.println(getMaximumScore(n, A));
    }
    
    // Uncomment this line if you want to read from a file
    In.close();
  }
  
  public static int getMaximumScore(int n, int[] A) {
    int[][] DP = new int[n][n];
    for (int s = 2; s <= n; s++) {
      for (int i = 0; i < n - s + 1; i++) {
        int j = i + s - 1;
        if (s == 2) DP[i][j] = Math.abs(A[i] - A[j]);
        else DP[i][j] = Math.max(Math.max(DP[i+1][j], DP[i][j-1]), Math.abs(A[i] - A[j]) + DP[i+1][j-1]);
      }
    }
    return DP[0][n-1];
  }
}