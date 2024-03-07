import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class MatrixTest {

	@Test
	public void test1() {
		int[][] input = getMatrix1();
		int[][] result = Matrix.kReduce(input, 2);
		
		int[][] expected = { 
				{3, 0, -4},
				{4, 8, -3}, 
				{5, 0,  4}
		};
		
		assertArrayEquals(expected, result);
	}
	
	@Test
	public void test2() {
		int[][] input = getMatrix1();
		int[][] result = Matrix.kReduce(input, 6);
		
		int[][] expected = new int[][] { {8} };
		
		assertArrayEquals(expected, result);
	}
	
	@Test
	public void test3() {		
		int[][] input = getMatrix1(); 				
		int[][] result = Matrix.kReduce(input, 3);	
	
		int[][] expected = new int[][] { 
			{8, -4}, 
			{5,  4} 
		};
		
		assertArrayEquals(expected, result);
	}

	@Test
	public void test4() {
		int[][] input = getMatrix2();
		int[][] result = Matrix.kReduce(input, 2);	

		int[][] expected = new int[][] { 
			{ 0,  0}, 
			{-8,  17} 
		};
		
		assertArrayEquals(expected, result);
	}
	
	@Test
	public void test5() {		
		assertThrows(IllegalArgumentException.class, 
				() -> { Matrix.kReduce(new int[5][5], 2); }
		);
	}
	
	@Test
	public void test6() {
		assertThrows(IllegalArgumentException.class, 
				() -> { Matrix.kReduce(new int[6][4], 2); }
		);
	}
		
	public int[][] getMatrix1() {
		int[][] a = 
			{ {2,  3, -1, -1,  3, -4},
			  {-1, 0,  2,  0,  1, -3},
			  {3,  3,  8,  4,  1, -3},
			  {3,  4,  2,  0,  2, -2},
			  {5,  2,  0,  0,  3, -1},
			  {2,  5,  0,  0,  4,  0} };
		
		return a;
	}
	
	public int[][] getMatrix2() {
		int[][] a = 
			{ { 2,   4,   8,  10},
			  {-2,  -4, -18,   0},
			  { 1,   5,  12,  -8},
			  {-4,  -8,  17,   2},
			};
		
		return a;
	}

}
