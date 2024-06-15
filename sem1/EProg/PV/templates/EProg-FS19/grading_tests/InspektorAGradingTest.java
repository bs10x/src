import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class InspektorAGradingTest {

	@Test
	public void testGivenGefahrenStufeExample() {
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
	public void testGefahrenStufeEmpty() {
		Inspektor insp = new Inspektor();
		assertEquals("OK", insp.gefahrenStufe());
	}
	
	@Test
	public void testGefahrenStufeOnlyParasites() {
		Inspektor insp = new Inspektor();
		insp.eintrag("Stall1  -1");
		insp.eintrag("Stall2  -1");
		insp.eintrag("Stall3  -1");
		assertEquals("PANDEMIE", insp.gefahrenStufe());
	}
	
	@Test
	public void testGefahrenStufeNormalOrdering() {
		Inspektor insp = new Inspektor();
		insp.eintrag("Stall1  -1");
		insp.eintrag("Stall2  -1");
		insp.eintrag("Stall3  -1");
		insp.eintrag("Stall1  0.5  0.1667 XYZ");
		insp.eintrag("Stall2  0.5  0.1667 XYZ");
		insp.eintrag("Stall3  0.5  0.1667 XYZ");
		assertEquals("PANDEMIE", insp.gefahrenStufe());
		
		insp = new Inspektor();
		insp.eintrag("Stall1  0.5  0.1667 XYZ");
		insp.eintrag("Stall2  0.5  0.1667 XYZ");
		insp.eintrag("Stall3  0.5  0.1667 XYZ");
		assertEquals("OK", insp.gefahrenStufe());
		insp.eintrag("Stall1  -1");
		insp.eintrag("Stall2  -1");
		insp.eintrag("Stall3  -1");
		assertEquals("PANDEMIE", insp.gefahrenStufe());
	}
	
	@Test
	public void testGefahrenStufeLevels() {
		Inspektor insp = new Inspektor();
		insp.eintrag("Stall0  0.5  0.1667 XYZ");
		insp.eintrag("Stall1  0.5  0.1667 XYZ");
		insp.eintrag("Stall2  0.5  0.1667 XYZ");
		insp.eintrag("Stall3  0.5  0.1667 XYZ");
		insp.eintrag("Stall4  0.5  0.1667 XYZ");
		insp.eintrag("Stall5  0.5  0.1667 XYZ");
		insp.eintrag("Stall6  0.5  0.1667 XYZ");
		insp.eintrag("Stall7  0.5  0.1667 XYZ");
		insp.eintrag("Stall8  0.5  0.1667 XYZ");
		insp.eintrag("Stall9  0.5  0.1667 XYZ");
		assertEquals("OK", insp.gefahrenStufe());
		insp.eintrag("Stall1  -1");
		assertEquals("OK", insp.gefahrenStufe());
		insp.eintrag("Stall2  -1");
		assertEquals("OK", insp.gefahrenStufe());
		insp.eintrag("Stall3  -1");
		assertEquals("EPIDEMIE", insp.gefahrenStufe());
		insp.eintrag("Stall4  -1");
		assertEquals("EPIDEMIE", insp.gefahrenStufe());
		insp.eintrag("Stall5  -1");
		assertEquals("PANDEMIE", insp.gefahrenStufe());
	}
}
