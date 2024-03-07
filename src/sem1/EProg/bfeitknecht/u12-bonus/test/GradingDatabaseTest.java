import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.Map;
import java.util.Set;

import org.junit.jupiter.api.Test;

public class GradingDatabaseTest {
	
/** a) **/
	
	@Test
	public void testA01Given() {
		Database database = new Database();
		ItemFactory factory = database.getItemFactory();
		
		database.add(factory.createItem(new Level(Set.of(1,2)),  1, 20, 100));
		database.add(factory.createItem(new Level(Set.of(2,3)),  2, 31, 109));
		database.add(factory.createItem(new Level(Set.of(5,6)),  3, 50, 100));
		database.add(factory.createItem(new Level(Set.of(4)),   15, 20, 133));
		
		Map<Integer, Integer> res = database.summary(new Level(Set.of(3,5)));
		assertEquals(2, res.size());
		assertEquals(25, res.get(100));
		assertEquals(20, res.get(130));
	}
	
	@Test
	public void testA02Given() {
		Database database = new Database();
		ItemFactory factory = database.getItemFactory();
		
		database.add(factory.createItem(new Level(Set.of(1,2)),   0, 22, 100));
		database.add(factory.createItem(new Level(Set.of(2,3)),   1, 31, 109));
		database.add(factory.createItem(new Level(Set.of(1,2)),   2, 20, 52));
		database.add(factory.createItem(new Level(Set.of(4)),     3, 30, 53));
		database.add(factory.createItem(new Level(Set.of(2,3,1)), 4, 40, 53));
		database.add(factory.createItem(new Level(Set.of(2,3,1)), 5, 40, 9));
		database.add(factory.createItem(new Level(Set.of(6)),     6, 50, 100));
		
		Map<Integer, Integer> res = database.summary(new Level(Set.of(3,5)));
		assertEquals(3, res.size());
		assertEquals(40, res.get(0));
		assertEquals(30, res.get(50));
		assertEquals(26, res.get(100));
	}
	
	@Test
	public void testA03NoWeakerItems() {
		Database database = new Database();
		ItemFactory factory = database.getItemFactory();
		
		database.add(factory.createItem(new Level(Set.of(4,5)), 0, 10, 100));
		database.add(factory.createItem(new Level(Set.of(5,6)), 1, 11, 100));
		
		Map<Integer, Integer> res = database.summary(new Level(Set.of(1,3)));
		assertNotNull(res);
		assertEquals(0, res.size());
	}
	
	@Test
	public void testA04Singleton() {
		Database database = new Database();
		ItemFactory factory = database.getItemFactory();
		
		database.add(factory.createItem(new Level(Set.of(1)), 0, 21, 101));
		
		Map<Integer, Integer> res = database.summary(new Level(Set.of(5)));
		assertNotNull(res);
		assertEquals(Map.of(100, 21), res);
	}
	
	@Test
	public void testA05WO01() {
		Database database = new Database();
		ItemFactory factory = database.getItemFactory();
		
		database.add(factory.createItem(new Level(Set.of(4,2)), 0, 41, 10));
		database.add(factory.createItem(new Level(Set.of(5,2)), 1, 42, 20));
		database.add(factory.createItem(new Level(Set.of(6,7)), 2, 43, 30));
		database.add(factory.createItem(new Level(Set.of(1,3)), 3, 44, 50));
		
		Map<Integer, Integer> res = database.summary(new Level(Set.of(8,9)));
		assertNotNull(res);
		assertEquals(
				Map.of(10, 41,
					   20, 42,
					   30, 43,
					   50, 44), res);
	}
	
