import java.util.Arrays;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
// ADDITIONAL IMPORTS ARE NOT ALLOWED

class Main {
  public static void main(String[] args) {
    // Uncomment the following two lines if you want to read from a file
    In.open("public/5-min_mst_small.in");
    Out.compareTo("public/5-min_mst_small.out");

    int t = In.readInt(); // number of tests
    for (int test = 0; test < t; test++) {
      int n = In.readInt(); // number of vertices
      int query = In.readInt(); // method to test
      
      int[] U = new int[n];
      int[] V = new int[n];
      int[] C = new int[n];
      int[] D = new int[n];
      
      for (int i = 0; i < n - 1; i++) { // read edges
        U[i] = In.readInt(); // edge between parent U[i]
        V[i] = In.readInt(); // and child V[i]
        C[i] = In.readInt(); // of cost C[i]
      }
      for (int i = 1; i < n; i++) { // read D[1], ..., D[n - 1]
        D[i] = In.readInt();
      }
      
      if (query == 1) {
        Out.println(leaves(n, U, V, C, D));  
      } else if (query == 2) {
        Out.println(maxChildren(n, U, V, C, D));
      } else if (query == 3) {
        Out.println(longestCycle(n, U, V, C, D));
      } else {
        Out.println(minMST(n, U, V, C, D));
      }
    }
    
    // Uncomment this line if you want to read from a file
    In.close();
  }
  
  public static int leaves(int n, int[] U, int[] V, int[] C, int[] D) {
    int leaves = 0;
    int[] deg = new int[n];
    for (int i = 0; i < n; i++) deg[U[i]]++;
    for (int i = 0; i < n; i++) if (deg[i] == 0) leaves++;
    return leaves;
  }

  public static int maxChildren(int n, int[] U, int[] V, int[] C, int[] D) {
    int maxChildren = 0;
    int[] deg = new int[n];
    for (int i = 0; i < n; i++) deg[U[i]]++;
    for (int i = 0; i < n; i++) if (deg[i] > maxChildren) maxChildren = deg[i];
    return maxChildren;
  }
  
  public static int longestCycle(int n, int[] U, int[] V, int[] C, int[] D) {
    // For your convenience, we consutrct G and H such that:
    //   G.get(i).get(j) = the j-th child of vertex i
    //   H.get(i).get(j) = cost of the edge between vertex i and the j-th child of vertex i
    // The number of elements of G.get(i) is given by G.get(i).size().
    ArrayList<ArrayList<Integer>> G = new ArrayList<ArrayList<Integer>>(n);
    ArrayList<ArrayList<Integer>> H = new ArrayList<ArrayList<Integer>>(n);
    for (int i = 0; i < n; i++) { 
      G.add(new ArrayList<Integer>());
      H.add(new ArrayList<Integer>());
    } // initialization with empty arrays
    for (int i = 0; i < n - 1; i++) { 
      G.get(U[i]).add(V[i]);
      H.get(U[i]).add(C[i]);
    } // adding information from (U, V, C) to (G, H)
    /* 
    INTUITION:
    maximise D[i] + maxC2U to v_i
    */
    int longestCycle = 0;
    int[] c2U = maxC2U(0, n, G, H);
    for (int i = 0; i < n; i++) if (c2U[i] + D[i] > longestCycle) longestCycle = c2U[i] + D[i];
    return longestCycle;
  }
  
  public static int[] maxC2U(
    int s,
    int n,
    ArrayList<ArrayList<Integer>> G,
    ArrayList<ArrayList<Integer>> H
    ) {
      
    int[] c2U = new int[n];
    c2U[s] = 0;
    Queue<Integer> queue = new LinkedList<>(); // queue for BFS
    queue.add(s); // initialize queue with the root
    while (!queue.isEmpty()) {
      int u = queue.poll();
      for (int i = 0; i < G.get(u).size(); i++) { // check each child of u
        int v = G.get(u).get(i);
        int c = H.get(u).get(i);
        if (c2U[u] + c > c2U[v]) { // update distance if a longer path is found
          c2U[v] = c2U[u] + c;
          queue.add(v);
        }
      }
    }
    return c2U;
  }
  
  public static int minMST(int n, int[] U, int[] V, int[] C, int[] D) {
    // For your convenience, we consutrct G and H such that:
    //   G.get(i).get(j) = the j-th child of vertex i
    //   H.get(i).get(j) = cost of the edge between vertex i and the j-th child of vertex i
    // The number of elements of G.get(i) is given by G.get(i).size().
    ArrayList<ArrayList<Integer>> G = new ArrayList<ArrayList<Integer>>(n);
    ArrayList<ArrayList<Integer>> H = new ArrayList<ArrayList<Integer>>(n);
    for (int i = 0; i < n; i++) {
      G.add(new ArrayList<Integer>());
      H.add(new ArrayList<Integer>());
    } // initialization with empty arrays
    for (int i = 0; i < n - 1; i++) { 
      G.get(U[i]).add(V[i]);
      H.get(U[i]).add(C[i]);
    } // adding information from (U, V, C) to (G, H)
    /*
    INTUITION:
    connect to vj with max cost edge from vi to vj s.t. 
    minimize H.get(i).get(j) - D[j]
    */
    int minMST = 0;
    int stonks = Integer.MAX_VALUE;
    int[] mC = totalMC(0, n, G, H);
    for (int i = 0; i < n; i++) if (D[i] - mC[i] < stonks) stonks = D[i] - mC[i];
    for (int i = 0; i < n; i++) minMST += mC[i];
    return minMST + stonks;
  }
  
  public static int[] totalMC(
    int s,
    int n,
    ArrayList<ArrayList<Integer>> G,
    ArrayList<ArrayList<Integer>> H
    ) {
    
    int[] mC = new int[n];
    Queue<Integer> queue = new LinkedList<>(); // queue for BFS
    queue.add(s); // initialize queue with the root
    while (!queue.isEmpty()) {
      int u = queue.poll();
      for (int i = 0; i < G.get(u).size(); i++) { // check each child of u
        int v = G.get(u).get(i);
        int c = H.get(u).get(i);
        /*
        if (c2U[u] + c > c2U[v]) { // update distance if a longer path is found
          c2U[v] = c2U[u] + c;
          queue.add(v);
        }*/
        mC[v] = c;
        queue.add(v);
      }
    }
    return mC;
  }
}

  