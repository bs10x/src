// ADDITIONAL IMPORTS ARE NOT ALLOWED

class Main {
  public static void main(String[] args) {
    // Uncomment the following two lines if you want to read from a file.
    In.open("public/small.in");
    Out.compareTo("public/small.out");
    
    int D = In.readInt();
    int k = In.readInt();
    int n = In.readInt();
    int[] A = new int[n];
    for (int i = 0; i < n; i++) {
      A[i] = In.readInt();
    }
    Out.println(minTicket(D, k, n, A));
    
    // Uncomment this line if you want to read from a file
    In.close();
  }
  
  public static int minTicket(int D, int k, int n, int[] A) {
    // for (int a : A) if ((a == D) || ((k > 0) && 2 * a == D)) return 1;
    
    int[][][] DP = new int[D + 1][n + 1][k + 1];
    for (int i = 0; i <= D; i++) {
      for (int j = 0; j <= n; j++) {
        for (int v = 0; v <= k; v++) {
          if (i == 0) DP[i][j][v] = 0;
          else if (v == 0) DP[i][j][v] = 
           if (i - A[j - 1] >= 0) {
              DP[i][j][v] = DP[i - A[j - 1]][j][v] + 1;
            }
            if (v > 0) {
              DP[i][j][v] = Math.min(DP[i][j][v], DP[i][j][v - 1]);
            }
        }
      }
    }
    return DP[D][n][k];
  }
}