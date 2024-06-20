# Left and Right

You are given an array $A$ of $n$ integers, indexed from $0$ to $n-1$. 

You play the following game. You start with a score of $0$. At each step of the game, you can make one of the following moves:

1. If $A$ contains at least two elements, you can remove the **leftmost** and the **rightmost** element of $A$ and add to your score the absolute value of their difference. For example, if the leftmost and the rightmost elements had values $x$ and $y$, you add $|x-y|$ to your score.
2. You can remove the **leftmost** element of $A$ with no change to your score.
3. You can remove the **rightmost** element of $A$ with no change to your score.

Your task is to find the maximum score that you can obtain in the game. You need to implement your solution as a method **getMaximumScore($n$, $A$)**.

*Hint*: Use dynamic programming with $D[i][j]$ representing the maximum score that you can obtain on $A[i], ..., A[j]$.

**Grading** (16 points):

- An $O(n^2)$ implementation gets 16 points and an $O(n^3)$ implementation gets 6 points.

**Attention**: You are **NOT** allowed to use additional imports, other than the imports already included in the code template.
