import static org.junit.Assert.*;

import org.junit.Test;

public class MatrixTest {


	@Test
	public void testBasic() {
		int[][] in1 = { {1, 5, 9}, {8, 3, 4}, {6, 7, 2} };
		int [][] in2 = { {4, 9, 2}, {3, 5, 7}, {8, 1, 6} };
		int [][] in3 = {{11, 18, 25, 2, 9}, {10, 12, 19, 21, 3}, {4, 6, 13, 20, 22}, {23, 5, 7, 14, 16}, {17, 24, 1, 8, 15}
		};
        int[][] in4 = { {1, 5, 9}, {3, 8, 4}, {6, 7, 2} };
        int[][] in5 = { {1, 2, 3}, {4, 5, 6}, {7, 8, 9} };
        
		assertTrue(Matrix.checkMatrix(in1));
		assertTrue(Matrix.checkMatrix(in2));
		assertTrue(Matrix.checkMatrix(in3));
        assertFalse(Matrix.checkMatrix(in4));
        assertFalse(Matrix.checkMatrix(in5));
	}
	
	@Test
	public void testDimensions() {
        int[][] in1 = { {1, 5, 9}, {8, 3, 4}, {6, 7, 2}, {0, 0, 0} };
        int[][] in2 = { {1, 5, 9, 0}, {8, 3, 4, 0}, {6, 7, 2, 0} };
        int[][] in3 = { {1, 5, 9}, {8, 3, 4, 0}, {6, 7, 2} };
        
        assertFalse(Matrix.checkMatrix(in1));
        assertFalse(Matrix.checkMatrix(in2));
        assertFalse(Matrix.checkMatrix(in3));
	}
	
	@Test
	public void testUnique() {
		int[][] in2 = { {1, 2, 3}, {3, 1, 2}, {2, 3, 1} };
		  
        assertFalse(Matrix.checkMatrix(in2));
	}
	
	@Test
	public void testDomain() {
		int[][] in2 = { {2, 6, 10}, {9, 4, 5}, {7, 8, 3} };
        
        assertFalse(Matrix.checkMatrix(in2));
	}
}
