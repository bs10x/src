import static org.junit.Assert.*;

import java.util.Map;

import org.junit.Test;

public class InspektorBGradingTest {

	@Test
	public void testGivenBesteStaelleExample() {
		Inspektor insp = new Inspektor();
		insp.eintrag("Stall-1  0.5  0.1667 XYZ");
		assertEquals(Map.of("XYZ", "Stall-1"), insp.besteStaelle());
		insp.eintrag("Stall-2  0.7  0.15   ABC");
		assertEquals(Map.of("XYZ", "Stall-1", "ABC", "Stall-2"), insp.besteStaelle());
		insp.eintrag("Stall-2  -1");
		assertEquals(Map.of("XYZ", "Stall-1"), insp.besteStaelle());
		insp.eintrag("Stall-3  0.7  0.185  ABC");
		assertEquals(Map.of("XYZ", "Stall-1", "ABC", "Stall-3"), insp.besteStaelle());
	}

	@Test
	public void testBesteStaelleOnlyParasites() {
		Inspektor insp = new Inspektor();
		insp.eintrag("Stall-2  -1");
		Map<String, String> staelle = insp.besteStaelle();
		assertNotNull(staelle);
		assertTrue(staelle.isEmpty());
		insp.eintrag("Stall-3  -1");
		staelle = insp.besteStaelle();
		assertNotNull(staelle);
		assertTrue(staelle.isEmpty());
	}

	@Test
	public void testBesteStaelleEmpty() {
		Inspektor insp = new Inspektor();
		Map<String, String> staelle = insp.besteStaelle();
		assertNotNull(staelle);
		assertTrue(staelle.isEmpty());
	}

	@Test
	public void testBesteStaelleFindsBest() {
		Inspektor insp = new Inspektor();
		insp.eintrag("Stall-1  0.5  0.1667 XYZ");
		insp.eintrag("Stall-2  0.7  0.15   ABC");
		assertEquals(Map.of("XYZ", "Stall-1", "ABC", "Stall-2"), insp.besteStaelle());
		insp.eintrag("Stall-3  0.7  0.185  ABC");
		assertEquals(Map.of("XYZ", "Stall-1", "ABC", "Stall-2"), insp.besteStaelle());
		insp.eintrag("Stall-4  0.7  0.14  ABC");
		assertEquals(Map.of("XYZ", "Stall-1", "ABC", "Stall-4"), insp.besteStaelle());
	}
}
