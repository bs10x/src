import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

public class SquareTest {
	
	@Test
	public void testTrue() {
		
		int[][] t3 = { 
			{20, 5, 0}, 
			{8, 6, 5}, 
			{20, 5, 0} 
		};
		assertTrue(Square.checkProperty(t3));
		
		int[][] t5 = { 
			{0, 3, 5, 7, 2}, 
			{2, 7, 4, 3, 0}, 
			{4, 4, 6, 1, 4}, 
			{7, 7, 4, 3, 3}, 
			{0, 3, 5, 7, 2} 
		};
		assertTrue(Square.checkProperty(t5));
	}
	
	@Test
	public void testFalse() {
		
		int[][] f3 = { 
			{3, 5, 14}, 
			{4, 19, 8}, 
			{0, 13, 3} 
		};
		assertFalse(Square.checkProperty(f3));
			 
		int[][] f5 = { 
			{7, 2, 3, 2, 5}, 
			{5, 0, 6, 5, 4}, 
			{0, 6, 4, 5, 6}, 
			{5, 5, 7, 1, 3}, 
			{3, 4, 6, 3, 2} 
		};
		assertFalse(Square.checkProperty(f5));
	}
}