	@Test
	public void testA06WO02() {
		Database database = new Database();
		ItemFactory factory = database.getItemFactory();
		
		database.add(factory.createItem(new Level(Set.of(1,2,15)), 1, 53, 34));
		database.add(factory.createItem(new Level(Set.of(15,17)),  2, 81, 12));
		database.add(factory.createItem(new Level(Set.of(19)),     4, 45, 74));
		database.add(factory.createItem(new Level(Set.of(5,20)),   5, 45, 55));
		database.add(factory.createItem(new Level(Set.of(1,19)),   7, 76, 41));
		database.add(factory.createItem(new Level(Set.of(2)),      9, 84, 22));

		
		Map<Integer, Integer> res = database.summary(new Level(Set.of(1,35,5,2)));
		assertNotNull(res);
		assertEquals(
				Map.of(30, 53,
					   10, 81,
					   70, 45,
					   50, 45,
					   40, 76,
					   20, 84), res);
	}
	
	@Test
	public void testA07WM01() {
		Database database = new Database();
		ItemFactory factory = database.getItemFactory();
		
		database.add(factory.createItem(new Level(Set.of(5,3)), 0, 32, 10));
		database.add(factory.createItem(new Level(Set.of(6,3)), 1, 10, 50));
		database.add(factory.createItem(new Level(Set.of(8,5)), 2, 30, 20));
		database.add(factory.createItem(new Level(Set.of(5,6)), 3, 58, 10));
		database.add(factory.createItem(new Level(Set.of(3)),   4, 40, 20));
		database.add(factory.createItem(new Level(Set.of(8)),   5, 46, 20));
		database.add(factory.createItem(new Level(Set.of(1,8)), 6, 16, 50));
		
		Map<Integer, Integer> res = database.summary(new Level(Set.of(9)));
		assertNotNull(res);
		assertEquals(
				Map.of(10, 45,
					   20, 38,
					   50, 13), res);
	}
	
	@Test
	public void testA08WM02() {
		Database database = new Database();
		ItemFactory factory = database.getItemFactory();
		
		database.add(factory.createItem(new Level(Set.of(1,5,3,4)),   0, 20, 101));
		database.add(factory.createItem(new Level(Set.of(6)),         1, 21, 102));
		database.add(factory.createItem(new Level(Set.of(9,10)),      2, 22, 105));
		database.add(factory.createItem(new Level(Set.of(2)),         3, 28, 202));
		database.add(factory.createItem(new Level(Set.of(3,9)),       4, 29, 203));
		database.add(factory.createItem(new Level(Set.of(10,11)),     5, 34, 301));
		database.add(factory.createItem(new Level(Set.of(2)),         6, 35, 302));
		database.add(factory.createItem(new Level(Set.of(2)),         7, 31, 208));
		
		Map<Integer, Integer> res = database.summary(new Level(Set.of(4,2,15,3,12)));	
		assertNotNull(res);

		assertEquals(
				Map.of(100, 21,
					   200, 29,
					   300, 34),
				res);
	}
	
	@Test
	public void testA09SO01() {
		Database database = new Database();
		ItemFactory factory = database.getItemFactory();
		
		database.add(factory.createItem(new Level(Set.of(2,3)), 0, 80, 20));
		database.add(factory.createItem(new Level(Set.of(1,2)), 1, 70, 30));
		database.add(factory.createItem(new Level(Set.of(9)),   2, 40, 60)); //
		database.add(factory.createItem(new Level(Set.of(3,4)), 3, 20, 80));
		database.add(factory.createItem(new Level(Set.of(8,7)), 4, 60, 40)); //
		database.add(factory.createItem(new Level(Set.of(5,4)), 5, 80, 10));		

		
		Map<Integer, Integer> res = database.summary(new Level(Set.of(6)));
		assertNotNull(res);
		assertEquals(
				Map.of(20, 80,
					   30, 70,
					   80, 20,
					   10, 80), res);
	}
	
