import algorithms.*;
import java.util.*;

//             _(I)(I)_
//            ( _ .. _ )
//             `.`--'.'
//          ,-./      \,-.
//         ( _( ||  || )_ )
//        __\ \\||--||'/ /__
//        `-._//||\/||\\_.-'
//             `--'`--'
// // // // // // // // // // // // // //
class Main {
    public static double[][] DP;
    public static double[] Pr, Fl;
    public static int n, m, k;
    
    public static void main(String[] args) {
      In.open("public/sample.in");
      Out.compareTo("public/sample.out");
      int t = In.readInt();
      for (int i = 0; i < t; i++) testCase();
      In.close();
    }
    
    public static void testCase() {
      n = In.readInt();
      k = In.readInt();
      m = In.readInt();
      
      Fl = new double[n];
      Pr = new double[n];
      for (int i = 0; i < n; i++) Fl[i] = In.readDouble();
      for (int i = 0; i < n; i++) Pr[i] = In.readDouble();
      
      DP = new double[n][m + 1];
      for (int i = 0; i < n; i++) {
        for (int j = 0; j <= m; j++) {
          DP[i][j] = -1;
        }
      }
      
      // printInfo(n, k, m, Pr, Fl, DP);
      Out.println(snack(k, m));
    }
    
    public static double snack(int position, int jumps) {
      if (position < 0 || position > n - 1) return 0.0;
      if (jumps == 0) return Fl[position];
      if (DP[position][jumps] != -1) return DP[position][jumps];
      
      DP[position][jumps] = Fl[position]
        + Pr[position] * snack(position - 1, jumps - 1)
        + (1 - Pr[position]) * snack(position + 1, jumps - 1);
      return DP[position][jumps];
    }
    
    public static void printInfo(int n, int k, int m, double[] Pr, double[] Fl, double[][] DP) {
      String info = "";
      info += "\nn:\t" + n;
      info += "\nk:\t" + k;
      info += "\nm:\t" + m;
      info += "\nPr:\t" + Arrays.toString(Pr);
      info += "\nFl:\t" + Arrays.toString(Fl);
      
      String dp = "";
      for (int i = 0; i <= m; i++) {
        dp += "\t[";
        for (int j = 0; j < n - 1; j++) {
          dp += DP[j][i] + ", ";
        }
        dp += DP[n-1][i] + "]\n";
      }
      info += "\nDP:" + dp;
      
      System.out.print(info);
    }
}
