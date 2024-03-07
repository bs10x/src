import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
//import java.util.Set;

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
	
	// summary method
	public Map<Integer, Integer> summary(Level groupLevel) {
		Map<Integer, Integer> summary = new HashMap<>();
		ArrayList<Item> workingSet = new ArrayList<>();
		
		
		// add all Items E with a level A weaker than groupLevel to a workingSet
		for (Item E : this.getItems()) {
			//System.out.println(E.getID());
			if(isWeaker(E.getLevel(), groupLevel)) {
				workingSet.add(E);
				//System.out.println(E.getID());
			}			
		}
		
		
		/*
		// debug
		for(Item E : this.getItems()) {
			if(E.getID() == 7) {
				System.out.println(E.getLevel().getPoints());
				System.out.println(groupLevel.getPoints());
				System.out.println(isWeaker(E.getLevel(), groupLevel));
				if (isWeaker(E.getLevel(), groupLevel)) {
				}
			}
		}
		*/
		
		
		// populate k
		ArrayList<Integer> k = new ArrayList<>();
		for(Item I : workingSet) {
			if(!k.contains(I.getHealth() / 10)) k.add(I.getHealth() / 10);
		}
		
		// in each group k, calculate rounded down average age
		for(int i : k) {
			int kSum = 0;
			int kAvg = 0;
			int kCounter = 0;
			
			for (Item E : workingSet) {
				if(E.getHealth() / 10 == i) {
					kCounter++;
					kSum += E.getAge();
				}
				if(kCounter != 0) kAvg = kSum / kCounter;
			}
			summary.put(i * 10, kAvg);
		}
		return summary;
	}
	
	// compare wether Level A is weaker than Level B
	public boolean isWeaker (Level A, Level B) {
		boolean isWeaker = true;
		int aMax = 0;
		int bMax = 0;
		
		for(Integer a : A.getPoints()) if(a > aMax) aMax = a;
		for(Integer b : B.getPoints()) if(b > bMax) bMax = b;

		if(Math.max(aMax, bMax) == (aMax)) isWeaker = false;
		return isWeaker;
	}	
}