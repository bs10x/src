# Pair-Subsequence

You are given an array $A$ of $n$ integers, some of them positive and some of them negative, indexed from $0$ to $n-1$.

Your task is to find the maximum sum that the elements of a *pair-subsequence* of $A$ can have. A pair-subsequence is defined as a subsequence that also satisfies the following properties:

- if the element with index $i$ appears in the subsequence, then either the element with index $i-1$ or the element with index $i+1$ also appears in the subsequence
- no three elements with consecutive indices appear in the subsequence

More informally, a pair-subsequence is a subsequence obtained by selecting pairs of consecutive elements of $A$, such that the pairs are not adjacent to each other. A pair-subsequence is also allowed to be empty, case in which the sum of its elements is defined to be $0$.

For example, for $n=8$ and $A=[1, 2, 3, 3, 2, 2, -10, 10]$, the pair-subsequence of maximum sum is the one with indices $1, 2, 4, 5$, corresponding to values $2, 3, 2, 2$, and hence with sum $9$.

Return the maximum sum of the elements of a pair-subsequence of $A$.

You need to implement your solution as a method **getMaximumSum($n$, $A$)**.

**Grading** (16 points):

- An $O(n^2)$ solution gets 6 points and an $O(n)$ solution gets 16 points.

**Attention**: 

- You are **NOT** allowed to use imports explicitly or implicitly, other than the imports already included in the code template.  
- When you submit the code, you see your score as a **percentage**, not as a number of points.