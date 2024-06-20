import algorithms.*;
import java.util.*;

class Main {
    public static void main(String[] args) {
      In.open("public/test1.in");
      Out.compareTo("public/test1.out");
      int t = In.readInt();
      for (int i = 0; i < t; i++) {
        testCase();
      }
      In.close();
    }
    
    public static void testCase() {
      int n = In.readInt();
      ArrayList<String> substrs = collectSubstrs(n);
      Graph G = new Graph(substrs);
      // printInfo(G);
      if (G.connected && G.structured) Out.println("yes");
      else Out.println("no");
    }
    
    static int tC = 0;
    public static void printInfo(Graph G) {
      Out.println();
      Out.println("##### " + tC + " ############################################################################################");
      Out.println("#####################################################################################################");
      Out.println("n:\t" + G.n);
      Out.println("m:\t" + G.m);
      Out.println("ab:\t" + G.ab);
      Out.println("yz:\t" + G.yz);
      Out.println("balanced:\t" + G.balanced);
       Out.println("balanced:\t" + G.balanced);
      Out.println("connected:\t\t" + G.connected);
      Out.println("structured:\t\t" + G.structured);
      boolean cs = G.connected && G.structured;
      Out.println("reconstructable:\t" + cs);
      printAdjM(G);
      tC++;
    }
    public static void printAdjM(Graph G) {
      for (Node n : G.adjM.keySet()) {
        Out.print(n.name + nodeInfo(n) + " --> [ ");
        for (Node nn : G.adjM.get(n)) Out.print(nn.name + ", ");
        Out.println("]");
      }
    }
    public static String nodeInfo(Node n) {
      String info = " (ID: ";
      info += n.ID + ", ";
      info += "dI: " + n.degIn + ", ";
      info += "dO: " + n.degOut + ", ";
      info += "v?: " + n.visited + ")";
      return info;
    }
    
    public static ArrayList<String> collectSubstrs(int n) {
      ArrayList<String> substrs = new ArrayList<>();
      for (int i = 0; i < n; i++) substrs.add(In.readWord());
      return substrs;
    }
    
    static class Graph {
      public int n; // number of substrings * 2 - #duplicates
      public int m; // number of edges
      public boolean connected;
      public boolean structured;
      public HashMap<Node, ArrayList<Node>> adjM;
      
      public int ab;  // degOut == degIn + 1
      public int yz;  // degIn == degOut + 1
      public int unbalanced;   // nodes with unbalanced degrees
      public int balanced;
      
      public Graph(ArrayList<String> substrs) {
        // initialize fields
        this.n = 0;
        this.m = 0;
        this.adjM = new HashMap<>();
        
        this.ab = 0;
        this.yz = 0;
        this.unbalanced = 0;
        this.balanced = 0;
        
        // instantiate nodes, initialize their adjL and connect them
        for (String s : substrs) {
          String preS = s.substring(0, 2);
          String postS = s.substring(1, 3);
          
          if(!containsNode(preS)) {
            Node preN = new Node(this.n, preS);
            adjM.put(preN, new ArrayList<>());
            this.n++;
          }
          if(!containsNode(postS)) {
            Node postN = new Node(this.n, postS);
            adjM.put(postN, new ArrayList<>());
            this.n++;
          }
          connect(getByName(preS), getByName(postS));
        }
        
        this.connected = isConnected();
        this.structured = isStructured();
      }
      
      // add edge from preN to postN and update appropriate fields
      public void connect(Node preN, Node postN) {
        if (preN == postN) return;
        preN.degOut++;
        postN.degIn++;
        this.adjM.get(preN).add(postN);
        this.m++;
      }
      
      public boolean isConnected() {
        boolean connected = true;
        DFS();
        for (Node n : adjM.keySet()) if (!n.visited) connected = false;
        // resetNodes();
        return connected;
      }
      public void DFS() {
        Node u = getByID(0);
        for (Node n : adjM.keySet()) if (n.degIn == 0) u = n;
        visit(u);
      }
      public void visit(Node v) {
        v.visited = true;
        for (Node w : adjM.get(v)) if (!w.visited) visit(w);
      }
      public void resetNodes() {
        for (Node n : this.adjM.keySet()) n.visited = false;
      }
      
      public boolean isStructured() {
        for (Node n : adjM.keySet()) {
          if (n.degIn == n.degOut + 1) yz++;
          else if (n.degOut == n.degIn + 1) ab++;
          else if (n.degIn == n.degOut) balanced++;
          else unbalanced++;
        }
        return (ab == 1 && yz == 1) && unbalanced == 0 || balanced == n;
      }
      
      public boolean containsNode(String name) {
        for (Node n : adjM.keySet()) if (n.name.equals(name)) return true;
        return false;
      }
      public Node getByName(String name) {
        for (Node n : adjM.keySet()) {
          if (n.name.equals(name)) return n;
        }
        return null;
      }
      public Node getByID(int ID) {
        if (ID > this.n || ID < 0) return null;
        for (Node n : adjM.keySet()) {
          if (n.ID == ID) return n;
        }
        return null;
      }
    }
    
    static class Node {
      public int ID;
      public String name;
      public int degIn;
      public int degOut;
      public boolean visited;
      
      public Node(int ID, String name) {
        this.ID = ID;
        this.name = name;
        this.degIn = 0;
        this.degOut = 0;
      }
    }
}