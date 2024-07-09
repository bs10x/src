import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;	// is this allowed
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class Database {

	private List<Item> items = new ArrayList<>();
	private ItemFactory factory = new ItemFactory(this); 

	public void add(Item item) { 
		items.add(item); 
	}

	public List<Item> getItems() { 
		return new ArrayList<>(items);
	}
	
	public Item getItemByID(int targetID) {
		for (Item i : items) {
			if (i.getID() == targetID) return i;
		}
		return null;
	}
	
	public boolean targetItemExists(int targetID) {
		return getItemByID(targetID) != null;
	}

	public ItemFactory getItemFactory() { 
		return factory; 
	}

	public Map<Integer, Integer> summary(Level groupLevel) {
		
		Map<Integer, Integer> summary = new HashMap<>();
		
		
		List<Item> items = getItems().stream()
									 .filter(i -> i.isWeakerThan(groupLevel))
									 .collect(Collectors.toList());

		Set<Integer> keys = items.stream()
							  .map(i -> i.getHealth() / 10 * 10)
							  .collect(Collectors.toSet());
		
		for (int k : keys) {
			int count = (int) items.stream()
					   		 	   .filter(i -> i.getHealth() / 10 * 10 == k)
					   		 	   .count();
			
			int avg = items.stream()
						   .filter(i -> i.getHealth() / 10 * 10 == k)
						   .map(i -> i.getAge())
						   .reduce(0, Integer::sum)
						   / count;
			
			summary.put(k, avg);
		}

		return summary;
	}
	
	public void createLink(Set<Integer> ids) {
		for (int id : ids) if(!targetItemExists(id)) throw new IllegalArgumentException();
		
		Set<Item> newLink = new HashSet<>();
		for (int id : ids) {
			for (Item newItem : getItemByID(id).getLink()) {
				newLink.add(newItem);
			}
		}
		for (int id : ids) getItemByID(id).setLink(newLink); 
	}
	

}