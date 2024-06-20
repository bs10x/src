import algorithms.*;
import java.util.*;

class Main {
    public static int q, n, k;
    public static double[] p, r, E;
    public static double[][] dp;
    
    public static void main(String[] args) {
      In.open("public/test3.in");
      Out.compareTo("public/test3.out");
      int t = In.readInt();
      for (int i = 0; i < t; i++) {
        testCase();
      }
      In.close();
    }
    
    public static void testCase() {
      init();
      if(q == 1) Out.println(q1());
      if(q == 2) Out.println(q2());
      if(q == 3) seb();//Out.println(seb());
    }
    
    public static void init() {
      q = In.readInt();
      n = In.readInt();
      k = In.readInt();
      p = new double[n+1];
      r = new double[n+1];
      for (int i = 1; i <= n; i++) {
        p[i] = In.readDouble();
        r[i] = In.readDouble();
      }
      
      // hm();
    }
    
    public static double q1() {
      double exp_not_taken_k_eq_n = 0;
      for (int i = 1; i <= n; i++) {
        exp_not_taken_k_eq_n += 1 - (p[i] * r[i]);
      }
      return exp_not_taken_k_eq_n;
    }
    public static double q2() {
      // A = "first taken"
      // B = "second taken"
      double p1 = p[1];
      double r1 = r[1];
      double p2 = p[2];
      double r2 = r[2];
      double pr_A = p1*r1;
      double pr_B = r2 * ((p1*r1) + ((1-p1)*p2) + (p1*(1-r1)*p2));
      double pr_A_B = (r2 * pr_A) / pr_B;
      return pr_A_B;
    }
    public static double q3() {
      double exp_not_taken = 0;
      for (int i = 1; i <= n; i++) {
        exp_not_taken += 1 - E[i];
      }
      return exp_not_taken;
    }
    
    public static double streak(int a, int b) {
      double streak = 1;
      for (int i = a; i < b; i++) {
        streak *= E[i];
        // System.out.println("E["+i+"]="+E[i]);
      }
      return streak;
    }
    
    public static void before() {
      
      // // E[i] = Pr["taking i-th bag"]
      // double[] E = new double[n+1];
      // // streaks[b][a] = Pr["taking all bags from a to b"]
      // double[][] streaks = new double[n+1][];
      // streaks[0] = new double[1];
      // // streaks ending at b ..
      // for (int b = 1; b <= n; b++) {
        
      //   streaks[b] = new double[b+1]; // # streaks ending at b
        
      //   // 1-Pr["k-th bag"] * p[b] * r[b]
      //   if (b<=k) {
      //     E[b] = p[b] * r[b];
      //   }
      //   else {
      //     E[b] = (1-streaks[b-1][b-k]) * p[b] * r[b];
      //   }
        
      //   // .. starting at a
      //   for (int a = 1; a <= b; a++) {
      //     streaks[b][a] = streaks[b-1][a] * E[b];
      //     if (b-a>k) {
      //       // when the streak is longer than k
      //       // modify the probability to take b-th bag
      //       E[b] += streaks[b][a] * r[b];
      //     }
      //   }
      // }
      
      // E[i] = "probability of taking i-th bag, given last bag not taken"
      E = new double[n+1];
      for (int i = 1; i <= n; i++)  {
        E[i] = (1-E[i-1]) * p[i]*r[i];
      }
      
      // System.out.println("p:\t" + Arrays.toString(p));
      // System.out.println("r:\t" + Arrays.toString(r));
      // System.out.println("E:\t" + Arrays.toString(E));
      
      // streaks[b][a] = Pr["taking all bags from a to b"]
      double[][] streaks = new double[n+1][];
      
      // streaks ending at b ..
      for (int b = k+1; b <= n; b++) {
        // # streaks from a to b, length > k
        // refactor to deprecate streak() ??
        // make it not exclusive to b-a > k
        streaks[b] = new double[(b>k)? b-k+1 : 1];
        streaks[b][b-k] = streak(b-k, b);
        // .. starting at a
        for (int a = 1; a <= b-k; a++) {
          // System.out.println("x");
          // streaks[b][a] = streaks[b][a-1] * E[b];
          streaks[b][a] = streak(a, b);
        }
        // System.out.println("s["+b+"]:\t" + Arrays.toString(streaks[b]));
        
        // sum to E[b] 
        for (int a = 1; a <= b-k; a++) {
          E[b] += streaks[b][a] * r[b];
          // System.out.println(streaks[b][a] * p[b] * r[b]);
        }
      }
      // System.out.println("E:\t" + Arrays.toString(E));
    }
    
