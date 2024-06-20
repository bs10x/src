# Longest Alternating Subarray


Your task is to program an algorithm that, for an array of distinct integers, computes the length of its longest alternating subarray. A subarray is called alternating if its values form a zig-zag pattern. More precisely, for a subarray, write down comparison signs ($<$ and $>$) between its elements. Then, the subarray is called alternating if the comparison signs form a sequence $(<, >, <, >, ...)$ or $(>, <, >, <, ...)$.

To illustrate, consider the subarray $(1, 5, 3, 4, 2, 6)$. Then the comparison signs are $(<, >, <, >, <)$, so the subarray is alternating. On the other hand, consider the subarray $(1, 5, 3, 4, 6, 2)$. Then the comparison signs are $(<, >, <, <, >)$, so the subarray is not alternating.

As a final example, for the array $(1, 9, 2, 3, 5, 4, 6, 7, 8)$, the longest alternating subarray has length $4$ and corresponds to either $(1, 9, 2, 3)$ or $(3, 5, 4, 6)$.

Please find a more detailed description of the task in the Main.java file. The task is to implement the method longest_alternating_subarray. You are free to create auxiliary methods. You do not need to change other methods. 

You get one point for each passing test set. To pass both test sets correctly, your solution needs to run in $O(n)$ time, where $n$ is the length of the input array.

**Attention**: Although the index of an array starts at 0 in Java, to be consistent with our lecture notes, the index of an array in the code template starts at 1. 