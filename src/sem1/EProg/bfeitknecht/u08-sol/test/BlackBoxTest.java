import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Random;
import java.util.Set;

import org.junit.jupiter.api.Test;


public class BlackBoxTest {

	@Test
	public void testSimple() {
		int[] elevations = new int[] { 3, 1, 2, 5, 3, 6, 5, 6 }; // .t.gTGT
		int[] result1 = new int[] { 3, 0, 3, 5, 5, -1, 5, -1 };
		int[] result2 = new int[] { 3, 0, 3, 5, 5, -1, 7, -1 };
		int[] dominators = BlackBox.dominators(elevations);
		assertTrue(Arrays.equals(dominators, result1) || Arrays.equals(dominators, result2));
	}

	@Test
	public void testReturnsNull() {
		/*
		 * Dieser Test schlägt fehl und zeigt somit, dass sich die gegebene BlackBox-Implementierung
		 * nicht an die Spezifikation hält. Die Beschreibung von 'dominators' verlangt, dass 'null'
		 * zurückgegeben wird, wenn 'null' als Argument übergeben wurde.
		 */
		assertEquals(null, BlackBox.dominators(null));
	}

	@Test
	public void testEmpty() {
		assertEquals(0, BlackBox.dominators(new int[0]).length);
	}

	@Test
	public void testSingleton() {
		for (int i = -10; i < 10; i++) {
			assertArrayEquals(new int[] { -1 }, BlackBox.dominators(new int[] { i }));
		}
	}

	@Test
	public void testSinglePeak() {
		assertArrayEquals(new int[] { 1, 2, 3, -1, 3, 4, 5 },
				BlackBox.dominators(new int[] { 0, 1, 2, 3, 2, 1, 0 }));
	}

	@Test
	public void testSinglePeakPlateau() {
		assertArrayEquals(new int[] { 1, 2, 3, -1, -1, -1, 5, 6, 7 },
				BlackBox.dominators(new int[] { 0, 1, 2, 3, 3, 3, 2, 1, 0 }));
	}

	@Test
	public void testSingleValley() {
		assertArrayEquals(new int[] { 6, 0, 1, 4, 5, 6, -1 },
				BlackBox.dominators(new int[] { 3, 2, 1, 0, 2, 3, 4 }));
	}

	@Test
	public void testTwoPeaks() {
		assertArrayEquals(new int[] { 1, 2, 4, 4, -1, 4, 5 },
				BlackBox.dominators(new int[] { 0, 1, 2, 1, 3, 1, 0 }));
	}

	@Test
	public void testTwoPeaksSameElevation() {
		int[] dominators = BlackBox.dominators(new int[] { 0, 1, 2, 1, 2, 1, 0 });
		assertTrue(Arrays.equals(new int[] { 1, 2, -1, 4, -1, 4, 5 }, dominators)
				|| Arrays.equals(new int[] { 1, 2, -1, 2, -1, 4, 5 }, dominators));
	}

	@Test
	public void testALot() {
		Random r = new Random(42);
		for (int length = 2; length < 1000; length++) {
			for (int rep = 0; rep < 10; rep++) {
				int[] elevations = new int[length];
				for (int i = 0; i < length; i++)
					elevations[i] = r.nextInt(20) - 5;

				int[] dominators = BlackBox.dominators(elevations);
				assertEquals(elevations.length, dominators.length);
				assertDominatorsExist(elevations, dominators);
				assertCorrectDominators(elevations, dominators);
			}
		}
	}

	void assertDominatorsExist(int[] elevations, int[] dominators) {
		int max = max(elevations);
		for (int i = 0; i < elevations.length; i++)
			if (elevations[i] == max)
				assertEquals(-1, dominators[i]);
			else
				assertTrue(dominators[i] >= 0 && dominators[i] < elevations.length);
	}

	void assertCorrectDominators(int[] elevations, int[] dominators) {
		for (int i = 0; i < elevations.length; i++) {
			int dominator = dominators[i];
			if (dominator > 0) {
				int diff = Math.abs(i - dominator);
				int min = Math.max(i - diff + 1, 0);
				int max = Math.min(i + diff - 1, elevations.length - 1);
				assertTrue(elevations[i] < elevations[dominator]);
				for (int j = min; j <= max; j++)
					assertTrue(elevations[j] < elevations[dominator]);
			}
		}
	}

	private int max(int[] values) {
		int max = Integer.MIN_VALUE;
		for (int i = 0; i < values.length; i++) {
			max = Math.max(max, values[i]);
		}
		return max;
	}
}
