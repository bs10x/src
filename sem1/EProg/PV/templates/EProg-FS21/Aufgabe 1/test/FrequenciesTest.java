import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.*;


public class FrequenciesTest {
	
	/** assignmentPossible **/
	@Test
	public void testAssignmentPossible1() {		
		ArrayList<Coordinate> stations = new ArrayList<Coordinate>();
		stations.add(new Coordinate(0.0, 0.0));
		stations.add(new Coordinate(0.0, 2.0));
		
			
		Frequencies freq = new Frequencies(stations, 1.0);
		assertTrue(freq.assignmentPossible(1));
		assertTrue(freq.assignmentPossible(2));	
	}
	
	@Test
	public void testAssignmentPossible2() {
		ArrayList<Coordinate> stations = new ArrayList<Coordinate>();
		stations.add(new Coordinate(0.0, 0.0));
		stations.add(new Coordinate(0.0, 2.0));
		
			
		Frequencies freq = new Frequencies(stations, 3.0);
		assertFalse(freq.assignmentPossible(1));
		assertTrue(freq.assignmentPossible(2));
	}
	
	@Test
	public void testAssignmentPossible3() {
		ArrayList<Coordinate> stations = new ArrayList<Coordinate>();
		stations.add(new Coordinate(0.0, 4.0));
		stations.add(new Coordinate(1.0, 1.0));
		stations.add(new Coordinate(3.0, 3.0));
		stations.add(new Coordinate(4.5, 4.0));
		stations.add(new Coordinate(5.5, 2.5));
		
		Frequencies freq = new Frequencies(stations, 2.0);
		
		assertFalse(freq.assignmentPossible(1));
		assertTrue(freq.assignmentPossible(2));			
	}
	
	/** minFrequencies **/	
	@Test
	public void testMinFrequencies1() {
		ArrayList<Coordinate> stations = new ArrayList<Coordinate>();
		stations.add(new Coordinate(0.0, 0.0));
		stations.add(new Coordinate(0.0, 1.0));
		stations.add(new Coordinate(0.0, 2.0));
			
		Frequencies freq = new Frequencies(stations, 3.0);
		assertEquals(3, freq.minFrequencies());
	}
	
	@Test
	public void testMinFrequencies2() {
		ArrayList<Coordinate> stations = new ArrayList<Coordinate>();
		stations.add(new Coordinate(0.0, 4.0));
		stations.add(new Coordinate(1.0, 1.0));
		stations.add(new Coordinate(3.0, 3.0));
		stations.add(new Coordinate(4.5, 4.0));
		stations.add(new Coordinate(5.5, 2.5));		
		
		Frequencies freq = new Frequencies(stations, 2.0);		
		assertEquals(2, freq.minFrequencies());	
	}
	
	
	/** mostProblematicStations **/	
	@Test
	public void testMostProblematicStations() {
		ArrayList<Coordinate> stations = new ArrayList<Coordinate>();
		
		stations.add(new Coordinate(1.5, 4.0));
		stations.add(new Coordinate(3.0, 1.5));
		stations.add(new Coordinate(3.0, 3.0));
		stations.add(new Coordinate(4.5, 1.0));
		stations.add(new Coordinate(4.5, 4.0));
		
		Frequencies freq = new Frequencies(stations, 2.0);
		
		/* Konfliktzahlen der Standorte:
		 *  (1.5, 4.0): 1 (moegliche Interferenz mit (3.0, 3.0))
		 *  (3.0, 1.5): 2 (moegliche Interferenzen mit (3.0, 3.0), (4.5, 1.0))
		 *  (3.0, 3.0): 3 (moegliche Interferenzen mit (1.5, 4.0), (3.0, 1.5), (4.5, 4.0))
		 *  (4.5, 1.0): 1 (moegliche Interferenz mit (3.0, 1.5))
		 *  (4.5, 4.0): 1 (moegliche Interferenz mit (3.0, 3.0))   
		 */
		
		List<Coordinate> result = freq.mostProblematicStations(2);
		assertNotNull(result);
		assertEquals(2, result.size());
		
		assertEquals(3.0, result.get(0).getX());
		assertEquals(3.0, result.get(0).getY());
		
		assertEquals(3.0, result.get(1).getX());
		assertEquals(1.5, result.get(1).getY());
	}

}
