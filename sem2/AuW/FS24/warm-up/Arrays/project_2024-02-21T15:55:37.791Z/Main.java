import algorithms.*;

class Main {
    public static void main(String[] args) {
        // Uncomment this line if you want to read from a file
        In.open("public/sample.in");
        Out.compareTo("public/sample.out");

        int t = In.readInt();
        for (int i = 0; i < t; i++) {
            testCase();
        }
        
        // Uncomment this line if you want to read from a file
        In.close();
    }

    public static void testCase() {
        int n = In.readInt();
        int[] A = new int[n];
        for (int i = 0; i < n; i++) A[i] = In.readInt();
        
        int d = In.readInt();
        A = removeElements(A, d, d);
        
        int a = In.readInt();
        int b = In.readInt();
        A = removeElements(A, a, b);
        
        printArray(A);
    }
    
    public static int[] removeElements(int[] A, int a, int b) {
      int n = A.length;
      
      int delta = b - a + 1;
      int m = n - delta;
      
      int[] B = new int[m];
      for (int i = 0; i < a; i++) B[i] = A[i];
      for (int j = b + 1; j < n; j++) B[j - delta] = A[j];
      return B;
    }
    
    public static void printArray(int[] A) {
      if (A.length == 0) {
        Out.print("Empty");
        return;
      }
        
      for (int a : A) Out.print(a + " ");
      Out.println();
    }
 }
