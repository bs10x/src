# Minimizing Pair 

Your task is to program an algorithm that given an array $A$ of positive _distinct_ integers and a goal value $k$, finds a pair $(x, y)$ with $x,y \in A$ and $x \neq y$ that minimizes $|x+y-k|$. The program then outputs the value $|x+y-k|$.

As an example, for $A=\{5, 3, 1, 7, 8 , 2\}$ and $k = 14$, the minimizing pairs are $(5,8)$ and $(7,8)$, therefore you should output $1$.

Please see the Main.java file. The task is to implement the method "minimize". You are free to create auxiliary methods. You do not need to change other methods. 

You get one point for each passing test set. To pass both test sets correctly, your solution is expected to run in $O(n \log n)$ time, where $n$ is the length of the input array.

**Hint**: Sort the input array. Do **NOT** use external libraries; implement the sorting yourself.

**Attention**: You are **NOT** allowed to use additional imports, other than the imports already included in the code template.