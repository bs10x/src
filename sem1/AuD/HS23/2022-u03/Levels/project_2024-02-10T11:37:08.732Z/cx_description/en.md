# Levels

You are given two arrays of $n$ positive integers $A$ and $B$ which satisfy $A[i] > B[i]$ for all $i=0,…,n-1$. These arrays describe a game with $n$ levels, numbered from $0$ to $n − 1$, which goes as follows. At the beginning of the game, you have an infinite amount of energy. In order to complete level i, your energy must be at least $A[i]$, and after completing level i your energy becomes $B[i]$.

You can choose to skip some of the levels, but the levels that you complete must be completed in the given order. Return the maximum number of levels that you can complete. That is, return the length of the longest subsequence of levels (not necessarily consecutive) that you can complete.

For example, for $n=5$, $A=(5, 6, 3, 3, 2)$, and $B=(4, 5, 1, 2, 1)$, the answer is $3$, with one such subsequence being formed of the levels $0$, $3$, and $4$.

You need to implement your solution as a method **getLevels($n$, $A$, $B$)** in the file Main.java. You get one point for each passing test. To pass both test sets, your solution needs to run in time $O(n \log n)$.

**Attention**: You are **NOT** allowed to use additional imports, other than the imports already included in the code template.