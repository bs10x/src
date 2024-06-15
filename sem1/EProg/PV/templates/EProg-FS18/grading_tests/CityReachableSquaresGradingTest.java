import static java.util.Arrays.asList;
import static java.util.concurrent.TimeUnit.SECONDS;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import java.util.HashSet;
import java.util.Set;

import org.junit.Rule;
import org.junit.Test;
public class CityReachableSquaresGradingTest {

	@Test
	public void testIllegalArguments() {
		City c = new City();
		c.intersections.add(CityDeadEndsGradingTest.newSquare("ABC"));
		c.intersections.add(CityDeadEndsGradingTest.newSquare("DEF"));
		
		// don't always throw
		c.reachableSquares("ABC", 0);
		
		try {
			c.reachableSquares("XYZ", 0);
			fail();
		} catch (IllegalArgumentException e) {}
		
		try {
			c.reachableSquares("ABC", -1);
			fail();
		} catch (IllegalArgumentException e) {}
	}

	@Test
	public void testSimplyFindSquareDist0() {
		City c = new City();
		c.intersections.add(CityDeadEndsGradingTest.newSquare("A"));
		c.intersections.add(CityDeadEndsGradingTest.newSquare("B"));
		c.intersections.add(CityDeadEndsGradingTest.newSquare("C"));
		c.intersections.add(CityDeadEndsGradingTest.newSquare("D"));

		// find itself
		Set<Square> s = c.reachableSquares("A", 0);
		assertSize(1, s);
		assertEquals("A", s.iterator().next().name);
		
		s = c.reachableSquares("B", 0);
		assertSize(1, s);
		assertEquals("B", s.iterator().next().name);
		
		s = c.reachableSquares("C", 0);
		assertSize(1, s);
		assertEquals("C", s.iterator().next().name);
		
		s = c.reachableSquares("D", 0);
		assertSize(1, s);
		assertEquals("D", s.iterator().next().name);
	}

	@Test
	public void testInwardsStar() {
		Square a = CityDeadEndsGradingTest.newSquare("A");
		Square b = CityDeadEndsGradingTest.newSquare("B");
		Square c = CityDeadEndsGradingTest.newSquare("C");
		Square d = CityDeadEndsGradingTest.newSquare("D");
		City simple = new City();
		simple.intersections.add(a);
		simple.intersections.add(b);
		simple.intersections.add(c);
		simple.intersections.add(d);
		CityDeadEndsGradingTest.connect1Way(simple, a, c, 2);
		CityDeadEndsGradingTest.connect1Way(simple, b, c, 3);
		CityDeadEndsGradingTest.connect1Way(simple, d, c, 5);

		assertSize(1, simple.reachableSquares("A", 1));
		assertSize(2, simple.reachableSquares("A", 2));
		assertSize(1, simple.reachableSquares("B", 2));
		assertSize(2, simple.reachableSquares("B", 3));
		assertSize(1, simple.reachableSquares("D", 4));
		assertSize(2, simple.reachableSquares("D", 5));
		assertSize(1, simple.reachableSquares("C", 100));
	}

	@Test
	public void testOutwardsStar() {
		Square a = CityDeadEndsGradingTest.newSquare("A");
		Square b = CityDeadEndsGradingTest.newSquare("B");
		Square c = CityDeadEndsGradingTest.newSquare("C");
		Square d = CityDeadEndsGradingTest.newSquare("D");
		City simple = new City();
		simple.intersections.add(a);
		simple.intersections.add(b);
		simple.intersections.add(c);
		simple.intersections.add(d);
		CityDeadEndsGradingTest.connect1Way(simple, c, a, 1);
		CityDeadEndsGradingTest.connect1Way(simple, c, b, 2);
		CityDeadEndsGradingTest.connect1Way(simple, c, d, 3);

		assertSize(1, simple.reachableSquares("A", 10));
		assertSize(1, simple.reachableSquares("B", 10));
		assertSize(1, simple.reachableSquares("D", 10));
		assertSize(1, simple.reachableSquares("C", 0));
		assertSize(2, simple.reachableSquares("C", 1));
		assertSize(3, simple.reachableSquares("C", 2));
		assertSize(4, simple.reachableSquares("C", 3));
	}

