import algorithms.*;

class Main {
    public static void main(String[] args) {
      In.open("public/test1.in");
      Out.compareTo("public/test1.out");
      int t = In.readInt();
      for (int i = 0; i < t; i++) {
        testCase();
      }
      In.close();
    }

    public static void testCase() {
      int n = In.readInt(); // number of children
      int m = In.readInt(); // number of different toys
      int s = 0;            // supersource
      int t = n+m+1;        // supersink
      int D = 0;            // total deserved toys
      
      Graph G = new Graph(n+m+2);
      for (int i = 1; i <= n+m; i++) {
        if (i <= n) {             // iterate over kids
          int d = In.readInt();   // how many toys i-th kid deserves
          G.addEdge(i, t, d);     // connect kid to sink w/ capacity d
          D += d;                 // sum to total deserved toys
        }
        else {                    // iterate over toy types
          int c = In.readInt();   // how many copies of i-th toy
          G.addEdge(s, i, c);     // connect source to toy type w/ capacity c
          for (int j = 1; j <= n; j++)    // iterate over kids
            G.addEdge(i, j, 1);           // connect toy to kid w/ capacity c
        }
      }
      
      boolean possible = D == G.computeMaximumFlow(s, t);
      Out.println(possible? "yes" : "no");
    }
}
