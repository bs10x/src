import static java.util.Arrays.asList;
import static java.util.concurrent.TimeUnit.SECONDS;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import java.util.Set;

import org.junit.Rule;
import org.junit.Test;

public class CityDeadEndsGradingTest {

	@Test
	public void testDeadEndsZueri() {
		City zueri = buildZueri();
		Set<Street> dead = zueri.deadEnds();
		
		assertSize(3, dead);
		for (Street street : dead) {
			if ((street.to instanceof Square) 
					&& ((Square)street.to).name.equals("Sihlpost")) {
				// X ------> [Sihlpost]
			} else if ((street.from instanceof Square) 
					&& ((Square)street.from).name.equals("Polyterrasse")) {
				// [Polyterrasse] ---> X
			} else if ((street.from instanceof Square) 
					&& ((Square)street.from).name.equals("Bahnhofplatz")) {
				// [Bahnhofplatz] -----> X
			} else {
				fail();
			}
		}
	}

	@Test
	public void testTrivial() {
		Square a = newSquare("A");
		City simple = new City();
		simple.intersections.add(a);
		assertSize(0, simple.deadEnds());
		
		simple = new City();
		assertSize(0, simple.deadEnds());
	}

	@Test
	public void testSimpleDeadEnd() {
		Square a = newSquare("A");
		Square b = newSquare("B");
		City simple = new City();
		simple.intersections.add(a);
		simple.intersections.add(b);
		connect1Way(simple, a, b, 3);
		
		Set<Street> dead = simple.deadEnds();
		
		assertSize(1, dead);
		Street x = dead.iterator().next();
		assertEquals(a, x.from);
		assertEquals(b, x.to);
	}

	@Test
	public void testSimpleNonDeadEnd() {
		Square a = newSquare("A");
		Square b = newSquare("B");
		City simple = new City();
		simple.intersections.add(a);
		simple.intersections.add(b);
		connect2Way(simple, a, b, 3);
		
		assertSize(0, simple.deadEnds());
	}

	@Test
	public void testStarInwardsAllDeadEnd() {
		Square a = newSquare("A");
		Square b = newSquare("B");
		Square c = newSquare("C");
		Square d = newSquare("D");
		City simple = new City();
		simple.intersections.add(a);
		simple.intersections.add(b);
		simple.intersections.add(c);
		simple.intersections.add(d);
		connect1Way(simple, a, c, 1);
		connect1Way(simple, b, c, 1);
		connect1Way(simple, d, c, 1);
		
		assertSize(3, simple.deadEnds());
	}

	@Test
	public void testStarAllDeadEnd() {
		Square a = newSquare("A");
		Square b = newSquare("B");
		Square c = newSquare("C");
		Square d = newSquare("D");
		City simple = new City();
		simple.intersections.add(a);
		simple.intersections.add(b);
		simple.intersections.add(c);
		simple.intersections.add(d);
		connect1Way(simple, c, a, 1);
		connect1Way(simple, c, b, 1);
		connect1Way(simple, c, d, 1);
		
		assertSize(3, simple.deadEnds());
	}

	@Test
	public void testOneIterDeadEnd() {
		Square a = newSquare("A");
		Square b = newSquare("B");
		Square c = newSquare("C");
		City simple = new City();
		simple.intersections.add(a);
		simple.intersections.add(b);
		simple.intersections.add(c);
		connect1Way(simple, a, b, 3);
		connect1Way(simple, b, c, 4);
		
		assertSize(2, simple.deadEnds());
	}

	@Test
	public void testTwoIterDeadEnd() {
		Square a = newSquare("A");
		Square b = newSquare("B");
		Square c = newSquare("C");
		Square d = newSquare("D");
		City simple = new City();
		simple.intersections.add(a);
		simple.intersections.add(b);
		simple.intersections.add(c);
		connect1Way(simple, a, b, 3);
		connect1Way(simple, b, c, 4);
		connect1Way(simple, c, d, 5);
		
		assertSize(3, simple.deadEnds());
	}

	@Test
	public void testOneIterNoDeadEnd() {
		Square a = newSquare("A");
		Square b = newSquare("B");
		Square c = newSquare("C");
		City simple = new City();
		simple.intersections.add(a);
		simple.intersections.add(b);
		simple.intersections.add(c);
		connect2Way(simple, a, b, 3);
		connect1Way(simple, b, c, 4);
		
		Set<Street> dead = simple.deadEnds();
		assertSize(1, dead);
		Street x = dead.iterator().next();
		assertEquals(b, x.from);
		assertEquals(c, x.to);
	}

	static Square newSquare(String name) {
		Square res = new Square();
		res.name = name;
		return res;
	}

	static Street newStreet(Intersection from, Intersection to, int length) {
		Street res = new Street();
		res.from = from;
		res.to = to;
		res.length = length;
		from.outgoingStreets.add(res);
		return res;
	}

	static void connect1Way(City c, Intersection from, Intersection to, int length) {
		c.streets.add(newStreet(from, to, length));
	}

	private static void connect2Way(City c, Intersection i1, Intersection i2, int length) {
		c.streets.add(newStreet(i1, i2, length));
		c.streets.add(newStreet(i2, i1, length));
	}

	static City buildZueri() {
		City zueri = new City();
		
		Intersection sihlpost = newSquare("Sihlpost");
		Intersection postbruecke = new Intersection();
		Intersection loewen = newSquare("Loewenplatz");
		Intersection bahnhof = newSquare("Bahnhofplatz");
		Intersection central = newSquare("Central");
		Intersection stampfen = newSquare("Stampfenbachplatz");
		Intersection haldeneck = new Intersection();
		Intersection poly = newSquare("Polyterrasse");
		Intersection uni = new Intersection();
		
		zueri.intersections.addAll(asList(sihlpost, postbruecke,
				loewen, bahnhof, central, stampfen,
				haldeneck, poly, uni));
		
		connect1Way(zueri, postbruecke, sihlpost, 6);
		connect1Way(zueri, bahnhof, postbruecke, 5);
		connect1Way(zueri, loewen, bahnhof, 6);
		connect2Way(zueri, bahnhof, stampfen, 10);
		connect2Way(zueri, bahnhof, central, 2);
		connect1Way(zueri, central, stampfen, 9);
		connect2Way(zueri, central, haldeneck, 7);
		connect2Way(zueri, haldeneck, poly, 8);
		connect1Way(zueri, poly, uni, 3);
		
		return zueri;
	}

	private static void assertSize(int expected, Set<?> set) {
		assertNotNull(set);
		assertEquals(expected, set.size());
	}
}
