package algorithms;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Arrays;

/**
* This class represents a graph with implementation of an algorithm
* that finds the maximum flow.
* You can create a new instance of this class with the following command:
* 
* Graph g = new Graph(numVertices);
* 
* This will create a new graph `g` with `numVertices` vertices. 
* You can then access any public member of this instance in the usual way:
* 
* g.addEdge(0, 1, 5); 
* 
* This will add an edge from vertex 0 to vertex 1 with capacity 5.
* 
* int maxFlow = g.computeMaximumFlow(0, 1);
* 
* This will compute the maximum flow from vertex 0 (source)
* to vertex 1 (sink). You may call this function multiple times, possibly 
* with different (source, sink) pairs. You may also call addEdge to change 
* the graph in between these calls. Note that the current graph at any point
* in time is determined by all calls to addEdge that were ever made - that
* is, calling computeMaximumFlow does NOT mean that later calls to addEdge
* start with an empty graph.
* 
* int flowFromuTov = g.getFlow(u, v);
* 
* After computing the maximum flow, you may want to inspect the flows on the
* edges. You can do this with the function getFlow. The above command 
* stores in `flowFromuTov` the flow on the edge from vertex `u`
* to vertex `v`.
* 
* int capacityFromuTov = g.getCapacity(u, v);
* 
* Likewise, the above command stores in `capacityFromuTov` the capacity of 
* the edge from vertex `u` to vertex `v`.
* 
* int numVertices = g.getNumVertices();
* 
* Finally, the above command stores in `numVertices` the number of vertices
* in the graph.
* 
* Memory usage: an instance of the class takes O(n^2) memory,
* where `n` is the number of vertices in the graph.
*/
public class Graph {
    private int numVer;  // number of vertices in the graph
    private ArrayList<ArrayList<Integer>> graph;  // adjacency list of the graph
    private int[][] capacity;  // capacities of the edges
    private boolean[][] edge_exists; // flags for whether edges have been added
    private int[][] flow;  // flows currently passing through the edges
    private boolean flow_computed = false; // a flag indicating whether the flow is computed
    
    
    /**
     * Creates a new instance of a graph with `n` vertices.
     * If the provided `n` is not positive, creates a graph with 1 vertex.
     * 
     * @param   n   number of vertices in graph, should be positive
     * 
     * Runtime: O(n^2)  
     * Memory usage: O(n^2)
     */
    public Graph(int n) {
        if (n <= 0) {
            printError("The number of vertices in the graph should be"
            + " positive (n=" + n + "):");
            n = 1;
        }
        numVer = n;
        capacity = new int[numVer][numVer];
        edge_exists = new boolean[numVer][numVer];
        flow = new int[numVer][numVer];
        
        graph = new ArrayList<ArrayList<Integer>>();
        for (int i = 0; i < numVer; i++)
            graph.add(new ArrayList<Integer>());
    }
    
    /**
     * Gets the number of vertices in the graph.
     */
    public int getNumVertices() {
        return numVer;
    }
    
    /**
     * Gets the capacity of the edge from u to v. 
     * If no edge from u to v exists, returns 0.
     */
    public int getCapacity(int u, int v) {
        if (u < 0 || u >= numVer || v < 0 || v >= numVer) {
            printError("Invalid vertex indices (u=" + u + ", v=" + v + "):");
            return -1;
        }
        return capacity[u][v];
    }
    
    /**
     * Gets the flow on the edge from u to v. 
     * If no edge from u to v exists, returns 0.
     */
    public int getFlow(int u, int v) {
        if (u < 0 || u >= numVer || v < 0 || v >= numVer) {
            printError("Invalid vertex indices (u=" + u + ", v=" + v + "):");
            return -1;
        }
        return flow[u][v];
    }

