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
    
    
    /*
    destroy multiple (even all) connections in shade, rather than one in the sun
    destroy as few connections in the shade as possible
    
    max-flow min-cut, want to minimize the cut edges
    */
    
    public static void testCase() {
      int n = In.readInt();   // number of connections ...
      int s = In.readInt();   // ... in the sun
      int d = In.readInt();   // ... in the shade
      Graph G = new Graph(n);
      
      // connections in the sun
      for (int i = 0; i < s; i++) {
        int u = In.readInt();
        int v = In.readInt();
        G.addEdge(u, v, 501);
        G.addEdge(v, u, 501);
      }
      
      // connections in the shade
      for (int i = 0; i < d; i++) {
        int u = In.readInt();
        int v = In.readInt();
        G.addEdge(u, v, 1);
        G.addEdge(v, u, 1);
      }
      
      int maxFlow = G.computeMaximumFlow(0, n-1);
      int minSunny = maxFlow / 501;
      int minShade = maxFlow % 501;
      Out.println(minSunny + " " + minShade);
    }
}
