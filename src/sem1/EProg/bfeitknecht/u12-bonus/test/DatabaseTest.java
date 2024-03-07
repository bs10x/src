import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Map;
import java.util.Set;

import org.junit.jupiter.api.Test;

public class DatabaseTest {
	
/** a) **/
	
	@Test
	public void testA1() {
		Database database = new Database();
		ItemFactory factory = database.getItemFactory();
		
		database.add(factory.createItem(new Level(Set.of(1,2)), 1, 20, 100));
		database.add(factory.createItem(new Level(Set.of(2,3)), 2, 31, 109));
		database.add(factory.createItem(new Level(Set.of(5,6)), 3, 50, 100));
		database.add(factory.createItem(new Level(Set.of(4)), 15, 20, 133));
		
		Map<Integer, Integer> res = database.summary(new Level(Set.of(3,5)));
		assertEquals(2, res.size());
		assertEquals(25, res.get(100));
		assertEquals(20, res.get(130));
	}
	
	@Test
	public void testA2() {
		Database database = new Database();
		ItemFactory factory = database.getItemFactory();
		
		database.add(factory.createItem(new Level(Set.of(1,2)), 0, 22, 100));
		database.add(factory.createItem(new Level(Set.of(2,3)), 1, 31, 109));
		database.add(factory.createItem(new Level(Set.of(1,2)), 2, 20, 52));
		database.add(factory.createItem(new Level(Set.of(4)), 3, 30, 53));
		database.add(factory.createItem(new Level(Set.of(2,3,1)), 4, 40, 53));
		database.add(factory.createItem(new Level(Set.of(2,3,1)), 5, 40, 9));
		database.add(factory.createItem(new Level(Set.of(6)), 6, 50, 100));
		
		Map<Integer, Integer> res = database.summary(new Level(Set.of(3,5)));
		assertEquals(3, res.size());
		assertEquals(40, res.get(0));
		assertEquals(30, res.get(50));
		assertEquals(26, res.get(100));
	}
	
}