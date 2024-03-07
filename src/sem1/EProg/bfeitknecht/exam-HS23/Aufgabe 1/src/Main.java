import java.util.Arrays;

public class Main {
	
  public static void main(String[] args) {
    // Sie koennen die main Methode beliebig aendern.

    int[][] input = 
        { {2,  3, -1, -1,  3, -4},
          {-1, 0,  2,  0,  1, -3},
          {3,  3,  8,  4,  1, -3},
          {3,  4,  2,  0,  2, -2},
          {5,  2,  0,  0,  3, -1},
          {2,  5,  0,  0,  4,  0} };
		
    int[][] result = Matrix.kReduce(input, 2);
    
    System.out.println(Arrays.deepToString(result));
  }

}
