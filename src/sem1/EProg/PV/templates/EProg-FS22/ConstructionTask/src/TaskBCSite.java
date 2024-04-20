import java.util.Set;

public class TaskBCSite implements CSite {

	Resource resource1, resource2 = null;
	int type;
	
	TaskBCSite(int type) {
		this.type = type;
	}
	
	@Override
	public Set<Resource> resources() {
		if (resource1 == null) {
			return Set.of();
		} else if (resource2 == null) {
			return Set.of(resource1);
		} else {
			return Set.of(resource1, resource2);
		}
	}
	
	@Override
	public boolean canAdd(Resource resource) {
		return resource.type() == type && (resource1 == null || resource2 == null);
	}

	@Override
	public void add(Resource resource) {
		// Diese Methode wirft keine Exception.
		// Sie muessen fuer Task (b) auch keine Exception hinzufuegen.
		
		if (resource1 == null) {
			resource1 = resource;
		} else if (resource2 == null) {
			resource2 = resource;
		} 
	}

	@Override
	public void use(Resource resource) {
		// Diese Methode wirft keine Exception.
		// Sie muessen fuer Task (b) auch keine Exception hinzufuegen.
		
		if (resource1 == resource) {
			resource1 = resource2;
			resource2 = null;
		} else if (resource2 == resource) {
			resource2 = null;
		}
	}

	@Override
	public void close() {
		// TODO
	}

}
