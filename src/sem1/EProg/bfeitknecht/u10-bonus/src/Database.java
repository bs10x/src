import java.util.ArrayList;
import java.util.List;

public class Database {

	private List<Item> items = new ArrayList<>();
	private ItemFactory factory = new ItemFactory(); 

	public void add(Item item) { 
		items.add(item); 
	}

	public List<Item> getItems() { 
		return new ArrayList<>(items);
	}

	public ItemFactory getItemFactory() { 
		return factory; 
	}
	
	public void createLink(List<Integer> ids) {
		// TODO
	}
}