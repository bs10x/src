# An Undirected, Unweighted Graph

During the semester, you have learned basic graph theory and many graph algorithms. This assignment considers an **undirected**, **unweighted** graph $G=(V,E)$ with adjacency lists. 
You need to implement the following tasks:


1. **Two_Induced_Path($u$, $v$, $w$)**: For three given vertices $v$, $u$ and $w$, check if they form a **2-induced path**, i.e., if **exactly** two of the three edges $(v, u)$, $(u,w)$ and $(v, w)$ belong to $E$. 
2. **Exists_Euler_Cycle()**: Check if there exists an **Euler cycle** in $G$ provided that $G$ is **connected**. (A Euler cycle is a cycle that visits every edge exactly once.)
3. **Two_Colorable()**: Check if $G$ is **2-colorable**. (A graph is *2-colorable* if its vertices can be assigned with 2 colors such that no two adjacent vertices share the same color).
4. **Max_Distance($v$)**: Given a vertex $v$, provided that $G$ is connected, compute the **maximum distance** from $v$ to another vertex in $V$ where the distance between two vertices is the minimum number of edges along a path between them.  

**Grading** (24 points):

1.  **Two_Induced_Path($u$, $v$, $w$)** (4 points): An $O(|V|)$-time implementation gets 4 points, while an $O(|V|^2)$-time implementation only gets 2 points.
2. **Exists_Euler_Cycle()** (6 points): An $O(|E|)$-time implementation gets 6 points. If border cases are treated incorrectly, you will instead get 3 points.
3.  **Two_Colorable()** (6 points): An $O(|E|)$-time implementation gets 6 points. If border cases are treated incorrectly, you will instead get 3 points.
4.  **Max_Distance($v$)** (8 points): An $O(|E|)$-time implementation gets 8 points, while an $O(|V^2|)$-time implementation only gets 4 points.


**Comments for the code template**:
- The Class Graph
    - **n** is the number of vertices in the graph.
    - **m** is the number of edges in the graph.
    - **degree[i]** is the degree of the i-th vertex in the graph.
    - **edges[i][j]** indicates the endpoint of the j-th edge of the i-th vertex in the graph.
    - **visited[i]** indicates if the i-th vertex has been visited, which is useful for a depth-first search or a breadth-first search.
- We provide template functions to show how to do a depth-first search.
    - **DFS_Initialization()** sets all the $n$ vertices as **unvisited**.
    - **DFS(v)** conducts a depth-first search from the vertex $v$.





**Attention**: 
- You are NOT allowed to use imports explicitly or implicitly other than the imports already in the code template.  
- You see the **percentage** of your submission in the CodeExpert system instead of points.
 









