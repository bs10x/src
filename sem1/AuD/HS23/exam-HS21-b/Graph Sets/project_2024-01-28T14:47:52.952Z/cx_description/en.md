# Graph Sets

You are given an undirected connected graph with $n$ nodes numbered from $0$ to $n-1$ and $m$ edges between them.

The nodes of the graph are partitioned into sets in the following way. Node $0$ forms a set of its own. Suppose that node $0$ is removed from the graph. Then, the nodes in each connected subgraph of the resulting graph forms another set.

An example is shown below, in which the nodes are colored according to the set they are in. Specifically, the sets in this example are $\{0\}$, $\{1\}$, $\{2, 3, 5, 6\}$, and $\{4, 7\}$.

![graph_sets.png](/cx_description/graph_sets.png)

Given such a graph, you have to answer queries of the following type:

1. **hasCycle()**: Return $1$ if the graph has a cycle, and $0$ otherwise.

2. **hasCycleWithoutNodeZero()**: Suppose that node $0$ is removed from the graph. Return $1$ if the resulting graph has a cycle, and $0$ otherwise.

3. **isSameSet($x$, $y$)**: Given two nodes $x$ and $y$, return $1$ if they are in the same set, and $0$ otherwise.

4. **getShortestPath($x$, $y$)**: Given two nodes $x$ and $y$ **that are in different sets**, return the shortest-path distance between them.

You have to implement the four methods described above. In addition, we provide an **intialize()** method which we guarantee to run before any of the queries. Feel free to use this method, for example, to initialize information that you want available in all of the queries (of course, the time consumed by **intialize()** counts toward the time limit of the problem).

For an easier implementation of the queries, **recall and make use of the fact** that the graph is connected.

**Grading** (24 points):

1. **hasCycle()** (4 points): This query will be run at most once. An $O(n+m)$-time implementation gets $4$ points.
2. **hasCycleWithoutNodeZero()** (4 points): This query will be run at most once. An $O(n+m)$-time implementation gets $4$ points.
3. **isSameSet($x$, $y$)** (8 points): If $q$ is the number of queries of this type, an $O(n+m+q)$-time implementation gets 8 points and an $O(q(n+m))$-time implementation gets 4 points.
4. **getShortestPath($x$, $y$)** (8 points): If $q$ is the number of queries of this type, an $O(n+m+q)$-time implementation gets 8 points and an $O(q(n+m))$-time implementation gets 4 points.

**Attention**: 

- You are NOT allowed to use imports explicitly or implicitly other than the imports already in the code template.  
- You see the **percentage** of your submission in the CodeExpert system instead of points.