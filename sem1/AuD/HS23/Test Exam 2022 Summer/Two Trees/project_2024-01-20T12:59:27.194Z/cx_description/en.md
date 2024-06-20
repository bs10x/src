# Two Trees

You are given two rooted trees, $A$ and $B$, with disjoint vertex sets. Tree $A$ has vertices indexed by $a_0$, ..., $a_{n-1}$, with the root at index $a_0$, and tree $B$ has vertices indexed by $b_0$, ..., $b_{n-1}$, with the root at index $b_0$. The edges in each tree are weighted by positive integers.

You want to add some new edges that connect leaves of $A$ with leaves of $B$, thus creating a connected graph. Specifically, you can add an edge only if it goes between a leaf of $A$ and a leaf of $B$. Any edge that you add has weight $0$.

The distance between two vertices is defined as minimum total weight of a path that connects the two vertices.

Given these two trees, you have to implement the following methods. All the answers are guaranteed to fit on an "int" type.

1. **edgeCount()**: Return the total number of edges that you can add between the two trees.

2. **minDistRoots()**: Return the minimum distance between the roots of the two trees that you can achieve by adding exactly one edge.

3. **cycle()**: Return $1$ if you can add exactly two edges such that the resulting graph has a simple cycle (no repeated vertices) that contains the two roots.

4. **minDistCycle()**: Return the minimum length of a simple cycle (no repeated vertices) that contains the two roots that you can achieve by adding exactly two edges. You can assume such a simple cycle exists.

**Grading** (24 points):

1. **edgeCount()** (4 points): An $O(n)$-time implementation gets $4$ points.
2. **minDistRoots()** (8 points): An $O(n)$-time implementation gets $8$ points and an $O(n^2)$-time implementation gets $4$ points.
3. **cycle()** (4 points): An $O(n)$-time implementation gets $4$ points.
4. **minDistCycle()** (8 points): An $O(n)$-time implementation gets $8$ points and an $O(n^2)$-time implementation gets $4$ points.

**Attention**: You are **NOT** allowed to use additional imports, other than the imports already included in the code template.