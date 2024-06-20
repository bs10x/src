# Triangles


Your task is to program an algorithm that, for an array $A$ of positive integers, counts the number of triplets of indices $(i,j,k)$ with $i < j < k$ such that there exists a triangle with edge lengths $(A[i], A[j], A[k])$. Recall that such a triangle exists if and only if $A[i] + A[j] \geq A[k]$, $A[j] + A[k] \geq A[i]$, and $A[i] + A[k] \geq A[j]$. Note that, for the purposes of this task, a triangle in which all three vertices are colinear is considered valid.

As an example, for $A=\{5, 1, 8, 6, 2\}$, the answer is $4$, corresponding to $0$-indexed triplets of indices $(0,1,3)$, $(0,2,3)$, $(0,3,4)$, and $(2,3,4)$.

Please see the Main.java file. The task is to implement the method "triangles". You are free to create auxiliary methods. You do not need to change other methods. 

You get one point for each passing test set. To pass both test sets correctly, your solution is expected to run in $O(n^2)$ or $O(n^2 \log n)$ time, where $n$ is the length of the input array.

**Attention**: The index of an array in the code template starts at **0** following the convention of Java.