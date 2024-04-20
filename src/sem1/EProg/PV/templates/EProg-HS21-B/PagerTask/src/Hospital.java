import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;

public class Hospital {
	
	public List<String> registeredNames;
	public List<PagerObject> pagerObjects;
	
	public HashMap<String, Pager> pagers;
	
		
	public Hospital() {
		this.registeredNames = new ArrayList<>();
		this.pagerObjects = new ArrayList<>();
		
		this.pagers = new HashMap<>();
	}

	public Pager createPager(String role) {
		PagerObject p = new PagerObject(this);
		p.assignRole(role);
		pagerObjects.add(p);
		return p;
	}
	
	public boolean alreadyRegisteredName(String name) {
		return registeredNames.contains(name);
	}
		
	public PagerObject getPagerByName(String name) {
		for (PagerObject p : pagerObjects) if(p.getName().equals(name)) return p;
		return null;
	}
	
	
	
	
	
	
}




