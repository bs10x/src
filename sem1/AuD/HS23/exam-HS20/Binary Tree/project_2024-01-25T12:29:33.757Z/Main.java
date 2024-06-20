import java.io.*;
import java.util.LinkedList;
import java.util.Scanner;
import java.lang.Math;
import java.lang.Integer;
import java.lang.String;


class Main {
    public static void main(String[] args) {
        // Uncomment the following two lines if you want to read from a file
        In.open("public/example-key_of_rank.in");
        Out.compareTo("public/example-key_of_rank.out");
        
        int m = In.readInt();                 // Number of Operations
        char[] Operation_Array=new char[m];   // Operators (Insertion, Deletion, Query)
        int[] Operand_Array=new int[m];       // Keys
        
        for(int i=0;i<m;i++){
          Operation_Array[i]=In.readChar();
          switch(Operation_Array[i]){
            case 'I':  // Insertion
            case 'D':  // Deletion
            case 'H':  // Depth Query
            case 'C':  // Children Query
            case 'K':  // Key of Rank Query
              Operand_Array[i]=In.readInt();
              break;
          }
        }
        
        BinaryTree T=new BinaryTree();
        
        for(int i=0;i<m;i++){
        
          switch(Operation_Array[i]){
            case 'I':  // Insertion
              T.insert(Operand_Array[i]);
              break;
              
            case 'D':  // Deletion
              T.delete(Operand_Array[i]);
              break;
              
            case 'M':  // Minimum Query 
              Out.println(T.min());
              break;
              
            case 'H':  // Depth Query 
              Out.println(T.depth(Operand_Array[i]));
              break;
              
            case 'C':  // Children Query 
              Out.println(T.children_of_node(Operand_Array[i]));
              break;
              
            case 'K':  // Key of Rank Query
              Out.println(T.key_of_rank(Operand_Array[i]));
              
          }
        }
      
        // Uncomment the following line if you want to read from a file
        In.close();
    }
    

   
}

class TreeNode{
    public int key;
    public TreeNode parent;
    public TreeNode left;
    public TreeNode right;
  
    TreeNode(int key){
        this.key=key;
        this.parent=null;
        this.left=null;
        this.right=null;
    }
    
    //public boolean isRoot() { return thisroot; }
    public boolean hasParent() { return this.parent != null; }
    public boolean hasLeft() { return this.left != null; }
    public boolean hasRight() { return this.right != null; }
}

class BinaryTree{
  
    TreeNode root;
    
    BinaryTree(){
      this.root=null;
    }
    
    
    

    
    public int min(){
      if (root == null) return -1;
      TreeNode current = root;
      while (current.left != null) {
        current = current.left;
      }
      return current.key;
    }
    
    public int depth(int key){
      if (this.search(key) == null) return -1;
      int counter = 0;
      TreeNode current = this.root;
      while (current.key != key) {
        if (key < current.key) {
          current = current.left;
          counter++;
        }
        else {
          current = current.right;
          counter++;
        }
      }
      return counter;
    }

    public int children_of_node(int key){
      if (search(key) == null) return -1;
      int children = 0;
      if (search(key).left != null) children++;
      if (search(key).right != null) children++;
      return children;
    }
    
    
    
    
    
    
    
    
    
    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public int key_of_rank(int rank){
      
      TreeNode current = search(min());
      rank--;
      
      
      while (rank != 0) {
        /*
        boolean edgeCase = (current.parent == null && current.left.key < current.key)
        if (edgeCase) current = getNextMin(current.right);
        
        current = getNextMin(current);
        
        if (current == root) getNextMin(current.right);
        rank--;
        */
        if (current.key < root.key) {
          current = current.parent;
          rank--;
          if (rank != 0 && current.right != null) {
            current = getMin(current.right);
            rank--;
          }
          
        }
        else if (current.key >= root.key) {
          if (current == root && current.hasRight()) {
            current = getMin(current.right);
            rank--;
          }
          
          else if (rank != 0) {
            current = current.parent;
            rank--;
            if (rank != 0 && current.hasRight()) {
              current = getMin(current.right);
              rank--;
            }
            
          }
        }
        
      }
      
      if (current == null) return -2;
      return current.key;
      
    }
    
    
    
    
    public TreeNode getMin(TreeNode start) {
      if (start == null) return null;
      TreeNode current = start;
      while (current.left != null) {
        current = current.left;
      }
      return current;
    }
    
