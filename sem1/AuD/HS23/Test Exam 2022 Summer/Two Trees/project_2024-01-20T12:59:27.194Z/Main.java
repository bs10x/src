import java.util.Arrays;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
// ADDITIONAL IMPORTS ARE NOT ALLOWED

class Main {
  public static void main(String[] args) {
    // Uncomment the following two lines if you want to read from a file
    In.open("public/example.in");
    Out.compareTo("public/example.out");

    int t = In.readInt(); // number of tests
    for (int test = 0; test < t; test++) {
      int n = In.readInt(); // number of vertices
      int query = In.readInt(); // method to test
      
      // We consutrct Av, Aw, Bv, and Bw such that:
      //   Av.get(i).get(j) = the j-th child of vertex i in A
      //   Aw.get(i).get(j) = the weight of the edge from vertex i in A to its j-th child
      //   (same for Bv, Bw)
      // The number of elements of Av.get(i) is given by Av.get(i).size() (same for Aw, Bv, Bw).
      ArrayList<ArrayList<Integer>> Av = new ArrayList<ArrayList<Integer>>(n);
      ArrayList<ArrayList<Integer>> Aw = new ArrayList<ArrayList<Integer>>(n);
      ArrayList<ArrayList<Integer>> Bv = new ArrayList<ArrayList<Integer>>(n);
      ArrayList<ArrayList<Integer>> Bw = new ArrayList<ArrayList<Integer>>(n);
      for (int i = 0; i < n; i++) { // initialization with empty arrays
        Av.add(new ArrayList<Integer>());
        Aw.add(new ArrayList<Integer>());
        Bv.add(new ArrayList<Integer>());
        Bw.add(new ArrayList<Integer>());
      }
      
      int u, v, w;
      for (int i = 0; i < n - 1; i++) { // read edges of A
        u = In.readInt(); // parent is u
        v = In.readInt(); // child is v
        w = In.readInt(); // weight is w
        Av.get(u).add(v);
        Aw.get(u).add(w);
      }
      for (int i = 0; i < n - 1; i++) { // read edges of B
        u = In.readInt(); // parent is u
        v = In.readInt(); // child is v
        w = In.readInt(); // weight is w
        Bv.get(u).add(v);
        Bw.get(u).add(w);
      }
      
      if (query == 1) {
        Out.println(edgeCount(n, Av, Aw, Bv, Bw));
      } else if (query == 2) {
        Out.println(minDistRoots(n, Av, Aw, Bv, Bw));
      } else if (query == 3) {
        Out.println(cycle(n, Av, Aw, Bv, Bw));
      } else {
        Out.println(minDistCycle(n, Av, Aw, Bv, Bw));
      }
    }
    
    // Uncomment this line if you want to read from a file
    In.close();
  }
  
  // For all methods, we have Av, Aw, Bv, and Bw such that:
  //   Av.get(i).get(j) = the j-th child of vertex i in A
  //   Aw.get(i).get(j) = the weight of the edge from vertex i in A to its j-th child
  //   (same for Bv, Bw)
  // The number of elements of Av.get(i) is given by Av.get(i).size() (same for Aw, Bv, Bw).
  // The root of each tree is the vertex with index 0.
  
  public static int edgeCount(
    int n, 
    ArrayList<ArrayList<Integer>> Av, 
    ArrayList<ArrayList<Integer>> Aw, 
    ArrayList<ArrayList<Integer>> Bv, 
    ArrayList<ArrayList<Integer>> Bw
    ) {
      
    int ALeaves = 0;
    int BLeaves = 0;
    
    for (int i = 0; i < n; i++) {
      if (Av.get(i).size() == 0) ALeaves++;
      if (Bv.get(i).size() == 0) BLeaves++;
    }
    
    return ALeaves * BLeaves;
  }
  

  public static int minDistRoots(
    int n, 
    ArrayList<ArrayList<Integer>> Av, 
    ArrayList<ArrayList<Integer>> Aw,
    ArrayList<ArrayList<Integer>> Bv, 
    ArrayList<ArrayList<Integer>> Bw
    ) {
    // both roots have no children
    if (Av.get(0).size() == 0 && Bv.get(0).size() == 0) return 0;
    // both trees only one child, add their weights
    if (Av.get(0).size() == 1 && Bv.get(0).size() == 1) return Aw.get(0).get(0) + Bw.get(0).get(0);
    // run dijkstra on both trees' roots and add together
    return dijkstra(0, n, Av, Aw) + dijkstra(0, n, Bv, Bw);
  }
  
  private static int dijkstra(
    int s,
    int n,
    ArrayList<ArrayList<Integer>> Av, 
    ArrayList<ArrayList<Integer>> Aw
    ) {
    
    int[] dp = new int[n]; // dp table to store the shortest paths
    Arrays.fill(dp, Integer.MAX_VALUE);
    Queue<Integer> queue = new LinkedList<>(); // queue for BFS
    queue.add(s); // initialize queue with the root
    dp[s] = 0;
    while (!queue.isEmpty()) {
      int u = queue.poll();
      // check each child of u
      for (int i = 0; i < Av.get(u).size(); i++) {
        int v = Av.get(u).get(i);
        int w = Aw.get(u).get(i);
        // update distance if a shorter path is found
        if (dp[u] + w < dp[v]) {
          dp[v] = dp[u] + w;
          queue.add(v);
        }
      }
    }
    // find the minimum distance to a leaf
    int minDist = Integer.MAX_VALUE;
    for (int i = 0; i < n; i++) {
      if (Av.get(i).size() == 0) {
        minDist = Math.min(minDist, dp[i]);
      }
    }
    return minDist;
  }
  
  public static int cycle(
    int n,
    ArrayList<ArrayList<Integer>> Av,
    ArrayList<ArrayList<Integer>> Aw,
    ArrayList<ArrayList<Integer>> Bv,
    ArrayList<ArrayList<Integer>> Bw
    ) {
    // both trees only root --> can cycle
    if (Av.get(0).size() == 0 && Bv.get(0).size() == 0) return 1;
    // either tree only one child --> can't cycle
    if (Av.get(0).size() == 1 || Bv.get(0).size() == 1) return 0;
    // can cycle
    return 1;
  }
  
  public static int minDistCycle(
    int n,
    ArrayList<ArrayList<Integer>> Av,
    ArrayList<ArrayList<Integer>> Aw,
    ArrayList<ArrayList<Integer>> Bv,
    ArrayList<ArrayList<Integer>> Bw
    ) {
     
    /*
    Return the minimum length of a simple cycle (no repeated vertices)
    that contains the two roots that you can achieve by adding exactly two edges.
    You can assume such a simple cycle exists.
    
    O(n)
    */
    return sumShortestTwoUniquePaths(0, n, Av, Aw) + sumShortestTwoUniquePaths(0, n, Bv, Bw);
  }
  
  private static int sumShortestTwoUniquePaths(
    int s,
    int n,
    ArrayList<ArrayList<Integer>> Av,
    ArrayList<ArrayList<Integer>> Aw
    ) {
    if (Av.get(s).size() == 0) return 0;
    
    int first = Integer.MAX_VALUE;
    int second = Integer.MAX_VALUE;
    
    for (int i = 0; i < Av.get(s).size(); i++) {
      int currentPath = dijkstra(Av.get(s).get(i), n, Av, Aw) + Aw.get(s).get(i);
      
      second = Math.min(second, Math.max(first, currentPath));
      first = Math.min(first, currentPath);
      
      /*
      if (currentPath < first) first = currentPath;
      if (currentPath < second) second = currentPath;
      */
    }
    
    return first + second;
  }
  
}