	@Test
	public void testLinearMultiHop() {
		Square a = CityDeadEndsGradingTest.newSquare("A");
		Square b = CityDeadEndsGradingTest.newSquare("B");
		Square c = CityDeadEndsGradingTest.newSquare("C");
		Square d = CityDeadEndsGradingTest.newSquare("D");
		City simple = new City();
		simple.intersections.add(a);
		simple.intersections.add(b);
		simple.intersections.add(c);
		simple.intersections.add(d);
		CityDeadEndsGradingTest.connect1Way(simple, a, b, 1);
		CityDeadEndsGradingTest.connect1Way(simple, b, c, 2);
		CityDeadEndsGradingTest.connect1Way(simple, c, d, 3);

		assertSize(1, simple.reachableSquares("A", 0));
		assertSize(2, simple.reachableSquares("A", 1));
		assertSize(2, simple.reachableSquares("A", 2));
		assertSize(3, simple.reachableSquares("A", 3));
		assertSize(4, simple.reachableSquares("A", 6));
	}

	@Test
	public void testMultiHopSplit() {
		Square a = CityDeadEndsGradingTest.newSquare("A");
		Square b = CityDeadEndsGradingTest.newSquare("B");
		Square c = CityDeadEndsGradingTest.newSquare("C");
		Square d = CityDeadEndsGradingTest.newSquare("D");
		Square e = CityDeadEndsGradingTest.newSquare("E");
		City simple = new City();
		simple.intersections.add(a);
		simple.intersections.add(b);
		simple.intersections.add(c);
		simple.intersections.add(d);
		simple.intersections.add(e);
		CityDeadEndsGradingTest.connect1Way(simple, a, b, 1);
		CityDeadEndsGradingTest.connect1Way(simple, b, c, 2);
		CityDeadEndsGradingTest.connect1Way(simple, c, d, 3);
		CityDeadEndsGradingTest.connect1Way(simple, c, e, 3);

		assertSize(1, simple.reachableSquares("A", 0));
		assertSize(2, simple.reachableSquares("A", 1));
		assertSize(2, simple.reachableSquares("A", 2));
		assertSize(3, simple.reachableSquares("A", 3));
		assertSize(5, simple.reachableSquares("A", 6));
	}

	@Test
	public void testSquaresReachableWithinZueri() {
		City zueri = CityDeadEndsGradingTest.buildZueri();
		assertEquals(asSet("Bahnhofplatz"),
				names(zueri.reachableSquares("Bahnhofplatz", 0)));
		assertEquals(asSet("Bahnhofplatz"),
				names(zueri.reachableSquares("Bahnhofplatz", 1)));
		
		assertEquals(asSet("Bahnhofplatz", "Central"),
				names(zueri.reachableSquares("Bahnhofplatz", 2)));
		
		assertEquals(asSet("Sihlpost"),
				names(zueri.reachableSquares("Sihlpost", 0)));
		assertEquals(asSet("Sihlpost"),
				names(zueri.reachableSquares("Sihlpost", 1000)));
		
		assertEquals(asSet("Polyterrasse", "Central", "Bahnhofplatz"),
				names(zueri.reachableSquares("Polyterrasse", 20)));

		assertEquals(asSet("Sihlpost", "Bahnhofplatz", "Stampfenbachplatz",
				"Central", "Polyterrasse"),
				names(zueri.reachableSquares("Stampfenbachplatz", 100)));
	}

	private static void assertSize(int expected, Set<?> set) {
		assertNotNull(set);
		assertEquals(expected, set.size());
	}

	private static Set<String> asSet(String... elements) {
		return new HashSet<>(asList(elements));
	}

	private static Set<String> names(Set<Square> squares) {
		assertNotNull(squares);
		Set<String> result = new HashSet<>();
		for (Square s : squares) {
			result.add(s.name);
		}
		return result;
	}
}
