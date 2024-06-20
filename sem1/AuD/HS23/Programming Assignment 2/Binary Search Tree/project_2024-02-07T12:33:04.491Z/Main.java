// ADDITIONAL IMPORTS ARE NOT ALLOWED

class Main {
  public static void main(String[] args) {
    // Uncomment the following two lines if you want to read from a file
    In.open("public/example.in");
    Out.compareTo("public/example.out");

    BinaryTree T = new BinaryTree();

    int m = In.readInt(); // number of operations
    for (int i = 0; i < m; i++) {
      char operation = In.readChar();
      if (operation == 'I') {
        int key = In.readInt();
        int value = In.readInt();
        T.insert(key, value);
      } else {
        int x = In.readInt();
        Out.println(T.query(x));
      }
    }

    // Uncomment the following line if you want to read from a file
    In.close();
  }
}

class TreeNode {
  public int key;
  public int value;
  public TreeNode parent;
  public TreeNode left;
  public TreeNode right;
  public int nodeHeight;

  TreeNode(int key, int value) {
    this.key = key;
    this.value = value;
    this.parent = null;
    this.left = null;
    this.right = null;
  }
}

class BinaryTree {
  TreeNode root;
  public int treeHeight;
  
  BinaryTree() {
    this.root = null;
  }
  
  // Inserts a node with the given key and value in the binary tree rooted at
  // BinaryTree.root.
  public void insert(int key, int value) {
    if (root == null) {
      root = new TreeNode(key, value);
    } else {
      insert(root, key, value);
    }
  }
  
  // Inserts a node with the given key and value in the binary tree rooted at
  // node.
  public void insert(TreeNode node, int key, int value) {
    if (key < node.key) { // insert in left subtree
      if (node.left != null) {
        insert(node.left, key, value);
      } else {
        node.left = new TreeNode(key, value);
        node.left.parent = node;
      }
    } else { // insert in right subtree
      if (node.right != null) {
        insert(node.right, key, value);
      } else {
        node.right = new TreeNode(key, value);
        node.right.parent = node;
      }
    }
  }
  
  // Returns the maximum value associated with a key that is less than or equal
  // to x in the binary tree rooted at BinaryTree.root.
  public int query(int x) {
    TreeNode current = root;
    int cK = current.key;
    int cV = current.value;
    while (!cV > )
    
    // TODO: implement.
    return 0;
  }
  
}