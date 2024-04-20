import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.*;

import org.junit.jupiter.api.Test;

public class SeqAnalyzerTest {
	
	@Test
	public void testNumbersSimple() {
		Scanner scanner = new Scanner("1 3 2 9 2 45");
		SeqAnalyzer analyzer = new SeqAnalyzer(scanner);
		
		List<SeqNumber> numbers = analyzer.getNumbers();
		assertEquals(5, numbers.size());
		
		assertContainsNumber(1, Set.of(0), numbers);
		assertContainsNumber(2, Set.of(2,4), numbers);
		assertContainsNumber(3, Set.of(1), numbers);
		assertContainsNumber(9, Set.of(3), numbers);
		assertContainsNumber(45, Set.of(5), numbers);			
	}
	
	@Test
	public void testRankingSimple() {
		Scanner scanner = new Scanner("1 2 3 2 4 5 4 2 2 6");

		SeqAnalyzer analyzer = new SeqAnalyzer(scanner);
		List<SeqNumber> rankedNumbers = analyzer.getRanking(2);

		assertEquals(2, rankedNumbers.size());
		
		assertEquals(4, rankedNumbers.get(0).getNumber());
		assertEquals(2, rankedNumbers.get(1).getNumber());
		
		assertEquals(Set.of(4, 6), rankedNumbers.get(0).getPositions());
		assertEquals(Set.of(1, 3, 7, 8), rankedNumbers.get(1).getPositions());	
	}
	
	@Test
	public void testNotEnoughNumbers() {
		Scanner scanner = new Scanner("1 2 1");
		
		SeqAnalyzer analyzer = new SeqAnalyzer(scanner);
		assertThrows(IllegalArgumentException.class, () -> {
			analyzer.getRanking(3);
		});
	}
	
    /** Tested ob 'expectedNumber' mit den Positionen 'expectedPositions' in der Liste 'numbers' vorkommt, wobei 'expectedNumer'
     *  hoechstens ein Mal in der Liste vorkommen darf. */
    public static void assertContainsNumber(int expectedNumber, Set<Integer> expectedPositions, List<SeqNumber> numbers) {
    	Set<Integer> foundPositions = null;
    	
    	for (SeqNumber number : numbers) {
    		if (number.getNumber() == expectedNumber) {
    			assertTrue(foundPositions == null);
    			foundPositions = number.getPositions();
    		}
    	}
    	
    	assertEquals(expectedPositions, foundPositions);
    }

}
