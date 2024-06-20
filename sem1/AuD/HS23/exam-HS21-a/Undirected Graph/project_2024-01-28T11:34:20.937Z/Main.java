import java.io.*;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.Queue;
import java.lang.Math;
import java.lang.Integer;
import java.lang.String;


class Main {
  public static void main(String[] args) {
    // Uncomment the following two lines if you want to read from a file.
    // Also make sure that the lines are commented when testing with the 
    // "Test" button or when submitting. Otherwise your code may exceed the
    // time liimt.
    In.open("public/example-Largest-Perimeter.in");
    Out.compareTo("public/example-Largest-Perimeter.out");
        
    char operator = In.readChar();  // type of operation
    int N;                          // number of graphs 
    int n;                          // number of vertices
    int m;                          // number of edges
    int q;                          // number of querie
    int[][] edge_array;             // array of edges
    Graph G;                        // Graph
        
    switch (operator) {
      case 'P':
        // IsPath
        N = In.readInt();           // number of graphs 
        for (int l = 0; l < N; l++) {
          n = In.readInt();         // number of vertices
          m = In.readInt();         // number of edges
        
          edge_array = new int[m][2];
          for (int i = 0; i < m; i++) {
            edge_array[i][0] = In.readInt();
            edge_array[i][1] = In.readInt();
          }
          
          G = new Graph(n, m, edge_array);
          Out.println(G.IsPath());
        }
        break;
      case 'T':
        // EdgeOfTriangle
        n = In.readInt();           // number of vertices
        m = In.readInt();           // number of edges
        
        edge_array = new int[m][2];
        for(int i = 0; i < m; i++) {
          edge_array[i][0] = In.readInt();
          edge_array[i][1] = In.readInt();
        }
        
        G = new Graph(n, m, edge_array);
        q = In.readInt();           // number of querie
        for (int i = 0; i < q; i++) {
          int u = In.readInt();
          int v = In.readInt();
          Out.println(G.EdgeOfTriangle(u, v));
        }
        break;
      case 'C':
        // NumberOfComponents
        N = In.readInt();           // number of graphs 
        for(int l = 0; l < N; l++) {
          n = In.readInt();         // number of vertices
          m = In.readInt();         // number of edges
          
          edge_array = new int[m][2];
          for (int i = 0; i < m; i++) {
            edge_array[i][0] = In.readInt();
            edge_array[i][1] = In.readInt();
          }
          
          G = new Graph(n, m, edge_array);
          Out.println(G.NumberOfComponents());
        }
        break;
      case 'L':
        // LargestPerimeter
        n = In.readInt();           // number of vertices
        m = In.readInt();           // number of edges
        
        edge_array = new int[m][2];
        for (int i = 0; i < m; i++) {
          edge_array[i][0] = In.readInt();
          edge_array[i][1] = In.readInt();
        }
        
        G = new Graph(n, m, edge_array);
        q = In.readInt();           // number of querie
        for(int i = 0; i < q; i++) {
          int v = In.readInt();
          Out.println(G.LargestPerimeter(v));
        }
        break;
    }
    
    // Uncomment the following line if you want to read from a file
    In.close();
  }
}

class Graph{
  private int n;              // number of vertices
  private int m;              // number of edges
  private int[] degrees;      // degrees[i]: the degree of vertex i
  private int[][] edges;      // edges[i][j]: the endpoint of the j-th edge of vertex i
  private boolean[] visited;  // visited[i]: whether vertex i has been visited
  
  Graph(int n, int m, int[][] edge_array) {
    this.n = n;
    this.m = m;
    degrees = new int[n];
    edges = new int[n][];
    visited = new boolean[n];
    
    for (int i = 0; i < n; i++) {
      degrees[i] = 0;
    }
    for (int i = 0; i < m; i++) {
      degrees[edge_array[i][0]]++;
      degrees[edge_array[i][1]]++;
    }
    for(int i = 0; i < n; i++) {
      if (degrees[i] != 0) {
        edges[i] = new int[degrees[i]];
        degrees[i] = 0;
      } else {
        edges[i] = null;
      }
    }
    for (int i = 0; i < m; i++) {
      edges[edge_array[i][0]][degrees[edge_array[i][0]]++] = edge_array[i][1];
      edges[edge_array[i][1]][degrees[edge_array[i][1]]++] = edge_array[i][0];
    } 
  }


  public boolean IsPath() {
    boolean isPath = false;
    int oddDegVs = 0;
    for (int i = 0; i < n; i++) {
      if (degrees[i] % 2 != 0) oddDegVs++;
    }
    if (oddDegVs == 0 || oddDegVs == 2) isPath = true;
    return isPath;
  }

  public boolean EdgeOfTriangle(int u, int v) {
    boolean hasTriangle = false;
    for (int i = 0; i < degrees[u]; i++) {
      for (int j = 0; j < degrees[v]; j++) {
        if (edges[u][i] == edges[v][j]) hasTriangle = true;
      }
    }
    return hasTriangle;
  }
  
  public int NumberOfComponents() {
    int connectedComponents = 0;
    for (int i = 0; i < n; i++) {
      if (!visited[i]) {
        DFS(i);
        connectedComponents++;
      }
    }
    return connectedComponents;
  }

  public int LargestPerimeter(int u) {
    Queue<Integer> q = new LinkedList<Integer>();
    boolean[] visited = new boolean[n];
    int[] distance = new int[n];
    int[] equidistants = new int[n];
    int maxPerimeter = 0;
    
    q.add(u);
    visited[u] = true;
    distance[u] = 0;
    equidistants[0] = 1;
    
    while (!q.isEmpty()) {
      int v = q.poll();
      
      for (int w : edges[v]) {
        if (!visited[w]) {
          visited[w] = true;
          distance[w] = distance[v] + 1;
          q.add(w);
          equidistants[distance[w]]++;
          maxPerimeter = Math.max(maxPerimeter, equidistants[distance[w]]);
        }
      }
    }
    return maxPerimeter;
  }
  
  private void DFS(int v) {
    visited[v]=true;
    for (int i = 0; i < degrees[v]; i++) {
      if(visited[edges[v][i]] == false) {
        DFS(edges[v][i]);
      }
    }
  }
  
  
}