    public TreeNode getMax(TreeNode start) {
      if (start == null) return null;
      TreeNode current = start;
      while (current.right != null) {
        current = current.right;
      }
      return current;
    }
    
    
    /*
    public TreeNode getNextMin(TreeNode current) {
      
      boolean isRoot = current.isRoot();
      boolean hasParent = current.hasParent();
      boolean hasLeft = current.hasLeft();
      boolean hasRight = current.hasRight();
      
      boolean edgeCase =  current.parent.key < getMin(current).key;  // rhs of tree,   //(current.key > current.right.key);
      
      boolean RHS = (current.key >= root.key);
      
      boolean condition2 = (current.hasRight() && 
      
      
      
      if (current == null) return null;
      if (current.isRoot()) return getMin(current.right); 
      
      if (!current.hasLeft()) return current.parent;
      if (!current.hasRight()) return current.parent; // (current.parent.hasRight()) ? getMin(current.parent.right) : current.parent;
      if (current.hasRight()) return getMin(current.right);
      
      // logic to see if we need to go right
      
      if (!RHS) {
   
      }
      else {
        
      }
      
      
      // only do this if we are on LHS ?
      //current = current.parent;
      
      boolean isValid = (current.parent.key < getMin(current.right).key);
      
      return (isValid) ? current.parent : getMin(current.right);
      
      boolean nextMin = (current.key > getMin(current.right).key);
      return (nextMin) ? getMin(current.right) : current;
      
      /*
      leftmost
      leftmost's parent
      leftmost's parent's leftmost
      leftmost's parent's leftmost's parent
      leftmost's parent's leftmost's parent's leftmost
      
      
     
     
      }
    }
    */
    
    public TreeNode assimilate(TreeNode oldMin) {
      
      if (oldMin == root) {
        TreeNode newMin = oldMin;
        
        root = newMin.right;
        newMin.right.parent = null;
        insert(newMin.right, newMin.key);
        newMin.parent = null;
        newMin.right = null;
      }
      else {
        TreeNode newMin = oldMin.parent;
        int newMinKey = newMin.key;
        delete(newMin.key);
        insert(newMinKey);
      }
      return search(min());
      
      //newMin = newMin.parent;
      /*
      boolean singlePringle = (newMin.parent == null && newMin.left == null && newMin.right == null);
      boolean isRoot = (newMin == root);
      
      if (singlePringle) return null;
      if (newMin.right == null) return newMin;
      
      TreeNode newParent = newMin.right;
      
      if (isRoot) {
        root = newMin.right;
        newMin.right.parent = null;
        insert(newMin.right, newMin.key);
        newMin.parent = null;
        newMin.right = null;
      }
      else {
      insert(newMin.right, newMin.key);
      newMin.parent.left = newMin.right;
      newMin.right.parent = newMin.parent;
      newMin.parent = null;
      newMin.right = null;
      }  
      return newParent;*/
    }
    
    
   
    
    
    
    
  
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    public void insert(int key){
      
      if(root==null){
        root=new TreeNode(key);
      }
      else{
        insert(root,key);
      }
    }
    
    
    public void insert(TreeNode node,int key){
      
      if(key<node.key){
        if(node.left!=null){
          insert(node.left,key);
        }
        else{
          node.left=new TreeNode(key);
          node.left.parent=node;
        }
      }
      else{
        if(node.right!=null){
          insert(node.right,key);
        }
        else{
          node.right=new TreeNode(key);
          node.right.parent=node;
        }
      }
    }
    
    
    
 
    
    public void delete(int key){
      
      // find the node to be deleted
      TreeNode node=search(key);
      
      if(node==null){
        return;
      }
        
      if(node.left==null){
        if(node.right==null){
          // no child
          
          if(node==root){
            root=null;
          }
          else{
            if(node.parent.left==node){
              node.parent.left=null;
            }
            else{
              node.parent.right=null;
            }
          }
        }
        else{
          // only right child
          
          if(node==root){
            root=node.right;
            root.parent=null;
          }
          else{
            if(node.parent.left==node){
              node.parent.left=node.right;
              node.right.parent=node.parent;
            }
            else{
              node.parent.right=node.right;
              node.right.parent=node.parent;
            }
          }
        }
        
      }
      else{
        if(node.right==null){
          //only left child
          
          if(node==root){
            root=node.left;
            root.parent=null;
          }
          else{
            if(node.parent.left==node)
            {
              node.parent.left=node.left;
              node.left.parent=node.parent;
            }
            else
            {
              node.parent.right=node.left;
              node.left.parent=node.parent;
            }
          }  
        }
        else{      
          // two children
          
          TreeNode swap_node=node.left;
          
          while(swap_node.right!=null){
            swap_node=swap_node.right;
          }
            
          node.key=swap_node.key;
          
          if(swap_node.left!=null){
            // swap_node has left Child
            
            if(swap_node.parent.left==swap_node){
              swap_node.parent.left=swap_node.left;
              swap_node.left.parent=swap_node.parent;
            }
            else{
              swap_node.parent.right=swap_node.left;
              swap_node.left.parent=swap_node.parent;
            }
          }
          else{
            // swap_node has no child
            
            if(swap_node.parent.left==swap_node){
              swap_node.parent.left=null;
            }
            else{
              swap_node.parent.right=null;
            }
          }
        }
      }
            
    }
    
    public TreeNode search(int key){
      
      if(root==null){
        return null;
      }
      else{
        return search(root,key);
      }
    }
    
    public TreeNode search(TreeNode node,int key){
      if(node==null){
        return null;
      }
      else if(key==node.key){
        return node;
      }
      else{
        if(key<node.key){
          return search(node.left,key);
        }
        else{
          return search(node.right,key);
        }
      }
    }
} 