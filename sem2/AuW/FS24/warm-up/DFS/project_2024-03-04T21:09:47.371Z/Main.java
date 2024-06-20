  import algorithms.*;
import java.util.*;

class Main {
    public static void main(String[] args) {
        // Uncomment this line if you want to read from a file
        In.open("public/test1.in");
        Out.compareTo("public/test1.out");
        int t = In.readInt();
        for (int i = 0; i < t; i++) {
          testCase();
        }
        // testCase();
        
        // Uncomment this line if you want to read from a file
        In.close();
    }
    
    public static void testCase() {
      int n = In.readInt();
      int m = In.readInt();
      int v = In.readInt();
      Graph G = new Graph(n);
      for (int i = 0; i < m; i++) G.addEdge(In.readInt(), In.readInt());
      for (int a = 0; a < n; a++) {
        // if (G.adjM.get(a).isEmpty()) G.adjM.remove(a);
      }
      G.source = v;
      // G.setSource();
      G.dfs();
      Out.println();
      
      // printInfo(G);
    }
    
    public static void printInfo(Graph G) {
      for (int a : G.adjM.keySet()) {
        // if (!G.adjM.get(a).isEmpty()) System.out.println(a + " --> " + G.adjM.get(a).toString());
        if (!G.hasParent[a]) System.out.println(a + " --> " + G.adjM.get(a).toString());
      }
    }

}



class Graph {
    public int n;  // number of vertices in the graph
    public int m;   // number of edges currently ion the graph
    public HashMap<Integer, ArrayList<Integer>> adjM;  // adjacency list of the graph
    public boolean[] visited;
    public boolean[] hasParent;
    public int source;
    
    public Graph(int n) {
      if (n <= 0) {
        this.n = 1;
      }
      this.n = n;
      this.adjM = new HashMap<Integer, ArrayList<Integer>>();
      for (int i = 0; i < n; i++) this.adjM.put(i, new ArrayList<>());
      this.visited = new boolean[n];
      this.hasParent = new boolean[n];
    }
    
    public void addEdge(int u, int v) {
      if (this.n == 1) {
        return;
      }
      if (u < 0 || u >= n || v < 0 || v >= n) {
        return;
      }
      if (u == v) {
        return;
      }
      hasParent[v] = true;
      adjM.get(u).add(v);
      this.m++;
    }
    public void setSource() {
      // for (int i = 0; i < n; i++) if(!exists[i]) source = i;
      for (int x : adjM.keySet()) {
        // System.out.println(x);
        // if (existsNowhere(x) && !adjM.get(x).isEmpty()) source = x;
        if (!hasParent[x]) source = x;
      }
    }
    
    public void dfs() {
      visit(source);
      for (int t : adjM.get(source)) {
        if (!visited[t]) {
          visit(t);
        }
      }
    }
    
    public void visit(int t) {
      Out.print(t + " ");
      visited[t] = true;
      for (int u : adjM.get(t)) visit(u);
    }
}
