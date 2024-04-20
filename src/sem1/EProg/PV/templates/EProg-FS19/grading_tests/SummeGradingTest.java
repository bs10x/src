import static org.junit.Assert.*;

import org.junit.Test;

public class SummeGradingTest {

	@Test
	public void testNo7() {
		assertValidPair(5, Summe.ohne7(5));
		assertValidPair(8, Summe.ohne7(8));
		assertValidPair(20, Summe.ohne7(20));
		assertValidPair(200, Summe.ohne7(200));
	}

	@Test
	public void testSimple7() {
		assertValidPair(17, Summe.ohne7(17));
		assertValidPair(171, Summe.ohne7(171));
		assertValidPair(700, Summe.ohne7(700));
	}

	@Test
	public void testMultiple7() {
		assertValidPair(77, Summe.ohne7(77));
		assertValidPair(717, Summe.ohne7(717));
		assertValidPair(171717, Summe.ohne7(171717));
	}

	@Test
	public void testMany7() {
		assertValidPair(777, Summe.ohne7(777));
		assertValidPair(7777, Summe.ohne7(7777));
		assertValidPair(6777, Summe.ohne7(6777));
		assertValidPair(7778, Summe.ohne7(7778));
		assertValidPair(77777, Summe.ohne7(77777));
	}

	@Test
	public void testSpecial() {
		assertValidPair(1, Summe.ohne7(1));
		assertValidPair(2, Summe.ohne7(2));
		assertValidPair(6, Summe.ohne7(6));
		assertValidPair(7, Summe.ohne7(7));
	}

	@Test
	public void testStress() {
		for (int s = 1; s < 99; s++) {
			Pair res = Summe.ohne7(s);
			assertValidPair(s, res);
		}

		for (int s = 2048; s < 2100; s++) {
			Pair res = Summe.ohne7(s);
			assertValidPair(s, res);
		}

		for (int s = 67000; s < 67099; s++) {
			Pair res = Summe.ohne7(s);
			assertValidPair(s, res);
		}
	}

	private static void assertValidPair(int sum, Pair p) {
		assertNotNull(p);
		assertNo7(p.first);
		assertNo7(p.second);
		assertEquals(sum, p.first + p.second);
	}

	private static void assertNo7(int num) {
		assertFalse((num + "").contains("7"));
	}

}
