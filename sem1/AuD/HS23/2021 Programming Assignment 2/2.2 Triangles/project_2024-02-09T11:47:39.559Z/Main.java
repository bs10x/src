class Main {
    public static void main(String[] args) {
        // Uncomment this line if you want to read from a file
        In.open("public/small.in");
        Out.compareTo("public/small.out");

        int n = In.readInt(); // read the size of A
        int []A = new int[n];
        
        // Read A
        for(int i = 0; i < n; i++) {
          A[i] = In.readInt(); 
        }
        
        Out.println(triangles(n, A));
        
        // Uncomment this line if you want to read from a file
        In.close();
    }
    
    public static long triangles(int n, int []A) {
    //   long[][] DP = new long[n][n];
    //   // base case, i, j, k direct neighbors
    //   for (int l = 0; l <= n - 3; l++) if (isValidTriangle(A[l], A[l + 1], A[l + 2])) DP[l][l + 2]++;
    //   int spread = 0;
    //   while (spread <= n - 3) {
    //     for (int start = n - 3; start >= 0; start--) {
    //       int end = start + spread + 2;
    //       if (end >= n) break;
    //       DP[start][end] = Math.max(DP[start + 1][end], DP[start][end - 1]);
    //       for (int mid = start + 1; mid < end; mid++) {
    //         if (isValidTriangle(A[start], A[mid], A[end])) DP[start][end]++;
    //       }
    //     }
    //     spread++;
    //   }
    //   printMatrix(DP);
    //   return DP[0][n - 1];
      long trianlges = 0;
      for (int i = 0; i < n - 2; i++) {
        for (int j = i + 1; i < n - 1; i++) {
          for (int k = j + 1; k < n; k++) {
            if (isValidTriangle(A[i], A[j], A[k])) trianlges++;
          }
        }  
     }
     return trianlges;
    }
    
    
    
    public static boolean isValidTriangle(int a, int b, int c) {
      return (a + b >= c) && (b + c >= a) && (c + a >= b); 
    }
    
    public static void printArray(int[] L) {
      for (int i = 0; i < L.length - 1; i++) System.out.print(L[i] + ", ");
      System.out.println(L[L.length - 1]);
    }
    
    public static void printArray(long[] L) {
      for (int i = 0; i < L.length - 1; i++) System.out.print(L[i] + ", ");
      System.out.println(L[L.length - 1]);
    }
    
    public static void printMatrix(long[][] M) {
      for (int i = 0; i < M.length; i++) printArray(M[i]);
    }
  
}