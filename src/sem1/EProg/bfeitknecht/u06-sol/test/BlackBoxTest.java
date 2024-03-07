import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class BlackBoxTest {

	static private int[] createRangeArray(int start, int end) {
		int size = end - start;
		int[] res = new int[size];
		for (int i = 0; i < size; i++) {
			res[i] = start + i;
		}

		return res;
	}

	@Test
	public void testRotateArray() {
		// null
		BlackBox.rotateArray(null, 0);
		BlackBox.rotateArray(null, 1);
		BlackBox.rotateArray(null, -1);
		
		// empty
		int[] expected = new int[] {};
		int[] values = new int[] {};
		BlackBox.rotateArray(values, 0);
		assertArrayEquals(expected, values);

		// 1-elem
		expected = new int[] { 1 };
		values = new int[] { 1 };
		BlackBox.rotateArray(values, 0);
		assertArrayEquals(expected, values);
		BlackBox.rotateArray(values, 1);
		assertArrayEquals(expected, values);
		BlackBox.rotateArray(values, -1);
		assertArrayEquals(expected, values);

		// 2-elem
		expected = new int[] { 1, 2 };
		values = new int[] { 1, 2 };
		BlackBox.rotateArray(values, 0);
		assertArrayEquals(expected, values);
		BlackBox.rotateArray(values, 2);
		assertArrayEquals(expected, values);
		BlackBox.rotateArray(values, -2);
		assertArrayEquals(expected, values);

		expected = new int[] { 2, 1 };
		values = new int[] { 1, 2 };
		BlackBox.rotateArray(values, 1);
		assertArrayEquals(expected, values);
		values = new int[] { 1, 2 };
		BlackBox.rotateArray(values, -1);
		assertArrayEquals(expected, values);

		expected = new int[] {4, 1, 2, 3 };
		values = new int[] { 1, 2, 3, 4 };
		BlackBox.rotateArray(values, 1);
		assertArrayEquals(expected, values);
		
		// general
		int[] rotations = createRangeArray(-42, 43);
		for (int n = 0; n < 13; n++) {
			for (int steps : rotations) {
				expected = createRangeArray(-1, n - 1);
				values = expected.clone();
				BlackBox.rotateArray(values, steps);
				for (int i = 0; i < n; i++)
					assertEquals(expected[((i - steps) % n + n) % n], values[i]);
			}
		}
		
		// special
		expected = new int[] { 1, 2 };
		values = new int[] { 2, 1 };
		BlackBox.rotateArray(values, Integer.MAX_VALUE);
		assertArrayEquals(expected, values);
		
		expected = new int[] { 1, 2 };
		values = new int[] { 1, 2 };
		BlackBox.rotateArray(values, Integer.MIN_VALUE);
		assertArrayEquals(expected, values);
	}

}
