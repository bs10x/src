5       // # of operations

I 2 3   // T.insert(root, 2, 3) --> root is now node(key: 2, value: 3, parent: null, left: null, right: null)
Q 1     // T.query(1) --> 0, since there exists no node with a key <= 1
Q 2     // T.query(2) --> 3, since the root node has a key == 2
I 1 4   // T.insert(1, 4) --> 
Q 2





root

%%%%%%%%%%%

root -> 2, 3

%%%%%%%%%%%

root -> 2, 3 
root.left -> 1, 4

%%%%%%%%%%%



