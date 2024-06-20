import java.util.Arrays;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.PriorityQueue;
// ADDITIONAL IMPORTS ARE NOT ALLOWED

class Main {
  public static void main(String[] args) {
    // Uncomment the following two lines if you want to read from a file.
    // In.open("public/example.in");
    // Out.compareTo("public/example.out");

    int n = In.readInt();
    int m = In.readInt();
    int s = In.readInt();
    int t = In.readInt();
    
    // Ev is an adjacency list such that Ev.get(i) is the list of neighbors of
    // vertex i. Specifically:
    //   Ev.get(i).get(j) = the j-th neighbor of vertex i
    //   Ew.get(i).get(j) = the weight of the edge from vertex i to its j-th neighbor
    // The number of elements of Ev.get(i) is given by Ev.get(i).size() (same for Ew).
    ArrayList<ArrayList<Integer>> Ev = new ArrayList<ArrayList<Integer>>(n);
    ArrayList<ArrayList<Integer>> Ew = new ArrayList<ArrayList<Integer>>(n);
    for (int i = 0; i < n; i++) { // initialization with empty arrays
      Ev.add(new ArrayList<Integer>());
      Ew.add(new ArrayList<Integer>());
    }
    
    for (int i = 0; i < m; i++) {
      int e1 = In.readInt();
      int e2 = In.readInt();
      int ew = In.readInt();
      Ev.get(e1).add(e2);
      Ew.get(e1).add(ew);
      Ev.get(e2).add(e1);
      Ew.get(e2).add(ew);
    }
    Out.println(countEdges(n, m, s, t, Ev, Ew));
    
    // Uncomment this line if you want to read from a file
    // In.close();
  }
  
  // Feel free to use the following class. It defines "Tuple" objects that can
  // be sorted according to the compareTo function that you can define.
  // This allows you, for example, to have a priority queue with Tuple
  // elements sorted according to your desired criteria. Of course, in the current
  // form the class is empty -- you need to add variables, etc.
  
  /*
  static class Tuple implements Comparable<Tuple> {
    public Tuple() {
      
    }
    
    // In case you want to sort a custom class you need to have this method
    @Override public int compareTo(Tuple other) {
      // in case this is > than other, return a positive number,
      // otherwise a negative number
      return 0;
    }
  }
  */
  
  public static int countEdges(int n, int m, int s, int t, ArrayList<ArrayList<Integer>> Ev,
                               ArrayList<ArrayList<Integer>> Ew) {
    // Ev is an adjacency list such that Ev.get(i) is the list of neighbors of
    // vertex i. Specifically:
    //   Ev.get(i).get(j) = the j-th neighbor of vertex i
    //   Ew.get(i).get(j) = the weight of the edge from vertex i to its j-th neighbor
    // The number of elements of Ev.get(i) is given by Ev.get(i).size() (same for Ew).
    
    return 0;
  }
}