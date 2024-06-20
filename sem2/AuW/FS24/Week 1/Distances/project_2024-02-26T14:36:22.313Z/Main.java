import algorithms.*;
import java.util.*;

class Main {
    public static void main(String[] args) {
        // Uncomment this line if you want to read from a file
        In.open("public/sample.in");
        Out.compareTo("public/sample.out");
        
        int t = In.readInt();
        for (int i = 0; i < t; i++) {
          testCase();
        }
        
        // Uncomment this line if you want to read from a file
        In.close();
    }

    public static void testCase() {
      // Input using In.java class
      int n = In.readInt();   // number of vertices
      int m = In.readInt();   // number of edges
      int v = In.readInt();   // starting v
      
      Graph G = new Graph(n);
      for (int i = 0; i < m; i++) G.addEdge(In.readInt(), In.readInt());
      printArray(BFS(G, v));
    }
    
    public static int[] BFS(Graph G, int s) {
      Queue<Integer> Q = new LinkedList<>();
      int[] d2s = new int[G.n];           // distanc to source
      Arrays.fill(d2s, -1);               // default distance: -1
      boolean[] ex = new boolean[G.n];    // explored vertices
      
      Q.add(s);
      d2s[s] = 0;     // distance to source from source is zero
      ex[s] = true;   // explored s
      while (!Q.isEmpty()) {
        int u = Q.poll();
        for (int v : G.adjL.get(u))
          if (!ex[v]) {
            ex[v] = true;
            d2s[v] = d2s[u] + 1;
            Q.add(v);
          }
      }
      return d2s;
    }
    
    public static void printArray(int[] d2s) {
      for (int d : d2s) Out.print(d + " ");
      Out.println();
    }
}

class Graph {
    public int n;  // number of vertices in the graph
    public int m;   // number of edges currently ion the graph
    public ArrayList<ArrayList<Integer>> adjL;  // adjacency list of the graph
    
    public Graph(int n) {
      if (n <= 0) this.n = 1;
      this.n = n;
      this.adjL = new ArrayList<ArrayList<Integer>>();
      for (int i = 0; i < n; i++) this.adjL.add(new ArrayList<Integer>());
    }
    
    public void addEdge(int u, int v) {
      if (this.n == 1) return;
      if (u < 0 || u >= n || v < 0 || v >= n) return;
      if (u == v) return;
      adjL.get(u).add(v);
      adjL.get(v).add(u);
      this.m++;
    }
}

