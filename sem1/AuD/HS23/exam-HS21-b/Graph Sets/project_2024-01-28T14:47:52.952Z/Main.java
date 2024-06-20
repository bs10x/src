import java.util.Arrays;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;


class Main {
  public static void main(String[] args) {
    // Uncomment the following two lines if you want to read from a file.
    // Also make sure that the lines are commented when testing with the 
    // "Test" button or when submitting. Otherwise your code may exceed the
    // time liimt.
    In.open("public/5-shortest_path_small.in");
    Out.compareTo("public/5-shortest_path_small.out");

    int tests = In.readInt(); // number of tests
    for (int t = 0; t < tests; t++) {
      int n = In.readInt(); // number of nodes
      int m = In.readInt(); // number of edges
      int q = In.readInt(); // number of queries
      
      int[][] edgeArray = new int[m][2]; // array of edges
      for (int i = 0; i < m; i++) {
        edgeArray[i][0] = In.readInt();
        edgeArray[i][1] = In.readInt();
      }
      
      Graph G = new Graph(n, m, edgeArray); // graph
      G.initialize();
      
      // queries
      for (int i = 0; i < q; i++) {
        int type = In.readInt();
        if (type == 1) {
          // hasCycle
          Out.println(G.hasCycle());
        } else if (type == 2) {
          // hasCycleWithoutNodeZero
          Out.println(G.hasCycleWithoutNodeZero());
        } else if (type == 3) {
          // isSameSet
          int x = In.readInt();
          int y = In.readInt();
          Out.println(G.isSameSet(x, y));
        } else if (type == 4) {
          // getShortestPath
          int x = In.readInt();
          int y = In.readInt();
          Out.println(G.getShortestPath(x, y));
        }
      }
    }
    
    // Uncomment this line if you want to read from a file
    In.close();
  }
}

class Graph {
  private int n;                // number of nodes
  private int m;                // number of edges
  private int[] degree;         // degrees[i]: the degree of vertex i
  private int[][] edges;        // edges[i][j]: the endpoint of the j-th edge of vertex i
  private boolean[] visited;    // visited[i]: whether node i has been visited
  public int[] distance;
  public int[] set;
  public int sets;
  
  Graph(int n, int m, int[][] edgeArray){
    this.n = n;
    this.m = m;
    degree = new int[n];
    edges = new int[n][];
    visited = new boolean[n];
    
    for (int i = 0; i < m; i++) {
      degree[edgeArray[i][0]]++;
      degree[edgeArray[i][1]]++;
    }
    for (int i = 0; i < n; i++) {
      edges[i] = new int[degree[i]];
      degree[i] = 0;
    }
    for (int i = 0; i < m; i++) {
      edges[edgeArray[i][0]][degree[edgeArray[i][0]]++] = edgeArray[i][1];
      edges[edgeArray[i][1]][degree[edgeArray[i][1]]++] = edgeArray[i][0];
    } 
  }
  
  public void initialize() {
    
    this.set = new int[n];
    for (int i = 0; i < n; i++) set[i] = -1;
    set[0] = 0;
    
    this.sets = 1;
    
    visited[0] = true;
    for (int i = 0; i < degree[0]; i++) {
      if (!visited[edges[0][i]]) {
        set[edges[0][i]] = sets++;
        DFS(edges[0][i]);
      }
    }
    
    this.distance = new int[n];
    for (int i = 0; i < n; i++) distance[i] = -1;
    Queue<Integer> Q = new LinkedList<Integer>();
    Q.add(0);
    distance[0] = 0;
    visited[0] = true;
    while (!Q.isEmpty()) {
      int x = Q.poll();
      for (int i = 0; i < degree[x]; i++) {
        if (distance[edges[x][i]] == -1) {
          distance[edges[x][i]] = distance[x] + 1;
          Q.add(edges[x][i]);
        }
      }
    }
  }
  
  
  public int hasCycle() {
    if (m >=n ) return 1;
    else return 0;
  }
  
  public int hasCycleWithoutNodeZero() {
    if (m - degree[0] >= n - sets + 1) return 1;
    else return 0;
  }
  
  public int isSameSet(int x, int y) {
    if (set[x] == set[y]) return 1;
    else return 0;
  }
  
  public int getShortestPath(int x, int y) {
    return distance[x] + distance[y];
    // return dijkstra(x, y);
  }
  
  private void DFS(int x) {
    visited[x] = true;
    for (int i = 0; i < degree[x]; i++) {
      if (!visited[edges[x][i]]) {
        set[edges[x][i]] = set[x];
        DFS(edges[x][i]);
      }
    }
  }
}