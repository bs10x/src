import java.io.*;
import java.util.LinkedList;
import java.util.Scanner;
import java.lang.Math;
import java.lang.Integer;
import java.lang.String;

class Main {
    public static void main(String[] args) {
        
        // Uncomment the following two lines if you want to read from a file
        In.open("public/1-small.in");
        Out.compareTo("public/1-small.out");
        
        int T = In.readInt();              // Read Number of Tasks
        for(int i=0;i<T;i++)
        {
          int n = In.readInt();           // Read Length of A
          int m = In.readInt();           // Read Length of B
          
          
          String SA=In.readString();       // Read A as a string
          String SB=In.readString();       // Read B as a string
          String SC=In.readString();       // Read C as a string
          
          char[] A=new char[n+1];
          char[] B=new char[m+1];
          char[] C=new char[n+m+1];
          
          for(int j=1;j<=n;j++){
            A[j]=SA.charAt(j-1);
          }
          
          for(int j=1;j<=m;j++){
            B[j]=SB.charAt(j-1);
          }
          
          for(int j=1;j<=n+m;j++){
            C[j]=SC.charAt(j-1);
          }
          
          Out.println(Shuffle(n,m,A,B,C));
          
        }
        
      
        // Uncomment the following line if you want to read from a file
        In.close();
    }
    
    public static boolean Shuffle(int n, int m, char[] A, char[] B, char[] C) {
      if (C.length != n + m + 1) return false;
      
      boolean[][] DP = new boolean[n + 1][m + 1];
      DP[0][0] = true;
      for(int i = 1; i <= n; i++) DP[i][0] = ( DP[i - 1][0] && C[i] == A[i] );
      for(int j = 1; j <= m; j++) DP[0][j] = ( DP[0][j - 1] && C[j] == B[j] );
      
      for (int i = 1; i <= n; i++) {
        for (int j = 1; j <= m; j++) {
          DP[i][j] = ( DP[i - 1][j] && C[i + j] == A[i] ) || ( DP[i][j - 1] && C[i + j] == B[j] );
        }
      }
      return DP[n][m];
    }
    
    
    public int LCS(int n, int m, char[] A, char[] B) {
      if (n + m == 0) return -1;
      
      int[][] DP = new int[n + 1][m + 1];
      for (int i = 0; i <= n; i++) DP[i][0] = 0;
      for (int j = 0; j <= m; j++) DP[0][j] = 0;
      
      for (int i = 1; i <= n; i++) {
        for (int j = 1; j <= m; j++) {
          DP[i][j] = Math.max( DP[i - 1][j] , DP[i][j - 1] );
          if (A[i] == B[j]) DP[i][j]++;
        }
      }
      return DP[n][m];
    }
    
    
    
    
    
    
    /*
    A = [ ABC ], n
    B = [ 123 ], m
    C = [ A1B2C3 ], n + m
    
    A1B2C345
    
        A   B   C
   1  |   |   |   |
   2  |   |   |   |
   3  |   |   |   |
   4  |   |   |   |
   5  |   |   |   |
   
   
    A = [ A, B, C]
    B = [ 1, 2, 3, 4, 5]
    C = [ A, 1, 2, B, 3, C, 4, 5]
   
    
    */
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
  ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
  /*
    
  ///////////////////////////
    int k = 1;
    int i = 1;
    int j = 1;
    boolean result = A[i] == C[k] || B[j] == C[k];
    for (int l = 1; k <= n + m; k++) {
      if (A[i] == C[k]) {
        i++;
        k++;
        break;
      } else
      if (B[j] == C[k]) {
        j++;
        k++;
        break;
      } else result = false;
    }
    return result;
  
  
  
  
  ///////////////////////////
    int counterA = 1;
    int counterB = 1;
    boolean result = false;
    
    char a1 = A[1];
    char b1 = B[1];
    char c1 = C[1];
    
    if (c1 == a1) {
      counterA++;
      result = true;
    }
    if (c1 == b1) {
      counterB++;
      result = true;
    }
    
    for (int i = 2; 1 < n + m; i++) {
      if (counterA > n || counterB > m) break;
      
      char a = A[counterA];
      char b = B[counterB];
      char c = C[i];
      
      if (c != a && c != b) {
        result = false;
        break;
      }
      
      if (c == a) {
        counterA++;
        break;
      } else
      if (c == b) {
        counterB++;
        break;
      } else break;
    }
    return result;
    */

   
}