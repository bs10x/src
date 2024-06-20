// ADDITIONAL IMPORTS ARE NOT ALLOWED

class Main {
  public static void main(String[] args) {
    // Uncomment this line if you want to read from a file
    In.open("public/example.in");
    Out.compareTo("public/example.out");

    int n = In.readInt(); //Read the size of Array;
    int []A = new int[n];
    
    int k = In.readInt(); // Read goal value
    
    // Read A
    for(int i = 0; i < n; i++) {
      A[i] = In.readInt(); 
    }
    
    Out.println(minimize(n, A, k));
    
    // Uncomment this line if you want to read from a file
    In.close();
  }
    
    
  public static int minimize(int n, int[] A, int k) {
    heapsort(A); // O(n*log(n))
    int minDiff = Integer.MAX_VALUE;
    int[] diff = new int[n];
    for (int i = 0; i < n; i++) {
      diff[i] = A[i] - k;
    }
    printArray(A);
    printArray(diff);
    return -1;
  }
  
  public static int[] heapsort(int[] A) {
    int n = A.length;
    for (int i = n / 2; i >=  0; i--) heapify(i, n - 1, A);
    for (int i = n - 1; i >= 0; i--) {
      swap(0, i, A);
      heapify(0, i - 1, A);
    }
    return A;
  }
  
  private static void heapify(int i, int m, int[] A) {
    while (2 * i <= m) {
      int j = 2 * i;
      if (j + 1 <= m && A[j + 1] > A[j]) j++;
      if (A[j] > A[i]) {
        swap(i, j, A);
        i = j;
      } else break;
    }
  }
  
  private static void swap(int i, int j, int[] A) {
    if (i == j) return;
    int swap = A[j];
    A[j] = A[i];
    A[i] = swap;
  }
  
  
  
  
  
  
  public static void printArray(int[] A) {
    for (int i = 0; i < A.length; i++) {
      System.out.print(A[i]);
      if (i < A.length -1) System.out.print(", ");
    }
    System.out.println();
  }

  
}