# Longest Power-of-Two Subsequence

You are given an array of $n$ integers between (and including) $0$ and $2^{20}-1 = 1048575$. Your task is to compute the length of the longest subsequence such that the absolute difference between consecutive elements is a power of two (that is, one of $1$, $2$, $4$, $8$, and so on). Note that the elements of the subsequence do not need to be on consecutive positions in the original array.

For example, for the input array $1$, $6$, $3$, $7$, $3$, $9$, the answer is $4$. This is because there exists a valid subsequence of length $4$: this is $1$, $3$, $7$, $3$. On the other hand, there exists no valid subsequence of length $5$.

You need to implement your solution as a method **getLongestSubsequence($n$, $A$)**, where $A$ is the given array.

**Grading** (16 points):

- Let $h$ be the maximum value of an integer in the input. So, $h$ is at most $2^{20}-1 = 1048575$. An $O(h + n \log h)$-time implementation gets 16 points and an $O(n^2)$-time implementation gets 6 points.

**Attention**: 

- You are NOT allowed to use imports explicitly or implicitly other than the imports already in the code template.  
- You see the **percentage** of your submission in the CodeExpert system instead of points.
