# Subarray Sum in Smaller Array

You are given an $n$-element array $A$ of integers and a positive integer $S$. You are guaranteed that the subarray of $A$ of maximum sum has sum greater than or equal to $S$.

$A$ is too large for your taste. Therefore, you want to choose an integer $B$ and remove from $A$ all elements with value greater than or equal to $B$. However, you still want the new array to contain at least one subarray with sum greater than or equal to $S$.

Return the minimum integer $B$ that you can choose in order to satisfy this constraint.

For example, for $n=10$, $A=(-1, 4, 4, 6, -6, 5, -5, 4, 4, -1)$, and $S=9$, the answer is $6$. This is because removing all the elements with value greater than or equal to $6$ results in the array $A=(-1, 4, 4, -6, 5, -5, 4, 4, -1)$, which has a subarray of sum $10$: $(4, 4, -6, 5, -5, 4, 4)$. Note that the answer is not $5$, because removing all the elements with value greater than or equal to $5$ results in the array $A=(-1, 4, 4, -6, -5, 4, 4, -1)$, in which all subarrays have sum at most $8$.

You need to implement your solution as a method **getB($n$, $A$, $S$)** in the file Main.java. You get one point for each passing test. To pass both test sets, your solution needs to run in time $O(n \log n)$.

**Hint**: Use binary search.

**Attention**: You are **NOT** allowed to use additional imports, other than the imports already included in the code template.