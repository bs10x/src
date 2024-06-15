import java.util.List;
import java.util.Set;

// Aendern Sie diese Datei nicht!

public class IntMessage extends BasicMessage {
	public int payload;
	
	IntMessage(int payload, List<Set<Integer>> path) {
		super(path);
		this.payload = payload;
	}
	
	@Override
	public IntMessage withPath(List<Set<Integer>> path) {
		return new IntMessage(payload, path);
	}
}
