import algorithms.*;
import java.util.*;

class Main {
    public static void main(String[] args) {
      In.open("public/sample.in");
      Out.compareTo("public/sample.out");
      int t = In.readInt();
      for (int i = 0; i < t; i++) {
        testCase();
      }
      
      In.close();
    }
    
    public static void testCase() {
      int n = In.readInt();
      int k = In.readInt();
      double[] Pr = new double[n];
      for (int i = 0; i < n; i++) Pr[i] = In.readDouble();
      
      double P = 0.0;
      
      double[][] DP = new double[n + 1][n + 1];
      for (int i = 0; i <= n; i++) {
        for (int j = i; j <= n; j++) {
          if (i > 0) DP[i][j] = DP[i-1][j-1] * Pr[j-1] + DP[i][j-1] * (1-Pr[j-1]);
          else DP[i][j] = (j > 0) ? DP[i][j] = DP[i][j-1] * (1-Pr[j-1]) : 1;
          
          // DP[i][j] = (i > 0) ?
          //   DP[i-1][j-1] * Pr[j-1] + DP[i][j-1] * (1-Pr[j-1]) :
          //   (j > 0) ?
          //     DP[i][j] = DP[i][j-1] * (1-Pr[j-1]) : 1;
        }
      }
      
      for (int i = k; i <= n; i++) P += DP[i][n];
      // printInfo(n, k, Pr, DP);
      Out.println(P);
    }
    
    public static void printInfo(int n, int k, double[] Pr, double[][]DP) {
      String info = "";
      double p = 1.0;
      double s = 0.0;
      for (double pr : Pr) {
        p *= pr;
        s += pr;
      }
      
      info += "\nn:\t" + n;
      info += "\nk:\t" + k;
      // info += "\nC(n,k):\t" + Algorithms.binom(n, k);
      
      String probs = "\nPr:\t[ ";
      for (int i = 0; i < Pr.length - 1; i++) probs += Pr[i] + ", ";
      probs += Pr[Pr.length - 1] + " ]";
      info += probs;
      
      // info += "\np:\t" + p;
      // info += "\n1-p:\t" + (1.0 - p);
      // info += "\n*k:\t" + (1.0 - p) * k;
      // info += "\ns:\t" + s;
      // info += "\n1-s:\t" + (1.0 - s);
      
      System.out.println(info);
      printDP(DP);
    }
    public static void printDP(double[][] DP) {
      int n = DP.length;
      System.out.print("DP:");
      for (int i = 0; i < n; i++) {
        System.out.print("\t[ ");
        for (int j = 0; j < n; j++) {
          System.out.print(DP[i][j] + ", ");
        }
        System.out.println("]");
      }
    }
}
