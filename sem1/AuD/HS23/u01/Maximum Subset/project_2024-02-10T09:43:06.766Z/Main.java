// ADDITIONAL IMPORTS ARE NOT ALLOWED

class Main {
  public static void main(String[] args) {
    // Uncomment the following two lines if you want to read from a file.
    In.open("public/example.in");
    Out.compareTo("public/example.out");

    int n = In.readInt();
    int k = In.readInt();
    int[] A = new int[n];
    for (int i = 0; i < n; i++) {
      A[i] = In.readInt();
    }
    Out.println(maxSubset(n, k, A));
    
    // Uncomment this line if you want to read from a file
    In.close();
  }
  
  public static int maxSubset(int n, int k, int[] A) {
    // sort the array
    // iterate over the array, sliding window
    // find max # a in A such that a_max - a_min <= k
    //
    //
    //
    //
    
    heapsort(A);
    int a = A.length;
    int max = 0;
    for (int m : A) max = (m > max)? m : max;
    
    int res = 0;
    
    
    return res;
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
  
  
  
  
  
  
  
  
  
  
  

}