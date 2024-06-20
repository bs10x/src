# Players on a Graph

You are given a directed graph with $n$ nodes and $n$ edges, such that the out-degree of each node is $1$. You are guaranteed that, viewing the edges as *undirected*, the graph is connected. Such a graph always contains exactly one cycle. Here is an example for $n=11$, in which the nodes $4$, $7$, $8$, and $10$ form the cycle:

![cycle_graph.png](/cx_description/cycle_graph.png)

In the beginning, each node of the graph contains a player. Then a timer starts, and at each time step, each players moves to the node indicated by the edge of its current node. The timer continues forever. Note that multiple players may arrive at the same time at the same node -- that is allowed.

Given such a graph, you have to answer queries of the following type:

1.  **cycleLength()**: Return the length of the cycle of the graph.

2.  **distanceToCycle($x$)**: For the player that starts at node $x$, return the number of edges that it has to traverse to reach the cycle. (If $x$ is already in the cycle, the answer is $0$.)

3.  **firstNodeInCycle($x$)**: After some time steps, the player that starts at node $x$ reaches the cycle. Return the first node in the cycle that this player reaches. (If $x$ is already in the cycle, the answer is $x$.)

4.  **distanceInCycle($x$, $y$)**: After some time steps, both the player that starts at node $x$ and the player that starts at node $y$ have reached the cycle. Return the distance between them on the cycle (this distance is calculated viewing the edges of the cycle as *undirected*; that is, the minimum of the clockwise distance and counterclockwise distance on the cycle).

    (Note that the two players may reach the cycle at different time steps.)

You have to implement the four methods described above. In addition, we provide an **intialize()** method which we guarantee to run exactly once, before the queries. Feel free to use this method, for example, to initialize information that you want available in all of the queries.

**Grading** (24 points):

1.  **cycleLength()** (4 points): This query will be run at most once. An $O(n)$-time implementation gets $4$ points.
2.  **distanceToCycle($x$)** (6 points): If $q$ is the number of queries of this type, an $O(n+q)$-time implementation gets 6 points and an $O(nq)$-time implementation gets 3 points.
3.  **firstNodeInCycle($x$)** (6 points): If $q$ is the number of queries of this type, an $O(n+q)$-time implementation gets 6 points and an $O(nq)$-time implementation gets 3 points.
4.  **distanceInCycle($x$, $y$)** (8 points): If $q$ is the number of queries of this type, an $O(n+q)$-time implementation gets 8 points and an $O(nq)$-time implementation gets 4 points.

**Attention**:

*   You are **NOT** allowed to use imports explicitly or implicitly, other than the imports already included in the code template.
*   When you submit the code, you see your score as a **percentage**, not as a number of points.
