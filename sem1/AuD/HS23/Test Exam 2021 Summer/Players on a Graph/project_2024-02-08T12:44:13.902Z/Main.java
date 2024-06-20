import java.lang.Math;


class Main {
  public static void main(String[] args) {
    // Uncomment the following two lines if you want to read from a file.
    In.open("public/6-distance_in_cycle_small.in");
    Out.compareTo("public/6-distance_in_cycle_small.out");

    int tests = In.readInt(); // number of tests
    for (int t = 0; t < tests; t++) {
      int n = In.readInt(); // number of nodes
      int q = In.readInt(); // number of queries
      
      int[] edgeFrom = new int[n]; // array of edges
      for (int i = 0; i < n; i++) {
        edgeFrom[i] = In.readInt(); // there is an edge from i to edgeFrom[i]
      }
      
      Graph G = new Graph(n, edgeFrom); // graph
      G.initialize();
      
      // queries
      for (int i = 0; i < q; i++) {
        int type = In.readInt();
        if (type == 1) {
          // cycleLength
          Out.println(G.cycleLength());
        } else if (type == 2) {
          // distanceToCycle
          int x = In.readInt();
          Out.println(G.distanceToCycle(x));
        } else if (type == 3) {
          // firstNodeInCycle
          int x = In.readInt();
          Out.println(G.firstNodeInCycle(x));
        } else if (type == 4) {
          // distanceInCycle
          int x = In.readInt();
          int y = In.readInt();
          Out.println(G.distanceInCycle(x, y));
        }
      }
    }

    // Uncomment this line if you want to read from a file
    In.close();
  }
}

class Graph {
  private int n;             // number of nodes
  private int[] edgeFrom;    // edgeFrom[i]: there is an edge from i to edgeFrom[i]
  private int[] inDegree;    // inDegree[i]: the in-degree of i
  private int[][] edgesTo;   // edgesTo[i][j]: there is an edge to i from edgesTo[i][j]
                             // note: edgesTo[i] is an array of length inDegree[i]
                             
  public int[] cN;
  public int cL;
  public int[] d2C;
  public boolean[] visited;
  public int[] firstCN;

  Graph(int n, int[] edgeFrom){
    this.n = n;
    this.edgeFrom = edgeFrom;
    
    // The following code computes inDegree and edgesTo.
    inDegree = new int[n];
    edgesTo = new int[n][];
    for (int i = 0; i < n; i++) {
      inDegree[edgeFrom[i]]++;
    }
    for (int i = 0; i < n; i++) {
      edgesTo[i] = new int[inDegree[i]];
      inDegree[i] = 0;
    }
    for (int i = 0; i < n; i++) {
      edgesTo[edgeFrom[i]][inDegree[edgeFrom[i]]++] = i;
    }
  }
  
  public void printArray(int[] A) {
    for (int i = 0; i < A.length; i++) System.out.print(A[i] + ", ");
    System.out.println();
  }

  public void initialize() {
    
    this.cL = cycleLength();
    this.d2C = new int[n];
    this.visited = new boolean[n];
    this.cN = new int[cL]; 
    this.firstCN = new int[n];

    int x = 0;
    while (!visited[x]) {
      visited[x] = true;
      x = edgeFrom[x];
    }
    for (int k = 0; k < cL; k++) {
      d2C[x] = 0;
      cN[k] = x;
      x = edgeFrom[x];
    }
    
    for (int cyNo : cN) {
      updateAncestorsD2C(cyNo, d2C);
      firstCN[cyNo] = cyNo;
      updateAncestors1CN(cyNo, firstCN);
    }
    
    //System.out.println(cL);
    // printArray(cN);
    // printArray(d2C);
    // System.out.println();
    
    
  }
  
  public void updateAncestorsD2C(int p, int[] d2C) {
    for (int c : edgesTo[p]) {    // iterate through all direct ancestors of p
      if (c != move(c,cL)) {      // if current is not cN
        d2C[c] = d2C[p] + 1;      // d2C of current is d2C of prev + 1
        updateAncestorsD2C(c, d2C);  // updateAncestors of current
      }
    }
  }
  
  public void updateAncestors1CN(int p, int[] firstCN) {
    for (int c : edgesTo[p]) {    // iterate through all direct ancestors of p
      if (c != move(c,cL)) {      // if current is not cN
        firstCN[c] = firstCN[p];      // firstCN of current is firstCN of prev
        updateAncestors1CN(c, firstCN);  // updateAncestors of current
      }
    }
  }
  
  public int cycleLength() {
    int cycleLength = 0;
    int slow = 0;
    int fast = 0;
    do {
      slow = edgeFrom[slow];
      fast = edgeFrom[edgeFrom[fast]];
    } while (slow != fast);
    do {
      fast = edgeFrom[edgeFrom[fast]];
      slow = edgeFrom[slow];
      cycleLength++;
    } while (slow != fast);
    return cycleLength;
  }
  
  public int move(int x, int steps) {
    while (steps > 0) {
      x = edgeFrom[x];
      steps--;
    }
    return x;
  }
  
  public int distanceToCycle(int x) {
    return d2C[x];
  }
  
  public int firstNodeInCycle(int x) {
    return firstCN[x];
  }
  
  public int distanceInCycle(int x, int y) {
    
    int fast = Math.min(distanceToCycle(x), distanceToCycle(y));
    x = move(x, fast); 
    y = move(y, fast);
    
    int slow = Math.max(distanceToCycle(x), distanceToCycle(y));
    x = move(x, slow); 
    y = move(y, slow);
    
    int distance1 = 0;
    int x1 = x, y1 = y;
    while (x1 != y1) {
      x1 = edgeFrom[x1];
      distance1++;
    }
    int x2 = x, y2 = y;
    int distance2 = 0;
    while (y2 != x2) {
      y2 = edgeFrom[y2];
      distance2++;
    }
    return Math.min(distance1, distance2);
  }
}