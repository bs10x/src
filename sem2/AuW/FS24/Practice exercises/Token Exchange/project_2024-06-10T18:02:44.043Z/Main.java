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
      int n = In.readInt();       // even aged
      int m = In.readInt();       // odd aged
      int k = In.readInt();       // friendships
      int S = 2*(n+m);            // source
      int T = S+1;                // target
      int I = Integer.MAX_VALUE;  // infinity
      int N = 0;                  // need total
      Graph G = new Graph(2*(n+m+1));
      
      // for (int i = 0; i < n; i++) {
      //   // even aged people
      //   int a = In.readInt();   // even tokens need
      //   int b = In.readInt();   // odd tokens have
      //   G.addEdge(s, i, a);
      //   G.addEdge(i+2, t, b);
      // }
      // for (int j = 0; j < m; j++) {
      //   // odd aged people
      //   int c = In.readInt();   // odd tokens need
      //   int d = In.readInt();   // even tokens have
      //   G.addEdge(s, j+n, c);
      //   G.addEdge(j+n+2, t, d);
      // }
      
      for (int i = 0; i < n+m; i++) {
        int s = i*2;
        int t = s+1;
        int need = In.readInt();
        int have = In.readInt(); 
        G.addEdge(S, s, have);
        G.addEdge(t, T, need);
        N += need;
      }
      
      for (int f = 0; f < k; f++) {
        // friendship (symetric!)
        int e = In.readInt();   // even aged
        int o = In.readInt();   // odd aged
        G.addEdge(e*2, (o*2)+3, I);
        G.addEdge((o*2)+2, (e*2)+1, I);
      }
      
      boolean possible = G.computeMaximumFlow(S, T) == N;
      Out.println(possible? "yes" : "no");
    }
}
