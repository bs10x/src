import algorithms.*;

class Main {
    public static void main(String[] args) {
      String file = "sample";
      In.open("public/" + file + ".in");
      Out.compareTo("public/" + file + ".out");
      int t = In.readInt();
      for (int i = 0; i < t; i++) {
        testCase();
      }
      In.close();
    }
    
    public static void testCase() {
      int n = In.readInt();
      int m = In.readInt();
      int[] v = new int[n];
      int[] p = new int[n];
      int[] d = new int[n];
      int S = 5*n+1;
      int T = 5*n+2;
      int D = 0;
      Graph G = new Graph(5*n+2);
      for (int i = 0; i < n; i++) {
        v[i] = In.readInt();        // variety, "favorite"
        p[i] = In.readInt();        // produce
        d[i] = In.readInt();        // demand
        D += d[i];                  // total demand
        G.addEdge(S, v[i]*n+i, p[i]);     // from source to produce
        G.addEdge(v[i]*n+i, 4*n+i, p[i]); // from produce to variety
        G.addEdge(1*n+i, T, d[i]);  // from variety to sink
        G.addEdge(2*n+i, T, d[i]);  // from variety to sink
        G.addEdge(3*n+i, T, d[i]);  // from variety to sink
      }
      for (int i = 0; i < m; i++) {
        int x = In.readInt();   // from
        int y = In.readInt();   // to
        if (v[x] != v[y]) {
          G.addEdge(v[x]*n+x, v[y]*n+y, 7);
        }
      }
      
      
      boolean possible = G.computeMaximumFlow(S, T) == D;
      Out.println(possible? "y" : "n");
    }
}

