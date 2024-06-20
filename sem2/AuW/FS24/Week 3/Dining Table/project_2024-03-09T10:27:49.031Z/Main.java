import algorithms.*;
import java.util.*;

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
      int n = In.readInt();   // # ppl
      int m = In.readInt();   // # adjL
      int r = In.readInt();   // bestie
      Graph G = Graph.make(n, m, r);
      // G.printInfo();
      if (G.connected && G.bipartite) G.printSet(r);
      else Out.println("no");
    }
}

class Graph {
  public int n;
  public int m;
  public int r;
  public boolean connected;
  public boolean bipartite;
  public ArrayList<Integer> visited;
  public ArrayList<ArrayList<Integer>> adjL;
  public ArrayList<Character> which;
  public ArrayList<Integer> A;
  public ArrayList<Integer> B;
  
  public Graph(int n, int m, int r) {
    this.n = n;
    this.m = m;
    this.r = r;
    this.visited = new ArrayList<>();
    this.adjL = new ArrayList<>();
    this.which = new ArrayList<>();
    this.A = new ArrayList<>();
    this.B = new ArrayList<>();
    for (int i = 0; i < n; i++) adjL.add(new ArrayList<>());
    for (int i = 0; i < n; i++) which.add('X');
  }
  
  public static Graph make(int n, int m, int r) {
    Graph G = new Graph(n, m, r);
    
    for (int i = 0; i < m; i++) {
      int a = In.readInt();
      int b = In.readInt();
      G.adjL.get(a).add(b);
      G.adjL.get(b).add(a);
    }
    G.connected = G.connected();
    G.bipartite = G.bipartite();
    return G;
  }
  public void printInfo() {
    System.out.println();
    System.out.println("n: " + n);
    System.out.println("m: " + m);
    System.out.println("r: " + r);
    System.out.println("connected: " + connected);
    System.out.println("bipartite: " + bipartite);
    for (int i = 0; i < n; i++) {
      ArrayList<Integer> aL = adjL.get(i);
      System.out.print(i + " --> [ ");
      for (int x : aL) System.out.print(x + ", ");
      System.out.println("]");
    }
    System.out.print("A:\t");
    printAL(A);
    System.out.print("B:\t");
    printAL(B);
  }
  
  public boolean connected() {
    dfs(r);
    for (int i = 0; i < n; i++) if (!visited.contains(i)) return false;
    return true;
  }
  public void dfs(int s) {
    visited.clear();
    visit(s);
  }
  public void visit(int u) {
    visited.add(u);
    for (int v : adjL.get(u)) {
      if (!visited.contains(v)) visit(v);
    }
  }
  
  public boolean bipartite() {
    if (!connected) return false;
    return bfs(r);
  }
  public boolean bfs(int s) {
    visited.clear();
    Queue<Integer> Q = new LinkedList<>();
    
    Q.offer(s);
    visited.add(s);
    A.add(s);
    which.set(s, 'A');
    
    while (!Q.isEmpty()) {
      int u = Q.poll();
      
      for (int v : adjL.get(u)) {
        if (which.get(v) == which.get(u)) return false;
        if (!visited.contains(v)) {
          if (which.get(u) == 'A') {
            B.add(v);
            which.set(v, 'B');
          }
          if (which.get(u) == 'B') {
            A.add(v);
            which.set(v, 'A');
          }
          visited.add(v);
          Q.offer(v);
        }
      }
    }
    Collections.sort(A);
    Collections.sort(B);
    return true;
  }
  public void printSet(int x) {
    if (A.contains(x)) for (int a : A) Out.print(a + " ");
    if (B.contains(x)) for (int b : B) Out.print(b + " ");
    Out.println();
  }
  
  public void printAL(ArrayList<Integer> aL) {
    for (int x : aL) System.out.print(x + " ");
    System.out.println();
  }
}