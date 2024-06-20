import algorithms.*;

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
      int q = In.readInt();
      
      double[] delicousness = new double[n];
      for (int i = 0; i < n; i++) delicousness[i] = In.readDouble();
      
      double denom = 0;
      for (double d : delicousness) denom += d;
      
      double d1 = delicousness[0];
      double d2 = delicousness[1];
      
      double probability = 0;
      
      if (q == 1) {
        probability = 1.0 - (d1 * d1) / (denom * denom);
      }
      if (q == 2) {
        double pr_friend_not_d1 = 1 - d1/denom;
        double pr_friend_d1 = d1/denom;
        probability = ((d1/denom) * pr_friend_not_d1) * pr_friend_not_d1;
        // System.out.println("pr_friend_not_d1=" + pr_friend_not_d1);
      }
      if (q == 3) {
        probability = d2 / denom;
      }
      Out.println(probability);
    }
}
