# Binary Search Tree

In the file Main.java you are given an implementation of a binary search tree in which pairs $(\rm{key}, \rm{value})$ can be inserted. The nodes of the tree are ordered by the key, but each node also stores the value  associated with the key. We did not implement a deletion operation, and there will be no deletion operations in this task.

Your task is to augment this binary search tree so that it supports queries of the following type: given an integer $x$, return the maximum value associated with a key such that the key is less than or equal to $x$. If there is no key less than or equal to $x$, you need to return $0$.

Specifically, your task is to implement the method **query($x$)** in the class **BinaryTree**. To do so, you may need to add auxiliary variables and methods to the classes **TreeNode** and **BinaryTree**, and also to modify some of the existing methods.

You get one point for each passing test set.
To pass both test sets, the time complexity per query needs to be $O(h)$, where $h$ is the height of the binary search tree.

**Attention**: You are **NOT** allowed to use additional imports, other than the imports already included in the code template.

