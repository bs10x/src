import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.List;

import org.junit.jupiter.api.Test;

public class GradingDatabaseTest {

/** a) **/
	
	@Test
	public void testA01Given() {
		Database database = new Database();
		ItemFactory factory = database.getItemFactory();
		
		database.add(factory.createItem(new Level(List.of(1,2)),      0, 20, 100));
		Item item = factory.createItem(new Level(List.of(2,3)),       1, 30, 109);		
		database.add(item);
		Item declass = factory.createDeclass(new Level(List.of(5,6)), 2, 1);
		
		assertNotNull(item);
		assertNotNull(declass);
		
		assertEquals(2, declass.getID());
		assertEquals(30, declass.getAge());
		assertEquals(109, declass.getHealth());
		
		item.setHealth(40);
		
		assertEquals(40, item.getHealth());
		assertEquals(40, declass.getHealth());
	}
	
	@Test
	public void testA02Given() {
		Database database = new Database();
		ItemFactory factory = database.getItemFactory();
		
		database.add(factory.createItem(new Level(List.of(1,2)), 0, 20, 100));
		database.add(factory.createItem(new Level(List.of(2,3)), 1, 30, 109));
		database.add(factory.createDeclass(new Level(List.of(5,6)), 2, 1));
		Item declass2 = factory.createDeclass(new Level(List.of(4,6)), 3, 2);
		
		assertNotNull(declass2);
		
		assertEquals(3, declass2.getID());
		assertEquals(30, declass2.getAge());
		assertEquals(109, declass2.getHealth());
	}
	
