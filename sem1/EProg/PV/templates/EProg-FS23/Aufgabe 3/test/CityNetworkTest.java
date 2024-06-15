	
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;

public class CityNetworkTest {
	
	@Test
	public void transformTest01() {
		City c3 = new City(6, new HashSet<City>());
		City c2 = new City(3, new HashSet<City>(Set.of(c3)));
		City c1 = new City(8, new HashSet<City>(Set.of(c2)));
		
		CityNetwork.transform(c1, 4);
		
		//check
		
		assertEquals(Set.of(c2, c3), c1.oneWayStreets);
		assertEquals(Set.of(c3), c2.oneWayStreets);
		assertEquals(Set.of(), c3.oneWayStreets);
	}
		
	@Test
	public void transformTest02() {
		City [][] cs = network1();
				
		CityNetwork.transform(cs[0][0], 5);
		
		//check
	
		assertEquals(Set.of(cs[1][0], cs[1][1], cs[2][1], cs[3][0], cs[3][1]),
				     cs[0][0].oneWayStreets);
		
		assertEquals(Set.of(cs[2][0]), cs[1][0].oneWayStreets);
		assertEquals(Set.of(cs[2][1]), cs[1][1].oneWayStreets);
		
		assertEquals(Set.of(cs[3][0], cs[3][1]), cs[2][0].oneWayStreets);
		assertEquals(Set.of(cs[2][0], cs[3][0], cs[3][1]), cs[2][1].oneWayStreets);
		
		assertEquals(Set.of(cs[4][0], cs[4][1]), cs[3][0].oneWayStreets);
		assertEquals(Set.of(), cs[3][1].oneWayStreets);
		
		assertEquals(Set.of(), cs[4][0].oneWayStreets);
		assertEquals(Set.of(), cs[4][1].oneWayStreets);		
	}
	
	@Test
	public void transformTest03() {
		City [][] cs = network1();
		
		CityNetwork.transform(cs[0][0], 10);
		
		//check
	
		assertEquals(Set.of(cs[1][0], cs[1][1]), cs[0][0].oneWayStreets);
		
		assertEquals(Set.of(cs[2][0]), cs[1][0].oneWayStreets);
		assertEquals(Set.of(cs[2][1]), cs[1][1].oneWayStreets);
		
		assertEquals(Set.of(cs[3][0], cs[3][1]), cs[2][0].oneWayStreets);
		assertEquals(Set.of(cs[2][0], cs[3][1], cs[4][1]), cs[2][1].oneWayStreets);
		
		assertEquals(Set.of(cs[4][0], cs[4][1]), cs[3][0].oneWayStreets);
		assertEquals(Set.of(), cs[3][1].oneWayStreets);
		
		assertEquals(Set.of(), cs[4][0].oneWayStreets);
		assertEquals(Set.of(), cs[4][1].oneWayStreets);		
	}

	@Test
	public void getCitiesTest01() {
		City c3 = new City(3, new HashSet<City>());
		City c2 = new City(2, new HashSet<City>());
		City c1 = new City(1, new HashSet<City>(Set.of(c2,c3)));
				
		{
			Set<City> result1 = CityNetwork.getCities(
					c1, 2, new HashSet<Integer>(Set.of(3))
					);
			assertNotNull(result1);
			assertEquals(Set.of(), result1);
		}
				
		{
			Set<City> result2 = CityNetwork.getCities(c1, 1, new HashSet<Integer>(Set.of(3)));
			assertNotNull(result2);
			assertEquals(Set.of(c3), result2);
		}
		
		{
			Set<City> result3 = CityNetwork.getCities(c1, 1, new HashSet<Integer>(Set.of(1,3)));
			assertNotNull(result3);
			assertEquals(Set.of(c3), result3);
		}
	}
	
	@Test
	public void getCitiesTest02() {
		City [][] cs = network1();
		
		{
			Set<City> result = CityNetwork.getCities(
					cs[0][0], 1, new HashSet<Integer>(Set.of(2,5,8))
					);
			
			assertNotNull(result);		
			assertEquals(Set.of(cs[3][0], cs[4][0], cs[4][1]), result);
		}
		
		{
			Set<City> result2 = CityNetwork.getCities(
					cs[0][0], 2, new HashSet<Integer>(Set.of(2,5,8))
					);		
			assertNotNull(result2);		
			assertEquals(Set.of(cs[3][0], cs[4][0], cs[4][1]), result2);
		}
	}
	
	@Test
	public void getCitiesTest03() {
		City [][] cs = network1();
		Set<City> result = CityNetwork.getCities(
				cs[0][0], 2, new HashSet<Integer>(Set.of(4))
				);
		
		assertNotNull(result);		
		assertEquals(Set.of(), result);
	}
	
	@Test
	public void getCitiesTest04() {
		City [][] cs = network2();
		
		{
			Set<City> result = CityNetwork.getCities(
					cs[0][0],
					4,
					new HashSet<Integer>(Set.of(1))
					);
			
			assertNotNull(result);
			assertEquals(Set.of(cs[4][0]), result);
		}
		
		{
			Set<City> result2 = CityNetwork.getCities(
					cs[0][0],
					4,
					new HashSet<Integer>(Set.of(20))
					);
			
			assertNotNull(result2);
			assertEquals(Set.of(), result2);
		}
	}
	
	public static City [][] network1() {
		City c40 = new City(2, new HashSet<City>());
		City c41 = new City(10, new HashSet<City>());


		City c30 = new City(5, new HashSet<City>(Set.of(c40, c41)));
		City c31 = new City(10, new HashSet<City>());
				
		City c20 = new City(2, new HashSet<City>(Set.of(c30, c31)));
		City c21 = new City(10, new HashSet<City>(Set.of(c20)));
		
		City c10 = new City(4, new HashSet<City>(Set.of(c20)));
		City c11 = new City(1, new HashSet<City>(Set.of(c21)));
		
		City c00 = new City(8, new HashSet<City>(Set.of(c10, c11)));
		
		return new City [][] {
				new City [] { c00 },
				new City [] { c10, c11 },
				new City [] { c20, c21 },
				new City [] { c30, c31 },
				new City [] { c40, c41 }
		};
	}
	
	public static City [][] network2() {
		City c40 = new City(5, new HashSet<City>());
		
		City c30 = new City(4, new HashSet<City>(Set.of(c40)));
		City c31 = new City(4, new HashSet<City>(Set.of(c40)));
		
		City c20 = new City(3, new HashSet<City>(Set.of(c30, c31)));
		
		City c10 = new City(2, new HashSet<City>(Set.of(c20)));
		City c11 = new City(2, new HashSet<City>(Set.of(c20)));
		
		City c00 = new City(1, new HashSet<City>(Set.of(c10, c11)));
		
		return new City [][] {
			new City [] { c00 },
			new City [] { c10, c11 },
			new City [] { c20 },
			new City [] { c30, c31 },
			new City [] { c40 }
	};
	}

		
	
}