	@Test
	public void testA10SO02() {
		Database database = new Database();
		ItemFactory factory = database.getItemFactory();
		
		database.add(factory.createItem(new Level(Set.of(18,19)),     2,    55,   64)); //
		database.add(factory.createItem(new Level(Set.of(12)),        3,    74,   17));
		database.add(factory.createItem(new Level(Set.of(1,12,2,15)), 5,    51,   39));
		database.add(factory.createItem(new Level(Set.of(1,18,5)),    7,    48,   32)); //
		database.add(factory.createItem(new Level(Set.of(4,5)),       11,   49,   49));
		database.add(factory.createItem(new Level(Set.of(6,8,9)),     13,   51,   28));		
		database.add(factory.createItem(new Level(Set.of(28)),        17,   38,   29)); //
		database.add(factory.createItem(new Level(Set.of(22,23)),     19,   38,   27)); //
		database.add(factory.createItem(new Level(Set.of(1,2,16)),    23,   23,   64)); 
		database.add(factory.createItem(new Level(Set.of(4,6,7,9)),   34,   32,   75));
		database.add(factory.createItem(new Level(Set.of(3,5)),       45,   34,   84));
		database.add(factory.createItem(new Level(Set.of(100,200)),   56,   61,   17)); //
		
		Map<Integer, Integer> res = database.summary(new Level(Set.of(10,17,2,12)));
		assertNotNull(res);
		assertEquals(
				Map.of(10, 74,
					   30, 51,
					   40, 49,
					   20, 51,
					   60, 23,
					   70, 32,
					   80, 34),
				res
		);
	}
	
	@Test
	public void testA11SM01() {
		Database database = new Database();
		ItemFactory factory = database.getItemFactory();
		
		database.add(factory.createItem(new Level(Set.of(3,4)), 1, 80, 50));
		database.add(factory.createItem(new Level(Set.of(8,7)), 2, 70, 30)); //
		database.add(factory.createItem(new Level(Set.of(1,2)), 3, 40, 50));
		database.add(factory.createItem(new Level(Set.of(1,2)), 4, 70, 20));
		database.add(factory.createItem(new Level(Set.of(3,4)), 5, 60, 40)); 
		database.add(factory.createItem(new Level(Set.of(7,8)), 6, 80, 10)); //
		database.add(factory.createItem(new Level(Set.of(10)),  7, 80, 10)); //
		database.add(factory.createItem(new Level(Set.of(1,4)), 8, 80, 20));
		database.add(factory.createItem(new Level(Set.of(2)),   9, 80, 40));

		
		Map<Integer, Integer> res = database.summary(new Level(Set.of(5,6)));
		assertNotNull(res);
		assertEquals(
				Map.of(50, 60,
					   20, 75,
					   40, 70), res);
	}
	
	@Test
	public void testA12SM02() {
		Database database = new Database();
		ItemFactory factory = database.getItemFactory();
		
		database.add(factory.createItem(new Level(Set.of(18,22,29)), 0, 55, 106)); //
		database.add(factory.createItem(new Level(Set.of(5,7,19)),   1, 42, 105));
		database.add(factory.createItem(new Level(Set.of(2)),        2, 63, 117));
		database.add(factory.createItem(new Level(Set.of(5,19,20)),  3, 48, 103));
		database.add(factory.createItem(new Level(Set.of(1,2,3)),    4, 43, 109));
		database.add(factory.createItem(new Level(Set.of(4,12,16)),  5, 53, 118));		
		database.add(factory.createItem(new Level(Set.of(18,21,23)), 6, 22, 142));  //
		database.add(factory.createItem(new Level(Set.of(10,12)),    7, 29, 132));
		database.add(factory.createItem(new Level(Set.of(22,25)),    8, 82, 57));  //
		database.add(factory.createItem(new Level(Set.of(15,7)),     9, 32, 134));
		database.add(factory.createItem(new Level(Set.of(2,3)),     10, 21, 139));
		
		Map<Integer, Integer> res = database.summary(new Level(Set.of(2,3,22,1,5)));
		assertNotNull(res);
		assertEquals(
				Map.of(100, 44,
				       110, 58,
				       130, 27),
				res
		);
	}

}
