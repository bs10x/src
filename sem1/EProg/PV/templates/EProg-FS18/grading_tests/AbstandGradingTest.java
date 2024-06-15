import static java.util.concurrent.TimeUnit.SECONDS;
import static org.junit.Assert.*;

import org.junit.Rule;
import org.junit.Test;

public class AbstandGradingTest {

	@Test
	public void testGiven() {
		assertEquals(18, Abstand.abstand(new int[] {1,-2,0,3}, new int[] {1,4,-5,3,7}));
	}

	@Test
	public void testEmptyAndNonZero() {
		assertEquals(0, Abstand.abstand(new int[] {}, new int[] {}));
		// even no-impl would pass the above, so we assert another example to be non-zero
		assertTrue(0 != Abstand.abstand(new int[] {1}, new int[] {1000}));
	}

	@Test
	public void testOneElemWithoutAbs() {
		assertEquals(0, Abstand.abstand(new int[] {1}, new int[] {1}));
		// use abs() to ignore the mistake of not using abs()
		assertEquals(7, Math.abs(Abstand.abstand(new int[] {0}, new int[] {7})));
		assertEquals(7, Math.abs(Abstand.abstand(new int[] {7}, new int[] {0})));
	}

	@Test
	public void testOneElemEnsureAbs() {
		// same as testOneElemWithoutAbs, but abs() is checked for
		assertEquals(0, Abstand.abstand(new int[] {1}, new int[] {1}));
		assertEquals(7, Abstand.abstand(new int[] {0}, new int[] {7}));
		assertEquals(7, Abstand.abstand(new int[] {7}, new int[] {0}));
	}

	@Test
	public void testAbstandSameLengthsWithoutAbs() {
		assertEquals(0, Abstand.abstand(new int[] {1,2,3,4}, new int[] {1,2,3,4}));
		// use abs() to ignore the mistake of not using abs()
		assertEquals(10, Math.abs(Abstand.abstand(new int[] {1,2,3,4}, new int[] {0,0,0,0})));
		assertEquals(10, Math.abs(Abstand.abstand(new int[] {1,2,3,4}, new int[] {2,4,6,8})));
		assertEquals(10, Math.abs(Abstand.abstand(new int[] {-1,-2,-3,-4}, new int[] {-2,-4,-6,-8})));
		assertEquals(20, Math.abs(Abstand.abstand(new int[] {1,2,3,4}, new int[] {-1,-2,-3,-4})));
	}

	@Test
	public void testAbstandSameLengthsEnsureAbs() {
		// same as testAbstandSameLengthsWithoutAbs, but abs() is checked for
		assertEquals(0, Abstand.abstand(new int[] {1,2,3,4}, new int[] {1,2,3,4}));
		assertEquals(10, Abstand.abstand(new int[] {1,2,3,4}, new int[] {0,0,0,0}));
		assertEquals(10, Abstand.abstand(new int[] {1,2,3,4}, new int[] {2,4,6,8}));
		assertEquals(10, Abstand.abstand(new int[] {-1,-2,-3,-4}, new int[] {-2,-4,-6,-8}));
		assertEquals(20, Abstand.abstand(new int[] {1,2,3,4}, new int[] {-1,-2,-3,-4}));
	}

	@Test
	public void testAbstandDifferentLengthsWithoutAbs() {
		// use abs() to ignore the mistake of not using abs()
		assertEquals(1, Math.abs(Abstand.abstand(new int[] {}, new int[] {1})));
		assertEquals(1, Math.abs(Abstand.abstand(new int[] {1}, new int[] {})));
		
		assertEquals(3, Math.abs(Abstand.abstand(new int[] {}, new int[] {1, 2})));
		assertEquals(3, Math.abs(Abstand.abstand(new int[] {1, 2}, new int[] {})));
		
		assertEquals(2, Math.abs(Abstand.abstand(new int[] {1}, new int[] {1,1,1})));
		assertEquals(7, Math.abs(Abstand.abstand(new int[] {2,3,4}, new int[] {2})));

		assertEquals(333, Math.abs(Abstand.abstand(new int[] {10, 20, 30, 300}, new int[] {11, 22})));
		assertEquals(333, Math.abs(Abstand.abstand(new int[] {11, 22}, new int[] {10, 20, 30, 300})));
	}

	@Test
	public void testAbstandDifferentLengthsEnsureAbs() {
		// same as testAbstandDifferentLengthsWithoutAbs, but abs() is checked for
		assertEquals(1, Abstand.abstand(new int[] {}, new int[] {1}));
		assertEquals(1, Abstand.abstand(new int[] {1}, new int[] {}));
		
		assertEquals(3, Abstand.abstand(new int[] {}, new int[] {1, 2}));
		assertEquals(3, Abstand.abstand(new int[] {1, 2}, new int[] {}));
		
		assertEquals(2, Abstand.abstand(new int[] {1}, new int[] {1,1,1}));
		assertEquals(7, Abstand.abstand(new int[] {2,3,4}, new int[] {2}));

		assertEquals(333, Abstand.abstand(new int[] {10, 20, 30, 300}, new int[] {11, 22}));
		assertEquals(333, Abstand.abstand(new int[] {11, 22}, new int[] {10, 20, 30, 300}));
	}
}
