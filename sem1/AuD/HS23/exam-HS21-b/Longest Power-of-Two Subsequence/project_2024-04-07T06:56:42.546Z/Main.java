import java.io.*;
import java.util.Scanner;
import java.lang.Math;
import java.lang.Integer;
import java.lang.String;


class Main {
  public static void main(String[] args) {
    // Uncomment the following two lines if you want to read from a file.
    // Also make sure that the lines are commented when testing with the 
    // "Test" button or when submitting. Otherwise your code may exceed the
    // time liimt.
    // In.open("public/example.in");
    // Out.compareTo("public/example.out");

    int tests = In.readInt(); // number of tests
    for (int t = 0; t < tests; t++) {
      int n = In.readInt(); // length of A
      int[] A = new int[n]; // A
      for (int i = 0; i < n; i++) {
        A[i] = In.readInt();
      }
      Out.println(getLongestSubsequence(n, A));
    }
    
    // Uncomment this line if you want to read from a file
    // In.close();
  }
  
  public static int getLongestSubsequence(int n, int[] A) {
    // TODO: implement
    return 0;
  }
}