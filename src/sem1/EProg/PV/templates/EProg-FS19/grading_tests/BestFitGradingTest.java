import static org.junit.Assert.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Test;

public class BestFitGradingTest {

	@Test
	public void testGiven1() {
		List<Integer> res = BestFit.bestFit(Set.of(3, 4, 7), 6);
		assertNotNull(res);
		assertEquals(2, new HashSet<>(res).size());
		assertAverageEquals(6, res);
	}

	@Test
	public void testGiven2() {
		List<Integer> res = BestFit.bestFit(Set.of(0, 3, 6, 10), 5);
		assertNotNull(res);
		assertEquals(2, new HashSet<>(res).size());
		assertAverageEquals(5, res);
	}

	public void assertNotUnimplemented() {
		assertNotNull(BestFit.bestFit(Set.of(4, 5, 6), 5));
	}
	
	@Test
	public void testSingleValue() {
		assertNull(BestFit.bestFit(Set.of(5), 1000));
		assertNull(BestFit.bestFit(Set.of(5), 1));
		List<Integer> res = BestFit.bestFit(Set.of(4), 4);
		assertNotNull(res);
		assertEquals(1, new HashSet<>(res).size());
		assertEquals(4, (int) res.get(0));
	}

	@Test
	public void testExact() {
		List<Integer> res = BestFit.bestFit(Set.of(0, 5, 7, 10, 1000), 5);
		assertNotNull(res);
		assertEquals(1, new HashSet<>(res).size());
		assertEquals(5, (int) res.get(0));
	}

	@Test
	public void testNoSolutionLow() {
		assertNotUnimplemented();
		assertNull(BestFit.bestFit(Set.of(5, 7, 10, 1000), 4));
	}
	
	@Test
	public void testNoSolutionHigh() {
		assertNotUnimplemented();
		assertNull(BestFit.bestFit(Set.of(5, 7, 10), 1000));
	}
	
	@Test
	public void testNoSolutionEmpty() {
		assertNotUnimplemented();
		assertNull(BestFit.bestFit(Set.of(), 1000));
	}
	
	@Test
	public void testSolvedButMaybeNotMinimal() {
		List<Integer> res = BestFit.bestFit(Set.of(0, 5, 7, 10, 1000), 5);
		assertNotNull(res);
		assertAverageEquals(5, res);
		
		res = BestFit.bestFit(Set.of(0, 5, 7, 10, 1000), 8);
		assertNotNull(res);
		assertAverageEquals(8, res);
		
		res = BestFit.bestFit(Set.of(0, 5, 7, 10), 3);
		assertNotNull(res);
		assertAverageEquals(3, res);
		
		res = BestFit.bestFit(Set.of(0, 5, 10), 7);
		assertNotNull(res);
		assertAverageEquals(7, res);
	}
	
	@Test
	public void testSolvedMinimal() {
		List<Integer> res = BestFit.bestFit(Set.of(0, 5, 7, 10, 1000), 8);
		assertNotNull(res);
		assertEquals(2, new HashSet<>(res).size());
		assertAverageEquals(8, res);
		
		res = BestFit.bestFit(Set.of(0, 5, 7, 10), 3);
		assertNotNull(res);
		assertEquals(2, new HashSet<>(res).size());
		assertAverageEquals(3, res);
		
		res = BestFit.bestFit(Set.of(0, 5, 10), 7);
		assertNotNull(res);
		assertEquals(2, new HashSet<>(res).size());
		assertAverageEquals(7, res);
	}
	
	@Test
	public void testWithNegativeNumbers() {
		List<Integer> res = BestFit.bestFit(Set.of(-10, 5, 7), -10);
		assertNotNull(res);
		assertEquals(1, new HashSet<>(res).size());
		assertAverageEquals(-10, res);
		
		res = BestFit.bestFit(Set.of(-10, 5, 7), -1);
		assertNotNull(res);
		assertEquals(2, new HashSet<>(res).size());
		assertAverageEquals(-1, res);
		
		res = BestFit.bestFit(Set.of(-10, -5, -7), -9);
		assertNotNull(res);
		assertEquals(2, new HashSet<>(res).size());
		assertAverageEquals(-9, res);
		
		assertNull(BestFit.bestFit(Set.of(-10, -5, -7), -1));
		assertNull(BestFit.bestFit(Set.of(-10, -5, -7), -11));
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
