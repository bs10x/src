// ADDITIONAL IMPORTS ARE NOT ALLOWED
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

class Main {
  public static void main(String[] args) {
    // Uncomment the following two lines if you want to read from a file.
    In.open("public/small.in");
    Out.compareTo("public/small.out");
    
    int n = In.readInt();
    int m = In.readInt();
    int i1 = In.readInt();
    int j1 = In.readInt();
    int i2 = In.readInt();
    int j2 = In.readInt();
    int[][] M = new int[n][m];
    for (int i = 0; i < n; i++) {
      for (int j = 0; j < m; j++) {
        M[i][j] = In.readInt();
      }
    }

    Out.println(steps(n, m, M, i1, j1, i2, j2));

    // Uncomment this line if you want to read from a file
    In.close();
  }
  
  
  
  static class Pair {
    public int i;
    public int j;
    
    public Pair(int i, int j) {
      this.i = i;
      this.j = j;
    }
    
    public Pair plus(Pair other) {
      return new Pair(this.i + other.i, this.j + other.j);
    }
    
    public boolean equals(Pair other) {
      return (this.i == other.i) && (this.j == other.j);
    }
    
    public boolean been(boolean[][] visited) {
      return visited[this.i][this.j];
    }
    
    public void gonnaBe(boolean[][] visited) {
      visited[this.i][this.j] = true;
    }
    
    public boolean isValid(int n, int m, int[][] M) {
     return (this.i >= 0 && this.i < n) && (this.j >= 0 && this.j < m) && (M[this.i][this.j] == 0);
    }
  }
  
  public static int steps(int n, int m, int[][] M, int i1, int j1, int i2, int j2) {
    Queue<Pair> Q = new LinkedList<>();
    int steps = 0;
    boolean[][] visited = new boolean[n][m];
    Pair up = new Pair(+1, 0);
    Pair down = new Pair(-1, 0);
    Pair right = new Pair(0, +1);
    Pair left = new Pair(0, -1);
    ArrayList<Pair> moves = new ArrayList<>();
    moves.add(up);
    moves.add(down);
    moves.add(left);
    moves.add(right);
    Pair source = new Pair(i1, j1);
    Pair target = new Pair(i2, j2);
    source.gonnaBe(visited);
    Q.add(source);
    while (!Q.isEmpty()) {
      int level = Q.size();
      for (int l = 0; l < level; l++) {
        Pair kid = Q.poll();
        for (Pair nextMove : moves) {
          Pair boy = kid.plus(nextMove);
          if (boy.equals(target)) return steps + 1;
          if (boy.isValid(n, m, M) && !boy.been(visited)) {
            boy.gonnaBe(visited); 
            Q.offer(boy);
          }
        }
      }
      steps++;
    }
    return -1;
  }
}