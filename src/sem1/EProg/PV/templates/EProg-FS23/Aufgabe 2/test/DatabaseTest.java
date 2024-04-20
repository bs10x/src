import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

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
	
/** b) **/
	
	@Test
	public void testB1() {
		Database database = new Database();
		ItemFactory factory = database.getItemFactory();
		
		database.add(factory.createItem(new Level(Set.of(1,2)), 0, 20, 100));
		Item item = factory.createItem(new Level(Set.of(2,3)), 1, 30, 109);
		database.add(item);
		Item declass = factory.createDeclass(new Level(Set.of(5,6)), 2, 1);
		
		assertEquals(2, declass.getID());
		assertEquals(30, declass.getAge());
		assertEquals(109, declass.getHealth());
		
		item.setHealth(40);
		
		assertEquals(40, item.getHealth());
		assertEquals(40, declass.getHealth());
	}
	
	@Test
	public void testB2() {
		Database database = new Database();
		ItemFactory factory = database.getItemFactory();
		
		database.add(factory.createItem(new Level(Set.of(1,2)), 0, 20, 100));
		database.add(factory.createItem(new Level(Set.of(2,3)), 1, 30, 109));
		database.add(factory.createDeclass(new Level(Set.of(5,6)), 2, 1));
		Item declass2 = factory.createDeclass(new Level(Set.of(4,6)), 3, 2);
		
		assertEquals(3, declass2.getID());
		assertEquals(30, declass2.getAge());
		assertEquals(109, declass2.getHealth());
	}
	
	@Test
	public void testB3() {
		Database database = new Database();
		ItemFactory factory = database.getItemFactory();
		
		database.add(factory.createItem(new Level(Set.of(1,2)), 0, 20, 100));
		database.add(factory.createItem(new Level(Set.of(1,2,3,4)), 1, 30, 109));
		database.add(factory.createDeclass(new Level(Set.of(5,6)), 2, 1));
		assertThrows(
				IllegalArgumentException.class,
		        () -> factory.createDeclass(new Level(Set.of(4,6)), 3, 2)
		);
	}
	
/** c) **/

	@Test
	public void testC1() {
		Database database = new Database();
		ItemFactory factory = database.getItemFactory();
		
		Item item1 = factory.createItem(new Level(Set.of(2,3)), 1, 30, 109);
		Item item2 = factory.createItem(new Level(Set.of(2,3)), 2, 20, 50);
		Item item3 = factory.createItem(new Level(Set.of(2,3)), 3, 23, 45);
		Item item4 = factory.createItem(new Level(Set.of(2,3)), 4, 54, 89);
		
		database.add(item1);
		database.add(item2);
		database.add(item3);
		database.add(item4);
		
		database.createLink(Set.of(1,2,3));
		
		assertEquals(109, item1.getHealth());
		assertEquals(50, item2.getHealth());
		assertEquals(45, item3.getHealth());
		assertEquals(89, item4.getHealth());
		
		item2.setHealth(0);
		
		assertEquals(0, item1.getHealth());
		assertEquals(0, item2.getHealth());
		assertEquals(0, item3.getHealth());
		assertEquals(89, item4.getHealth());
	}
	
	@Test
	public void testC2() {
		Database database = new Database();
		ItemFactory factory = database.getItemFactory();
		
		Item item1 = factory.createItem(new Level(Set.of(2,3)), 1, 30, 109);
		Item item2 = factory.createItem(new Level(Set.of(2,3)), 2, 20, 50);
		Item item3 = factory.createItem(new Level(Set.of(2,3)), 3, 23, 45);
		Item item4 = factory.createItem(new Level(Set.of(2,3)), 4, 54, 89);
		
		database.add(item1);
		database.add(item2);
		database.add(item3);
		database.add(item4);
		
		database.createLink(Set.of(1,2));
		database.createLink(Set.of(3,4));
		database.createLink(Set.of(1,4));
		
		item2.setHealth(0);
		
		assertEquals(0, item1.getHealth());
		assertEquals(0, item2.getHealth());
		assertEquals(0, item3.getHealth());
		assertEquals(0, item4.getHealth());
	}
	
}