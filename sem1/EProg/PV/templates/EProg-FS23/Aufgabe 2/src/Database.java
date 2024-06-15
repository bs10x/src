import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

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

	public Map<Integer, Integer> summary(Level groupLevel) {
		// TODO
		return null;
	}
	
	public void createLink(Set<Integer> ids) {
		// TODO
	}
}