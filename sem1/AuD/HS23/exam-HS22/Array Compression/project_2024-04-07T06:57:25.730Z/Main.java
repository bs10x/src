// ADDITIONAL IMPORTS ARE NOT ALLOWED

class Main {
  public static void main(String[] args) {
    // Uncomment the following two lines if you want to read from a file
    // In.open("public/example.in");
    // Out.compareTo("public/example.out");

    int t = In.readInt(); // number of tests
    for (int test = 0; test < t; test++) {
      int n = In.readInt(); // size of A
      int[] A = new int[n];
      for (int i = 0; i < n; i++) {
        A[i] = In.readInt();
      }
      Out.println(maxCompressions(n, A));
    }
    
    // Uncomment this line if you want to read from a file
    In.close();
  }
  
  public static int maxCompressions(int n, int[] A) {
    // TODO: implement
    return 0;
  }
}