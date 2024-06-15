import static org.junit.Assert.*;

import java.util.Map;

import org.junit.Test;

public class InspektorTest {

	@Test
	public void testGefahrenStufeExample() {
		Inspektor insp = new Inspektor();
		insp.eintrag("Stall-1  0.5  0.1667 XYZ");
		assertEquals("OK", insp.gefahrenStufe());
		insp.eintrag("Stall-2  -1");
		assertEquals("PANDEMIE", insp.gefahrenStufe());
		insp.eintrag("Stall-2  0.7  0.15   ABC");
		assertEquals("PANDEMIE", insp.gefahrenStufe());
		insp.eintrag("Stall-3  0.7  0.185  ABC");
		assertEquals("EPIDEMIE", insp.gefahrenStufe());
	}
	
	@Test
	public void testBesteStaelleExample() {
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
}
