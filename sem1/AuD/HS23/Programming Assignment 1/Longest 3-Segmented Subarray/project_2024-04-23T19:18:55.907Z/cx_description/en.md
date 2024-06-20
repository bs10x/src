# Longest $3$-Segmented Subarray

In this task, we define a segment as a non-empty subarray (consecutive indices) of equal values. A subarray is $3$-segmented if it consists of $3$ consecutive segments. That is, the subarray is of the form $a, a, \dots, a, b, b, \dots, b, c, c, \dots, c$.

Your task is to program an algorithm that, for an array $A$ of $n$ integers, computes the length of its longest $3$-segmented subarray.

For example, consider the array $(5, 4, 4, 2, 2, 2, 3, 3)$. We have $4$ segments: $(5)$, $(4, 4)$, $(2, 2, 2)$, $(3, 3)$. The longest $3$-segmented subarray is $(4, 4, 2, 2, 2, 3, 3)$. Notice that $(5, 4, 4, 3, 3)$ is not a $3$-segmented subarray because subarrays must be consecutive.

Note that you only need to output the length of the longest $3$-segmented subarray, not the subarrays themselves. Moreover, you need to output 0 if there are no $3$-segmented subarrays.

You need to implement your solution as a method **longestSubarray(n, A)** in the file Main.java. You get one point for each passing test set. To pass both test sets correctly, your solution needs to run in $O(n)$ time, where $n$ is the length of the input array.

**Attention**: You are **NOT** allowed to use additional imports, other than the imports already included in the code template.