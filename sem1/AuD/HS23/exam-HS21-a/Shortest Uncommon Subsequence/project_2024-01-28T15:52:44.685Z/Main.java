import java.io.*;
import java.util.LinkedList;
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
    In.open("public/custom.in");
    //Out.compareTo("public/custom.out");

    int T = In.readInt();           // number of tasks
    for(int i = 0; i < T; i++)
    {
      int n = In.readInt();         // length of A
      int m = In.readInt();         // length of B
      
      String SA = In.readString();  // read A as a string
      String SB = In.readString();  // read B as a string
      
      char[] A = new char[n];
      char[] B = new char[m];
      for (int j = 0; j < n; j++) {
        A[j] = SA.charAt(j);
      }
      for (int j = 0; j < m; j++) {
        B[j] = SB.charAt(j);
      }
      
      Out.println(ShortestUncommonSubsequence(n, m, A, B));
    }
  
    // Uncomment the following line if you want to read from a file
    In.close();
  }
  
  /*
  You may use an (n+1)*(m+1)-size DP table where, e.g., DP[i][j]
  stores the answer for the first i characters of A
  and the first j characters of B.
  
  For a dynamic program, you can decompose the problem based on the largest position in B
  that matches the last character of A.
  
  
  
  
  A = EETTT, n = 5
  B = TETTE, m = 5
  
  sUS = "EET"
  
  // DP TABLE
  
  X   0   1   2   3   4   5  6 (n+1)
  
  0 
  1       0   0   0   
  2       0   0   0
  3       0   0   0
  4       0   0   0
  5       0   0   0      *3*
  6 
  
  n ≤ m
  
  A = [ A, B, B, C, D, E, F, F, F, Z, X ], n
  B = [ A, B, B, B, A, E, F, F, F, Z, Z ], m
  */
  
  
  public static int ShortestUncommonSubsequenceBF(int n, int m, char[] A, char B[]) {
    int[][] DP = new int[n+1][m+1];
    
    for (int i = 1; i <= n; i++) {
      for (int j = 1; j <= m; j++) {
        if (A[i - 1] == B[j - 1]) {
          DP[i][j] = Math.min(DP[i - 1][j], DP[i][j - 1]) + 1;
        }
        else {
          DP[i][j] = DP[i - 1][j - 1];
        }
      }
    }
    printDP(DP);
    return DP[n][m] ;
  }
  
  
  public static int ShortestUncommonSubsequence(int n, int m, char[] A, char B[]) {
    int[][] DP = new int[n + 1][m + 1];
    for (int i = 0; i <= n; i++) DP[i][0] = 1;
    for (int j = 0; j <= m; j++) DP[0][j] = Integer.MAX_VALUE - 1;
    for (int i = 1; i <= n; i++) {
      for (int j = 1; j <= m; j++) {
        char common = A[i - 1];
        int search;
        for (search = j - 1; search >= 0; search--) if (B[search] == common) break;
        if (search == -1) DP[i][j] = 1; 
        else {
          if (DP[i - 1][j] <= DP[i - 1][search] + 1) DP[i][j] = DP[i - 1][j];
          else DP[i][j] = DP[i - 1][search] + 1;
        }
      }
    }
    if (DP[n][m] < Integer.MAX_VALUE) return DP[n][m];
    else return -1;
  }
  
  
  
  
  
  
  
  
  
  
  
  public static void printDP(int[][] DP) {
    for (int i = 0; i < DP.length; i++) {
      for (int j = 0; j < DP[i].length; j++) {
        System.out.print(DP[i][j] + " ");
      }
      System.out.println();
    }
  }
  
  
  
}
