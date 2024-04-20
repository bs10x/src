import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class CSiteObject implements CSite {
	Set<Integer> types;
	
	Map<Integer, Integer> capacity;
	int limit;
	
	public CSiteObject(Set<Integer> types, int limit) {
		this.types = types;
		this.limit = limit;
		capacity = new HashMap<>();
		for (Integer type : types) {
			capacity.put(type, 0);
		}
		
	}

	@Override
	public Set<Resource> resources() {
		Set<Resource> resources = new HashSet<>();
		for (Integer type : types) {}
		return resources;
	}

	@Override
	public boolean canAdd(Resource resource) {
		if (!types.contains(resource.type())) return false;
		if (capacity.get(resource.type()) == limit) return false;
		return true;
	}

	@Override
	public void add(Resource resource) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void use(Resource resource) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void close() {
		// TODO Auto-generated method stub
		
	}
	

}
