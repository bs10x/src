# Tree Augmentation

You are given a tree with $n$ vertices, numbered from $0$ to $n-1$, in which vertex $0$ is the root of the tree. The tree is given as an array of $n-1$ tuples $(U[i], V[i], C[i])$, which means there is an edge between parent $U[i]$ and child $V[i]$ of cost $C[i]$, where $C[i]$ is a positive integer. You are also given another array of $n-1$ positive integers $D[1], ..., D[n - 1]$, which we will explain in a moment.

You want to augment this tree by adding **one** edge to it, from the root (vertex $0$) to one of the other vertices. If you choose to add the edge from the root to vertex $i$, the cost to traverse it is $D[i]$.

You want to understand the structure of the tree and the effect of adding an edge to it. You have to implement the following methods:

1. **leaves()**: Return the number of leaves (that is, vertices with no children) in the tree.

2. **maxChildren()**: Return the maximum number of children of a vertex in the tree.

3. **longestCycle()**: Return the length of the longest cycle that you can create by adding one edge between the root and one of the other vertices. The length of a cycle is the sum of the costs of the edges in it. (A cycle may also consist of only two vertices, if you add an edge between the root and one of its children; in that case the length is equal to the cost of the original edge plus the cost of the added edge.)

4. **minMST()**: Return the minimum cost of a minimum spanning tree that can be obtained by adding one edge between the root and one of the other vertices. (Note that, if you do not add any edge, the minimum spanning tree is the tree itself, and it has a cost equal to the sum of the costs of the edges in the tree. By adding one more edge you may make the cost of a minimum spanning tree smaller.) This question is independent of the the **longestCycle()** question (the edge that you add does not need to lead to a longest cycle).

    *Hint*: Try to understand under what conditions the cost of the minimum spanning tree decreases after adding one edge to the tree.

**Grading** (24 points):

1.  **leaves()** (4 points): An $O(n)$-time implementation gets $4$ points.
2.  **maxChildren()** (4 points): An $O(n)$-time implementation gets $4$ points.
3.  **longestCycle()** (8 points): An $O(n)$-time implementation gets $8$ points, and an $O(n^2)$-time implementation gets $4$ points.
4.  **minMST()** (8 points): An $O(n)$-time implementation gets $8$ points, and an $O(n^2 \log n)$-time implementation gets $4$ points.

**Attention**: You are **NOT** allowed to use additional imports, other than the imports already included in the code template.