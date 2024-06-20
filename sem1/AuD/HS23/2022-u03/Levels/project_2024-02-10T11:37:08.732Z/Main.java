// ADDITIONAL IMPORTS ARE NOT ALLOWED

class Main {
  public static void main(String[] args) {
    // Uncomment the following two lines if you want to read from a file
    // In.open("public/example.in");
    // Out.compareTo("public/example.out");

    int n = In.readInt();
    int[] A = new int[n];
    int[] B = new int[n];
    for (int i = 0; i < n; i++) {
      A[i] = In.readInt();
    }
    for (int i = 0; i < n; i++) {
      B[i] = In.readInt();
    }
    Out.println(getLevels(n, A, B));
    
    // Uncomment this line if you want to read from a file
    // In.close();
  }
  
  public static int getLevels(int n, int[] A, int[] B) {
    // TODO: implement
    return 0;
  }
}