    public static void bf() {
      // E[i] = Pr["i-th bag taken"]
      E = new double[n+1];
      
      // streak[a] = Pr["all bags taken from a to b"]
      // streak[0] = Pr["more than k bags taken at b"]
      double[] streak = new double[n+1];
      
      // streak ending at b ..
      for (int b = 1; b <= n; b++) {
        streak[b] = 1;    // * pr_no streak at b // ?
        
        // .. starting at a
        for (int a = 1; a <= b; a++) {
          streak[a] *= E[b];
        }
        
        if (b<=k) {
          E[b] = p[b] * r[b];
        }
        else {
          E[b] = p[b] * r[b] * (1-streak[0]) + streak[0] * r[b];
        }
        // if (b>k) {
        //   E[b] += streak[0] * r[b];
        // }
        
        
        // update the total probability of streaks > k at b 
        for (int a = 1; a <= b-k; a++) {
          streak[0] += streak[a];
        }
        
        
        
        
        
     }
  }
    
    public static void hm() {
      
      // E[j] = Pr["j-th bag taken"]
      E = new double[n+1];
      
      // K[j] = Pr["k or more consecutive bags taken at j"]
      //      --> K[j<=k] = 0
      double[] K = new double[n+1];
      
      // dp[i][j] = "probability of taking all bags from i to j
      double[][] dp = new double[n+1][];
      
      // streaks ending at j ...
      for (int j = 1; j <= n; j++) {
        
        // less than or only the k-th bag
        if (j <= k) {
          E[j] = p[j] * r[j];
        }
        else {
          E[j] = (1-K[j-1]) * p[j] * r[j]; 
        }
        
        // there are j ways to reach the j-th bag
        dp[j] = new double[j+1];
        
        // ... starting at i
        for (int i = 1; i <= j; i++) {
          
          // j-k options to consider
          if (i-j>k) {
            // K[i-j] += dp[i][i-j];
          }
          
          dp[j][i] = dp[j-1][i] * E[j];
        }
      }
      
    }  
    
    public static void seb() {
      for (int i = 0; i < n; i++) {
        p[i] = p[i+1];
        r[i] = r[i+1];
      }
      var P = new double[n][n];
          var T = new double[n];
          P[0][0] = p[0] * r[0];
          T[0] = P[0][0];
          var E = 1-T[0];
          for (int i = 1; i < n; ++i) {
            for (int j = 0; j <= i; ++j) {
              if (j == 0) {
                P[i][j] = (1-T[i-1]) * p[i] * r[i];
              } else if (j < k) {
                P[i][j] = P[i-1][j-1] * p[i] * r[i];
              } else {
                P[i][j] = P[i-1][j-1] * r[i];
              }
              T[i] += P[i][j];
            }
            E += (1-T[i]);
          }

          Out.println(E);
    }
}


/*

n = 2
k = 1

pr1 = p1*r1                                       = 0.2 * 0.5   = 0.1
pr2 = p2*r2 + E1*r2 = (1-pr1) * 0.6*0.5 + 0.1*0.5 = 0.27 + 0.05  = 0.32

E1 = 0.1
E2 = 0.305 = 0.31
E2 = 0.305 // ????


*/