
public class ItemFactory {

	public Item createItem(Level level, int id, int age, int health) { 
		return new Item(level, id, age, health); 
	}
}
