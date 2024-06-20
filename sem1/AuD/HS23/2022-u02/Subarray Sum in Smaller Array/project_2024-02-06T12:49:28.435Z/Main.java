// ADDITIONAL IMPORTS ARE NOT ALLOWED

class Main {
  public static void main(String[] args) {
    // Uncomment the following two lines if you want to read from a file.
    // In.open("public/example.in");
    // Out.compareTo("public/example.out");

    int n = In.readInt();
    int S = In.readInt();
    int[] A = new int[n];
    for (int i = 0; i < n; i++) {
      A[i] = In.readInt();
    }
    Out.println(getB(n, A, S));
    
    // Uncomment this line if you want to read from a file
    // In.close();
  }
  
  public static int getB(int n, int[] A, int S) {
    // TODO: implement
    return 0;
  }
}