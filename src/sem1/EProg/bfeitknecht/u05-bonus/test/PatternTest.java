import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;


public class PatternTest {

	@Test
	public void testMatch1() {
		int[][] a = { 
				{ 1, 2, 3, 2, 1},
				{ 2, 1, 3, 1, 3},
				{ 1, 2, 3, 2, 1},
				{ 3, 1, 3, 1, 3},
				{ 1, 2, 1, 2, 1}
		};

		int[][] m1 = { 
				{ 1, 2},
				{ 2, 1}
		};

		MatchRecord r; 

		r = Pattern.match(a, m1);
		assertNotNull(r);
		assertEquals(0, r.x);
		assertEquals(0, r.y);
		assertEquals(0, r.count);
	}

	@Test
	public void testMatch2() {
		int[][] a = { 
				{ 1, 2, 1, 3, 1},
				{ 3, 1, 2, 1, 2},
				{ 1, 3, 1, 3, 1},
				{ 3, 1, 3, 1, 3},
				{ 1, 3, 1, 3, 1}
		};

		int[][] m2 = { 
				{ 2, 1},
				{ 1, 2}
		};

		MatchRecord r; 

		r = Pattern.match(a, m2);
		assertNotNull(r);
		assertEquals(0, r.x);
		assertEquals(1, r.y);
		assertEquals(0, r.count);
	}

	@Test
	public void testMatch3() {
		int[][] a = { 
				{ 1, 2, 1, 2, 1},
				{ 2, 1, 2, 1, 2},
				{ 1, 2, 1, 2, 1},
				{ 2, 1, 2, 1, 2},
				{ 1, 2, 1, 2, 1}
		};

		int[][] m3 = { 
				{3,3},
				{3,3}
		};

		MatchRecord r; 
		
		r = Pattern.match(a, m3);
		assertNotNull(r);
		boolean legalX = (r.x >= 0) && (r.x <= 3);
		boolean legalY = (r.y >= 0) && (r.y <= 3);
		assertTrue(legalX && legalY);
		assertEquals(4, r.count);
	}
	
	@Test
	public void testMatch4() {
		int[][] b  = { 
				{ 1, 2, 1, 2, 1},
				{ 2, 1, 2, 1, 2},
				{ 1, 2, 3, 3, 1},
				{ 2, 1, 3, 3, 3},
				{ 1, 2, 3, 1, 3}
		};

		int[][] m3 = { 
				{3,3},
				{3,3}
		};

		MatchRecord r; 

		r = Pattern.match(b, m3);
		assertNotNull(r);
		assertEquals(2, r.x);
		assertEquals(2, r.y);
		assertEquals(0, r.count);
	}
	

	@Test
	public void testMatch5() {		
		int[][] c  = { 
				{ 1, 2, 1, 2, 1},
				{ 2, 1, 2, 1, 2},
				{ 1, 2, 1, 2, 1},
				{ 2, 1, 2, 3, 3},
				{ 1, 2, 1, 3, 3}
		};

		int[][] m3 = { 
				{3,3},
				{3,3}
		};

		MatchRecord r; 

		r = Pattern.match(c, m3);
		assertNotNull(r);
		assertEquals(3, r.x);
		assertEquals(3, r.y);
		assertEquals(0, r.count);
	}
}
