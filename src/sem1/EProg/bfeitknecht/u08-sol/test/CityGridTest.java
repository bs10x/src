import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class CityGridTest {

	@Test
	public void testCityGrid() {
		CityGrid g = new CityGrid(3);
		int centreX = g.getCentreX();
		int centreY = g.getCentreY();
		assertEquals(1, centreX);
		assertEquals(1, centreY);
		
		assertTrue(g.isAtBorder(0, 0));
		assertTrue(g.isAtBorder(0, 1));
		assertTrue(g.isAtBorder(0, 2));
		assertTrue(g.isAtBorder(1, 0));
		assertFalse(g.isAtBorder(1, 1));
		assertTrue(g.isAtBorder(1, 2));
		assertTrue(g.isAtBorder(2, 0));
		assertTrue(g.isAtBorder(2, 1));
		assertTrue(g.isAtBorder(2, 2));
		
		assertEquals(4, g.unmarkedDirectionsFrom(centreX, centreY).length);
		assertEquals(2, g.unmarkedDirectionsFrom(0, 0).length);
		assertEquals(2, g.unmarkedDirectionsFrom(2, 2).length);
		assertEquals(2, g.unmarkedDirectionsFrom(2, 0).length);
		assertEquals(2, g.unmarkedDirectionsFrom(0, 2).length);
		assertEquals(3, g.unmarkedDirectionsFrom(1, 0).length);
		assertEquals(3, g.unmarkedDirectionsFrom(1, 2).length);
		assertEquals(3, g.unmarkedDirectionsFrom(0, 1).length);
		assertEquals(3, g.unmarkedDirectionsFrom(2, 1).length);
		
		g.mark(1, 0);
		assertEquals(3, g.unmarkedDirectionsFrom(centreX, centreY).length);
	}

}
