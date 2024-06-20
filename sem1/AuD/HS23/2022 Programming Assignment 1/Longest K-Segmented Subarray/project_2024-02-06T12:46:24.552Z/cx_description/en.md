# Longest $k$-Segmented Subarray

Your task is to program an algorithm that, for an array of integers, computes the length of its longest $k$-segmented subarray. We call segment a sequence of equal values. A subarray is $k$-segmented if, given an integer $k$, its values can be partitioned into segments of length $k$. 

To illustrate, consider the subarray $(5, 4, 4, 2, 2, 2, 3, 3)$ and $k = 2$. We have $4$ segments: $(5)$, $(4, 4)$, $(2, 2, 2)$, $(3, 3)$. With these, we can form two longest $2$-segmented subarrays: $(4, 4, 2, 2)$ and $(2, 2, 3, 3)$. 

As a final example, for the array $(1, 1, 2, 2, 2, 2, 1, 5, 5)$ and $k= 2$, the longest 2-Segmented subarray has length $6$ and corresponds to $(1, 1, 2, 2, 2, 2)$.

Note that you only need to output the length of the longest subarrays, not the subarrays themselves.

Please find a more detailed description of the task in the Main.java file. The task is to implement the method LongestKSegmentedSubarray. You are free to create auxiliary methods. You do not need to change other methods. 

You get one point for each passing test set. To pass both test sets correctly, your solution needs to run in $O(n)$ time, where $n$ is the length of the input array.

**Attention**: You are **NOT** allowed to use imports explicitly or implicitly, other than the imports already included in the code template.