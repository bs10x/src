import static org.junit.Assert.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Test;

public class BestFitTest {

	@Test
	public void testExample1() {
		List<Integer> res = BestFit.bestFit(Set.of(3, 4, 7), 6);
		assertNotNull(res);
		assertEquals(2, new HashSet<>(res).size());
		assertAverageEquals(6, res);
	}

	@Test
	public void testExample2() {
		List<Integer> res = BestFit.bestFit(Set.of(0, 3, 6, 10), 5);
		assertNotNull(res);
		assertEquals(2, new HashSet<>(res).size());
		assertAverageEquals(5, res);
	}

	private static void assertAverageEquals(int durchschnitt, List<Integer> werte) {
		int sum = 0;
		for (int wert : werte) {
			sum += wert;
		}
		assertEquals(0, sum % werte.size());
		assertEquals(durchschnitt, sum / werte.size());
	}
}
