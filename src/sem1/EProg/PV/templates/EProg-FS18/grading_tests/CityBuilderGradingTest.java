import static java.util.Arrays.asList;
import static java.util.Collections.emptyList;
import static java.util.Collections.sort;
import static java.util.concurrent.TimeUnit.SECONDS;
import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toSet;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.junit.Rule;
import org.junit.Test;

public class CityBuilderGradingTest {

	@Test
	public void testSimpleStats() {
		City city = CityBuilder.build();
		assertNothingNull(city);
		assertEquals(9, city.intersections.size());
		assertEquals(13, city.streets.size());

		long count = city.intersections.stream()
				.filter(i -> i instanceof Square)
				.count();
		assertEquals(6, count);
	}

	@Test
	public void testSquaresNamedCorrectly() {
		City city = CityBuilder.build();

		assertNothingNull(city);
		List<String> names = city.intersections.stream()
			.filter(s -> s instanceof Square)
			.map(s -> ((Square)s).name)
			.filter(n -> n != null)
			.sorted()
			.collect(toList());
		
		List<String> refNamePatterns = asList(
				"Sihlpost", "Loewenplatz",
				"Bahnhofplatz", "Stampfen-?bachplatz",
				"Central", "Polyterrasse");
		sort(refNamePatterns);

		assertEquals(refNamePatterns.size(), names.size());
		for(int i = 0; i < refNamePatterns.size(); i++) {
			String name = names.get(i);
			assertNotNull(name);
			assertMatches(refNamePatterns.get(i), name);
		}
	}

	@Test
	public void testSteetStats() {
		City city = CityBuilder.build();

		assertNothingNull(city);
		List<Integer> streetSet = city.streets.stream()
				.map(s -> s.length)
				.sorted()
				.collect(toList());

		List<Integer> refStreetSet = 
				asList(2, 2, 3, 5, 6, 6, 7, 7, 8, 8, 9, 10, 10);

		assertEquals(refStreetSet, streetSet);
	}
	
	@Test
	public void testSteetLengthSets() {
		// check that all sets of street lengths occur
		City city = CityBuilder.build();

		assertNothingNull(city);
		Set<List<Integer>> streetSets = city.intersections.stream()
				.map(i -> i.outgoingStreets.stream()
						.map(s -> s.length)
						.sorted()
						.collect(toList()))
				.collect(toSet());

		Set<List<Integer>> refStreetSets = new HashSet<>(asList(
				asList(6), asList(2, 5, 10), asList(10), asList(2, 7, 9),
				asList(7, 8), asList(3, 8), emptyList()));

		assertEquals(refStreetSets, streetSets);
	}
	
	@Test
	public void testCheckStructure() {
		City city = CityBuilder.build();

		assertNothingNull(city);
		Square loew = findLoewPlatz(city);
		assertEquals("Loewenplatz", loew.name);
		Square bhf = (Square) followStreetWithLen(loew, 6);
		Intersection i = followStreetWithLen(bhf, 5);
		
		Square sihlpost = (Square) followStreetWithLen(i, 6);
		assertTrue(sihlpost.outgoingStreets.isEmpty());
		
		Square stampf = (Square) followStreetWithLen(bhf, 10);
		assertEquals(1, stampf.outgoingStreets.size());
		assertEquals(bhf, followStreetWithLen(stampf, 10));
		
		Square central = (Square) followStreetWithLen(bhf, 2);
		assertEquals(3, central.outgoingStreets.size());
		assertEquals(stampf, followStreetWithLen(central, 9));
		assertEquals(bhf, followStreetWithLen(central, 2));
		
		i = followStreetWithLen(central, 7);
		assertEquals(2, i.outgoingStreets.size());
		assertEquals(central, followStreetWithLen(i, 7));
		
		Square poly = (Square) followStreetWithLen(i, 8);
		assertEquals(2, poly.outgoingStreets.size());
		assertEquals(i, followStreetWithLen(poly, 8));
		
		i = followStreetWithLen(poly, 3);
		assertTrue(i.outgoingStreets.isEmpty());
	}
	
	private Intersection followStreetWithLen(Intersection intersect, int len) {
		for (Street street : intersect.outgoingStreets) {
			if (street.length == len) {
				assertEquals(intersect, street.from);
				return street.to;
			}
		}
		assertTrue(false);
		return null;
	}
	
	private boolean checkStreetLengths(Intersection intersect,
			List<Integer> lengths) {
		List<Integer> streetList = intersect.outgoingStreets.stream()
				.map(s -> s.length)
				.sorted()
				.collect(Collectors.toList());
		
		return lengths.equals(streetList);
	}

	private Square findLoewPlatz(City city) {
		// find Loewenplatz without depending on "name"
		Square loew = null;
		for (Intersection intersect : city.intersections) {
			if (intersect instanceof Square) {
				loew = (Square) intersect;
				if (loew.outgoingStreets.size() == 1) {
					if (checkStreetLengths(loew, Arrays.asList(6)))
						break; // found!
				}
				loew = null;
			}
		}
		assertNotNull(loew);
		return loew;
	}

	private void assertMatches(String expected, String actual) {
		if (!actual.matches(expected)) {
			throw new AssertionError("\"" + actual.replace("\n", "\\n") + "\" did not match");
		}
	}

	private static void assertNothingNull(City city) {
		assertNotNull(city);
		assertNotNull(city.intersections);
		assertNotNull(city.streets);
	}
}
