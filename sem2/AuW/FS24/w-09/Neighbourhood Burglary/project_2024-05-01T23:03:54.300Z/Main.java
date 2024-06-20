import algorithms.*;

class Main {
    public static double[] v, e, o, p;
    public static int n, q;
    
    public static void main(String[] args) {
      In.open("public/custom.in");
      Out.compareTo("public/custom.out");
        int t = In.readInt();
        for (int i = 0; i < t; i++) {
          testCase();
        }
      In.close();
    }
    
    public static void testCase() {
      init();
      // printInfo();
      if (q == 1) Out.println(q1());
      if (q == 2) Out.println(q2());
      if (q == 3) Out.println(q3());
    }
    
    public static void init() {
      n = In.readInt();
      q = In.readInt();
      v = new double[n+1];
      e = new double[n+1];
      o = new double[n+1];
      p = new double[n+1];
      p[1] = 1.0;
      for (int i = 1; i <= n; i++) v[i] = In.readDouble();
      for (int i = 1; i <= n; i++) {
        e[i] = In.readDouble();
        o[i] = In.readDouble();
        if (i > 1) p[i] = pr(i);
      }
    }
    public static double q1() {
      double exp_veq = 0;
      for (int i = 1; i <= n; i++) exp_veq += v[i] * e[i];
      return exp_veq;
    }
    public static double q2() {
      double pr_3_1 = pr(3, 1);
      double pr_3_2 = pr(3, 2);
      double pr_2 = pr(2, 1);
      double pr_3 = pr_2 * pr_3_2 + (1-pr_2) * pr_3_1;
      double pr_2_3 = (pr_3_2 * pr_2) / pr_3;
      return pr_2_3;
    }
    public static double q3() {
      double exp_v = 0;
      for (int i = 1; i <= n; i++) exp_v += v[i] * p[i];
      return exp_v;
    }
    
    public static double pr(int i, int j) {
      return (v[i]>=v[j])? ((v[i]-v[j])%2==0)? e[i] : o[i] : 0.0;
    }
    public static double pr(int i) {
      double pi = 0;
      for(int j = 1; j < i; j++){
        double x = fail(j, i);
        pi += (v[i]>=v[j])? ((v[i]-v[j])%2==0)? x*e[i] : x*o[i] : 0;
      }
      return pi;
    }
    public static double fail(int j, int i) {
      double fail = p[j];
      for(int x = j+1; x < i; x++){
        fail *= (v[x]>=v[j])? ((v[x]-v[j])%2==0)? (1-e[x]) : (1-o[x]) : 1;
      }
      return fail;
    }
    
    public static void printInfo() {
      String info = "";
      info += "H\tv\te\to\tp\n";
      for (int i = 1; i < n; i++) {
        info +=   i  + "\t";
        info += v[i] + "\t";
        info += e[i] + "\t";
        info += o[i] + "\t";
        info += p[i] + "\n";
      }
      info += n + "\t" + v[n] + "\t" + e[n] + "\t" + o[n] + "\t" + p[n];
      System.out.println(info);
    }
}
/////////////////////////////////////////////////////////////////////////////////