	@Test
	public void testA03Given() {
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
	
	@Test
	public void testA04ND() {
		Database database = new Database();
		ItemFactory factory = database.getItemFactory();
		
		database.add(factory.createItem(new Level(List.of(8,9)), 5, 25, 95));
		
		Item d = factory.createDeclass(new Level(List.of(4,8)), 2, 5);
		
		assertItemMatches(2, List.of(4,8), 25, 95, d);
	}
	
	@Test
	public void testA05ND() {
		Database database = new Database();
		ItemFactory factory = database.getItemFactory();
		
		database.add(factory.createItem(new Level(List.of(8,9)),      1, 21, 90));
		database.add(factory.createItem(new Level(List.of(10,19,22)), 2, 22, 92));
		database.add(factory.createItem(new Level(List.of(7)),        3, 29, 94));
		database.add(factory.createItem(new Level(List.of(25)),       4, 32, 105));
		
		Item d1 = factory.createDeclass(new Level(List.of(5,6)),     19, 3);
		
		assertItemMatches(19, List.of(5,6), 29, 94, d1);
	}
	
	@Test
	public void testA06ND() {
		Database database = new Database();
		ItemFactory factory = database.getItemFactory();
		
		database.add(factory.createItem(new Level(List.of(8,9)),      5,  31, 25));
		database.add(factory.createItem(new Level(List.of(10)),       9,  32, 26));
		database.add(factory.createItem(new Level(List.of(7)),        11, 49, 27));
		database.add(factory.createItem(new Level(List.of(2,1)),      22, 51, 22));
		
		{
			Item d1 = factory.createDeclass(new Level(List.of(100)),  31, 9);
			assertItemMatches(31, List.of(100), 32, 26, d1);
		}
		
		{
			Item d2 = factory.createDeclass(new Level(List.of(101)),  32, 22);
			assertItemMatches(32, List.of(101), 51, 22, d2);
		}
		
		{
			Item d3 = factory.createDeclass(new Level(List.of(102)),  33, 11);
			assertItemMatches(33, List.of(102), 49, 27, d3);
		}
		
		{
			Item d4 = factory.createDeclass(new Level(List.of(104)),  45, 11);
			assertItemMatches(45, List.of(104), 49, 27, d4);
		}
	}
	
	@Test
	public void testA07ND() {
		Database database = new Database();
		ItemFactory factory = database.getItemFactory();		
		
		Item [] normalItems = new Item[10];
		
		for(int id = 0; id < normalItems.length; id++) {
			Item n = factory.createItem(new Level(List.of(id+2)), id, id+20, 2*id+20);
			normalItems[id] = n;
			database.add(n);
		}
		
		for(int i = 4; i < normalItems.length; i++) {			
			int id = i+normalItems.length+10;
			
			database.add(factory.createItem(new Level(List.of(id+2)), 2*id, id+20, 2*id+20));
			
			Item d = factory.createDeclass(new Level(List.of(id+3)), 2*id+1, i);
			assertItemMatches(2*id+1, List.of(id+3), normalItems[i].getAge(), normalItems[i].getHealth(), d);
			database.add(d);
		}
	}
	
	@Test
	public void testA08NDSetHealth01() {
		Database database = new Database();
		ItemFactory factory = database.getItemFactory();

		Item i2 = factory.createItem(new Level(List.of(10)),   2, 32, 100);
		database.add(i2);
		
		Item d = factory.createDeclass(new Level(List.of(17)), 4, 2);
		
		assertNotNull(d);
		
		assertEquals(100, d.getHealth());
		
		i2.setHealth(99);
		assertEquals(99, d.getHealth());
	}
	
	@Test
	public void testA09NDSetHealth02() {
		Database database = new Database();
		ItemFactory factory = database.getItemFactory();
		
		database.add(factory.createItem(new Level(List.of(7,8,9)), 1,  42, 24));
		database.add(factory.createItem(new Level(List.of(5,10)),  2,  43, 25));
		Item i3 =    factory.createItem(new Level(List.of(1)),     3,  59, 26);
		database.add(i3);
		database.add(factory.createItem(new Level(List.of(1)),     4,  61, 19));
		Item i5 =    factory.createItem(new Level(List.of(2)),     5,  62, 19);
		database.add(i5);
		
		Item d1 = factory.createDeclass(new Level(List.of(15)),    7, 5);
		Item d2 = factory.createDeclass(new Level(List.of(16)),   11, 3);
		Item d3 = factory.createDeclass(new Level(List.of(17)),   12, 3);
		
		assertNotNull(d1);
		assertNotNull(d2);
		assertNotNull(d3);
		
		assertEquals(19, d1.getHealth());
		assertEquals(26, d2.getHealth());
		assertEquals(26, d3.getHealth());
		
		i3.setHealth(100);		
		assertEquals(100, i3.getHealth());
		assertEquals(100, d2.getHealth());
		assertEquals(100, d3.getHealth());
		
		assertEquals(19, i5.getHealth());
		assertEquals(19, d1.getHealth());
		
		i5.setHealth(20);
		assertEquals(20, i5.getHealth());
		assertEquals(20, d1.getHealth());
		
		assertEquals(100, i3.getHealth());
		assertEquals(100, d2.getHealth());
		assertEquals(100, d3.getHealth());
	}
	
	@Test
	public void testA10NDNotFound01() {
		Database database = new Database();
		ItemFactory factory = database.getItemFactory();

		database.add(factory.createItem(new Level(List.of(8,9)),      5,  31, 25));
		database.add(factory.createItem(new Level(List.of(10)),       9,  32, 26));
		database.add(factory.createItem(new Level(List.of(7)),        11, 49, 27));
		database.add(factory.createItem(new Level(List.of(2,1)),      22, 51, 22));
		
		assertNotNull(factory.createDeclass(new Level(List.of(25)), 25, 5));
		
		assertThrows(
				IllegalArgumentException.class,
                () -> factory.createDeclass(new Level(List.of(29)), 25, 7)
        );
	}
	
	@Test
	public void testA11NDNotFound02() {
		Database db1 = new Database();
		Database db2 = new Database();
		
		ItemFactory factory1 = db1.getItemFactory();
		
		db1.add(factory1.createItem(new Level(List.of(10)), 1, 35, 100));
		
		assertNotNull(factory1.createDeclass(new Level(List.of(20)), 20, 1));

		assertThrows(
				IllegalArgumentException.class,
				() -> db2.getItemFactory().createDeclass(new Level(List.of(9)), 30, 1)
		);
	}
	
	@Test
	public void testA12NDRelated01() {
		Database database = new Database();
		ItemFactory factory = database.getItemFactory();
		
		database.add(factory.createItem(new Level(List.of(105)),       1, 20, 25));
		
		assertNotNull(factory.createDeclass(new Level(List.of(100,6)), 2, 1));
		
		assertThrows(
				IllegalArgumentException.class,
				() -> factory.createDeclass(new Level(List.of(100,5)), 5, 1)
		);
	}
	
	@Test
	public void testA13NDRelated02() {
		Database database = new Database();
		ItemFactory factory = database.getItemFactory();
		
		database.add(factory.createItem(new Level(List.of(2,9,11)),      5, 19, 107));
		database.add(factory.createItem(new Level(List.of(4,5,6,10,20)), 7, 19, 107));
		database.add(factory.createItem(new Level(List.of(1,3,5)),       10, 19, 107));
		
		assertNotNull(factory.createDeclass(new Level(List.of(4,5,6,12,20)), 12, 7));
		
		assertThrows(				
				IllegalArgumentException.class,
				() -> factory.createDeclass(new Level(List.of(2,43)), 15, 7)
		);
	}
	
	@Test
	public void testA14D() {
		Database database = new Database();
		ItemFactory factory = database.getItemFactory();
		
		database.add(factory.createItem(new Level(List.of(2,15)), 1, 20, 501));
		database.add(factory.createItem(new Level(List.of(2,16)), 2, 25, 502));
		database.add(factory.createItem(new Level(List.of(2,16)), 3, 26, 503));
		database.add(factory.createItem(new Level(List.of(2,17)), 4, 35, 504));
		
		database.add(factory.createDeclass(new Level(List.of(2,18)), 6, 2));
		
		Item d2 = factory.createDeclass(new Level(List.of(2,20)), 7, 6);
		
		assertItemMatches(7, List.of(2,20), 25, 502, d2);
	}
	
	@Test
	public void testA15DRelated01() {
		Database database = new Database();
		ItemFactory factory = database.getItemFactory();
		
		database.add(factory.createItem(new Level(List.of(1)),    1, 20, 500));
		database.add(factory.createItem(new Level(List.of(3)),    2, 25, 500));
		database.add(factory.createItem(new Level(List.of(4)),    3, 25, 500));
		database.add(factory.createItem(new Level(List.of(5)),    4, 35, 500));
		database.add(factory.createItem(new Level(List.of(5)),    5, 35, 500));

		
		database.add(factory.createDeclass(new Level(List.of(7)), 6, 3));
		
		assertThrows(
			IllegalArgumentException.class,
			() -> factory.createDeclass(new Level(List.of(4)),    9, 6)
		);
	}
	
	@Test
	public void testA16DRelated02() {
		Database database = new Database();
		ItemFactory factory = database.getItemFactory();
		
		database.add(factory.createItem(new Level(List.of(1,5,17,18)), 1, 20, 450));
		database.add(factory.createItem(new Level(List.of(1)),         2, 25, 452));
		database.add(factory.createItem(new Level(List.of(1,7,20)),    3, 26, 453));
		database.add(factory.createItem(new Level(List.of(2)),         4, 32, 449));
		database.add(factory.createItem(new Level(List.of(3)),         5, 32, 451));
		database.add(factory.createItem(new Level(List.of(4)),         6, 32, 453));
		database.add(factory.createItem(new Level(List.of(5)),         7, 32, 455));
		
		database.add(factory.createDeclass(new Level(List.of(1,5)),    8,  3));
		database.add(factory.createDeclass(new Level(List.of(7)),      9,  1));
		database.add(factory.createDeclass(new Level(List.of(8)),      10, 8));
		
		assertThrows(
			IllegalArgumentException.class,
			() -> factory.createDeclass(new Level(List.of(3,1,7,17)),      11, 10)
		);
		
		assertThrows(
				IllegalArgumentException.class,
				() -> factory.createDeclass(new Level(List.of(2,4)),  12, 10)
			);
	}
		
	public void assertItemMatches(int id, List<Integer> levelPoints, int age, int health, Item actualItem) {
		assertNotNull(actualItem);
		
		assertEquals(id, actualItem.getID());
		assertEquals(levelPoints, actualItem.getLevel().getPoints());
		assertEquals(age, actualItem.getAge());
		assertEquals(health, actualItem.getHealth());
	}

	/** b) **/

	@Test
	public void testB01Given() {
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
	public void testB02Given() {
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
	
	@Test
	public void testB03InvalidId() {
		Database database = new Database();		
		ItemFactory factory = database.getItemFactory();
		
		database.add(factory.createItem(new Level(List.of(1)),   1, 41, 109));
		database.add(factory.createItem(new Level(List.of(5,2)), 2, 24, 50));
		database.add(factory.createItem(new Level(List.of(8)),   5, 23, 45));
		database.add(factory.createItem(new Level(List.of(7,9)), 6, 19, 89));
		
		database.createLink(List.of(1,2,5,6));
		
		assertThrows(
				IllegalArgumentException.class,
				() -> database.createLink(List.of(1,3))
		);
	}
	
	@Test
	public void testB04NT() {
		Database database = new Database();
		ItemFactory factory = database.getItemFactory();
		
		Item i1 = factory.createItem(new Level(List.of(5,1,2)),  1, 25, 100);
		database.add(i1);
		Item i2 = factory.createItem(new Level(List.of(5,3,2)),  2, 26, 102);
		database.add(i2);
		Item i3 = factory.createItem(new Level(List.of(5,1,2)),  3, 42, 85);
		database.add(i3);
		Item i4 = factory.createItem(new Level(List.of(5,1,2)),  4, 43, 84);
		database.add(i4);
		Item i5 = factory.createItem(new Level(List.of(5,3,2)),  5, 28, 105);
		database.add(i5);
		
		database.createLink(List.of(2,5));
		
		i2.setHealth(110);
		
		assertEquals(100, i1.getHealth());
		assertEquals(85, i3.getHealth());
		assertEquals(84, i4.getHealth());
		assertEquals(110, i5.getHealth());
	}
	
	@Test
	public void testB05NT() {
		Database database = new Database();
		ItemFactory factory = database.getItemFactory();
		
		Item i1 = factory.createItem(new Level(List.of(5,1,2)),  1, 25, 100);
		database.add(i1);
		Item i2 = factory.createItem(new Level(List.of(5,3,2)),  2, 26, 102);
		database.add(i2);
		Item i3 = factory.createItem(new Level(List.of(5,1,2)),  3, 42, 85);
		database.add(i3);
		Item i4 = factory.createItem(new Level(List.of(5,1,2)),  4, 43, 84);
		database.add(i4);
		Item i5 = factory.createItem(new Level(List.of(5,3,2)),  5, 28, 105);
		database.add(i5);
		
		database.createLink(List.of(2,5));
		
		i5.setHealth(110);
		
		assertEquals(100, i1.getHealth());
		assertEquals(110, i2.getHealth());
		assertEquals(85, i3.getHealth());
		assertEquals(84, i4.getHealth());
	}
	
	@Test
	public void testB06NT() {
		Database database = new Database();
		ItemFactory factory = database.getItemFactory();
		
		Item [] items = new Item[10];
		
		for(int i = 0; i < items.length; i++) {
			items[i] = factory.createItem(new Level(List.of(i)), i, i+40, i+500);
			database.add(items[i]);
		}
		
		List<Integer> linkedIds = List.of(2,4,7,8,9);
		database.createLink(linkedIds);
		
		for(int i = 0; i < items.length; i++) {
			assertEquals(i+500, items[i].getHealth());
		}
		
		items[8].setHealth(708);
		
		for(int i = 0; i < items.length; i++) {
			if(linkedIds.contains(i)) {
				assertEquals(708, items[i].getHealth());
			} else {
				assertEquals(i+500, items[i].getHealth());
			}
		}
	}
	
	@Test
	public void testB07NT() {
		Database database = new Database();
		ItemFactory factory = database.getItemFactory();
		
		Item item1 = factory.createItem(new Level(List.of(1)),     1, 32, 107);
		Item item2 = factory.createItem(new Level(List.of(3,4)),   2, 21, 92);
		Item item3 = factory.createItem(new Level(List.of(5,9)),   3, 24, 91);
		Item item4 = factory.createItem(new Level(List.of(2,7,8)), 4, 55, 89);
		Item item5 = factory.createItem(new Level(List.of(2,7,8)), 5, 55, 86);
		Item item6 = factory.createItem(new Level(List.of(1)),     6, 57, 85);
	
		database.add(item1);
		database.add(item2);
		database.add(item3);
		database.add(item4);
		database.add(item5);
		database.add(item6);
		
		database.createLink(List.of(2,4,5));
				
		item2.setHealth(83);
		
		assertEquals(107, item1.getHealth());
		assertEquals(83, item2.getHealth());
		assertEquals(91, item3.getHealth());
		assertEquals(83, item4.getHealth());
		assertEquals(83, item5.getHealth());
		assertEquals(85, item6.getHealth());
		
		database.createLink(List.of(1,2));
		
		item2.setHealth(90);
		
		assertEquals(90, item1.getHealth());
		assertEquals(90, item2.getHealth());
		assertEquals(91, item3.getHealth());
		assertEquals(90, item4.getHealth());
		assertEquals(90, item5.getHealth());
		assertEquals(85, item6.getHealth());
	}
	
	@Test
	public void testB08NT() {
		Database database = new Database();
		ItemFactory factory = database.getItemFactory();
		
		Item item1 = factory.createItem(new Level(List.of(1)),     1, 32, 81);
		Item item2 = factory.createItem(new Level(List.of(2)),     2, 21, 72);
		Item item3 = factory.createItem(new Level(List.of(3)),     3, 24, 71);
		Item item4 = factory.createItem(new Level(List.of(4)),     4, 55, 100);
		Item item5 = factory.createItem(new Level(List.of(5)),     5, 55, 76);
		Item item6 = factory.createItem(new Level(List.of(6)),     6, 57, 81);
		Item item7 = factory.createItem(new Level(List.of(7)),     7, 57, 65);
		Item item8 = factory.createItem(new Level(List.of(8)),     8, 57, 67);
		Item item9 = factory.createItem(new Level(List.of(9)),     9, 57, 89);


		database.add(item1);
		database.add(item2);
		database.add(item3);
		database.add(item4);
		database.add(item5);
		database.add(item6);
		database.add(item7);
		database.add(item8);		
		database.add(item9);

		database.createLink(List.of(1,2,3));
		database.createLink(List.of(3,5));
		database.createLink(List.of(3,7));
		database.createLink(List.of(3,8));
		
		item3.setHealth(97);
		
		assertEquals(97, item1.getHealth());
		assertEquals(97, item2.getHealth());
		assertEquals(97, item3.getHealth());
		assertEquals(100, item4.getHealth());
		assertEquals(97, item5.getHealth());
		assertEquals(81, item6.getHealth());
		assertEquals(97, item7.getHealth());
		assertEquals(97, item8.getHealth());
		assertEquals(89, item9.getHealth());
	}

	@Test
	public void testB09T() {
		Database database = new Database();
		ItemFactory factory = database.getItemFactory();
		
		Item item1 = factory.createItem(new Level(List.of(1)),     1, 32, 81);
		Item item2 = factory.createItem(new Level(List.of(3,4)),   2, 21, 72);
		Item item3 = factory.createItem(new Level(List.of(5,9)),   3, 24, 71);
		Item item4 = factory.createItem(new Level(List.of(2,7,8)), 4, 55, 79);
		Item item5 = factory.createItem(new Level(List.of(2,7,8)), 5, 55, 76);
		Item item6 = factory.createItem(new Level(List.of(1)),     6, 57, 75);
		Item item7 = factory.createItem(new Level(List.of(1)),     7, 57, 65);
		Item item8 = factory.createItem(new Level(List.of(1)),     8, 57, 67);
		
		database.add(item1);
		database.add(item2);
		database.add(item3);
		database.add(item4);
		database.add(item5);
		database.add(item6);
		database.add(item7);
		database.add(item8);
		
		database.createLink(List.of(1,4));		
		database.createLink(List.of(4,6));
				
		item1.setHealth(55);

		assertEquals(55, item1.getHealth());
		assertEquals(72, item2.getHealth());
		assertEquals(71, item3.getHealth());
		assertEquals(55, item4.getHealth());
		assertEquals(76, item5.getHealth());
		assertEquals(55, item6.getHealth());
		assertEquals(65, item7.getHealth());
		assertEquals(67, item8.getHealth());
	}
		
	@Test
	public void testB10T() {
		Database database = new Database();
		ItemFactory factory = database.getItemFactory();
		
		Item item1 = factory.createItem(new Level(List.of(1)),  1, 42, 81);
		Item item2 = factory.createItem(new Level(List.of(2)),  2, 51, 72);
		Item item3 = factory.createItem(new Level(List.of(3)),  3, 44, 75);
		Item item4 = factory.createItem(new Level(List.of(4)),  4, 34, 71);
		
		database.add(item1);
		database.add(item2);
		database.add(item3);
		database.add(item4);
		
		database.createLink(List.of(4,2));
		database.createLink(List.of(2,1));
		database.createLink(List.of(1,4));
		
		item1.setHealth(92);
		
		assertEquals(92, item1.getHealth());
		assertEquals(92, item2.getHealth());
		assertEquals(75, item3.getHealth());
		assertEquals(92, item4.getHealth());
	}
	
	@Test
	public void testB11T() {
		Database database = new Database();
		ItemFactory factory = database.getItemFactory();
		
		Item item1 = factory.createItem(new Level(List.of(1)),     1, 32, 81);
		Item item2 = factory.createItem(new Level(List.of(3,4)),   2, 21, 72);
		Item item3 = factory.createItem(new Level(List.of(5,9)),   3, 24, 71);
		Item item4 = factory.createItem(new Level(List.of(2,7,8)), 4, 55, 79);
		Item item5 = factory.createItem(new Level(List.of(2,7,8)), 5, 55, 76);
		Item item6 = factory.createItem(new Level(List.of(1)),     6, 57, 75);
		Item item7 = factory.createItem(new Level(List.of(1)),     7, 57, 65);
		Item item8 = factory.createItem(new Level(List.of(1)),     8, 57, 67);
		
		database.add(item1);
		database.add(item2);
		database.add(item3);
		database.add(item4);
		database.add(item5);
		database.add(item6);
		database.add(item7);
		database.add(item8);		
		
		database.createLink(List.of(5,6,8));		
		database.createLink(List.of(2,1));		
		database.createLink(List.of(2,8));
		
		item6.setHealth(105);
		
		assertEquals(105, item1.getHealth());
		assertEquals(105, item2.getHealth());
		assertEquals(71, item3.getHealth());
		assertEquals(79, item4.getHealth());
		assertEquals(105, item5.getHealth());
		assertEquals(105, item6.getHealth());
		assertEquals(65, item7.getHealth());
		assertEquals(105, item8.getHealth());
	}
	
	@Test
	public void test12T() {
		Database database = new Database();
		ItemFactory factory = database.getItemFactory();

		Item [] items = new Item[12];
		
		for(int i = 0; i < items.length; i++) {
			items[i] = factory.createItem(new Level(List.of(i)), i, i+50, 100+i);
			database.add(items[i]);
		}
		
		database.createLink(List.of(1,3,10));
		
		items[0].setHealth(500);
		
		assertEquals(500, items[0].getHealth());
		
		for(int i = 1; i < items.length; i++) {
			assertEquals(100+i, items[i].getHealth());
		}
		
		items[3].setHealth(200);
		
		assertEquals(500, items[0].getHealth());
		
		for(int i = 1; i < items.length; i++) {
			if(List.of(1,3,10).contains(i)) {
				assertEquals(200, items[i].getHealth());
			} else {
				assertEquals(100+i, items[i].getHealth());
			}
		}
		
		database.createLink(List.of(5,7));
		database.createLink(List.of(1,5));
		
		items[10].setHealth(400);
		
		assertEquals(500, items[0].getHealth());

		for(int i = 1; i < items.length; i++) {
			if(List.of(1,3,10,5,7).contains(i)) {
				assertEquals(400, items[i].getHealth());
			} else {
				assertEquals(100+i, items[i].getHealth());
			}
		}
	}

}
