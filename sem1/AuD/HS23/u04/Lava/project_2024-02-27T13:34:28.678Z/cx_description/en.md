# Lava

You are in a 2-dimentional lava field $M$ of size $n \times m$, where $M[i][j]$ is a $\{0, 1\}$ value denoting whether position $(i, j)$ has lava ($1$ denotes that it has lava and $0$ denotes that it does not have lava). In each step, you can move 1 step horizontally or vertically, but you can only walk on positions with no lava and you cannot move out of the lava field. Your goal is to compute the smallest number of steps that you need to go from a given position $s=(i_1, j_1)$ to a given position $t=(i_2, j_2)$ (you can assume that $t$ is always reachable from $s$).

For example, given
$$
M = \begin{bmatrix}
0 & 1 & 0 & 1 \\
0 & 0 & 0 & 0\\
0 & 1 & 1 & 0\\
0 & 0 & 0 & 0
\end{bmatrix} \,.
$$
To get from position $(0, 2)$ to position $(3, 2)$, you can either take $(0, 2) \rightarrow (1, 2) \rightarrow (1, 1) \rightarrow (1, 0) \rightarrow (2, 0) \rightarrow (3, 0) \rightarrow (3, 1) \rightarrow (3, 2)$ which has 7 steps, or you can take $(0, 2) \rightarrow (1, 2) \rightarrow (1, 3) \rightarrow (2, 3) \rightarrow (3, 3) \rightarrow (3, 2)$ which has 5 steps. Hence, the smallest number of steps you need is 5.

You need to implement your solution as a method **steps($M$, $n$, $m$, $i1$, $j1$, $i2$, $j2$)** in the file Main.java. You get one point for each passing test set. To pass both test sets, your solution is expected to run in time $O(n * m)$.

**Attention**: You are **NOT** allowed to use additional imports, other than the imports already included in the code template.
