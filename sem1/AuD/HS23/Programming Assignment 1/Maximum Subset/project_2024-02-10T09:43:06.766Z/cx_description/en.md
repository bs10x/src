# Maximum Subset

You are given two integers $n$ and $k$ and an array $A$ of $n$ distinct integers. You want to find the maximum number of elements of $A$ that you can pick such that all of their values are at most $k$ apart from each other (that is, the maximum value that you pick minus the minimum value that you pick must be at most $k$). 

For example, for $n=7$, $k=4$, and $A=[5, 1, 12, 6, 9, 11, 8]$, the answer is $4$, and it is obtained by picking the elements with value $5$, $6$, $9$, and $8$; these values are at most $4$ apart.

You need to implement your solution as a method **maxSubset($n$, $A$)** in the file Main.java. You get one point for each passing test set. To pass both test sets, your solution is expected to run in time $O(n \log n)$.

*Hint:* Sort the array. You are allowed to create new methods if that is helpful to you.

**Attention**: You are **NOT** allowed to use additional imports, other than the imports already included in the code template.