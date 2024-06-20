# An Undirected, Unweighted Graph

During the semester, you have learned basic graph theory and many graph algorithms. This assignment considers an **undirected**, **unweighted** graph $G=(V,E)$ with adjacency lists. 
You need to implement the following tasks:

1. **IsPath()**: Check if $G$ is a ***path*** provided that $G$ is connected, i.e., $G$ contains a path connecting all the vertices in $V$, but $G$ does not conain any other edge. 
2. **EdgeOfTriangle($u$, $v$)**: For a given edge $e=(u,\, v)$, check if $G$ contains a ***triangle*** that includes $e$, i.e., $V$ contains a vertex $w$ such that $E$ contains all the three edges $(u,w)$, $(u,v)$ and $(v, w)$.
3. **NumberOfComponents()**: Calculate the number of connected components in $G$.
4. **LargestPerimeter($v$)**: Given a vertex $v$, calculate the ***largest perimeter*** from $v$ in $G$ provided that $G$ is connected, i.e., the largest number of vertices equidistant from $v$ in $G$.

**Grading** (24 points):

1.  **IsPath()** (4 points): An $O(|V|+|E|)$-time implementation gets 4 points.
2. **EdgeOfTriangle($u$, $v$)** (6 points): An $O(|V|)$-time implementation gets 6 points, while an $O(|V|^2)$-time implementation only gets 3 points.
3.  **NumberOfComponents()** (6 points): An $O(|V|+|E|)$-time implementation gets 6 points. 
4.  **LargestPerimeter($v$)** (8 points): An $O(|E|)$-time implementation gets 8 points, while an $O(|V|\cdot|E|)$-time implementation only gets 4 points.

**Attention**: 

- You are NOT allowed to use imports explicitly or implicitly other than the imports already in the code template.  
- You see the **percentage** of your submission in the CodeExpert system instead of points.
