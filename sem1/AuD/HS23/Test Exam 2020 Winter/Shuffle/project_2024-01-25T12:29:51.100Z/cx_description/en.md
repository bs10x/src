# Shuffle

Given three strings $A$, $B$ and $C$ with $|A|=n$, $|B|=m$ and $|C|=n+m$, your task is to decide if $C$ is a ***shuffle*** of $A$ and $B$.
A *shuffle* of $A$ and $B$ is formed by merging $A$ and $B$ into a new string while maintaining both the internal order of the characters of $A$ and the internal order of the characters of $B$. 
For example, $C$ = **P***INE***AP***P***LE**  is a shuffle of $A$ = **PAPLE** and $B$ = _INEP_.

**Grading** (16 points):

- An $O(n\cdot m)$-time or $O((n+m)^2)$-time implementation gets 16 points, while an $O(2^{n+m})$-time implementation still gets 5 points.


**Note**: The input arrays $A$, $B$ and $C$ are indexed from $1$ instead of $0$ in the code. 


**Attention**: 

- You are NOT allowed to use imports explicitly or implicitly other than the imports already in the code template.  
- You only can see the **percentage** of your submission in the CodeExpert system instead of points.
