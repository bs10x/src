class Main {
    public static void main(String[] args) {
        // Uncomment this line if you want to read from a file
        // In.open("public/example.in");
        // Out.compareTo("public/example.out");

        int n = In.readInt(); //Read the size of Array;
        int []A = new int[n + 1];
        
        // Read A
        for(int i = 1; i <= n; i++) {
          A[i] = In.readInt(); 
        }
        
        Out.println(longest_alternating_subarray(n, A));
        
        // Uncomment this line if you want to read from a file
        // In.close();
    }
    
    public static int longest_alternating_subarray(int n, int []A)
    {
      // n: length of A
      // A: an array of distinct integers
      
      return 0;
    }
  
}