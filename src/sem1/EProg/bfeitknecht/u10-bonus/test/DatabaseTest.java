import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.List;

import org.junit.jupiter.api.Test;

public class DatabaseTest {
	
/** a) **/
	
	@Test
	public void testA1() {
		Database database = new Database();
		ItemFactory factory = database.getItemFactory();
		
		database.add(factory.createItem(new Level(List.of(1,2)), 0, 20, 100));
		Item item = factory.createItem(new Level(List.of(2,3)), 1, 30, 109);
		database.add(item);
		Item declass = factory.createDeclass(new Level(List.of(5,6)), 2, 1);
		
		assertEquals(2, declass.getID());
		assertEquals(30, declass.getAge());
		assertEquals(109, declass.getHealth());
		
		item.setHealth(40);
		
		assertEquals(40, item.getHealth());
		assertEquals(40, declass.getHealth());
	}
	
	@Test
	public void testA2() {
		Database database = new Database();
		ItemFactory factory = database.getItemFactory();
		
		database.add(factory.createItem(new Level(List.of(1,2)), 0, 20, 100));
		database.add(factory.createItem(new Level(List.of(2,3)), 1, 30, 109));
		database.add(factory.createDeclass(new Level(List.of(5,6)), 2, 1));
		Item declass2 = factory.createDeclass(new Level(List.of(4,6)), 3, 2);
		
		assertEquals(3, declass2.getID());
		assertEquals(30, declass2.getAge());
		assertEquals(109, declass2.getHealth());
	}
	
	@Test
	public void testA3() {
		Database database = new Database();
		ItemFactory factory = database.getItemFactory();
		
		database.add(factory.createItem(new Level(List.of(1,2)), 0, 20, 100));
		database.add(factory.createItem(new Level(List.of(1,2,3,4)), 1, 30, 109));
		database.add(factory.createDeclass(new Level(List.of(5,6)), 2, 1));
		assertThrows(
				IllegalArgumentException.class,
		        () -> factory.createDeclass(new Level(List.of(4,6)), 3, 2)
		);
	}
	
/** b) **/

	@Test
	public void testB1() {
		Database database = new Database();
		ItemFactory factory = database.getItemFactory();
		
		Item item1 = factory.createItem(new Level(List.of(2,3)), 1, 30, 109);
		Item item2 = factory.createItem(new Level(List.of(2,3)), 2, 20, 50);
		Item item3 = factory.createItem(new Level(List.of(2,3)), 3, 23, 45);
		Item item4 = factory.createItem(new Level(List.of(2,3)), 4, 54, 89);
		
		database.add(item1);
		database.add(item2);
		database.add(item3);
		database.add(item4);
		
		database.createLink(List.of(1,2,3));
		
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
	public void testB2() {
		Database database = new Database();
		ItemFactory factory = database.getItemFactory();
		
		Item item1 = factory.createItem(new Level(List.of(2,3)), 1, 30, 109);
		Item item2 = factory.createItem(new Level(List.of(2,3)), 2, 20, 50);
		Item item3 = factory.createItem(new Level(List.of(2,3)), 3, 23, 45);
		Item item4 = factory.createItem(new Level(List.of(2,3)), 4, 54, 89);
		
		database.add(item1);
		database.add(item2);
		database.add(item3);
		database.add(item4);
		
		database.createLink(List.of(1,2));
		database.createLink(List.of(3,4));
		database.createLink(List.of(1,4));
		
		item2.setHealth(0);
		
		assertEquals(0, item1.getHealth());
		assertEquals(0, item2.getHealth());
		assertEquals(0, item3.getHealth());
		assertEquals(0, item4.getHealth());
	}
	
}