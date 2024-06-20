// ADDITIONAL IMPORTS ARE NOT ALLOWED

class Main {
  public static void main(String[] args) {
    // Uncomment this line if you want to read from a file
    In.open("public/example.in");
    Out.compareTo("public/example.out");
    
    int n = In.readInt();
    int[] A = new int[n];
    for (int i = 0; i < n; i++) {
      A[i] = In.readInt();
    }
    Out.println(longestSubarray(n, A));
    
    // Uncomment this line if you want to read from a file
    In.close();
  }
    
  public static int longestSubarray(int n, int[] A) {
    int maxLength = 1;  // Initialize the maximum length to 1 (minimum length of a segment)
    int currentLength = 0;  // Initialize the current segment length to 1

    for (int i = 1; i < n; i++) {
        if (A[i] == A[i - 1]) {
            currentLength++;
        } else {
            currentLength = 1;  // Reset the current segment length
        }

        // Update the maximum length if the current segment is longer
        if (currentLength > maxLength) {
            maxLength = currentLength;
        }
    }

    return (maxLength == n) ? maxLength - 1 : maxLength;
}


}