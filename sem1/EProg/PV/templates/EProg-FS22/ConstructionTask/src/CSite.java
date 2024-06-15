import java.util.Set;

public interface CSite {
	public Set<Resource> resources();
	public boolean canAdd(Resource resource);
	public void add(Resource resource);
	public void use(Resource resource);
	public void close();
}



