import java.util.List;
import java.util.Set;

// Aendern Sie diese Datei nicht!

public class UpdateMessage extends BasicMessage {
	public Integer newId;
	public Node newNode;
	
	UpdateMessage(Integer newId, Node node, List<Set<Integer>> path) {
		super(path);
		this.newId = newId;
		this.newNode = node;
	}
	
	@Override
	public Message withPath(List<Set<Integer>> path) {
		return new UpdateMessage(newId, newNode, path);
	}
}
