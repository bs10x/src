# Shortest Cycle

Consider an undirected, weighted, connected graph $G=(V,E, w)$ where all edges in $E$ are of positive weights (i.e., for any edge $e\in E$, $w(e)> 0$) and consider one vertex $v\in V$. The task is to compute the length of the shortest cycle in $G$ that contains $v$. 



At the end of the file Main.java, you will find a class Graph. Your task is to implement the method shortestCycle. For simplicity, we assuume $v$ to be the first vertex, i.e., $v$ is vertex 0. Besides, you can safely assume that $G$ has at least one cycle that contains $v$. Note that, since all edge weights are positive, each cycle has positive length. You do not need to modify the class Main in the template. In particular, you do not need to test whether the input is really a connected graph. 

There are two test sets, and you get one point for each passing test set. To pass both test sets, your solution has to run in time $O(|E|\log |E|)$. 




*Note*: You are allowed to use PriorityQueue and Comparators (if necessary). If you want to practice, consider implementing your own priority queue, for example using a heap. It is a nice example of using a data structure to design an advanced algorithm.


