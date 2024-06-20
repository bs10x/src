# Binary Search Tree

During the semester, you have learned binary search trees and also implemented a corresponding programming exercise. 
In this exercise, your task is to implement the following four kinds of queries:

1. **min()**: If the current binary search tree is not empty, return the **minimum key** in the binary search tree; otherwise, return $-1$.
2. **depth($k$)**: Given an integer $k$, if the current binary search tree contains a node whose key is $k$, return the **depth** of the node; otherwise, return $-1$.  Note that the depth of the *root* is defined to be **0**.
3. **children_of_node($k$)**: Given an integer $k$, if the current binary search tree contains a node whose key is $k$, return **the number of children** of the node; otherwise, return $-1$.
4. **key_of_rank($r$)**: Given an integer *r*, if the number of nodes in the current binary tree is at least $r$, return **the $r$-th smallest key** in the binary search tree; otherwise, return $-1$.

**Note**:

- For the last kind of queries, *key_of_rank($r$)*, you have to augment the binary search tree. 
- You can safely assume that all keys in the binary search tree are distinct and nonnegative. 


**Grading** (24 points): Let $h$ be the height of the current binary search tree.

1. **min()** (5 points): An $O(h)$-time implementation gets 5 points. If border cases are treated incorrectly, you will instead get 4 points.
2. **depth($k$)** (5 points): An $O(h)$-time implementation gets 5 points. If border cases are treated incorrectly, you will instead get 4 points.
3.  **children_of_node($k$)** (5 points): An $O(h)$-time implementation gets 5 points. If border cases are treated incorrectly, you will instead get 4 points.
4.  **key_of_rank($r$)** (9 points): An $O(h)$-time implementation gets 9 points, while an $O(n)$-time implementation gets 5 points, where $n$ is the number of nodes in the current binary search tree. If border cases are treated incorrectly, you will instead get 8 points and 4 points, respectively. 


**Attention**: 

- You are NOT allowed to use imports explicitly or implicitly other than the imports already in the code template.  
- You only can see the **percentage** of your submission in the CodeExpert system instead of points.