    /**
     * Adds an edge from `u` to `v` with capacity `c` to the graph.
     * If an edge from `u` to `v` already exists, it adds `c` to its capacity.
     * If the value of `c` is 0, no edge is added and the function
     * call has no effect.
     * If the value of `c` is negative, the function has no effect and an
     * error message is printed.
     * 
     * @param   u   tail of the edge to be added, should be between 0
     *              and the number of vertices minus 1
     * @param   v   head of the edge to be added, should be between 0
     *              and the number of vertices minus 1
     * @param   c   capacity of the edge to be added, should be non-negative
     * 
     * Runtime: O(1)  
     */
    public void addEdge(int u, int v, int c) {
        if (c == 0) {
            return;
        }
        if (c < 0) {
            printError("Only positive capacities allowed (c=" + c + "):");
            return;
        }
        if (u < 0 || u >= numVer || v < 0 || v >= numVer) {
            printError("Invalid vertex indices (u=" + u + ", v=" + v + "):");
            return;
        }
        if (u == v) {
            printError("No self-loops allowed (u=v=" + u + "):");
            return;
        }
        
        if (edge_exists[u][v] == false) {
            edge_exists[u][v] = true;
            edge_exists[v][u] = true;
            graph.get(u).add(v);
            graph.get(v).add(u);
        }
        capacity[u][v] += c;
    }

    /**
     * Computes the value of a maximum flow from `source` to `sink` in
     * the graph, using the Ford-Fulkerson algorithm.
     * 
     * Runtime: O(e * f), where `e` is the number of edges in the graph,
     * and `f` is the value of the maximum flow.
     * 
     * @param   source   source of flow, should be between 0
     *                   and the number of vertices minus 1
     * @param   sink     sink of flow, should be between 0
     *                   and the number of vertices minus 1
     * 
     * @return  the value of the maximum flow from `source` to `sink`, or
     *          -1 in case of invalid call of the function
     */
    public int computeMaximumFlow(int source, int sink) {
        if (source < 0 || source >= numVer || sink < 0 || sink >= numVer) {
            printError("Invalid vertex indices (source=" + source
            + ", sink=" + sink + "):");
            return -1;
        }
        if (source == sink) {
            printError("Source and sink should be different vertices (source="
            + source + ", sink=" + sink + "):");
            return -1;
        }
        if (flow_computed) {
            resetFlows();
        }
        flow_computed = true;
        int i, flowOnPath;
        // Find paths with BFS and return path in `previousVertexOnPath` array
        int[] previousVertexOnPath = new int[numVer];
        // Start with empty flow
        int maxFlow = 0;
        // Use augmenting path `P` as long as possible
        while (augmentingPathExists(previousVertexOnPath, source, sink)) {
            // Compute smallest remaining capacity on `P`
            flowOnPath = Integer.MAX_VALUE;
            for (i = sink; i != source; i = previousVertexOnPath[i]) {
                int p = previousVertexOnPath[i];
                flowOnPath = Math.min(flowOnPath, capacity[p][i] - flow[p][i]);
            }
            // Add the smallest remaining capacity to each edge of `P`
            for (i = sink; i != source; i = previousVertexOnPath[i]) {
                int p = previousVertexOnPath[i];
                flow[p][i] += flowOnPath;
                flow[i][p] -= flowOnPath;
            }
            maxFlow += flowOnPath; 
        }

        return maxFlow;
    }

    /** 
     * Helper function that computes a path with positive residual
     * capacity using BFS.
     * 
     * @return  true if such a path exists and false otherwise.
     */
    private boolean augmentingPathExists(int previousVertexOnPath[],
                                         int source, int sink) {
        // Allocate space for auxiliary data structures
        LinkedList<Integer> queue = new LinkedList<Integer>();
        boolean[] visited = new boolean[numVer];
        
        // Initialization of auxiliary data structures
        for (int i = 0; i < numVer; i++) visited[i] = false;
        queue.add(source);
        visited[source] = true;
        
        // BFS
        while (!queue.isEmpty()) {
            int v = queue.poll();
            for (int w : graph.get(v))
                if (!visited[w] && capacity[v][w] > flow[v][w]) {
                    visited[w] = true;
                    previousVertexOnPath[w] = v;
                    queue.add(w);
                    if (w == sink) return true;
                }
        }
        
        return false;
    }
    
    /**
     * Helper function that sets the flows on all edges to 0.
     */
    private void resetFlows() {
        for (int[] flowRow: flow) {
            Arrays.fill(flowRow, 0);
        }
    }
    
    /**
     * Helper to print stack trace to console for better debugging.
     */
    private void printError(String message) {
        System.err.println("-------------------------");
        System.err.println(message);
        for (StackTraceElement ste : Thread.currentThread().getStackTrace()) {
            if (ste.getMethodName() == "printError" ||
                ste.getMethodName() == "getStackTrace" ||
                ste.getClassName() == "TestRunner")
              continue;
            System.err.println("   " + ste.toString());
        }
        System.err.println("-------------------------");
    }
}