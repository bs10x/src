import java.io.*;
import java.util.LinkedList;
import java.util.Scanner;
import java.lang.Math;
import java.lang.Integer;
import java.lang.String;



class Main {
    public static void main(String[] args) {
      
        // Uncomment the following two lines if you want to read from a file
        In.open("public/3-Euler_Tour.in");
        Out.compareTo("public/3-Euler_Tour.out");
        
        char operator=In.readChar();  // Type of Operations
        
        int T=In.readInt();   // number of tasks
        
        switch(operator)
        {
          
          case 'P':
            // Two-Induced Path
        
            for(int l=0;l<T;l++){
              int n=In.readInt();     // number of vertices
              int m=In.readInt();     // number of edges
              int k=In.readInt();     // number of queries  
        
            
              int[][] edge_array=new int[m][2];
              
          
             
              for(int i=0;i<m;i++){
                edge_array[i][0]=In.readInt();
                edge_array[i][1]=In.readInt();
              }
              
              
              int[][] query_array=new int[k][3];
              
              for(int i=0;i<k;i++){
                query_array[i][0]=In.readInt();
                query_array[i][1]=In.readInt();
                query_array[i][2]=In.readInt();
              }
              
              
              
              Graph G= new Graph(n, m, edge_array);
              
              for(int i=0;i<k;i++){
                Out.println(G.Two_Induced_Path(query_array[i][0],query_array[i][1],query_array[i][2]));
              }
            }
            
            
            
            break;
          
          case 'E':
            // Euler Tour
        
            for(int l=0;l<T;l++){
              int n=In.readInt();     // number of vertices
              int m=In.readInt();     // number of edges
        
              int[][] edge_array=new int[m][2];
              
          
              for(int i=0;i<m;i++){
                edge_array[i][0]=In.readInt();
                edge_array[i][1]=In.readInt();
              }
              Graph G= new Graph(n, m, edge_array);
              Out.println(G.Exists_Euler_Cycle());
            }
            
            
            break;
            

            
            
            case 'C':
            // Two-Colorable
            
        
            for(int l=0;l<T;l++){
              int n=In.readInt();     // number of vertices
              int m=In.readInt();     // number of edges
        
              
              int[][] edge_array=new int[m][2];
              
              for(int i=0;i<m;i++){
                edge_array[i][0]=In.readInt();
                edge_array[i][1]=In.readInt();
              }
              Graph G= new Graph(n, m, edge_array);
              Out.println(G.Two_Colorable());
            }
            
            
            break;
            
            
            case 'D':
            // Maximum Distance
        
            for(int l=0;l<T;l++){
              int n=In.readInt();     // number of vertices
              int m=In.readInt();     // number of edges
              int k=In.readInt();     // number of queries  
        
              int[][] edge_array=new int[m][2];
              
              for(int i=0;i<m;i++){
                edge_array[i][0]=In.readInt();
                edge_array[i][1]=In.readInt();
              }
              
              
              int[] query_array=new int[k];
              
              for(int i=0;i<k;i++){
                query_array[i]=In.readInt();
              }
              

              
              Graph G= new Graph(n, m, edge_array);
              
              for(int i=0;i<k;i++){

                Out.println(G.Max_Distance(query_array[i]));
              }
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
  private int[] degree;      // degrees[i]: the degree of vertex i
  private int[][] edges;      // edges[i][j]: the endpoint of the j-th edge of vertex i
  private boolean[] visited;  // visited[i]: if vertex i has been visited. 
  
  Graph(int n, int m, int[][] edge_array)
  {
    this.n=n;
    this.m=m;
    degree=new int[n];
    
    for(int i=0;i<n;i++){
      degree[i]=0;
    }
      
    for(int i=0;i<m;i++){
      degree[edge_array[i][0]]++;
      degree[edge_array[i][1]]++;
    }
    
    edges=new int[n][];
      
    for(int i=0;i<n;i++){
      if(degree[i]!=0){
        edges[i]=new int[degree[i]];
        degree[i]=0;
      }
      else{
        edges[i]=null;
      }
    }
    
    for(int i=0;i<m;i++){
      edges[edge_array[i][0]][degree[edge_array[i][0]]++]=edge_array[i][1];
      edges[edge_array[i][1]][degree[edge_array[i][1]]++]=edge_array[i][0];
    } 
    
    visited=new boolean[n];
  }
  
  
  
  
  
  public boolean Exists_Euler_Cycle() {
    // takes too long, need to be O(m) not O(n)
    
    DFS_Initialization();
    DFS(0);
    for (int i = 0; i < n; i++) if (visited[i] == false) return false;
    
    int oddDegs = 0;
    if (n == 2 && m == 1) return true;
    for (int i = 0; i < n; i ++) {
      if (degree[i] % 2 != 0) oddDegs++;
    }
    return (oddDegs == 0) ;//|| (oddDegs == 2);
  }
  
  
  public boolean Two_Induced_Path(int u,int v, int w) {
    boolean vu = connected(v, u);
    boolean vw = connected(v, w);
    boolean uw = connected(u, w);
    boolean notThree = !(vu && vw && uw);
    boolean two = (vu && vw) || (vu && uw) || (vw && uw) ;
    return two && notThree;
  }
  
  private boolean connected(int u, int v) {
    boolean connected = false;
    for (int i = 0; i < degree[u]; i++) {
      if (edges[u][i] == v) connected = true;
    }
    return connected;
  }
  
  
  





  public boolean Two_Colorable() {
    return true;
  }
  

  public int Max_Distance(int v)
  {
    return 0;
  }
  
  
  private void DFS_Initialization() {
    //Initialization
    for(int i=0;i<n;i++){
      visited[i]=false;
    }
  }
  
  private void DFS(int v) {
    // DFS from v
    for(int i=0;i< degree[v];i++){
        if(visited[edges[v][i]]==false){
          visited[edges[v][i]]=true;
          DFS(edges[v][i]);
        }
    }
  }
  
  private void BFS(int v) {
    
  }
  

}