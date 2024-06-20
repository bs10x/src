class Main {
    public static void main(String[] args) {
        // Uncomment this line if you want to read from a file
        In.open("public/example.in");
        // Out.compareTo("public/example.out");

        int n = In.readInt(); //Read the size of Array;
        int []A = new int[n + 1];
        
        int K = In.readInt(); // Read size of segments
        
        // Read A
        for(int i = 1; i <= n; i++) {
          A[i] = In.readInt(); 
        }
        
        Out.println(longestKSegmentedSubarray(n, A, K));
        
        // Uncomment this line if you want to read from a file
        In.close();
    }
    
    public static int longestKSegmentedSubarray(int n, int []A, int k) {
      int[] kMaxSeg = new int[k];
      int[] seg = new int[n];
      
      for (int i = 0; i < n - 1; i++) {
        int segL = 1;
        for (int l = i; A[i] == A[l + segL]; l++) segL++; // counting current segL
        for (int j = i; j < i + segL; j++) seg[j] = segL; // update seg for all segL entries
      }
      
      printArray(seg);
      
      
      int counter = 0;
      int lastMax = 0;
      
      
      for (int i = 0; i < n && counter < k; i++) {
        if (seg[i] > kMaxSeg[counter] && seg[i] > lastMax) {
          kMaxSeg[counter] = seg[i];
          counter++;
          lastMax = seg[i];
        }
      }
      
      
      
      
      int res = 0;
      for (int m : kMaxSeg) res += m;
      return 123;
    }
    
    public static void printArray(int[] A) {
      for (int i = 0; i < A.length; i++) System.out.println(A[i]);
    }
  
}