# Shortest Uncommon Subsequence

Given an $n$-element string $A$ and an $m$-element string $B$ such that $A$ is not a subsequence of $B$ and $n \leq m$, your task is to compute the length of a ***shortest uncommon subsequence*** of $A$ with respect to $B$, i.e., the length of a shortest subsequence of $A$ that is not a subsequence of $B$. For example, if $A=EETTT$ and $B=TETTE$, $EET$ is a shortest uncommon subsequence of $A$ with respect to $B$, and its length is 3.


**Grading** (16 points):

- An $O(nm^2)$-time implementation gets 16 points, while an $O(2^n\cdot m)$-time implementation still gets 6 points.


**HINT**: 
- You may use an $(n+1)*(m+1)$-size DP table where, e.g., $\mathrm{DP}[i][j]$ stores the answer for the first $i$ characters of $A$ and the first $j$ characters of $B$.
- For a dynamic program, you can decompose the problem based on the largest position in $B$ that matches the last character of $A$.


**Note**: 
- The input array $A$ is indexed from $0$, and all characters are capital letters.


**Attention**: 

- You are NOT allowed to use imports explicitly or implicitly other than the imports already in the code template.  
- You see the **percentage** of your submission in the CodeExpert system instead of points.
