# Minimum Cost Edges

You are given an undirected connected graph with $n$ vertices and $m$ weighted edges, where each weight is a positive integer. You are also given two distinct vertices $s$ and $t$. We call an edge useless if there does not exist any path of minimum cost between $s$ and $t$ that uses this edge. Your task is to return the number of useless edges in the graph.

For example, for the graph in the picture, for $s=1$ and $t=5$, the minimum cost is $$5$$, and there are $$3$$ useless edges: $\{0, 1\}$, $\{0, 5\}$, and $\{1, 3\}$.

![graph.png](/cx_description/graph.png)

You need to implement your solution as a method **countEdges** in the file Main.java. You get one point for each passing test set. To pass both test sets correctly, your solution needs to run in $O(m \log m)$ time.

**Note**: The large.in files for this exercise are very large and it may not be possible to run them with the "run" button (that is, with In.open("public/test.large")). Please test these instead with the "test" (laboratory flask) button, which tests both public tests.

**Attention**: You are **NOT** allowed to use additional imports, other than the imports already included in the code template.