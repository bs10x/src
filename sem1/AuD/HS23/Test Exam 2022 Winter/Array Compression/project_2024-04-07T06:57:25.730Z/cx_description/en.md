# Array Compression

You are given an array of $n$ integers $A[0]$, ..., $A[n-1]$. 

You can perform the following compression operation to the array: for some $i < j$ such that $A[i] = A[j]$, you can remove from the array all the elements $A[i]$, $A[i + 1]$, ..., $A[j - 1]$, $A[j]$. After that the array is re-indexed and you can perform other compression operations.

You really love compression operations. Return the maximum number of compression operations that you can perform to the array.

For example, for $n=6$ and the array $A=[4, 5, 6, 4, 6, 5]$, the answer is $2$: you can compress $[6, 4, 6]$, after which the array becomes $[4, 5, 5]$, and then you can compress $[5, 5]$.

You need to implement your solution as a method **maxCompressions($n$, $A$)**.

*Hint*: Use dynamic programming with $D[i][j]$ representing the maximum number of compression operations that you can perform to the array $A[i]$, $A[i + 1]$, ..., $A[j - 1]$, $A[j]$.

**Grading** (16 points): An $O(n^3)$ implementation gets $16$ points. An implementation that runs in time less than $0.1$ seconds for $n \leq 10$ gets at least $6$ points.

**Attention**: You are **NOT** allowed to use additional imports, other than the imports already included in the code template.