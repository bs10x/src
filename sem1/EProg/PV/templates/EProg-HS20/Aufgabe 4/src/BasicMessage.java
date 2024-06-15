import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

// Aendern Sie diese Datei nicht!

public class BasicMessage implements Message {
	final private List<Set<Integer>> path;
	
	BasicMessage(List<Set<Integer>> path) {
		this.path = deepCopyPath(path);
	}
	
	public static List<Set<Integer>> deepCopyPath(List<Set<Integer>> path) {
		List<Set<Integer>> result = new LinkedList<Set<Integer>>();
		for (Set<Integer> s : path) {
			result.add(new HashSet<Integer>(s));
		}
		return result;
	}
	
	@Override
	public List<Set<Integer>> getPath() {
		return deepCopyPath(path);
	}
	
	@Override
	public Message withPath(List<Set<Integer>> path) {
		return new BasicMessage(path);
	}
}

