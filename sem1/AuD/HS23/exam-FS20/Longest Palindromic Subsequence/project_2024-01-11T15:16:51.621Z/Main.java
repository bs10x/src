import java.io.*;
import java.util.LinkedList;
import java.util.Scanner;
import java.lang.Math;
import java.lang.Integer;
import java.lang.String;

class Main {
    public static void main(String[] args) {
        
        // Uncomment the following two lines if you want to read from a file
        In.open("public/example.in");
        Out.compareTo("public/example.out");
        int T = In.readInt();               // Read Number of Tasks
        
        
        for(int i=0;i<T;i++)
        {
          int n = In.readInt();             // Read Length of A
          
          String SA=In.readString();        // Read A as a string
          
          char[] A=new char[n];
          for(int j=0;j<n;j++){
            A[j]=SA.charAt(j);
          }
          Out.println(Palindrome(A, n));
          
        }
        
      
        // Uncomment the following line if you want to read from a file
        In.close();
    }
    
    public static int Palindrome(char[] A, int n) {
      int [][] DP = new int[n][n];
      
      /*
        // or like this??
        
         0  1  2  3  4  5  6  7
        [A, B, C, D, D, C, C, C]
        
        // like they want it
        x       0   1   2   3   4   5   6   7     || i
        
        0       4   4   4   4   4   4   4   4      
        1       4   4   4   4   4   4   4 
        2       4   4   4   4   4   4
        3       0   0   4   4   4
        4       0   0   0   0
        5       0   0   0
        6       0   0
        7       0
        
        ||
        j
        
        (1) Dimensions (nxn)
        (2) Subproblems (is current subsequence a palindrome?)
        (3) Base case, recursion
        (4) Calculating order
        (5) Extracting solutions
        (6) Run time (filling table, extracting solutions)
        
      */
      
      
      // Base case: all subsequences of length 1 are palindromes
      for (int i = 0; i < n; i++) {
        DP[i][i] = 1;
      }
      
      for (int len = 2; len <= n; len++) {
          for (int i = 0; i <= n - len; i++) {
              int j = i + len - 1;
              if (A[i] == A[j] && len == 2) {
                  DP[i][j] = 2;
              } else if (A[i] == A[j]) {
                  DP[i][j] = DP[i + 1][j - 1] + 2;
              } else {
                  DP[i][j] = Math.max(DP[i][j - 1], DP[i + 1][j]);
              }
          }
      }
      /*
      // base case, no pal sub longer than 1
      //DP[0][n-1] = 1;
      // iterate n-1 times (i)
      for (int i = n-1; i >= 0; i--) {
        int currentMax = i+1; // 8, 7, 6, etc.
        
        // iterate n-i times (j)
        // to find palindromic subsequences of length i+1
        for (int j = n-i-1; j > 0; j--) {
          if (makesPalSub(A, currentMax, j)) {
            DP[i][j] = currentMax;
            //break;
          }
        }
        //if (DP[0][n-1] > 0) break;
      }
      */
      // return entry containing longest palindromic subsequence
      return DP[0][n-1];
      }
      
    /*
    public static boolean makesPalSub(char[] A, int seqL, int startIndex) {
      // guard arm, 1 letter seq is always pal
      if (seqL == 1) return true;
      boolean makesPalSub = true;
      int endIndex = startIndex + seqL -1;
      for (int i = startIndex; i < endIndex / 2; i++) {
        //if (!A[i].equals(A[endIndex -i])) makesPalSub = false;
        if (A[i] != A[endIndex -i]) makesPalSub = false;
      }
      return makesPalSub;
    }*/
}

