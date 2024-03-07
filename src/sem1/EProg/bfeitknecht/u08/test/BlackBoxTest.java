import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

public class BlackBoxTest {

	@Test
	public void testSimple() {
		int[] elevations = new int[] { 3, 1, 2, 5, 3, 6, 5, 6 };
		int[] result1 = new int[] { 3, 0, 3, 5, 5, -1, 5, -1 };
		int[] result2 = new int[] { 3, 0, 3, 5, 5, -1, 7, -1 };
		int[] dominators = BlackBox.dominators(elevations);
		assertTrue(Arrays.equals(dominators, result1) 
				|| Arrays.equals(dominators, result2));
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
}
